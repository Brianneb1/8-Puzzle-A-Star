import java.util.ArrayList;
import java.util.Arrays;

public class solutions {

    public static boolean isSolvable(Node start)
    {
        int inversionCount = 0;
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        for (int i = 0; i < 3; i++) //put numbers in order
        {
            for (int j = 0; j < 3; j++)
            {
                numbers.add(start.grid[i][j]);
            }
        }

        for (int i = 0; i < 8; i++)
        {
            for (int j = i+1; j < 9; j++)
            {
                if (numbers.get(j) > numbers.get(j))
                {
                    inversionCount++;
                }
            }
        }

        if (inversionCount%2==0)
        {
            System.out.println("Solvable");
            return true;
        }
        else
        {
            System.out.println("Unsolvable");
            return false;
        }
    }

    public static ArrayList<Node> astar(Node start, Node goal){
        //heap fvalues based on heuristic
        ArrayList<Node> closed = new ArrayList<Node>();//closed list
        minHeap open = new minHeap(); //heap based on fvalues (open list)
        ArrayList<Node> path = new ArrayList<Node>();
        int level = 0;

        //add starting node to open list
        open.add(start, level);

        while (open.size() != 0) //while elms left in open list
        {
            level++;
            Node n = open.remove(level); //get current node

            //for debugging purposes, print list of nodes in open list (heap)
            open.printList();

            //if (n.grid.equals(goal.grid)) //if found goal state, recreate path
            if(Arrays.deepEquals(n.grid, goal.grid)) //gets to a certain point that n never changes
            {
                while (n != null) //until reach end of chain
                {
                    path.add(n); //add n
                    n = n.getParent(); //get n's parent
                }
                return path;
            }

            closed.add(n);
            ArrayList<Node> children = n.createChildren(goal, level);

            for (int i = 0; i < children.size(); i++) //for each child of n (gets same child every time?)
            {
                Node child = children.get(i); //get child
                if (closed.contains(child)) //if child in closed, skip
                    continue;
                if(open.contains(child)) //if child in open
                {
                    if (child.gValue <= n.gValue) //if child gVal < parent, skip
                        continue;
                }
                open.add(child, level); //add child to open list
                child.setParent(n);
            }
        }
        return path;
    }


    public static ArrayList<Node> greedyBestFirst(Node start, Node goal){
        minHeapBFS heap = new minHeapBFS();
        heap.add(start);
        ArrayList<Node> visited = new ArrayList<Node>();
        ArrayList<Node> path = new ArrayList<Node>();


        while (heap.size() != 0)
        {
            Node n = heap.remove();
            //if (n.grid.equals(goal.grid))
            if(Arrays.deepEquals(n.grid, goal.grid))
            {
                while (n != null) //until reach end of chain
                {
                    path.add(n); //add n
                    n = n.getParent(); //get n's parent
                }
                return path;
            }
            else
            {
                ArrayList<Node> children = n.createChildren(goal,0);
                for (Node child: children)
                {
                    if (!visited.contains(child))
                    {
                        visited.add(child);
                        heap.add(child);
                        child.setParent(n);
                    }
                }

            }
        }

        return null;

    }

}
