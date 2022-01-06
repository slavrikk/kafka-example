package message.service;

import message.data.entity.Message;
import message.data.entity.Task;
import message.data.repository.MessageRepository;
import message.data.repository.TaskRepository;
import message.util.TaskEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private MessageRepository messageRepository;

    private Map<TaskEnum, Consumer<Task>> taskMap;

    @PostConstruct
    void initMap() {
        taskMap = new ConcurrentHashMap<>();
        taskMap.put(TaskEnum.DELETE, task -> messageRepository.deleteById(task.getMessageId()));
        taskMap.put(TaskEnum.UPDATE, task -> {
            Message message = messageRepository.getById(task.getMessageId());
            message.setMessage(task.getMessageText());
            messageRepository.save(message);
        });
    }

    @Transactional
    public void executeTask(Task task) {
        taskRepository.save(task);
        taskMap.get(task.getTask()).accept(task);
    }
}
