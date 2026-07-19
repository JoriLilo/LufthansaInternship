package de.lhind.internship.mini.project.repository;

import de.lhind.internship.mini.project.entity.GuestProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestProfileRepository extends JpaRepository<GuestProfile, Long> {
}
