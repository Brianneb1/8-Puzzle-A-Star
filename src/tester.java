import java.util.*;

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

    public static int[][] generateRandom(){
        int[][] puzzle = new int[3][3];
        ArrayList<Integer> values = new ArrayList();
        Random rand = new Random();

        //fill value arraylist (0 will represent empty)
        for (int i = 0; i < 9; i++)
        {
            values.add(i);
        }

        for (int h = 0; h < 3; h++)
        {
            for (int j = 0; j < 3; j++)
            {
                int randIndex = rand.nextInt(values.size());
                int value = values.get(randIndex);
                puzzle[h][j] = value;
                values.remove(randIndex);
            }
        }

        return puzzle;
    }
}
