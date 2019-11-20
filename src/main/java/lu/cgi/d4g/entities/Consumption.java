package lu.cgi.d4g.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Consumption {

    @Id
    private long homeId;

    private int readingDate;

    @Column(length = 8)
    private int energy;

    public long getHomeId() {
        return homeId;
    }

    public void setHomeId(long homeId) {
        this.homeId = homeId;
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
