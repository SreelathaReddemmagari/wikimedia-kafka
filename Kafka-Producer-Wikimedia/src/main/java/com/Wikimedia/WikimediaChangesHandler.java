package com.Wikimedia;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.MessageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
@Component
public class WikimediaChangesHandler implements EventHandler {
    private static final Logger logger = LoggerFactory.getLogger(WikimediaChangesHandler.class);

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String topic;

    // Inject the topic value from application.properties
    public WikimediaChangesHandler(
            @Value("wikimedia_changes") String topic,
            KafkaTemplate<String, String> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void onMessage(String s, MessageEvent messageEvent) throws Exception {
        logger.info(String.format("event data -> %s", messageEvent.getData()));
        kafkaTemplate.send(topic, messageEvent.getData());
    }


    @Override
    public void onOpen() throws Exception {

    }

    @Override
    public void onClosed() throws Exception {

    }


    @Override
    public void onComment(String s) throws Exception {

    }

    @Override
    public void onError(Throwable throwable) {

    }
}
