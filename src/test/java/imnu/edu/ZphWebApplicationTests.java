package imnu.edu;

import com.sun.deploy.net.HttpUtils;
import imnu.edu.dao.UserDao;
import imnu.edu.pojo.User;
import imnu.edu.utils.MD5Utils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import sun.net.www.http.HttpClient;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

@SpringBootTest
class ZphWebApplicationTests {
    @Autowired
    UserDao dao;

    @Test
    void contextLoads() {
        String url = "http://localhost:5000/api/getmostcountrys";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        String body = responseEntity.getBody();
        System.out.println(body);
    }


}
