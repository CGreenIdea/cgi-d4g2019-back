package lu.cgi.d4g.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "tenant")
public class TenantEntity {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "home_id")
    @MapsId
    private HomeEntity homeEntity;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

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
