package lu.cgi.d4g.house.information.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "landlord")
public class LandlordEntity {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "home_id")
    @MapsId
    private HomeEntity home;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    private String company;

    private String address;
}
