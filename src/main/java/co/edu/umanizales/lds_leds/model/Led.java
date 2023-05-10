package co.edu.umanizales.lds_leds.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;

@Data
public class Led {
    private int identification;
    private boolean state;
    private LocalTime dateOn;
    private LocalTime dateOff;


    public Led(int identification) {
        this.identification = identification;
        this.state=false;
        this.dateOff=null;
        this.dateOn=null;
    }

}
