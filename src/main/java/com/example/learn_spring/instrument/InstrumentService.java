package com.example.learn_spring.instrument;

import java.util.UUID;

public interface InstrumentService {

    Iterable<Instrument> getAllInstruments();

    Instrument getInstrumentById(UUID id);

    Instrument getInstrumentByTicker(String ticker);

    Instrument saveInstrument(Instrument instrument);

    Iterable<Instrument> saveInstruments(Iterable<Instrument> instruments);

    void deleteInstrumentById(UUID id);
}
