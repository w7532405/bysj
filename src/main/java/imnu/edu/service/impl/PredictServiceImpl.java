package imnu.edu.service.impl;

import imnu.edu.dao.PredictDao;
import imnu.edu.service.PredictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PredictServiceImpl implements PredictService {
    @Autowired
    PredictDao predictDao;

    @Override
    public List<String> getAllCounrtys() {
        return predictDao.getAllCountrys();
    }

    @Override
    public List<String> getProvincesByCounrty(String country) {
        return predictDao.getProvincesByCountry(country);
    }
}
