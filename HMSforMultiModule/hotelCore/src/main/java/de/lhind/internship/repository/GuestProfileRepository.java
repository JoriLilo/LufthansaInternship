package de.lhind.internship.repository;

import de.lhind.internship.entity.GuestProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestProfileRepository extends JpaRepository<GuestProfile, Long> {
}
