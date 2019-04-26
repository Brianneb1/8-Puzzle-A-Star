import java.util.*;

public class main {
    public static void main(String[] args) {
        //create goal matrix and corresponding node
        int[][] goalState = {{1,2,3},{4,5,6},{7,8,0}}; //8 puzzle
        int[][] goalState2 = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,0}}; //16 puzzle
        Node goal = new Node(goalState, goalState, 0, 3);
        Node goal2 = new Node(goalState2, goalState2, 0, 4);

        System.out.println("SOLVED 8 PUZZLE. . .");
        //call astar function on already complete puzzle, print completed path
        System.out.println("A* test on solved puzzle");
        tester.astarTest(goal,goal, true);
        System.out.println("Greedy BFS test on solved puzzle");
        tester.greedyBFSTest(goal,goal, true);

        System.out.println("SOLVED 15 PUZZLE. . .");
        //call astar function on already complete puzzle, print completed path
        System.out.println("A* test on solved puzzle");
        tester.astarTest(goal2,goal2, true);
        System.out.println("Greedy BFS test on solved puzzle");
        tester.greedyBFSTest(goal2,goal2, true);

        //tester grids that are 2 numbers off/switched
        int[][] almostThere = {{1,2,3},{4,5,6},{7,0,8}}; //8 puzzle
        Node almost = new Node(almostThere, goalState, 0, 3);
        int[][] almostThere2 = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,0,15}}; //16 puzzle
        Node almost2 = new Node(almostThere2, goalState2, 0, 4);

        System.out.println("ALMOST SOLVED 8 PUZZLE. . .");
        //call astar function on almost complete puzzle, store completed path
        System.out.println("A* test on almost solved puzzle");
        tester.astarTest(almost,goal, true);
        System.out.println("Greedy BFS test on almost solved puzzle");
        tester.greedyBFSTest(almost,goal, true);

        System.out.println("ALMOST SOLVED 15 PUZZLE. . .");
        //call astar function on almost complete puzzle, store completed path
        System.out.println("A* test on almost solved puzzle");
        tester.astarTest(almost2,goal2, true);
        System.out.println("Greedy BFS test on almost solved puzzle");
        tester.greedyBFSTest(almost2,goal2, true);

        System.out.println("RANDOM 8 PUZZLE. . .");
        boolean run = true;
        //fill matrix randomly (for testing)
        int[][] puzzle = tester.generateRandom(3);
        //create node of current state (store fvalues based on heuristic)
        Node start = new Node(puzzle, goalState, 0, 3);
        boolean solvable = solutions.isSolvable(start); //check if solvable based on inversions

        //loop til you find solvable random puzzle and print those results before moving on to timing
        while(run)
        {
            if (solvable) //call astar function on random puzzle
            {
                System.out.println("A* test on random puzzle");
                tester.astarTest(start, goal, true);
                System.out.println("\nGreedy BFS test on random puzzle");
                tester.greedyBFSTest(start, goal, true);
                run = false;
            }
            else
            {
                //fill matrix randomly (for testing)
                puzzle = tester.generateRandom(3);
                //create node of current state (store fvalues based on heuristic)
                start = new Node(puzzle, goalState, 0, 3);
                solvable = solutions.isSolvable(start); //check if solvable based on inversions
            }
        }

