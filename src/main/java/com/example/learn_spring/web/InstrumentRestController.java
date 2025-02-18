package com.example.learn_spring.web;

import com.example.learn_spring.instrument.InstrumentService;
import com.example.learn_spring.instrument.exception.InstrumentIdMismatchException;
import com.example.learn_spring.instrument.Instrument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("api/instruments")
public class InstrumentRestController {

    @Autowired
    private InstrumentService instrumentService;

    @GetMapping
    public Iterable<Instrument> getAllInstruments() {
        return instrumentService.getAllInstruments();
    }

    @GetMapping("/{id}")
    public Instrument findInstrumentById(@PathVariable UUID id) {
        return instrumentService.getInstrumentById(id);
    }

    @GetMapping("/ticker/{ticker}")
    public Instrument findInstrumentByTicker(@PathVariable String ticker) {
        return instrumentService.getInstrumentByTicker(ticker);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Instrument createInstrument(@RequestBody Instrument instrument) {
        return instrumentService.saveInstrument(instrument);
    }

    @PostMapping("/bulk")
    @ResponseStatus(HttpStatus.CREATED)
    public Iterable<Instrument> createInstruments(@RequestBody List<Instrument> instruments) {
        return instrumentService.saveInstruments(instruments);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Instrument updateInstrumentById(@RequestBody Instrument instrument,
                                        @PathVariable UUID id) {
        if (!Objects.equals(instrument.getId(), id)) {
            throw new InstrumentIdMismatchException(String.format("ID in path %s does not match request body %s",
                    id, instrument.getId()));
        }
        instrumentService.getInstrumentById(id);
        return instrumentService.saveInstrument(instrument);
    }

//   @PatchMapping("/{id}")
//   @ResponseStatus(HttpStatus.OK)
//   public Instrument patchInstrumentById(@RequestBody Instrument instrument,
//                                         @PathVariable Long id){
//        if (!Objects.equals(instrument.getId(), id)){
//            throw new InstrumentIdMismatchException();
//        }
//       instrumentService.getInstrumentById(id)
//               .orElseThrow(InstrumentNotFoundException::new);
//        return instrumentService.
//   }

    @DeleteMapping("/{id}")
    public void deleteInstrumentById(@PathVariable UUID id) {
        instrumentService.getInstrumentById(id);
        instrumentService.deleteInstrumentById(id);
    }

}
