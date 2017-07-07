package timely_data.data_model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by Kateryna Sergieieva on 06.07.2017.
 */
@Entity
@Table(name = "session")
public class Session {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long session_id;

    @Column(name = "upload_date")
    private Date upload_date;

    @Column(name = "session_data")
    private String session_data;

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL)
    private Set <Measure> measures;

    protected Session() {}

    public Session(Date upload_date, String session_data) {
        this.upload_date = upload_date;
        this.session_data = session_data;
    }

    public void setMeasures(Set<Measure> measures) {
        this.measures = measures;
    }

}
