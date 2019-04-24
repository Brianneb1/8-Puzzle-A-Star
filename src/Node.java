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

//    public Node(Node n, int[][] goal)
//    {
//        this.parent = n.getParent();
//        this.grid = n.getGrid();
//        this.hValue = n.gethValue();
//        setgValue(this.getgValue()+1);
//        this.fValue = n.fValue(goal, gValue);
//    }

    public boolean equals(Node n1, Node n2)
    {
        boolean equal = Arrays.deepEquals(n1.grid, n2.grid);
        return equal;
    }

    //create child nodes, return as an arraylist used for astar
    public ArrayList<Node> createChildren(Node goal, int level)
    {
        //arraylist to add child nodes to
        ArrayList<Node> children = new ArrayList<Node>();
//
//        int[][] grid1 = new int[3][3]; //clone this.grid so it doesn't get altered after each node is created
//        int[][] grid2 = new int[3][3];
//        int[][] grid3 = new int[3][3];
//        int[][] grid4 = new int[3][3];
//
//        for (int i = 0; i < 3; i++) //ugly but necessary to maintain object values
//        {
//            for (int j = 0; j < 3; j++)
//            {
//                grid1[i][j] = this.grid[i][j];
//            }
//        }
//        for (int i = 0; i < 3; i++)
//        {
//            for (int j = 0; j < 3; j++)
//            {
//                grid2[i][j] = this.grid[i][j];
//            }
//        }
//        for (int i = 0; i < 3; i++)
//        {
//            for (int j = 0; j < 3; j++)
//            {
//                grid3[i][j] = this.grid[i][j];
//            }
//        }
//        for (int i = 0; i < 3; i++)
//        {
//            for (int j = 0; j < 3; j++)
//            {
//                grid4[i][j] = this.grid[i][j];
//            }
//        }

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
//            n2.grid[x][y] = grid2[x][y + 1];
//            n2.grid[x][y + 1] = 0;
//            children.add(n2);

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
//            n3.grid[x][y] = grid3[x + 1][y];
//            n3.grid[x + 1][y] = 0;
//            children.add(n3);

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
//            n4.grid[x][y] = grid4[x-1][y];
////            n4.grid[x-1][y] = 0;
////            children.add(n4);

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
























