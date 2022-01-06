package message.controller;

import message.service.KafkaSenderService;
import message.util.TaskEnum;
import message.util.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for execute tasks with Kafka
 */
@RestController
public class TaskController {

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private KafkaSenderService kafkaSenderService;

    @Value("${task.topic}")
    private String messageTopic;

    @PutMapping(path = "/task/update")
    public void taskUpdate(@RequestParam(value = "id") Long messageId,
                           @RequestParam(value = "message") String message) {
        String stringObj = taskMapper.stringObj(messageId, message, TaskEnum.UPDATE);
        kafkaSenderService.sendMessage(stringObj, messageTopic);
    }

    @DeleteMapping(path = "/task/delete")
    public void taskDelete(@RequestParam(value = "id") Long messageId) {
        String stringObj = taskMapper.stringObj(messageId, null, TaskEnum.DELETE);
        kafkaSenderService.sendMessage(stringObj, messageTopic);
    }
}