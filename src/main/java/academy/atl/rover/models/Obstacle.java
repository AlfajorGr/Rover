package academy.atl.rover.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "obstacle")
@Entity
public class Obstacle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "posx")
    private Integer x;
    @Column(name = "posy")
    private Integer y;
}
