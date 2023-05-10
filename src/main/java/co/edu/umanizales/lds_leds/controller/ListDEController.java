package co.edu.umanizales.lds_leds.controller;

import co.edu.umanizales.lds_leds.controller.dto.ResponseDTO;
import co.edu.umanizales.lds_leds.model.Led;
import co.edu.umanizales.lds_leds.service.ListDEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/listde")
public class ListDEController {
    @Autowired
    private ListDEService listDEService;


    @GetMapping(path = "/see_leds")
    public ResponseEntity<ResponseDTO> getLeds(){
        return new ResponseEntity<>(new ResponseDTO(200,listDEService.getLeds().seeLeds(),null), HttpStatus.OK);
    }
    @GetMapping(path = "/add_led_to_star/{id}")
    public ResponseEntity<ResponseDTO> addLedToStart(@PathVariable int id){
        listDEService.getLeds().addLetToStart(new Led(id));
        return new ResponseEntity<>(new ResponseDTO(200,"Se ha agregado el led.",null), HttpStatus.OK);
    }
    @GetMapping(path = "/add_led_to_end/{id}")
    public ResponseEntity<ResponseDTO> addLedToEnd(@PathVariable int id){
        listDEService.getLeds().addLetToEnd(new Led(id));
        return new ResponseEntity<>(new ResponseDTO(200,"Se ha agregado el led.",null), HttpStatus.OK);
    }
    @GetMapping(path = "/reset_leds")
    public ResponseEntity<ResponseDTO> resetLeds(){
        listDEService.getLeds().resetLeds();
        return new ResponseEntity<>(new ResponseDTO(200,"Se ha resetado los leds.",null), HttpStatus.OK);
    }
    @GetMapping(path = "/see_size")
    public ResponseEntity<ResponseDTO> seeSize(){
        return new ResponseEntity<>(new ResponseDTO(200,listDEService.getLeds().seeSize(),null), HttpStatus.OK);
    }
    @GetMapping(path = "/turn_on_leds_from_the_middle_to_extremes")
    public ResponseEntity<ResponseDTO> turnOnLedsFromTheMiddleToExtremes(){
        try {
            listDEService.getLeds().turnOnLedsFromTheMiddleToExtremes();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(new ResponseDTO(200,"Se ha aplicado el metodo.",null), HttpStatus.OK);
    }
}
