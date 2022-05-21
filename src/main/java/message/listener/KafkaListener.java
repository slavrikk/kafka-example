package message.listener;

import lombok.extern.slf4j.Slf4j;
import message.data.entity.Message;
import message.data.repository.MessageRepository;
import message.service.TaskService;
import message.util.TaskMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class KafkaListener {

    private final MessageRepository messageRepository;
    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @org.springframework.kafka.annotation.KafkaListener(topics = "messageTopic", groupId = "group")
    public void listenMessageTopic(String message) {
        log.warn("Hello from listener! message: {}", message);
        messageRepository.save(Message.builder()
                .message(message)
                .build());
    }

    @org.springframework.kafka.annotation.KafkaListener(topics = "taskTopic", groupId = "group")
    public void listenTaskTopic(String message) {
        taskService.executeTask(taskMapper.objFrString(message));
    }

}
