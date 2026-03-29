package Elevator;

import java.util.List;

class ElevatorController {

    List<Elevator> elevators;
    ElevatorSelectionStrategy selectionStrategy;

    public ElevatorController(List<Elevator> elevators,
            ElevatorSelectionStrategy selectionStrategy) {
        this.elevators = elevators;
        this.selectionStrategy = selectionStrategy;
    }

    public void requestElevator(Request request) {
        Elevator elevator = selectionStrategy.selectElevator(elevators, request);

        if (elevator == null) {
            System.out.println("No elevator available");
            return;
        }
        System.out.println("Elevator " + elevator.getId() + " assigned to floor " + request.floor);

        elevator.addStop(request.floor, request.direction);
    }
}
