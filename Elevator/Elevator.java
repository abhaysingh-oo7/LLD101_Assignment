package Elevator;

import java.util.ArrayList;
import java.util.List;

class Elevator {
    int id;
    int currentFloor;
    Direction direction;
    ElevatorState state;
    int currentLoad;
    int maxCapacity;

    List<Integer> stops; // all the stops where the lift has to stop
    ElevatorStatus status;

    SchedulingStrategy schedulingStrategy;

    public Elevator(int id, int maxCapacity, SchedulingStrategy strategy) {
        this.id = id;
        this.maxCapacity = maxCapacity;
        this.schedulingStrategy = strategy;
        this.stops = new ArrayList<>();
        this.state = ElevatorState.IDLE;
        this.direction = Direction.UP;
        this.status = ElevatorStatus.ACTIVE;
    }

    void move() {
        if (status == ElevatorStatus.MAINTENANCE) {
            System.out.println("Elevator " + id + " is under maintenance");
            return;

        }

        if (isOverWeight()) {
            System.out.println("Cannot move. Elevator overloaded");
            return;
        }

        if (stops.isEmpty()) {
            state = ElevatorState.IDLE;
            System.out.println("Elevator " + id + " is idle");
            return;
        }

        state = ElevatorState.MOVING;

        // Get next stop

        int targetFloor = stops.get(0);

        while (currentFloor != targetFloor) {

            if (direction == Direction.UP) {
                currentFloor++;
            } else {
                currentFloor--;
            }

            System.out.println("Elevator " + id + " at floor " + currentFloor);
        }
        // Reached destination
        System.out.println("Elevator " + id + " stopped at floor " + currentFloor);

        stops.remove(0);
        state = ElevatorState.STOPPED;

        if (stops.isEmpty()) {
            state = ElevatorState.IDLE;
        } else {
            int next = stops.get(0);

            if (next > currentFloor)
                direction = Direction.UP;
            else
                direction = Direction.DOWN;
        }

    }

    public void addStop(int floor, Direction requestDirection) {

        if (status == ElevatorStatus.MAINTENANCE) {
            System.out.println("this elevator is under maintenance");
            return;
        }

        if (stops.contains(floor)) {
            return;
        }

        stops = schedulingStrategy.scheduleStops(stops, floor, requestDirection);

        if (state == ElevatorState.IDLE) {
            if (floor > currentFloor) {
                direction = Direction.UP;
            } else if (floor < currentFloor) {
                direction = Direction.DOWN;
            }
        }
    }

    boolean isOverWeight() {
        if (currentLoad > maxCapacity) {
            playAlarm();
            return true;
        }
        return false;
    }

    void playAlarm() {
        System.out.println("lift is overweight");
    }
    int getId(){
        return id;
    }
    ElevatorStatus getStatus(){
        return status;
    }
    int getCurrentFloor(){
        return currentFloor;
    }
    ElevatorState getState(){
        return state;
    }
    Direction getDirection(){
        return direction;
    }
    int getMaxCapacity(){
        return maxCapacity;
    }
    int getCurrentLoad(){
        return currentLoad;
    }
}