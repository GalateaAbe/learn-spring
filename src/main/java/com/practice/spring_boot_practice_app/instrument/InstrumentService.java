package com.practice.spring_boot_practice_app.instrument;

import java.util.UUID;

public interface InstrumentService {

    Iterable<InstrumentSummary> getAllInstruments();

    Instrument getInstrumentById(UUID id);

    Instrument getInstrumentByTicker(String ticker);

    Instrument saveInstrument(Instrument instrument);

    Iterable<Instrument> saveInstruments(Iterable<Instrument> instruments);

    void deleteInstrumentById(UUID id);
}
