package objectid;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;


@Entity
@Table(name = "client")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", length = 200)
    private String name;

    @OneToMany(mappedBy = "client")
    private List<Ticket> tickets;

}
