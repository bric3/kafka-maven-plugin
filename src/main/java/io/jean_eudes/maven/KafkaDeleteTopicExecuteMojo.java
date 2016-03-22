package io.jean_eudes.maven;

import static org.apache.maven.plugins.annotations.LifecyclePhase.POST_INTEGRATION_TEST;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import kafka.admin.AdminUtils;
import kafka.utils.ZkUtils;

@Mojo(name = "deleteTopic",
      defaultPhase = POST_INTEGRATION_TEST,
      threadSafe = true)
public class KafkaDeleteTopicExecuteMojo extends AbstractMojo {

    @Parameter(property = "kafka.topic", defaultValue = "myTopic")
    private String topic;

    @Parameter(property = "zookeeper.host", defaultValue = "localhost")
    private String zookeeperHost;

    @Parameter(property = "zookeeper.port", defaultValue = "2181")
    private int zookeeperPort;

    public void execute() throws MojoExecutionException, MojoFailureException {
        ZkUtils zkUtils = ZkUtils.apply(String.format("%s:%d", zookeeperHost, zookeeperPort), 10000, 10000, false);

        if (!AdminUtils.topicExists(zkUtils, topic)) {
            getLog().info("Topic " + topic + " doesn't exist, skipping deletion");
            return;
        }

        getLog().info("Deleting topic: " + topic);

        AdminUtils.deleteTopic(zkUtils, topic);
    }

}
