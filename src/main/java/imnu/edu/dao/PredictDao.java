package imnu.edu.dao;

import org.apache.ibatis.annotations.Mapper;

import javax.websocket.server.PathParam;
import java.util.List;

@Mapper
public interface PredictDao {
    List<String> getAllCountrys();

    List<String> getProvincesByCountry(@PathParam("country") String country);
}
