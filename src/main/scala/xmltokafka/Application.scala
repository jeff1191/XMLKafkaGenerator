package xmltokafka

import xmltokafka.xml.XMLGenerator

/**
  * Created by root on 7/28/17.
  */
object Application extends App{

    println("-------------------------");
    println("  XMLKafka Generator  ");
    println("-------------------------");

    if(args.length == 2)
      new XMLGenerator(args(0),args(1)).start()
    else
      println("Usage: <template.xml> <application.conf>")

}
