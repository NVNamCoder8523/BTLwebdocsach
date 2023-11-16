package ptit.edu.vn.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Book {
	@Id
	// GeneratedValue: Tự động tăng
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

    // @Column: Đánh dấu thuộc tính này là một cột trong database
	@Column(unique = true, nullable = false)
	private String title;
    
	private LocalDate releaseDate;

	@Column(length = 255)
	private String author;

	@Column(nullable = false, length = 200, unique = true)
	private String coverImage;

	@Column(nullable = false)
	private LocalDateTime uploadAt;
	
	private String description;

	@Transient
	private Integer viewCount;

	@Transient
	private Integer likeCount;

	@Transient
	private Integer commentCount;
    


	@ManyToMany(mappedBy = "Books")
	private List<Category> Categories;
    
	@OneToMany(mappedBy = "book")
	//JoinColumn: Tạo khóa ngoại
	private Set<Chapter> Chapters;

	@OneToMany(mappedBy = "book")
	private List<Comment> Comments;

	@OneToMany(mappedBy = "book")
	private List<Rating> Ratings;
}