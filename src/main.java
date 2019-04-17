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

        System.out.println();

        //create goal matrix and corresponding node
        int[][] goalState = {{1,2,3},{4,5,6},{7,8,0}};
        Node goal = new Node(goalState);

        int[][] empty = {{0,0,0},{0,0,0},{0,0,0}};
        Node emptyNode = new Node(empty);

        //call astar function on already complete puzzle, print completed path
        tester.astarTest(goal,goal);

        //tester grid that's 2 numbers off
        int[][] almostThere = {{2,1,3},{4,5,6},{7,8,0}};
        Node almost = new Node(almostThere);

        //call astar function on almost complete puzzle, store completed path
        tester.astarTest(almost,goal);


        //fill matrix randomly (for testing)
//        for (int i = 0; i < 3; i++)
//        {
//            for (int j = 0; j < 3; j++)
//            {
//                int randIndex = rand.nextInt(values.size());
//                int value = values.get(randIndex);
//                puzzle[i][j] = value;
//                values.remove(randIndex);
//                System.out.print(value + " ");
//            }
//            System.out.println();
//        }

        //create node of current state, first level of children, and heap fvalues based on heuristic
//        Node start = new Node(puzzle);
//
//        //call astar function on random puzzle, store completed path
//        ArrayList<Node> complete = new solutions().astar(start, goal);
//        for (int i = 0; i < complete.size(); i++)
//        {
//            System.out.println("Node "+i+":");
//            complete.get(i).printGrid();
//        }





    }
}
