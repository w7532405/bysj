package imnu.edu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    private String username;
    private String nickname;
    private String password;
    private String email;
    private String avatar;
    private Integer type;
    private Date createTime;
    private Date updateTime;

    public User(String username, String nickname, String password, String email) {
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
    }
}
