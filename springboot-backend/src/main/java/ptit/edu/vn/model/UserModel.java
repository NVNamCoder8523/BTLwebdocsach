package ptit.edu.vn.model;

import lombok.Data;

@Data
public class UserModel {
    private Integer userId;

    private String fullName;

    private String userName;

    private String email;

    private String password;
}
