package lu.cgi.d4g.house.consumption.dto;

public class ConsumptionBean {

    private String dateStart;
    private String dateEnd;

    public ConsumptionBean() {
    }

    public ConsumptionBean(String dateStart, String dateEnd) {
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public String getDateStart() {
        return dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }
}
