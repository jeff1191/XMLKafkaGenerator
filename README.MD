# XMLKafkaGenerator

## Overview
Simple XML generator for Kafka(0.10.0), this project read a xml template with special labels for generate random messages 
in string format.

##Configuration

1. `git clone https://github.com/jeff1191/XMLKafkaGenerator` 
2. `cd XMLKafkaGenerator; mvn package`
3. `bin/kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic xmltest`
##Usage
There is a config file(config.json) generated using mvn, this file is a template that you can modify depending on what you want

config.json:

`{
   "type": "kafka",
   "broker.server": "localhost",
   "broker.port": 9092,
   "topic": "xmltest",
   "loop": 2,
   "date_format":"yyyy-MM-dd HH:mm:ss"
 }
`

**date_format** is used only for timestamp function

example.xml

`<breakfast_menu>
     <food>
         <name>##RANDOM_STRING(Belgian Waffles, Strawberry Belgian Waffles, Homestyle Breakfast, French Toast)##</name>
         <price>##RANDOM_DOUBLE(2.2, 5.5)##</price>
         <description>whatever...</description>
         <calories>##RANDOM_INT(200, 800)##</calories>
     </food>
 </breakfast_menu>`
 ##Labels
| Function |      
|------------- 
| ##RANDOM_INT(x,y)##| 
| ##RANDOM_DOUBLE(x,y)##| 
| ##RANDOM_STRING(x,y,z,..)##|
| ##RANDOM_BOOLEAN()##| 
| ##TIMESTAMP()##| 
 