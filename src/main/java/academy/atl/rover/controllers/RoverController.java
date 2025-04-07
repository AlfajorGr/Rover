package academy.atl.rover.controllers;

import academy.atl.rover.dto.CommandDto;
import academy.atl.rover.dto.RoverDto;
import academy.atl.rover.models.Rover;
import academy.atl.rover.services.RoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RoverController {

    @Autowired
    private RoverService roverService;

    @GetMapping("/rover")
    @CrossOrigin(origins = "http://localhost:63342")
    public Rover get(){
        return roverService.get();
    }

    @PostMapping("/rover")
    @CrossOrigin(origins = "http://localhost:63342")
    public void create(@RequestBody RoverDto rover){

    }

    @PostMapping("/rover/command")
    @CrossOrigin(origins = "http://localhost:63342")
    public void sendCommand(@RequestBody CommandDto commands){

        for (String command:commands.getCommands()){
            roverService.sendCommand(command);
            System.out.printf(command);
        }
    }
}
