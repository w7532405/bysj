package imnu.edu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CountryNums {
    private Integer id;
    private String country;
    private Integer newConfirmed;
    private Integer sumConfirmed;
    private Integer newDeadths;
    private Integer sumDeadths;
    private Integer newRecovered;
    private Integer sumRecovered;
}
