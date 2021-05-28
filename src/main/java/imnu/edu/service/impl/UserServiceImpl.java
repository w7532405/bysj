package imnu.edu.service.impl;

import imnu.edu.dao.UserDao;
import imnu.edu.pojo.User;
import imnu.edu.service.UserService;
import imnu.edu.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public User checkUser(String username, String password) {
        return userDao.checkUser(username, MD5Utils.code(password));
    }

    @Override
    public boolean checkUsername(String username) {
        return userDao.checkUsername(username)!=null;
    }

    @Override
    public boolean checkEmail(String email) {
        return userDao.checkEmail(email)!=null;
    }

    @Override
    public boolean registerUser(String username, String nickname, String email, String password) {
        User user=new User(username,nickname,MD5Utils.code(password),email);
        try {
            return userDao.registerUser(user);
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean updateLoginTime(String username) {
        return userDao.updateLoginTime(username);
    }
}
