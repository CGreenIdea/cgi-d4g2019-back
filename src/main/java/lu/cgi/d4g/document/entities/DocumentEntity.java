package lu.cgi.d4g.document.entities;

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

@Entity
@Getter
@Setter
@Table(name = "document")
public class DocumentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "home_id")
    private HomeEntity home;

    private String filename;

    @Column(name = "local_name", length = 36)
    private String localName;
}
