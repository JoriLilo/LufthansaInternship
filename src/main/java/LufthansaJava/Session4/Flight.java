package LufthansaJava.Session4;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String origin;
    private String destination;
    private String airline;
    private String flightNumber;
    private String departureTime;
    private String arrivalTime;
    private String status;


    @ManyToMany(mappedBy = "flights")
    private List<Booking> bookings;
}