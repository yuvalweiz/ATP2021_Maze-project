package algorithms.search;

public interface ISearchingAlgorithm {
    Solution solve(ISearchable origin);   //interface for serching algorithem

    int getNumberOfNodesEvaluated();

    String getName();

}
