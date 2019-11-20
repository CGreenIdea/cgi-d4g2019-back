package lu.cgi.d4g.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Home {

    @Id
    @GeneratedValue
    private long id;

    private String label;

    @OneToMany(mappedBy = "home")
    private List<Users> users;

    @OneToMany(mappedBy = "home")
    private List<Tenant> tenants;

    @OneToMany(mappedBy = "home")
    private List<Landlord> landlord;

    @OneToMany(mappedBy = "home")
    private List<Consumption> consumptions;

    @OneToMany(mappedBy = "home")
    private List<Housing> housings;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }

    public List<Tenant> getTenants() {
        return tenants;
    }

    public void setTenants(List<Tenant> tenants) {
        this.tenants = tenants;
    }

    public List<Landlord> getLandlord() {
        return landlord;
    }

    public void setLandlord(List<Landlord> landlord) {
        this.landlord = landlord;
    }

    public List<Consumption> getConsumptions() {
        return consumptions;
    }

    public void setConsumptions(List<Consumption> consumptions) {
        this.consumptions = consumptions;
    }

    public List<Housing> getHousings() {
        return housings;
    }

    public void setHousings(List<Housing> housings) {
        this.housings = housings;
    }
}
