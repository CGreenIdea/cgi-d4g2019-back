package lu.cgi.d4g.house.information.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HomeBean {

    private String label;

    private short type;

    private int surface;

    private int nbRooms;

    private String heatSource;

    private int constructionYear;

    private String streetNb;

    private String street;

    private String city;

    private String zipCode;
}
