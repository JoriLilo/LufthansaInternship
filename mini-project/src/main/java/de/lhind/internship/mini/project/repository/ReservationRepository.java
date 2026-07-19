package de.lhind.internship.mini.project.repository;

import de.lhind.internship.mini.project.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByGuestId(Long guestId);

    @Query("""
    SELECT COUNT(r)
    FROM Reservation r
    WHERE r.room.id = :roomId
    AND r.status <> de.lhind.internship.mini.project.entity.ReservationStatus.CANCELLED
    AND r.checkInDate < :checkOutDate
    AND r.checkOutDate > :checkInDate
""")
    long countOverlappingReservations(Long roomId, LocalDate checkInDate, LocalDate checkOutDate);

    @Query(value = """
    SELECT r.room_id AS roomId, COUNT(*) AS reservationCount
    FROM reservation r
    GROUP BY r.room_id
    ORDER BY COUNT(*) DESC
    LIMIT 5
    """, nativeQuery = true)
    List<RoomReservationCount> findMostReservedRooms();
}
