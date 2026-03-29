package Elevator;

import java.util.List;

class SmartElevatorSelectionStrategy implements ElevatorSelectionStrategy {

    @Override
    public Elevator selectElevator(List<Elevator> elevators, Request request) {

        Elevator bestElevator = null;
        int bestScore = Integer.MAX_VALUE;

        for (Elevator e : elevators) {

            if (e.getStatus() == ElevatorStatus.MAINTENANCE) continue;

            int score = calculateScore(e, request);

            if (score < bestScore) {
                bestScore = score;
                bestElevator = e;
            }
        }

        return bestElevator;
    }

    private int calculateScore(Elevator e, Request request) {

        int score = 0;

        // 1. Distance
        int distance = Math.abs(e.getCurrentFloor() - request.floor);
        score += distance * 2;

        // 2. Idle bonus
        if (e.getState() == ElevatorState.IDLE) {
            score -= 5;
        }

        // 3. Direction match bonus
        if (e.getDirection() == request.direction) {

            if ((request.direction == Direction.UP &&
                 e.getCurrentFloor() <= request.floor) ||

                (request.direction == Direction.DOWN &&
                 e.getCurrentFloor() >= request.floor)) {

                score -= 10; // strong preference
            } else {
                score += 5; // wrong path
            }
        } else {
            score += 8; // opposite direction penalty
        }

        // 4. Load penalty
        double loadFactor = (double) e.getCurrentLoad() / e.getMaxCapacity();
        score += (int)(loadFactor * 10);

        return score;
    }
}
