package message.controller;

import message.data.entity.Message;
import message.data.repository.MessageRepository;
import message.service.KafkaSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MessageController {

    @Autowired
    private KafkaSenderService kafkaSenderService;

    @Autowired
    private MessageRepository messageRepository;

    @Value("${message.topic}")
    private String messageTopic;

    /**
     * Save message to database using Kafka
     */
    @PostMapping(path = "/message/{message}")
    public void save(@PathVariable String message) {
        kafkaSenderService.sendMessage(message, messageTopic);
    }

    @GetMapping(path = "/messages")
    public List<Message> getMessages() {
        return messageRepository.findAll();
    }

}
