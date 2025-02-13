package com.example.learn_spring.instrument;

import com.example.learn_spring.web.exception.instrument.InstrumentNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class InstrumentServiceImpl implements InstrumentService{

    @Autowired
    private InstrumentRepository instrumentRepository;

    @Override
    public Iterable<Instrument> getAllInstruments() {
        return instrumentRepository.findAll();
    }

    @Override
    public Instrument getInstrumentById(UUID id) {
        return instrumentRepository.findById(id).orElseThrow(InstrumentNotFoundException::new);
    }

    @Override
    public Instrument getInstrumentByTicker(String ticker) {
        return instrumentRepository.findByTicker(ticker);
    }

    @Override
    public Instrument saveInstrument(Instrument instrument) {
        return instrumentRepository.save(instrument);
    }

    @Override
    public Iterable<Instrument> saveInstruments(Iterable<Instrument> instruments) {
        return instrumentRepository.saveAll(instruments);
    }

    @Override
    public void deleteInstrumentById(UUID id) {
        instrumentRepository.findById(id).orElseThrow(InstrumentNotFoundException::new);
        instrumentRepository.deleteById(id);
    }
}
