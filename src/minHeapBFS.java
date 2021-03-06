import java.util.ArrayList;

public class minHeapBFS {
        private ArrayList<Node> ctree;
        int[][] goalState = {{1,2,3},{4,5,6},{7,8,0}};

        public minHeapBFS()
        {
            ctree = new ArrayList<Node>();
        }

        public void add(Node n)
        {
            ctree.add(n); //add node after finding fvalue
            heapifyUp();;
        }

        public Node remove()
        {
            Node val = ctree.get(0);
            ctree.set(0, ctree.get(ctree.size()-1));
            ctree.remove(ctree.size() - 1);
            heapifyDown();
            return val;
        }

        public Node top()
        {
            return ctree.get(0);
        }

        public int size()
        {
            return ctree.size();
        }

        private void heapifyUp()
        {
            int curr = ctree.size()-1;
            while (curr > 0 && ctree.get(curr).hValue(goalState) < ctree.get(parent_idx(curr)).hValue(goalState)) //elms left and curr hval < parent hvalue
            {
                Node temp;
                temp = ctree.get(curr);
                ctree.set(curr, parent_node(curr));
                ctree.set(parent_idx(curr), temp);
                curr = parent_idx(curr);
            }
        }

        private void heapifyDown()
        {
            int curr = 0;
            while (right_node(curr) != null)
            {
                if (right_node(curr).hValue(goalState) < left_node(curr).hValue(goalState))
                {
                    if (ctree.get(curr).hValue(goalState) > right_node(curr).hValue(goalState))
                    {
                        Node temp = ctree.get(curr); //temp = current index Node
                        ctree.set(curr, right_node(curr)); // set current index to right Node
                        ctree.set(right_idx(curr), temp); // set right Node to current value
                        curr = right_idx(curr); // current idx = right idx
                    }
                }
                else if (ctree.get(curr).hValue(goalState) > left_node(curr).hValue(goalState))
                {
                    Node temp = ctree.get(curr);
                    ctree.set(curr, left_node(curr));
                    ctree.set(left_idx(curr), temp);
                    curr = left_idx(curr);
                }
                else
                    return;
            }
            if (left_node(curr) != null && ctree.get(curr).hValue(goalState) > left_node(curr).hValue(goalState))
            {
                Node temp = ctree.get(curr);
                ctree.set(curr, left_node(curr));
                ctree.set(left_idx(curr), temp);
            }
        }

        private int left_idx(int i)
        {
            return 2 * i + 1;
        }

        private Node left_node(int i)
        {
            int idx = left_idx(i);
            if (idx >= ctree.size())
                return null;
            return ctree.get(idx);
        }
        private int right_idx(int i)
        {
            return 2 * i + 2;
        }
        private Node right_node(int i)
        {
            int idx = right_idx(i);
            if (idx >= ctree.size())
                return null;
            return ctree.get(idx);
        }
        private int parent_idx(int i)
        {
            return (i-1)/2;
        }
        private Node parent_node(int i)
        {
            if (i== 0)
                return null;
            return ctree.get(parent_idx(i));
        }


        //swaps the Nodes in ctree at indices i1 and i2
        private void swap(int i1, int i2)
        {
            Node temp = ctree.get(i2);
            ctree.set(i2, ctree.get(i1));
            ctree.set(i1, temp);
        }

        public boolean contains(Node n)
        {
            for (int i = 0; i < this.size(); i++)
            {
                if (ctree.get(i).equals(n));
                return true;
            }
            return false;
        }

}
