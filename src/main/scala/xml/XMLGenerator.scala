package xml

import java.text.SimpleDateFormat
import java.util.{Date, Properties}

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

/**
  * Created by root on 7/28/17.
  */
class XMLGenerator(path:String, topic:String, times:String) {
  val generalPattern = """.*>##(.*)##<.*""".r

  val timestampPattern =""".*##TIMESTAMP\((.*)\)##.*""".r
  val intPattern =""".*##RANDOM_INT\((\d+.*\d+)\)##.*""".r
  val stringPattern =""".*##RANDOM_STRING\((.*)\)##.*""".r
  val doublePattern =""".*##RANDOM_DOUBLE\((\d+.*\d+)\)##.*""".r
  val booleanPattern =""".*##RANDOM_BOOLEAN\((.*)\)##.*""".r

  val source = scala.io.Source.fromFile(path)

  var template = source.getLines() mkString "\n"


  val format = "yyyy-MM-dd HH:mm:ss" //must be in config file
  val loopN = 2 //must be in config file
  val TOPIC = "testXML" //must be in config file
  val boostrapServers = "0.0.0.0:9092" //must be in config file

  def start(): Unit ={
    val properties = new Properties()
    // comma separated list of Kafka brokers
    properties.setProperty("bootstrap.servers", boostrapServers)
    properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    properties.put("key-class-type", "java.lang.String")
    properties.put("value-class-type", "java.lang.String")


    val producer = new KafkaProducer[String, String](properties)


    val ret = generalPattern.findAllIn(template).map { x =>

      val repLine = x match {
        case timestampPattern(x) => ("TIMESTAMP", format)
        case intPattern(x) => ("INT", x )
        case stringPattern(x) => ("STRING", x)
        case doublePattern(x) =>("DOUBLE",x )
        case booleanPattern(x) => ("BOOLEAN", x )
      }
      //return (line, operation, values in the op)
      ( x, repLine._1, repLine._2)
    }.toList

    var i=0
    while ( i != loopN){
      var message = template
      ret.foreach { x =>

        val randomValue = x._2 match {
          case "TIMESTAMP" =>  getTimestamp(format)
          case "INT" => getInt(x._3).toString
          case "STRING" => getString(x._3).toString
          case "DOUBLE" => getDouble(x._3).toString
          case "BOOLEAN" => getBoolean.toString
          case _ => ""
        }
        message = message.replace(x._1, "##.*##".r.replaceAllIn(x._1, randomValue))
      }

      val record = new ProducerRecord(TOPIC, "key", message)
      producer.send(record)
      i = i + 1
    }
  }

  def getInt(strInt:String):Integer ={
    val tmp = strInt.split(",")
    XMLRandomFunct.randomInteger(tmp(0).trim.toInt,tmp(1).trim.toInt)
  }

  def getString(strValues:String): String = {
    val tmp = strValues.split(",")
    XMLRandomFunct.randomString(tmp)
  }

  def getTimestamp(format:String): String ={
    new SimpleDateFormat(format).format(new Date())
  }

  def getDouble(strDoubles:String): Double={
    val tmp = strDoubles.split(",")
    XMLRandomFunct.randomDouble(tmp(0).trim.toDouble,tmp(1).trim.toDouble)
  }
  def getBoolean: Boolean ={
   XMLRandomFunct.randomBoolean()
  }

}
