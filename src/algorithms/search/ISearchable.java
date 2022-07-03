package algorithms.search;

import java.util.ArrayList;

public interface ISearchable {  //the interface of an object that can be searched from inside
    public ArrayList<AState> getAllPossibleStates(AState a);
    AState getStartState();
    AState getGoalState();


}
