package com.halconbit.structurebase_maven_springbootv33.__java21.repositories;

import com.halconbit.structurebase_maven_springbootv33.__java21.models.Ship;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipRepository extends JpaRepository<Ship, Long> {

    Page<Ship> findByName(String customQuery, Pageable page);

}
