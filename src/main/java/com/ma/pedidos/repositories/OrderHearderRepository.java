package com.ma.pedidos.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ma.pedidos.entities.OrderHearder;

@Repository
public interface OrderHearderRepository extends JpaRepository<OrderHearder, String> {

	@Query("SELECT o FROM OrderHearder o WHERE o.date = :date ")
	List<OrderHearder> findByDateIs(@Param("date") LocalDate date);

}
