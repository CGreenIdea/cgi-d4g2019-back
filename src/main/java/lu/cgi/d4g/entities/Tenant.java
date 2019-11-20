package lu.cgi.d4g.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Tenant {

    @Id
    private long homeId;

    private String lastName;

    private String firstName;

    public long getHomeId() {
        return homeId;
    }

    public void setHomeId(long homeId) {
        this.homeId = homeId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
