package ptit.edu.vn.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String fullName;
    
    @Column(nullable = false, unique = true)
    private String userName;

    // Nếu user ko đặt avatar thì sẽ lấy avatar mặc định
    @Column(nullable = false)
    private String avatar;

    @Column(nullable = false, unique = true)
    private String email;

    // pass sẽ được hash trước khi vào đây
    @Column(nullable = false)
    private String password;

    @Enumerated
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Comment> Comments;

    @OneToMany(mappedBy = "user")
    private List<Rating> Ratings;

    @OneToMany(mappedBy = "user")
    private List<Reading> Readings;

    @OneToMany(mappedBy = "user")
    private List<LikeComment> LikeComments;
}
