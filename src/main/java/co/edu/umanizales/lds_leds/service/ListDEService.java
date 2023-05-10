package co.edu.umanizales.lds_leds.service;

import co.edu.umanizales.lds_leds.model.ListDE;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class ListDEService {
    private ListDE leds;

    public ListDEService(){
        leds = new ListDE();
    }
}
