package lu.cgi.d4g.entities;

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
@Table(name = "housing")
public class HousingEntity {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "home_id")
    @MapsId
    private HomeEntity home;

    private boolean type;

    @Column(length = 4)
    private int surface;

    @Column(name = "nb_rooms", length = 2)
    private int nbRooms;

    @Column(name="heat_source")
    private String heatSource;

    @Column(name = "construction_year", length = 4)
    private int constructionYear;

    @Column(name = "street_nb", length = 10)
    private String streetNb;

    private String street;

    private String city;

    @Column(name = "postal_code", length = 6)
    private String postalCode;
}
