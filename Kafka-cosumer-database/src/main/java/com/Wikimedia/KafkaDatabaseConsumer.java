package com.Wikimedia;

import com.Wikimedia.entity.WikimediaData;
import com.Wikimedia.repository.WikimediaRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaDatabaseConsumer {
    @Autowired
    private WikimediaRepo wikimediaRepo;
    private static final Logger logger = LoggerFactory.getLogger(KafkaDatabaseConsumer.class);
    @KafkaListener(topics = "wikimedia_changes",groupId = "myGroup")
    public void consume(String eventMessage){
        logger.info(String.format("Message Received %s ->",eventMessage));
        WikimediaData wikimediaData=new WikimediaData();
        wikimediaData.setWikimediaEventData(eventMessage);
        wikimediaRepo.save(wikimediaData);

    }
}
