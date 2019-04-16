import java.util.ArrayList;

public class solutions {

    public static ArrayList<Node> astar(Node start, Node goal){
        //heap fvalues based on heuristic
        ArrayList<Node> closed = new ArrayList<Node>();//closed list
        minHeap fvalues = new minHeap(); //heap based on fvalues (open list)
        ArrayList<Node> path = new ArrayList<Node>();
        int level = 0;

        //add starting node to open list
        fvalues.add(start, level);

        while (fvalues.size() != 0)
        {
            level++;
            Node n = fvalues.remove(level); //get current node

            if (n.grid.equals(goal)) //if found goal state, recreate path
            {
//                while ( )
//                {
//                    path.add()
//                }
                //return path
            }

            closed.add(n);
            ArrayList<Node> children = n.createChildren();

            for (int i = 0; i < children.size(); i++) //for each child of n
            {
                Node child = children.get(i); //get child

                if (closed.contains(child)) //if child in closed, skip
                {
                    continue;
                }

                if(fvalues.contains(child)) //if child in open
                {
                    if (child.gValue > n.gValue) //if child gVal > parent, skip
                    {
                        continue;
                    }
                }

                fvalues.add(child, level); //add child to open list
            }
        }

        return path;

    }



}
