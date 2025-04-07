package academy.atl.rover.controllers;

import academy.atl.rover.dto.ObstacleDto;
import academy.atl.rover.models.Obstacle;
import academy.atl.rover.services.ObstacleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ObstacleController {

    @Autowired
    private ObstacleService obstacleService;

    @PostMapping("/obstacle")
    @CrossOrigin(origins = "http://localhost:63342")
    public void create(@RequestBody ObstacleDto obstacle){

    }

    @GetMapping("/obstacle")
    @CrossOrigin(origins = "http://localhost:63342")
    public List<Obstacle> findAll(){
        return obstacleService.findAll();
    }
}
