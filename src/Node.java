import java.util.*;

public class Node {
    public int[][] grid;
    public int fValue;
    public int hValue;
    public int gValue;
    public Node parent;

    public Node(int[][] puzzle, int[][] goal, int level)
    {
        this.parent = null;
        this.grid = new int[3][3];
        for (int i=0; i<3; i++)
            for (int j=0; j<3; j++)
                grid[i][j] = puzzle[i][j];
        this.hValue = hValue(goal);
        setgValue(level);
        this.fValue = fValue(goal, level);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<3; i++)
            for (int j=0; j<3; j++)
                sb.append(grid[i][j]);
        return sb.toString();
    }

    @Override
    public boolean equals(Object n2)
    {
        //boolean equal = Arrays.deepEquals(grid, n2.grid);
        //return equal;
        return toString().equals(n2.toString());
    }

    //create child nodes, return as an arraylist used for astar
    public ArrayList<Node> createChildren(Node goal)
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
                if (this.getGrid()[i][j] == 0)
                {
                    x = i;
                    y = j;
                }
            }
        }

        //create first child node, swap the space with number below
        Node n1 = new Node(this.getGrid(), goal.getGrid(), this.getgValue());
        n1.setgValue(this.getgValue()+1);
        if (y > 0) //make sure it doesn't go out of bounds
        {
            int temp = n1.grid[x][y - 1];
            n1.grid[x][y - 1] = 0;
            n1.grid[x][y] = temp;
            children.add(n1);
        }

        //create second child node, swap the space with number above
        Node n2 = new Node(this.getGrid(), goal.getGrid(), this.getgValue());
        n2.setgValue(this.getgValue()+1);
        if (y < 2)
        {
            int temp = n2.grid[x][y + 1];
            n2.grid[x][y + 1] = 0;
            n2.grid[x][y] = temp;
            children.add(n2);
        }

        //create third child node, swap the space with number to right
        Node n3 = new Node(this.getGrid(), goal.getGrid(), this.getgValue());
        n3.setgValue(this.getgValue()+1);
        if (x < 2)
        {
            int temp = n3.grid[x + 1][y];
            n3.grid[x + 1][y] = 0;
            n3.grid[x][y] = temp;
            children.add(n3);
        }

        //create fourth child node, swap the space with number to left
        Node n4 = new Node(this.getGrid(), goal.getGrid(), this.getgValue());
        n4.setgValue(this.getgValue()+1);
        if (x > 0)
        {
            int temp = n4.grid[x - 1][y];
            n4.grid[x - 1][y] = 0;
            n4.grid[x][y] = temp;
            children.add(n4);
        }
        return children;
    }

    //calculate heuristic function
    public int fValue(int[][] goal, int gValue)
    //gValue = level of child (how many moves made to get here)
    {
        setgValue(gValue);
        this.hValue = hValue(goal);
        //return heuristic function value
        this.fValue = this.gValue + this.hValue;
        return this.fValue;
    }

    public int getfValue()
    {
        return this.fValue;
    }

    public void setgValue(int gValue)
    {
        this.gValue = gValue;
    }

    public int getgValue()
    {
        return this.gValue;
    }

    public int hValue(int[][] goal)
    {
        this.hValue = 0;
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

    public int gethValue()
    {
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
    }

    public int[][] getGrid()
    {
        return this.grid;
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
























