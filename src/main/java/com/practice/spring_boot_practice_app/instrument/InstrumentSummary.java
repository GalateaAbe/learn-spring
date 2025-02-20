package com.practice.spring_boot_practice_app.instrument;

public interface InstrumentSummary {
    String getTicker();
    String getInstrumentName();
    String getStateOfIncorporation();
    Double getLatestPrice();
}
