package lu.cgi.d4g.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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

    private String role;

    public UserEntity() {
    }
}
