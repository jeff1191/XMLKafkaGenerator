import xml.XMLGenerator

/**
  * Created by root on 7/28/17.
  */
object Application extends App{
  println("-------------------------");
  println("  XMLKafka Generator  ");
  println("-------------------------");

  if(args.length == 3)
    new XMLGenerator(args(0),args(1),args(2)).start()
  else
    println("Usage: <file.xml> <kafka topic>")
}
