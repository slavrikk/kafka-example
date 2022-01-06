package message.util;

import message.data.entity.Task;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskMapper {

    @Autowired
    private ObjectMapper objectMapper;

    @SneakyThrows
    public String stringObj(Long id, String message, TaskEnum taskEnum) {
       return objectMapper.writeValueAsString(Task.builder()
                .task(taskEnum)
                .messageId(id)
                .messageText(message)
                .build());
    }

    @SneakyThrows
    public Task objFrString(String task) {
        return objectMapper.readValue(task,Task.class);
    }

}
