package algorithms.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public  class DepthFirstSearch extends ASearchingAlgorithm {
    @Override
    public Solution solve(ISearchable origin) {
        AState goalState = DFS(origin);
        if (goalState==null)
        {
            return null;
        }
        Solution s=backTrackingToStartState(goalState);
        this.solution=s;
        return s;
    }
    public AState DFS (ISearchable origin)
    {
        HashMap<String,AState> statesmap = new HashMap<>();
        Stack<AState> stack = new Stack<AState>();
        AState start = origin.getStartState();
        AState end = origin.getGoalState();
        stack.push(start);
        statesmap.put(start.toString(),start);
        int i;
        while (stack.isEmpty()==false)
        {
            AState current = stack.pop();
            if (current.equals(end))
            {
                return current;
            }
            if (statesmap.containsKey(current.toString())==false)
            {
                statesmap.put(current.toString(),current);
            }
            ArrayList<AState> statelist=origin.getAllPossibleStates(current);
            for (i=0;i<statelist.size();i++)
            {
                if (statesmap.containsKey(statelist.get(i).toString())==false)
                {
                    stack.push(statelist.get(i));
                }
            }
        }
        return null;


    }

}
