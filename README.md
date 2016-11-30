## Simple kafka topic management maven plugin

This project is a fork of [Jean-Eudes/kafka-maven-plugin](https://github.com/Jean-Eudes/kafka-maven-plugin), however 
this fork is maintained separately as I'd like to deploy it on central, hence new packages and most probably other changes. 



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

