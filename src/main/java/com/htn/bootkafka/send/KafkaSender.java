package com.htn.bootkafka.send;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.htn.bootkafka.entity.Message;
import com.htn.bootkafka.receive.KafkaReceiver;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

/**
 * @Description: KafkaSender
 * @Company: 深圳市东深电子股份有限公司
 * @Auther: leichengyang
 * @Date: 2019/1/12 0012
 * @Version 1.0
 */
@Component
@Slf4j
public class KafkaSender {

    private static final Logger logger = LoggerFactory.getLogger(KafkaReceiver.class);

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    private Gson gson = new GsonBuilder().create();

    //发送消息方法
    public void send() {
        Message message = new Message();
        message.setId(System.currentTimeMillis());
        message.setMsg(UUID.randomUUID().toString());
        message.setSendTime(new Date());
        logger.info("+++++++++++++++++++++  message = {}", gson.toJson(message));
        kafkaTemplate.send("leichengyang", gson.toJson(message));
    }

}
