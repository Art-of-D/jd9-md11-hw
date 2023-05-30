package objectid;

import hibernate.ConverterGsLong;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table(name = "ticket")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "created_at")
    private ZonedDateTime created_at;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "from_planet_id")
    private Planet planet;

    @ManyToOne
    @JoinColumn(name = "to_planet_id")
    private Planet planet2;

}
