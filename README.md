# kafka-maven-plugin

_A simple kafka topic management maven plugin._

[![Build Status](https://travis-ci.org/bric3/kafka-maven-plugin.svg)](https://travis-ci.org/bric3/kafka-maven-plugin) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.bric3.maven/kafka-maven-plugin/badge.svg?style=flat)](https://maven-badges.herokuapp.com/maven-central/com.github.bric3.maven/kafka-maven-plugin) [![Apache License](http://img.shields.io/badge/license-Apache-blue.svg) ](https://github.com/bric3/kafka-maven-plugin/blob/master/LICENSE)




This project is a fork of [Jean-Eudes/kafka-maven-plugin](https://github.com/Jean-Eudes/kafka-maven-plugin), however 
this fork is maintained separately as I'd like to deploy it on central, hence new packages and most probably other changes. 


## Requirements

 * Java 8
 * Maven 3.3.3 (maybe older version work, but this has not been tested)
 * A running Apache Kafka 0.9


Sample configuration:

```xml
<plugin>
    <groupId>com.github.bric3.maven</groupId>
    <artifactId>kafka-maven-plugin</artifactId>
    <version>0.6</version>
    <executions>
        <execution>
            <id>remove-all-topics_before_build</id>
            <phase>pre-integration-test</phase>
            <goals>
                <goal>deleteAllTopics</goal>
            </goals>
        </execution>
        <execution>
            <id>create-magic-topic</id>
            <phase>pre-integration-test</phase>
            <goals>
                <goal>createTopic</goal>
            </goals>
            <configuration>
                <topic>magic_topic</topic>
                <partition>1</partition>
                <replicationFactor>1</replicationFactor>
            </configuration>
        </execution>
        <execution>
            <id>remove-all-topics_after_build</id>
            <phase>post-integration-test</phase>
            <goals>
                <goal>deleteAllTopics</goal>
            </goals>
        </execution>
    </executions>
    <configuration>
        <zookeeperHost>${zookeeper.host}</zookeeperHost>
        <zookeeperPort>${zookeeper.port}</zookeeperPort>
    </configuration>
</plugin>
```

