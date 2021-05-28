package imnu.edu.service;

import imnu.edu.pojo.User;

public interface UserService {
    User checkUser(String username,String password);

    boolean checkUsername(String username);
    boolean checkEmail(String email);

    boolean registerUser(String username, String nickname, String email, String password);

    boolean updateLoginTime(String username);
}
