package academy.atl.rover.controllers;

import academy.atl.rover.dto.CommandDto;
import academy.atl.rover.services.RoverService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class RoverControllerTest {

    @Mock
    private RoverService roverService;
    @InjectMocks
    private RoverController roverController;

    @Test
    public void callService_whenReceiveCommand_callService(){

        CommandDto commandDto = new CommandDto();
        List<String> commandList = new ArrayList<>();
        commandList.add("F");
        commandDto.setCommands(commandList);
        roverController.sendCommand(commandDto);

        verify(roverService, times(1)).sendCommand("F");
    }

    @Test
    public void whenSendCommand_FRF_callService(){

        CommandDto commandDto = new CommandDto();
        List<String> commandList = new ArrayList<>();
        commandList.add("F");
        commandList.add("R");
        commandList.add("F");
        commandDto.setCommands(commandList);
        roverController.sendCommand(commandDto);

        verify(roverService, times(3)).sendCommand(any());
    }
}
