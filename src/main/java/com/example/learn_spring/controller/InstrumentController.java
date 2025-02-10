package com.example.learn_spring.controller;

import com.example.learn_spring.persistence.model.Instrument;
import com.example.learn_spring.persistence.repo.InstrumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/instruments")
public class InstrumentController {

    @Autowired
    private InstrumentRepository instrumentRepository;

    @GetMapping
    public Iterable<Instrument> findAllInstruments(){
        return instrumentRepository.findAll();
    }

    @GetMapping("/ticker/{ticker}")
    public List<Instrument> findByTicker(@PathVariable String ticker){
        return instrumentRepository.findByTicker(ticker);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody List<Instrument> instruments){
        for (Instrument instrument: instruments) {
            instrumentRepository.save(instrument);
        }
    }


}
