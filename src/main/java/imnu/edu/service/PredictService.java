package imnu.edu.service;

import java.util.List;

public interface PredictService {
    List<String> getAllCounrtys();

    List<String> getProvincesByCounrty(String country);
}
