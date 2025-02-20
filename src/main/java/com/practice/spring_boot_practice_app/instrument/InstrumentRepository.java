package com.practice.spring_boot_practice_app.instrument;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public interface InstrumentRepository extends JpaRepository<Instrument, UUID> {
    Optional<Instrument> findByTicker(String ticker);
    Iterable<InstrumentSummary> getAllInstrumentSummaryBy();
}
