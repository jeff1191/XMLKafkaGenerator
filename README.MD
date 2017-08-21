# XMLKafkaGenerator

## Overview
Simple XML generator to Kafka. The project reads an .xml with special tags to generate random messages
 in string format. XMLKafkaGenerator is built for 0.10.0 version, if you want to play with another version 
 you would have to change the version in pom and surely it will work
## Configuration
Unix:
1. `bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic xmltest`
2. `git clone https://github.com/jeff1191/XMLKafkaGenerator` 
3. `cd XMLKafkaGenerator; mvn package`
4. `mv target/XMLKafkaGenerator ~; cd ~/XMLKafkaGenerator`
5. `java -jar XMLKafkaGenerator-v1.0-SNAPSHOT.jar example.xml application.conf`

Windows OS:
1. `bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic xmltest`
2. `git clone https://github.com/jeff1191/XMLKafkaGenerator` 
3. `cd XMLKafkaGenerator & mvn package`
4. `move target\XMLKafkaGenerator %USERPROFILE% & cd %USERPROFILE%\XMLKafkaGenerator`
5. `java -jar XMLKafkaGenerator-v1.0-SNAPSHOT.jar example.xml application.conf`

You can check messages using kafka-console-consumer. 
## Usage
There is a config file(config.json)that you can modify depending on what you want, 
moreover it includes an example(example.xml) 

application.conf:
```
broker= "localhost"
port= 9092
topic= "xmltest"
frecuency= 5
delay=1000
date_format="yyyy-MM-dd HH=mm=ss"
```

**date_format** is used only for timestamp function

**use frequency=-1** if you want to generate a infinite streaming using random data

example.xml:

```
<breakfast_menu>
     <food>
         <name>##RANDOM_STRING(Belgian Waffles, Strawberry Belgian Waffles, Homestyle Breakfast, French Toast)##</name>
         <price>##RANDOM_DOUBLE(2.2, 5.5)##</price>
         <description>whatever...</description>
         <calories>##RANDOM_INT(200, 800)##</calories>
     </food>
 </breakfast_menu>
 ```
 ## Labels 
 
 |Function |  
 |------------- |
 |##RANDOM_INT(x,y)## | 
 |##RANDOM_DOUBLE(x,y)## | 
 |##RANDOM_STRING(x,y,z,..)## |
 |##RANDOM_BOOLEAN()## | 
 |##TIMESTAMP()## | 

