package br.com.dbc.vemser.produtor.service;

import br.com.dbc.vemser.produtor.dto.MessageDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConsumerService {
    private final ObjectMapper objectMapper;
    @KafkaListener(
            topics = "${kafka.topic}",
            clientIdPrefix = "{$spring.kafka.consumer.group-id}",
            groupId = "{$spring.kafka.consumer.group-id}",
            topicPartitions = {@TopicPartition(topic = "${kafka.topic}", partitions = {"0"})}
    )
    public void generalConsumer(@Payload String msg) throws JsonProcessingException {
        MessageDTO generalMessageReceived = objectMapper.readValue(msg, MessageDTO.class);
        log.info("{}. {}. : {}", generalMessageReceived.getSendDate().format(DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss")), generalMessageReceived.getUser(), generalMessageReceived.getMessage());
    }
    @KafkaListener(
            topics = "${kafka.topic2}",
            clientIdPrefix = "${spring.kafka.consumer.client-id}",
            groupId = "{$spring.kafka.consumer.client-id}",
            topicPartitions = {@TopicPartition(topic = "${kafka.topic2}", partitions = {"5"})}
    )
    public void personalConsumer(@Payload String msg) throws JsonProcessingException {
        MessageDTO personalMessageReceived = objectMapper.readValue(msg, MessageDTO.class);
        log.info("{}. {}. (Private): {}", personalMessageReceived.getSendDate().format(DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss")), personalMessageReceived.getUser(),  personalMessageReceived.getMessage());
    }

}
