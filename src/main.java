import java.util.*;

public class main {
    public static void main(String[] args) {

        //initialize variables
        int[][] puzzle = new int[3][3];
        ArrayList<Integer> values = new ArrayList();
        Random rand = new Random();

        //fill value arraylist (0 will represent empty)
        for (int i = 0; i < 9; i++)
        {
            values.add(i);
        }

        //fill matrix randomly
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                int randIndex = rand.nextInt(values.size());
                int value = values.get(randIndex);
                puzzle[i][j] = value;
                values.remove(randIndex);
                System.out.print(value + " ");
            }
            System.out.println();
        }

        System.out.println();

        //create goal matrix and corresponding node
        int[][] goalState = {{1,2,3},{4,5,6},{7,8,0}};
        Node goal = new Node(goalState);

        int[][] empty = {{0,0,0},{0,0,0},{0,0,0}};
        Node noSolution = new Node(empty);

        //create node of current state, first level of children, and heap
        //fvalues based on heuristic
        Node start = new Node(puzzle);

        //call astar function, store completed path
        ArrayList<Node> complete = new solutions().astar(start, goal);
        for (Node n: complete)
        {
            n.printGrid();
        }



    }
}
