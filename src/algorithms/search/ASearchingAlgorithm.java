package algorithms.search;

import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {
    protected Solution solution;
    protected HashMap<String,AState> states;
    protected AbstractQueue<AState> visited;
    abstract public Solution solve(ISearchable origin);
    @Override
    public String getName() {
        return getClass().getName();
    }

    @Override
    public int getNumberOfNodesEvaluated() {   //returns the number of cells we passed
        if (solution==null)
        {
            return 0;
        }
        return solution.getSolutionPath().size();
    }

    public Solution backTrackingToStartState(AState goalState) {    //back track the solution path from the last Astate

        ArrayList<AState> solutionPath = new ArrayList<>();
        solutionPath.add(goalState);
        AState current = goalState;
        while (current.camefrom!=null)
        {
            solutionPath.add(current.camefrom);
            current=current.camefrom;
        }
        ArrayList<AState> finalsolutionPath = new ArrayList<>();

        int i;
        for (i=solutionPath.size()-1;i>=0;i--)
        {
            finalsolutionPath.add(solutionPath.get(i));
        }
        return new Solution(finalsolutionPath);
    }




    }
