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

## For kafka-maven-plugin developers 

### To install locally :

```bash
mvn clean install
```

### To deploy a private maven repo :

Assuming the `pom.xml` is patched with a `distributionManagement` element like 

```xml
    <distributionManagement>
        <repository>
            <id>our-thirdparty</id>
            <name>Our Third Party Repository</name>
            <url>https://host/nexus/content/repositories/thirdparty/</url>
        </repository>
    </distributionManagement>
```

Deploy it locally with the following command line : 

```bash
mvn versions:set -DnewVersion=0.x-myproject
git commit --all --message="Version 0.x-myproject"
mvn deploy scm:tag
```

This plugin is released on central, but if crafting your own version it would be preferable to use a suffix to the version to avoid possible collision with an the coordinate of an artifact deployed on central. That means that version `0.1-myproject` should be used instead of a _raw_ `0.1`.

### To deploy on central

Make sure the `settings.xml` have the following information

```xml
<servers>                                                                                                                                                                             
     <server>
         <id>ossrh</id>
         <username>login</username>
         <password>password</password>
     </server>
</servers>
```

```xml
<profiles>
    <profile>
        <id>ossrh</id>
        <activation>
            <activeByDefault>true</activeByDefault>
        </activation>
        <properties>
            <gpg.keyname>keyname</gpg.keyname>
            <gpg.executable>gpg2</gpg.executable>
            <gpg.passphrase>passphrase</gpg.passphrase>
        </properties>
    </profile>
</profiles>
```

And perform manual steps, like :

```bash
mvn versions:set -DnewVersion=0.6
git commit --all --message="Version 0.6"
git tag kafka-maven-plugin-0.6
mvn -Prelease deploy
```

Or use `./maven-central-deploy.sh`

Make sure env is set up properly, more info in OSSRH.md file.
