import java.util.*;

public class Node {

    public int[][] grid;
    private int fValue;
    public int hValue;
    public int gValue;
    public Node parent;

    public Node(int[][] puzzle, int[][] goal, int level)
    {
        this.parent = null;
        this.grid = puzzle;
        this.fValue = fValue(goal, level);
        this.hValue = hValue(goal);
    }


    //create child nodes, return as an arraylist used for astar
    public ArrayList<Node> createChildren(Node goal, int level)
    {
        //arraylist to add child nodes to
        ArrayList<Node> children = new ArrayList<Node>();
        int[][] grid = new int[3][3]; //clone this.grid so it doesn't get altered after each node is created
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                grid[i][j] = this.grid[i][j];
            }
        }

        int x, y;
        x = 0;
        y = 0;

        //find the space
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if (grid[i][j] == 0)
                {
                    x = i;
                    y = j;
                }
            }
        }
        //create first child node, swap the space with number below
        Node n1 = new Node(grid, goal.grid, level);
        if (y > 0) //make sure it doesn't go out of bounds
        {
            int temp = n1.grid[x][y-1];
            n1.grid[x][y - 1] = 0;
            n1.grid[x][y] = temp;
            children.add(n1);
        }

        for (int i = 0; i < 3; i++) //reclone this.grid
        {
            for (int j = 0; j < 3; j++)
            {
                grid[i][j] = this.grid[i][j];
            }
        }

        //create second child node, swap the space with number above
        Node n2 = new Node(grid, goal.grid, level);
        if (y < 2)
        {
            n2.grid[x][y] = grid[x][y + 1];
            n2.grid[x][y + 1] = 0;
            children.add(n2);                   

        }

        for (int i = 0; i < 3; i++) //reclone this.grid
        {
            for (int j = 0; j < 3; j++)
            {
                grid[i][j] = this.grid[i][j];
            }
        }

        //create third child node, swap the space with number to right
        Node n3 = new Node(grid, goal.grid, level);
        if (x < 2)
        {
            n3.grid[x][y] = grid[x + 1][y];
            n3.grid[x + 1][y] = 0;
            children.add(n3);
        }

        for (int i = 0; i < 3; i++) //reclone this.grid
        {
            for (int j = 0; j < 3; j++)
            {
                grid[i][j] = this.grid[i][j];
            }
        }

        //create fourth child node, swap the space with number to left
        Node n4 = new Node(grid, goal.grid, level);
        if (x > 0)
        {
            n4.grid[x][y] = grid[x-1][y];
            n4.grid[x-1][y] = 0;
            children.add(n4);
        }

        return children;
    }

    //calculate heuristic function
    public int fValue(int[][] goal, int gValue)
    //gValue = level of child (how many moves made to get here)
    {
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                //count number of misplaced tiles (heuristic)
                if (this.grid[i][j] != goal[i][j])
                {
                    this.hValue++;
                }
            }
        }

        this.gValue(gValue);

        //return heuristic function value
        this.fValue = this.gValue + this.hValue;
        return this.fValue;
    }

    public void gValue(int gValue)
    {
        this.gValue = gValue;
    }

    public int hValue(int[][] goal)
    {
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                //count number of misplaced tiles (heuristic)
                if (this.grid[i][j] != goal[i][j])
                {
                    this.hValue++;
                }
            }
        }
        //return heuristic function value (how many misplaced tiles)
        return this.hValue;
    }

    public void printGrid()
    {
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                int value = this.grid[i][j];
                System.out.print(value + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }

    public void setParent(Node n)
    {
        this.parent = n;
    }

    public Node getParent()
    {
        return this.parent;
    }
}
























