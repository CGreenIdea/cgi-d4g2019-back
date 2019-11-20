package lu.cgi.d4g.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
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

    public List<UsersEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UsersEntity> users) {
        this.users = users;
    }

    public List<TenantEntity> getTenantEntities() {
        return tenantEntities;
    }

    public void setTenantEntities(List<TenantEntity> tenantEntities) {
        this.tenantEntities = tenantEntities;
    }

    public List<LandlordEntity> getLandlordEntity() {
        return landlordEntity;
    }

    public void setLandlordEntity(List<LandlordEntity> landlordEntity) {
        this.landlordEntity = landlordEntity;
    }

    public List<ConsumptionEntity> getConsumptionEntities() {
        return consumptionEntities;
    }

    public void setConsumptionEntities(List<ConsumptionEntity> consumptionEntities) {
        this.consumptionEntities = consumptionEntities;
    }

    public List<HousingEntity> getHousingEntities() {
        return housingEntities;
    }

    public void setHousingEntities(List<HousingEntity> housingEntities) {
        this.housingEntities = housingEntities;
    }
}
