package xmltokafka

import xmltokafka.xml.XMLGenerator

/**
  * Created by root on 7/28/17.
  */
object Application{
  def main(args: Array[String]): Unit = {

    println("-------------------------");
    println("  XMLKafka Generator  ");
    println("-------------------------");

    if(args.length == 2)
      new XMLGenerator(args(0),args(1)).start()
    else
      println("Usage: <template.xmltokafka.xml> <application.conf>")
  }
}
