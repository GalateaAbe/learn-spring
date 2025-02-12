package com.example.learn_spring.web;

import com.example.learn_spring.web.exception.InstrumentIdMismatchException;
import com.example.learn_spring.web.exception.InstrumentNotFoundException;
import com.example.learn_spring.persistence.model.Instrument;
import com.example.learn_spring.persistence.repo.InstrumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api/instruments")
public class InstrumentRestController {

    @Autowired
    private InstrumentRepository instrumentRepository;

    @GetMapping
    public Iterable<Instrument> findAllInstruments(){
        return instrumentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Instrument findInstrumentById(@PathVariable Long id){
        return instrumentRepository.findById(id)
                .orElseThrow(InstrumentNotFoundException::new);
    }

    @GetMapping("/ticker/{ticker}")
    public Instrument findInstrumentByTicker(@PathVariable String ticker){
        return instrumentRepository.findByTicker(ticker);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Instrument create(@RequestBody Instrument instrument){
        return instrumentRepository.save(instrument);
    }

    @PostMapping("/bulk")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody List<Instrument> instruments){
        for (Instrument instrument: instruments) {
            instrumentRepository.save(instrument);
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Instrument putInstrumentById(@RequestBody Instrument instrument,
                                        @PathVariable Long id){
        if (!Objects.equals(instrument.getId(), id)){
            throw new InstrumentIdMismatchException(String.format("ID in path %s does not match request body %s",
                    id, instrument.getId()));
        }
        instrumentRepository.findById(id)
                .orElseThrow(InstrumentNotFoundException::new);
        return instrumentRepository.save(instrument);
    }

//   @PatchMapping("/{id}")
//   @ResponseStatus(HttpStatus.OK)
//   public Instrument patchInstrumentById(@RequestBody Instrument instrument,
//                                         @PathVariable Long id){
//        if (!Objects.equals(instrument.getId(), id)){
//            throw new InstrumentIdMismatchException();
//        }
//       instrumentRepository.findById(id)
//               .orElseThrow(InstrumentNotFoundException::new);
//        return instrumentRepository.
//   }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteInstrumentById(@PathVariable Long id){
        instrumentRepository.findById(id)
                .orElseThrow(InstrumentNotFoundException::new);
        instrumentRepository.deleteById(id);
    }

}
