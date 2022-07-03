package algorithms.search;

public abstract class AState {     //uses to identify a move and what cost it took in a maze
    int cost;
    AState camefrom;

    public AState(AState camefrom,int cost) {
        this.camefrom = camefrom;
        this.cost=cost;
    }
    public AState(){
        this.cost=0;
    }
    public int getCost() {
        return cost;
    }

    public AState getCameFrom() {
        return camefrom;
    }
    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setCameFrom(AState cameFrom) {
        this.camefrom = cameFrom;
    }

}
