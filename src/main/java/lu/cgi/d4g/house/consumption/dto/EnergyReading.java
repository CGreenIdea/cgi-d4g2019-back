package lu.cgi.d4g.house.consumption.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Builder
public class EnergyReading {
    private final LocalDate date;
    private final int energy;
}
