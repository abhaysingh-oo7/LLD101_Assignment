package Elevator;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        // Step 1: Create Scheduling Strategy
        SchedulingStrategy schedulingStrategy = new SCANSchedulingStrategy();

        // Step 2: Create Elevators
        Elevator e1 = new Elevator(1, 10, schedulingStrategy);
        Elevator e2 = new Elevator(2, 10, schedulingStrategy);

        // Direct field access (as you requested)
        e1.currentFloor = 0;
        e2.currentFloor = 5;

        List<Elevator> elevators = Arrays.asList(e1, e2);

        // Step 3: Selection Strategy
        ElevatorSelectionStrategy selectionStrategy =
                new SmartElevatorSelectionStrategy();

        // Step 4: Controller
        ElevatorController controller =
                new ElevatorController(elevators, selectionStrategy);

        // Step 5: Requests
        System.out.println("=== REQUEST 1 ===");
        controller.requestElevator(new Request(3, Direction.UP));

        System.out.println("=== REQUEST 2 ===");
        controller.requestElevator(new Request(7, Direction.DOWN));

        System.out.println("=== REQUEST 3 ===");
        controller.requestElevator(new Request(2, Direction.UP));

        // Step 6: Simulation
        System.out.println("\n=== SIMULATION START ===");

        for (int i = 0; i < 10; i++) {
            System.out.println("\n--- Step " + (i + 1) + " ---");

            for (Elevator e : elevators) {
                e.move();
            }
        }
    }
}