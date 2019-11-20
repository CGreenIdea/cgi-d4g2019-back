package lu.cgi.d4g.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "home")
public class HomeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String label;

    @OneToMany(mappedBy = "home")
    private List<UserEntity> users;

    @OneToMany(mappedBy = "home")
    private List<TenantEntity> tenants;

    @OneToMany(mappedBy = "home")
    private List<LandlordEntity> landlords;

    @OneToMany(mappedBy = "home")
    private List<ConsumptionEntity> consumptions;

    @OneToMany(mappedBy = "home")
    private List<HousingEntity> housings;
}
