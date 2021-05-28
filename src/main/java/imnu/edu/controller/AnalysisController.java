package imnu.edu.controller;

import imnu.edu.service.PredictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequestMapping("/analysis")
public class AnalysisController {

    @Autowired
    PredictService predictService;

    @GetMapping("/choose")
    public String analysisChoose(Model model){
        List<String> allCounrty = predictService.getAllCounrtys();
        model.addAttribute("countrys", allCounrty);
        return "analysis";
    }

    @PostMapping("/choose")
    public String analysisChoose(@RequestParam("country") String country,Model model){
        model.addAttribute("country",country);
        String url = "http://localhost:5000/api/getcountry?country=" + country;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        String body = responseEntity.getBody();
        model.addAttribute("datas", body);
        return "analysisresult";
    }
}
