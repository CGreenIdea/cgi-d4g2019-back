package lu.cgi.d4g.house.consumption.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ConsumptionBean {

    private LocalDate dateStart;
    private LocalDate dateEnd;
}
