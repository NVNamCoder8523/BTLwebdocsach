package ptit.edu.vn.entity;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Data
public class Comment {
    @Id
    private Integer id;
    
    @Lob
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime commentAt;

    private Boolean isEdited;

    @OneToMany(mappedBy = "parentComment")
    private Set<ChildComment> ChildComments;

    @OneToMany(mappedBy = "comment")
    private Set<LikeComment> LikeComments;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "chapter_id", nullable = false)
    private Chapter chapter;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;
}
