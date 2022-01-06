package message.data.entity;

import message.util.TaskEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
public class Task {

    public Task() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskEnum task;

    @Column(name = "message_id", nullable = false)
    private Long messageId;

    @Column(name = "message_text")
    private String messageText;

}
