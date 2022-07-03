package algorithms.search;

import java.util.HashMap;
import java.util.concurrent.LinkedTransferQueue;

public class BreadthFirstSearch extends ASearchingAlgorithm {
    public BreadthFirstSearch(){
        visited = new LinkedTransferQueue<>();
        states = new HashMap<>();
    }
    public AState BFS(ISearchable origin){
        visited.add(origin.getStartState());
        states.put(origin.getStartState().toString(),origin.getStartState());
        while (!visited.isEmpty()) {

            AState currState = visited.poll();

            if (currState.equals(origin.getGoalState()))
                return currState;
            for (AState nearBy : origin.getAllPossibleStates(currState)) {
                if (!states.containsKey(nearBy.toString())){
                    states.put(nearBy.toString(),nearBy);
                    visited.add(nearBy);
                }
            }
        }

        return null;
    }

    @Override
    public Solution solve(ISearchable origin) {
        if (origin == null) return null;
        AState goalState = BFS(origin);
        this.solution = backTrackingToStartState(goalState);
        return goalState != null ? backTrackingToStartState(goalState) : null ;
    }
}
