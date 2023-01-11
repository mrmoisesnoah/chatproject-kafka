package br.com.dbc.vemser.produtor.controller;

import br.com.dbc.vemser.produtor.dto.MessageDTO;
import br.com.dbc.vemser.produtor.enums.ChatName;
import br.com.dbc.vemser.produtor.service.ProducerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/kafka")
@RequiredArgsConstructor
public class ProducerController {
    private final ProducerService producerService;
    @PostMapping("/enviar-chat-geral")
    public void sendGeneral(@RequestParam List<ChatName> chat, @RequestBody MessageDTO msg) throws JsonProcessingException {
        for (ChatName nome : chat) {
            producerService.sendMessage(msg, Collections.singletonList(nome));
        }
    }
}

