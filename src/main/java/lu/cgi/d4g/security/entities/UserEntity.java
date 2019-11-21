package lu.cgi.d4g.security.entities;

import lombok.Getter;
import lombok.Setter;
import lu.cgi.d4g.house.information.entities.HomeEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "home_id")
    private HomeEntity home;

    @Column(name = "user_id")
    private String userId;

    @Column(columnDefinition = "binary(32)")
    private String password;

    @Column(columnDefinition = "binary(24)")
    private String salt;

    private int iterations;

    private boolean validation;

    private LocalDate expiry;

    private String role;

    public UserEntity() {
    }
}
