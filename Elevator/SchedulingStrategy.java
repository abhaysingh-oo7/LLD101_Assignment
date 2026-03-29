package Elevator;

import java.util.List;

interface SchedulingStrategy {
    List<Integer> scheduleStops(List<Integer> currentStops, int newRequestFloor, Direction direction);
}
