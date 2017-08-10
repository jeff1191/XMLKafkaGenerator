package xmltokafka.xml

import java.io.File

import com.typesafe.config.ConfigFactory

/**
  * Created by root on 8/10/17.
  */
class Conf(configPath:String) {
  val config = ConfigFactory.parseFile(new File(configPath))
  val broker=config.getString("broker")
  val port=config.getInt("port")
  val topic=config.getString("topic")
  val loop=config.getInt("loop")
  val date_format=config.getString("date_format")
}
