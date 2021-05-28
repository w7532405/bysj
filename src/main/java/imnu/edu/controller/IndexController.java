package imnu.edu.controller;

import imnu.edu.pojo.CountryNums;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    @GetMapping({"/","/index"})
    public String index(Model model){
        String url = "http://localhost:5000/api/getmostcountrys";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        String body = responseEntity.getBody();
        model.addAttribute("datas", body);
        return "index";
    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }
}
