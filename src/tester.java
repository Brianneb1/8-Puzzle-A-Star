import java.util.ArrayList;

public class tester {

    public static void astarTest(Node start, Node goal){

        //call astar function on already complete puzzle, store completed path
        ArrayList<Node> tester = new solutions().astar(start, goal);
        for (int i = tester.size()-1; i >= 0; i--)
        {
            System.out.println("Node "+i+":");
            tester.get(i).printGrid();
        }

    }

    public static void greedyBFSTest(Node start, Node goal){
        //call astar function on already complete puzzle, store completed path
        ArrayList<Node> tester = new solutions().greedyBestFirst(start, goal);
        for (int i = tester.size()-1; i >= 0; i--)
        {
            System.out.println("Node "+i+":");
            tester.get(i).printGrid();
        }
    }
}
