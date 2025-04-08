package academy.atl.rover.services;

import academy.atl.rover.models.Direction;
import academy.atl.rover.models.Rover;
import academy.atl.rover.repository.RoverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoverServiceImp implements RoverService {
    @Autowired
    private RoverRepository roverRepository;

    @Override
    public Rover get() {
        List<Rover> roverList = roverRepository.findAll();
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
            moveLeft(rover);
        } else if ("R".equalsIgnoreCase(command)) {
            moveRight(rover);
        }
        roverRepository.save(rover);
    }

    private void moveForward(Rover rover) {
        System.out.println("Moviendo hacia adelante. Dirección: " + rover.getDirection());
        switch (rover.getDirection()) {
            case NORTH:
                rover.setY(rover.getY() - 1);
                break;
            case SOUTH:
                rover.setY(rover.getY() + 1);
                break;
            case EAST:
                rover.setX(rover.getX() + 1);
                break;
            case WEST:
                rover.setX(rover.getX() - 1);
                break;
        }
        System.out.println("Nueva posición del rover: X = " + rover.getX() + ", Y = " + rover.getY());
    }

    // Función para mover hacia atrás
    private void moveBackward(Rover rover) {
        switch (rover.getDirection()) {
            case NORTH:
                rover.setY(rover.getY() + 1);  // Mover hacia abajo
                break;
            case SOUTH:
                rover.setY(rover.getY() - 1);  // Mover hacia arriba
                break;
            case EAST:
                rover.setX(rover.getX() - 1);  // Mover hacia la izquierda
                break;
            case WEST:
                rover.setX(rover.getX() + 1);  // Mover hacia la derecha
                break;
        }
    }

    private void moveLeft(Rover rover) {
        System.out.println("Moviendo hacia la izquierda desde: " + rover.getDirection());
        switch (rover.getDirection()) {
            case NORTH:
                rover.setX(rover.getX() - 1);  // Mover hacia la izquierda
                break;
            case SOUTH:
                rover.setX(rover.getX() + 1);  // Mover hacia la derecha
                break;
            case EAST:
                rover.setY(rover.getY() + 1);  // Mover hacia abajo
                break;
            case WEST:
                rover.setY(rover.getY() - 1);  // Mover hacia arriba
                break;
        }
        System.out.println("Nueva posición del rover: X = " + rover.getX() + ", Y = " + rover.getY());
    }

    private void moveRight(Rover rover) {
        System.out.println("Moviendo hacia la derecha desde: " + rover.getDirection());
        switch (rover.getDirection()) {
            case NORTH:
                rover.setX(rover.getX() + 1);  // Mover hacia la derecha
                break;
            case SOUTH:
                rover.setX(rover.getX() - 1);  // Mover hacia la izquierda
                break;
            case EAST:
                rover.setY(rover.getY() - 1);  // Mover hacia arriba
                break;
            case WEST:
                rover.setY(rover.getY() + 1);  // Mover hacia abajo
                break;
        }
        System.out.println("Nueva posición del rover: X = " + rover.getX() + ", Y = " + rover.getY());
    }


}
