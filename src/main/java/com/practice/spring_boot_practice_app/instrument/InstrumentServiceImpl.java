package com.practice.spring_boot_practice_app.instrument;

import com.practice.spring_boot_practice_app.instrument.exception.InstrumentNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@Slf4j
public class InstrumentServiceImpl implements InstrumentService {

    @Autowired
    private InstrumentRepository instrumentRepository;

    @Override
    public Iterable<InstrumentSummary> getAllInstruments() {
        log.debug("Finding all Instruments");
        return instrumentRepository.getAllInstrumentSummaryBy();
    }

    @Override
    public Instrument getInstrumentById(UUID id) {
        log.debug("Finding Instrument by id: {}", id);
        return instrumentRepository.findById(id).orElseThrow(InstrumentNotFoundException::new);
    }

    @Override
    public Instrument getInstrumentByTicker(String ticker) {
        log.debug("Finding Instrument by ticker: {}", ticker);
        return instrumentRepository.findByTicker(ticker).orElseThrow(InstrumentNotFoundException::new);
    }

    @Override
    public Instrument saveInstrument(Instrument instrument) {
        log.debug("Saving Instrument: {}", instrument);
        return instrumentRepository.save(instrument);
    }

    @Override
    public Iterable<Instrument> saveInstruments(Iterable<Instrument> instruments) {
        log.debug("Saving a list of {} Instruments", instruments.spliterator().getExactSizeIfKnown());
        for (Instrument instrument : instruments) {
            log.trace(instrument.toString());
        }
        return instrumentRepository.saveAll(instruments);
    }

    @Override
    public void deleteInstrumentById(UUID id) {
        log.debug("Deleting Instrument with id: {}", id);
        instrumentRepository.findById(id).orElseThrow(InstrumentNotFoundException::new);
        instrumentRepository.deleteById(id);
    }
}
