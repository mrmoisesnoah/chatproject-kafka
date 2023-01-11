package br.com.dbc.vemser.produtor.service;

import br.com.dbc.vemser.produtor.dto.MessageDTO;
import br.com.dbc.vemser.produtor.enums.ChatName;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProducerService {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    @Value(value = "${spring.kafka.consumer.client-id}")
    private String usuario;
    public void sendMessage(MessageDTO messageDTO, List<ChatName> chat) throws JsonProcessingException {
        for( ChatName name : chat) {
            messageDTO.setSendDate(LocalDateTime.now());
            messageDTO.setUser(usuario);
            String msg = objectMapper.writeValueAsString(messageDTO);
            MessageBuilder<String> stringMessageBuilder = MessageBuilder.withPayload(msg)
                    .setHeader(KafkaHeaders.TOPIC, name.getTopico())
                    .setHeader(KafkaHeaders.MESSAGE_KEY, UUID.randomUUID().toString())
                    .setHeader(KafkaHeaders.PARTITION_ID, name.getParticao());

            ListenableFuture<SendResult<String, String>> sentToTopic = kafkaTemplate.send(stringMessageBuilder.build());
            sentToTopic.addCallback(new ListenableFutureCallback<>() {
                @Override
                public void onSuccess(SendResult result) {
                    log.info(" Log sent to kafka with the text: {} ", messageDTO);
                }
                @Override
                public void onFailure(Throwable ex) {
                    log.error(" Error sending questions to kafka with the following message: {}", messageDTO, ex);
                }
            });
        }
    }
}