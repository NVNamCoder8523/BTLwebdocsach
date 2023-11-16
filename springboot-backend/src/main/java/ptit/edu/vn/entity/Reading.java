package ptit.edu.vn.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Reading {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime readAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    private String bookName;
    
    @ManyToOne
    @JoinColumn(name = "chapter_id")
    private Chapter chapter;
}
