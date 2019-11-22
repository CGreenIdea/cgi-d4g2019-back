package lu.cgi.d4g.security.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetBean {

    private String token;
    private String password;
    private String passwordConfirm;
}
