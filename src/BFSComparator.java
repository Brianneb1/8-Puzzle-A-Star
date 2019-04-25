import java.util.Comparator;

public class BFSComparator implements Comparator<Node> {
    int[][] goalState = {{1,2,3},{4,5,6},{7,8,0}};

    @Override
    public int compare(Node o1, Node o2)
    {
        return o1.hValue(goalState) - o2.hValue(goalState);
    }
}