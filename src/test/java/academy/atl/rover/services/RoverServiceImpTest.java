package academy.atl.rover.services;

import academy.atl.rover.models.Direction;
import academy.atl.rover.models.Rover;
import academy.atl.rover.repository.RoverRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class RoverServiceImpTest {

    @Mock
    private RoverRepository roverRepository;
    @InjectMocks
    private RoverServiceImp roverService;

    @Test
    public void callService_whenReceiveCommand_callService(){
        Rover rover = new Rover();
        rover.setX(5);
        rover.setY(5);
        rover.setDirection(Direction.NORTH);

        List<Rover> roverList = new ArrayList<>();
        roverList.add(rover);

        when(roverRepository.findAll()).thenReturn(roverList);

        roverService.sendCommand("F");
        Rover finalRover = new Rover();
        finalRover.setX(5);
        finalRover.setY(4);
        finalRover.setDirection(Direction.NORTH);

        verify(roverRepository, times(1)).save(finalRover);

        /*CommandDto commandDto = new CommandDto();
        List<String> commandList = new ArrayList<>();
        commandList.add("F");
        commandDto.setCommands(commandList);
        roverService.sendCommand(commandDto);

        verify(roverService, times(1)).sendCommand("F");*/
    }

}

