package lu.cgi.d4g.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
    @GeneratedValue
    private long id;

    private String label;

    @OneToMany(mappedBy = "home")
    private List<UsersEntity> users;

    @OneToMany(mappedBy = "home")
    private List<TenantEntity> tenantEntities;

    @OneToMany(mappedBy = "home")
    private List<LandlordEntity> landlordEntity;

    @OneToMany(mappedBy = "home")
    private List<ConsumptionEntity> consumptionEntities;

    @OneToMany(mappedBy = "home")
    private List<HousingEntity> housingEntities;
}
