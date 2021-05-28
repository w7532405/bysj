package imnu.edu.dao;

import imnu.edu.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import javax.websocket.server.PathParam;

@Mapper
public interface UserDao {
    User checkUser(@PathParam("username") String username, @PathParam("password") String password);

    User checkUsername(@PathParam("username") String username);

    User checkEmail(@PathParam("email") String email);

    boolean registerUser(User user);

    boolean updateLoginTime(@PathParam("username") String username);
}
