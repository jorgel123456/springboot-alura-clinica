package med.voll.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hola")
public class HolaController {
    @GetMapping
    public String holaMundo(){
        return "Hola mundo Sprint Boot, desde peru-ucayali1";
    }

}
