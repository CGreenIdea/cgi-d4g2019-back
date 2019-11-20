package lu.cgi.d4g.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "housing")
public class HousingEntity {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "home_id")
    @MapsId
    private HomeEntity homeEntity;

    private String type;

    @Column(length = 4)
    private int surface;

    @Column(name = "nb_room", length = 2)
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HomeEntity getHomeEntity() {
        return homeEntity;
    }

    public void setHomeEntity(HomeEntity homeEntity) {
        this.homeEntity = homeEntity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSurface() {
        return surface;
    }

    public void setSurface(int surface) {
        this.surface = surface;
    }

    public int getNbRooms() {
        return nbRooms;
    }

    public void setNbRooms(int nbRooms) {
        this.nbRooms = nbRooms;
    }

    public String getHeatSource() {
        return heatSource;
    }

    public void setHeatSource(String heatSource) {
        this.heatSource = heatSource;
    }

    public int getConstructionYear() {
        return constructionYear;
    }

    public void setConstructionYear(int constructionYear) {
        this.constructionYear = constructionYear;
    }

    public String getStreetNb() {
        return streetNb;
    }

    public void setStreetNb(String streetNb) {
        this.streetNb = streetNb;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
