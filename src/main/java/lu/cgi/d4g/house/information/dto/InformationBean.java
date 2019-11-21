package lu.cgi.d4g.house.information.dto;

import lombok.Getter;
import lombok.Setter;
import lu.cgi.d4g.security.dto.UserBean;

@Getter
@Setter
public class InformationBean {

    private HomeBean home;

    private LandlordBean landlord;

    private TenantBean tenant;

    private UserBean user;

}
