package lu.cgi.d4g.house.consumption.entities;

import lombok.Getter;
import lombok.Setter;
import lu.cgi.d4g.house.information.entities.HomeEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "consumption")
public class ConsumptionEntity {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "home_id")
    private HomeEntity home;

    @Column(name = "reading_date")
    private LocalDate readingDate;

    @Column(length = 8)
    private int energy;
}