//        System.out.println("RANDOM 15 PUZZLE. . .");
//        System.out.println("**This may take about 30 seconds each**");
//        boolean run2 = true;
//        //fill matrix randomly (for testing)
//        int[][] puzzle2 = tester.generateRandom(4);
//        //create node of current state (store fvalues based on heuristic)
//        Node start2 = new Node(puzzle2, goalState2, 0, 4);
//        boolean solvable2 = solutions.isSolvable(start2); //check if solvable based on inversions
//
//        //loop til you find solvable random puzzle and print those results before moving on to timing
//        while(run2)
//        {
//            if (solvable2) //call astar function on random puzzle
//            {
//                System.out.println("A* test on random puzzle");
//                tester.astarTest(start2, goal2, true);
//                System.out.println("\nGreedy BFS test on random puzzle");
//                tester.greedyBFSTest(start2, goal2, true);
//                run2 = false;
//            }
//            else
//            {
//                //fill matrix randomly (for testing)
//                puzzle2 = tester.generateRandom(4);
//                //create node of current state (store fvalues based on heuristic)
//                start2 = new Node(puzzle2, goalState2, 0, 4);
//                solvable2 = solutions.isSolvable(start2); //check if solvable based on inversions
//            }
//        }

        double astarTime = 0;
        double GBFSTime = 0;
        double startTime;
        double endTime;
        double totalTime;

        //test both on 100 solved puzzles
        for (int i = 0; i < 100; i++)
        {
            startTime = System.nanoTime();
            tester.astarTest(goal, goal, false);
            endTime = System.nanoTime();
            totalTime = endTime - startTime;
            astarTime += totalTime;

            startTime = System.nanoTime();
            tester.greedyBFSTest(goal, goal, false);
            endTime = System.nanoTime();
            totalTime = endTime - startTime;
            GBFSTime += totalTime;
        }
        System.out.println("\nThe average time of A* algorithm to solve pre-solved 8 Puzzle (100 iterations): " + astarTime/100);
        System.out.println("The average time of Greedy BFS algorithm to solve pre-solved 8 Puzzle (100 iterations): " + GBFSTime/100);

        astarTime = 0;
        GBFSTime = 0;

        //test both on 100 almost solved puzzles
        for (int i = 0; i < 100; i++)
        {
            startTime = System.nanoTime();
            tester.astarTest(almost, goal, false);
            endTime = System.nanoTime();
            totalTime = endTime - startTime;
            astarTime += totalTime;

            startTime = System.nanoTime();
            tester.greedyBFSTest(almost, goal, false);
            endTime = System.nanoTime();
            totalTime = endTime - startTime;
            GBFSTime += totalTime;
        }
        System.out.println("\nThe average time of A* algorithm to solve almost solved 8 Puzzle (100 iterations): " + astarTime/100);
        System.out.println("The average time of Greedy BFS algorithm to solve almost solved 8 Puzzle (100 iterations): " + GBFSTime/100);

        System.out.println("\n\nCALCULATING TIMING. . .");
        System.out.println("**IMPORTANT - Please wait at least 1 minute for this section to complete**");
        System.out.println("Scroll up to view solution nodes and timing for A* and Greedy BFS on a solved and almost solved 8 puzzle and 15 puzzle.");
        System.out.println("You will also find the solution nodes for A* and Greedy BFS on a random 8 puzzle.");
        System.out.println("The random 15 puzzle can take way too long to complete, so I didn't include it in this testing.");

        astarTime = 0;
        GBFSTime = 0;

        //test both on 100 random puzzles
        for (int i = 0; i < 100; i++)
        {
            int[][] puzzle1 = tester.generateRandom(3);
            start = new Node(puzzle1, goalState, 0, 3);
            solvable = solutions.isSolvable(start); //check if solvable based on inversions
            if(solvable) //call astar function on random puzzle
            {
                startTime = System.nanoTime();
                tester.astarTest(start, goal, false);
                endTime = System.nanoTime();
                totalTime = endTime - startTime;
                astarTime += totalTime;

                //call GBFS function on random puzzle
                startTime = System.nanoTime();
                tester.greedyBFSTest(start, goal, false);
                endTime = System.nanoTime();
                totalTime = endTime - startTime;
                GBFSTime += totalTime;
            }
            else{
                if (i > 0)
                    i -= 1;
            }
        }

        System.out.println("\nThe average time of A* algorithm to solve 8 Puzzle (100 iterations): " + astarTime/100);
        System.out.println("The average time of Greedy Best First algorithm to solve 8 Puzzle (100 iterations): " + GBFSTime/100);
    }
}
