package Elevator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class SCANSchedulingStrategy implements SchedulingStrategy {

    @Override
    public List<Integer> scheduleStops(List<Integer> stops,
                                       int newFloor,
                                       Direction direction) {

        // Avoid duplicates
        if (!stops.contains(newFloor)) {
            stops.add(newFloor);
        }

        List<Integer> up = new ArrayList<>();
        List<Integer> down = new ArrayList<>();

        // Split stops based on direction
        for (int floor : stops) {
            if (floor >= newFloor) {
                up.add(floor);
            } else {
                down.add(floor);
            }
        }

        // Sort both lists
        Collections.sort(up); // ascending
        down.sort(Collections.reverseOrder()); // descending

        List<Integer> result = new ArrayList<>();

        if (direction == Direction.UP) {
            result.addAll(up);
            result.addAll(down);
        } else {
            result.addAll(down);
            result.addAll(up);
        }

        return result;
    }
}
