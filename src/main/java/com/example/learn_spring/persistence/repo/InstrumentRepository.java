package com.example.learn_spring.persistence.repo;

import com.example.learn_spring.persistence.model.Instrument;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InstrumentRepository extends CrudRepository<Instrument,Long> {
    List<Instrument> findByTicker(String ticker);

}
