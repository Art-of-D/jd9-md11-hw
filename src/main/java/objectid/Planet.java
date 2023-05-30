package objectid;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "planet")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Planet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "name", length = 500)
    private String name;

    @OneToMany(mappedBy = "planet")
    private List<Ticket> planets;

    @OneToMany(mappedBy = "planet2")
    private List<Ticket> planets2;
}
