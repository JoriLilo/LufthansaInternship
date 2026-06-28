package LufthansaJava.Session4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;
import java.time.LocalDateTime;

@SpringBootApplication
public class Application {

	@Autowired
	private AppService appService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}

	@PostConstruct
	public void init() {
		// Example usage — wire real data here for your session demos

		// Create a user (also creates linked UserDetails via cascade)
		User user = appService.createUser("jori123", "secret", "ADMIN");

		// Fill in user details
		appService.updateUserDetails(user.getUserDetails().getId(),
				"Jori", "Lilo", "jori@example.com", "+355601234567");

		// Create flights
		Flight f1 = appService.createFlight("TIA", "FRA", "Lufthansa",
				"LH1234", "2025-07-01T10:00", "2025-07-01T13:00", "SCHEDULED");
		Flight f2 = appService.createFlight("FRA", "MUC", "Lufthansa",
				"LH5678", "2025-07-01T15:00", "2025-07-01T16:00", "SCHEDULED");

		// Create a booking linking user + flights
		Booking booking = appService.createBooking(
				user.getUserId(),
				LocalDateTime.now(),
				"CONFIRMED",
				f1.getId(), f2.getId()
		);

		System.out.println("Booking created: " + booking.getId());
	}
}