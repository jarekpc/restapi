package pl.nullpointerexception.restapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * @author jazy
 */
@Getter
@Setter
@Entity
public class Comment {

    @Id
    private long id;
    private long postId;
    private String content;
    private LocalDateTime created;

}