package xmltokafka

import xmltokafka.xml.XMLGenerator

/**
  * Created by root on 7/28/17.
  */
object Application extends App{

    println("-------------------------");
    println("  XMLKafka Generator  ");
    println("-------------------------");

    if(args.length == 2 && checkExtFiles("xml",args(0)) && checkExtFiles("conf",args(1)))
      new XMLGenerator(args(0),args(1)).start()
    else
      println("Usage: java -jar XMLKafkaGenerator-v1.0-SNAPSHOT.jar <template.xml> <application.conf>")


  def checkExtFiles(ext:String, filename:String): Boolean ={
    val pat = s"""(.*)[.](${ext})""".r

    filename match {
      case pat(fn,ex) => true
      case _ => false
    }
  }
}
