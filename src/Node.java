import java.util.*;

public class Node {

    public int[][] grid;
    private int fValue;
    public int hValue;
    public int gValue;
    public Node parent;

    public Node(int[][] puzzle)
    {
        this.parent = null;
        this.grid = puzzle;
    }

    //create child nodes, return as an arraylist
    public ArrayList<Node> createChildren()
    {
        //arraylist to add child nodes to
        ArrayList<Node> children = new ArrayList<Node>();
        int x, y;
        x = 0;
        y = 0;

        //find the space
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if (this.grid[i][j] == 0)
                    x = i;
                    y = j;
            }
        }
        //create first child node, swap the space with number below
        Node n1 = new Node(this.grid);
        if (y > 0) //make sure it doesn't go out of bounds
        {
            n1.grid[x][y] = this.grid[x][y - 1];
            n1.grid[x][y - 1] = 0;
            children.add(n1);
        }

        //create second child node, swap the space with number above
        Node n2 = new Node(this.grid);
        if (y < 2)
        {
            n2.grid[x][y] = this.grid[x][y + 1];
            n2.grid[x][y + 1] = 0;
            children.add(n2);
        }

        //create third child node, swap the space with number to right
        Node n3 = new Node(this.grid);
        if (x < 2)
        {
            n3.grid[x][y] = this.grid[x + 1][y];
            n3.grid[x + 1][y] = 0;
            children.add(n3);
        }

        //create fourth child node, swap the space with number to left
        Node n4 = new Node(this.grid);
        if (x > 0)
        {
            n4.grid[x][y] = this.grid[x-1][y];
            n4.grid[x-1][y] = 0;
            children.add(n4);
        }

        return children;
    }

    //calculate heuristic function
    public int fValue(int[][] goal, int gValue) //gValue = level of child (how many moves made to get here)
    {
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                //count number of misplaced tiles
                if (this.grid[i][j] != goal[i][j])
                {
                    hValue++;
                }
            }
        }

        //return heuristic function value
        fValue = gValue + hValue;
        return fValue;
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
























