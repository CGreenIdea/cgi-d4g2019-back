package lu.cgi.d4g.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Housing {

    @Id
    private long homeId;

    private String type;

    @Column(length = 4)
    private int surface;

    @Column(length = 2)
    private int nbRooms;

    private String heatSource;

    @Column(length = 4)
    private int constructionYear;

    @Column(length = 10)
    private String streetNb;

    private String street;

    private String city;

    @Column(length = 6)
    private String postalCode;

    public long getHomeId() {
        return homeId;
    }

    public void setHomeId(long homeId) {
        this.homeId = homeId;
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
