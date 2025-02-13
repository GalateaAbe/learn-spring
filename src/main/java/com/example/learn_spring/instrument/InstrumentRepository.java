package com.example.learn_spring.instrument;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@Transactional
public interface InstrumentRepository extends JpaRepository<Instrument, UUID> {
    Instrument findByTicker(String ticker);
}
