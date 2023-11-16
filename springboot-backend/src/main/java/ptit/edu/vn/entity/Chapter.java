package ptit.edu.vn.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String fileName;

    private String title;
    
    private LocalDateTime uploadAt;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @OneToMany(mappedBy = "chapter")
    private List<Reading> Readings;

    @OneToMany(mappedBy = "chapter")
    private List<Comment> Comments;
}
