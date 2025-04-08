package academy.atl.rover.services;

import academy.atl.rover.models.Direction;
import academy.atl.rover.models.Rover;
import academy.atl.rover.repository.RoverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoverServiceImp implements RoverService{
    @Autowired
    private RoverRepository roverRepository;

    @Override
    public Rover get() {
        List <Rover> roverList = roverRepository.findAll();
        return roverList.get(0);
    }

    @Override
    public void sendCommand(String command) {
        Rover rover = get();
        if ("F".equalsIgnoreCase(command)) {
            moveForward(rover);
        } else if ("B".equalsIgnoreCase(command)) {
            moveBackward(rover);
        } else if ("L".equalsIgnoreCase(command)) {
            rotateLeft(rover);
        } else if ("R".equalsIgnoreCase(command)) {
            rotateRight(rover);
        }
        roverRepository.save(rover);
    }

    private void moveForward(Rover rover) {
        switch (rover.getDirection()) {
            case NORTH:
                rover.setY(rover.getY() - 1);  // Mover arriba
                break;
            case SOUTH:
                rover.setY(rover.getY() + 1);  // Mover abajo
                break;
            case EAST:
                rover.setX(rover.getX() + 1);  // Mover derecha
                break;
            case WEST:
                rover.setX(rover.getX() - 1);  // Mover izquierda
                break;
        }
    }

    private void moveBackward(Rover rover) {
        switch (rover.getDirection()) {
            case NORTH:
                rover.setY(rover.getY() + 1);  // Mover abajo
                break;
            case SOUTH:
                rover.setY(rover.getY() - 1);  // Mover arriba
                break;
            case EAST:
                rover.setX(rover.getX() - 1);  // Mover izquierda
                break;
            case WEST:
                rover.setX(rover.getX() + 1);  // Mover derecha
                break;
        }
    }

    private void rotateLeft(Rover rover) {
        Direction newDirection = rover.getDirection().turnLeft();
        rover.setDirection(newDirection);
    }

    private void rotateRight(Rover rover) {
        Direction newDirection = rover.getDirection().turnRight();
        rover.setDirection(newDirection);
    }
}
