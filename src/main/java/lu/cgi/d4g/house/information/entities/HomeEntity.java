package lu.cgi.d4g.house.information.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "home")
public class HomeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String label;

    private boolean type;

    @Column(length = 4)
    private int surface;

    @Column(name = "nb_rooms", length = 2)
    private int nbRooms;

    @Column(name = "heat_source")
    private String heatSource;

    @Column(name = "construction_year", length = 4)
    private int constructionYear;

    @Column(name = "street_nb", length = 10)
    private String streetNb;

    private String street;

    private String city;

    @Column(name = "zip_code", length = 6)
    private String zipCode;
}
