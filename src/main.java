import java.util.*;

public class main {
    public static void main(String[] args) {

        //create goal matrix and corresponding node
        int[][] goalState = {{1,2,3},{4,5,6},{7,8,0}};
        Node goal = new Node(goalState, goalState, 0);

        int[][] empty = {{0,0,0},{0,0,0},{0,0,0}};
        Node emptyNode = new Node(empty, goalState, 0);

        //call astar function on already complete puzzle, print completed path
        System.out.println("A* test on solved puzzle");
        tester.astarTest(goal,goal);
        System.out.println("Greedy BFS test on solved puzzle");
        tester.greedyBFSTest(goal,goal);

        //tester grid that's 2 numbers off/switched
        int[][] almostThere = {{1,2,3},{4,5,6},{7,0,8}};
        Node almost = new Node(almostThere, goalState, 0);

        //call astar function on almost complete puzzle, store completed path
        System.out.println("A* test on almost solved puzzle");
        tester.astarTest(almost,goal);
        System.out.println("Greedy BFS test on almost solved puzzle");
        tester.greedyBFSTest(almost,goal);

        System.out.println("Random puzzle:");
        //fill matrix randomly (for testing)
        int[][] puzzle = tester.generateRandom();

        //create node of current state (store fvalues based on heuristic)
        Node start = new Node(puzzle, goalState, 0);

        boolean solvable = solutions.isSolvable(start); //check if solvable based on inversions
        System.out.println("\nA* test on random puzzle");
        if(solvable) //call astar function on random puzzle
        {
            tester.astarTest(start,goal);
            System.out.println("\nGreedy BFS test on random puzzle");
            tester.greedyBFSTest(start,goal);
        }

        System.out.println("THE END");


        //eventually.... i hope
        double astarTime = 0;
        double GBFSTime = 0;

        //test both on 100 random puzzles
        for (int i = 0; i < 100; i++)
        {
            int[][] puzzle1 = tester.generateRandom();
            double startTime = 0;
            double endTime = 0;
            double totalTime = 0;

            start = new Node(puzzle1, goalState, 0);
            solvable = solutions.isSolvable(start); //check if solvable based on inversions
            if(solvable) //call astar function on random puzzle
            {
                startTime = System.nanoTime();
                tester.astarTest(start, goal);
                endTime = System.nanoTime();
                totalTime = endTime - startTime;
                astarTime += totalTime;

                //call GBFS function on random puzzle
                startTime = System.nanoTime();
                tester.greedyBFSTest(start, goal);
                endTime = System.nanoTime();
                totalTime = endTime - startTime;
                GBFSTime += totalTime;
            }
            else{
                if (i > 0)
                    i -= 1;
            }
        }

        System.out.println("The average time of A* algorithm to solve 8 Puzzle: " + astarTime/100);
        System.out.println("The average time of Greedy Best First algorithm to solve 8 Puzzle: " + GBFSTime/100);
    }
}
