package algorithms.mazeGenerators;

public abstract class AMazeGenerator implements IMazeGenerator   {
    @Override
    public long measureAlgorithmTimeMillis(int rows, int cols) {
        long start =  System.currentTimeMillis();
        generate(rows,cols);
        long end =  System.currentTimeMillis();

        return end-start;
    }
}
