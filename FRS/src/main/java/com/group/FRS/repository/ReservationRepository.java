package com.group.FRS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group.FRS.model.Reservation;


@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
//
}
