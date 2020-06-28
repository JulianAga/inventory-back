package com.example.inventoryback.repositories;

import com.example.inventoryback.models.Auditory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditoryRepository extends JpaRepository<Auditory, Long> {
}
