package timely_data.data_model;

import javax.persistence.*;

/**
 * Created by Kateryna Sergieieva on 06.07.2017.
 */

@Entity
@Table(name = "mesure")
public class Measure {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long measure_id;

    @Column(name = "measure_data")
    private String measure_data;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private Session session;

    protected Measure() {}

    public Measure(String measure_data, Session session) {
        this.measure_data = measure_data;
        this.session = session;
    }

}
