import java.util.Comparator;

public class AStarComparator implements Comparator<Node> {
    int[][] goalState = {{1,2,3},{4,5,6},{7,8,0}}; //8 puzzle
    int[][] goalState2 = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,0}}; //16 puzzle

    @Override
    public int compare(Node o1, Node o2)
    {
        if (o1.getGrid().length == 3)
            return o1.fValue(goalState, 1) - o2.fValue(goalState, 1);
        else
            return o1.fValue(goalState2, 1) - o2.fValue(goalState2, 1);
    }
}