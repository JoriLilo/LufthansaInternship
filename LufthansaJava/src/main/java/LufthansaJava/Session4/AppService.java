package LufthansaJava.Session4;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AppService {

    @PersistenceContext
    private EntityManager em;

    public User createUser(String username, String password, String role) {
        UserDetails details = new UserDetails();

        details.setFirstName(null);
        details.setLastName(null);
        details.setEmail(null);
        details.setPhone(null);

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);

        details.setUser(user);
        user.setUserDetails(details);

        em.persist(user);
        return user;
    }

    public User getUserById(Long id) {
        return em.find(User.class, id);
    }

    public User updateUser(Long id, String username, String password, String role) {
        User user = em.find(User.class, id);
        if (user == null) throw new RuntimeException("User not found: " + id);

        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);

        return user;
    }

    public void deleteUser(Long id) {
        User user = em.find(User.class, id);
        if (user == null) throw new RuntimeException("User not found: " + id);
        em.remove(user);
    }


    public UserDetails createUserDetails(Long userId, String firstName, String lastName,
                                         String email, String phoneNumber) {
        User user = em.find(User.class, userId);
        if (user == null) throw new RuntimeException("User not found: " + userId);

        UserDetails details = new UserDetails();
        details.setFirstName(firstName);
        details.setLastName(lastName);
        details.setEmail(email);
        details.setPhone(phoneNumber);


        details.setUser(user);
        user.setUserDetails(details);

        em.persist(details);
        return details;
    }

    public UserDetails getUserDetailsById(Long id) {
        return em.find(UserDetails.class, id);
    }

    public UserDetails updateUserDetails(Long id, String firstName, String lastName,
                                         String email, String phoneNumber) {
        UserDetails details = em.find(UserDetails.class, id);
        if (details == null) throw new RuntimeException("UserDetails not found: " + id);

        details.setFirstName(firstName);
        details.setLastName(lastName);
        details.setEmail(email);
        details.setPhone(phoneNumber);
        return details;
    }


    public void deleteUserDetails(Long id) {
        UserDetails details = em.find(UserDetails.class, id);
        if (details == null) throw new RuntimeException("UserDetails not found: " + id);


        User user = details.getUser();
        if (user != null) {
            user.setUserDetails(null);
        }

        em.remove(details);
    }


    public Booking createBooking(Long userId, LocalDateTime bookingDate,
                                 String status, Long... flightIds) {
        User user = em.find(User.class, userId);
        if (user == null) throw new RuntimeException("User not found: " + userId);

        List<Flight> flights = new ArrayList<>();
        for (Long fId : flightIds) {
            Flight flight = em.find(Flight.class, fId);
            if (flight == null) throw new RuntimeException("Flight not found: " + fId);
            flights.add(flight);
        }

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setBookingDate(bookingDate);
        booking.setStatus(status);
        booking.setFlights(flights);

        em.persist(booking);
        return booking;
    }

    public Booking getBookingById(Long id) {
        return em.find(Booking.class, id);
    }

    public Booking updateBooking(Long id, LocalDateTime bookingDate, String status) {
        Booking booking = em.find(Booking.class, id);
        if (booking == null) throw new RuntimeException("Booking not found: " + id);

        booking.setBookingDate(bookingDate);
        booking.setStatus(status);
        return booking;
    }


    public void deleteBooking(Long id) {
        Booking booking = em.find(Booking.class, id);
        if (booking == null) throw new RuntimeException("Booking not found: " + id);
        em.remove(booking);
    }

    public Flight createFlight(String origin, String destination, String airline,
                               String flightNumber, String departureTime,
                               String arrivalTime, String status) {
        Flight flight = new Flight();
        flight.setOrigin(origin);
        flight.setDestination(destination);
        flight.setAirline(airline);
        flight.setFlightNumber(flightNumber);
        flight.setDepartureTime(departureTime);
        flight.setArrivalTime(arrivalTime);
        flight.setStatus(status);

        em.persist(flight);
        return flight;
    }

    public Flight getFlightById(Long id) {
        return em.find(Flight.class, id);
    }

    public Flight updateFlight(Long id, String origin, String destination, String airline,
                               String flightNumber, String departureTime,
                               String arrivalTime, String status) {
        Flight flight = em.find(Flight.class, id);
        if (flight == null) throw new RuntimeException("Flight not found: " + id);

        flight.setOrigin(origin);
        flight.setDestination(destination);
        flight.setAirline(airline);
        flight.setFlightNumber(flightNumber);
        flight.setDepartureTime(departureTime);
        flight.setArrivalTime(arrivalTime);
        flight.setStatus(status);
        return flight;
    }


    public void deleteFlight(Long id) {
        Flight flight = em.find(Flight.class, id);
        if (flight == null) throw new RuntimeException("Flight not found: " + id);


        for (Booking booking : flight.getBookings()) {
            booking.getFlights().remove(flight);
        }

        em.remove(flight);
    }
}