package ptit.edu.vn.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Data
public class ChildComment {
    @Id
    private Integer id;
    
    @Lob
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime commentAt;

    private Boolean isEdited;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

    @ManyToOne
    @JoinColumn(name = "parent_comment_id", nullable = false)
    private Comment parentComment;

    @ManyToOne
    @JoinColumn(name = "child_comment_id")
    private ChildComment childComment;

}
