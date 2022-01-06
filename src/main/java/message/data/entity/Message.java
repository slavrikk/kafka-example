package message.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    private String message;

    public Message() {

    }
}
