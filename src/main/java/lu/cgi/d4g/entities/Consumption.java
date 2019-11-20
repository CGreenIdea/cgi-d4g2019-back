package lu.cgi.d4g.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class Consumption {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id")
    @MapsId
    private Home home;

    private int readingDate;

    @Column(length = 8)
    private int energy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
    }

    public int getReadingDate() {
        return readingDate;
    }

    public void setReadingDate(int readingDate) {
        this.readingDate = readingDate;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

}
