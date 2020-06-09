package pl.nullpointerexception.restapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author jazy
 */
@Entity
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String content;
    private LocalDateTime created;

    @OneToMany(cascade = CascadeType.REMOVE)//usuwa kaskadowa wszystko comment
    @JoinColumn(name = "postId", updatable = false, insertable = false)//przy save nie aktulizuje postId
    private List<Comment> comment;
}
