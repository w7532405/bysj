package imnu.edu.controller;

import imnu.edu.service.PredictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PredictController {

    @Autowired
    PredictService predictService;

    @GetMapping("/predict")
    public String predict(Model model) {
        List<String> allCounrty = predictService.getAllCounrtys();
        model.addAttribute("countrys", allCounrty);
        return "predict";
    }

    @PostMapping("/predict")
    public String predict(@RequestParam(defaultValue = "none") String country,
                          @RequestParam(defaultValue = "none") String province,
                          @RequestParam(defaultValue = "none") String type,
                          RedirectAttributes attributes) {
        if ("0".equals(country) && "0".equals(province) && "0".equals(type)) {
            return "redirect:/predict";
        } else {
            attributes.addAttribute("country", country);
            attributes.addAttribute("province", province);
            attributes.addAttribute("type", type);
            return "redirect:/predict/result";
        }
    }

    @GetMapping("/predict/result")
    public String predictResult(@RequestParam String country,
                                @RequestParam String province,
                                @RequestParam String type,
                                Model model) {
        model.addAttribute("country", country);
        model.addAttribute("province", "none".equals(province) ? "无" : province);
        model.addAttribute("type", type);
        if("all".equals(type)){
            String url = "http://127.0.0.1:5000/api/getconfired?type=confirmed&country=" + country + "&province=" + province;
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
            String body = responseEntity.getBody();
            model.addAttribute("datas1", body);

            String url2 = "http://127.0.0.1:5000/api/getconfired?type=deaths&country=" + country + "&province=" + province;
            System.out.println(url2);
            RestTemplate restTemplate2 = new RestTemplate();
            ResponseEntity<String> responseEntity2 = restTemplate2.getForEntity(url2, String.class);
            String body2 = responseEntity2.getBody();
            model.addAttribute("datas2", body2);

            String url3 = "http://127.0.0.1:5000/api/getconfired?type=recovered&country=" + country + "&province=" + province;
            RestTemplate restTemplate3 = new RestTemplate();
            ResponseEntity<String> responseEntity3 = restTemplate3.getForEntity(url3, String.class);
            String body3 = responseEntity3.getBody();
            model.addAttribute("datas3", body3);
            return "predictresults";
        }else{
            String url = "http://127.0.0.1:5000/api/getconfired?type="+type+"&country=" + country + "&province=" + province;
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
            String body = responseEntity.getBody();
            model.addAttribute("datas", body);
            return "predictresult";
        }
    }

    @RequestMapping("/api/getprovincesbycountry")
    @ResponseBody
    public List<String> getProvincesByCounrty(String country) {
        return predictService.getProvincesByCounrty(country);
    }

}
