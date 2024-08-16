
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

public class AlgorithmsAE<NodeType, EdgeType extends Number> extends BaseGraph<NodeType, EdgeType>
        implements AlgorithmEngineerInterface<NodeType, EdgeType> {

    /**
     * While searching for the shortest path between two nodes, a SearchNode
     * contains data about one specific path between the start node and another
     * node in the graph. The final node in this path is stored in it's node
     * field. The total cost of this path is stored in its cost field. And the
     * predecessor SearchNode within this path is referened by the predecessor
     * field (this field is null within the SearchNode containing the starting
     * node in it's node field). SearchNodes are Comparable and are sorted by
     * cost so that the lowest cost SearchNode has the highest priority within a
     * java.util.PriorityQueue.
     */
    protected class SearchNode implements Comparable<SearchNode> {
        public Node node;
        public double cost;
        public SearchNode predecessor;

        public SearchNode(Node node, double cost, SearchNode predecessor) {
            this.node = node;
            this.cost = cost;
            this.predecessor = predecessor;
        }

        public int compareTo(SearchNode other) {
            if (cost > other.cost)
                return +1;
            if (cost < other.cost)
                return -1;
            return 0;
        }
    }

    
    /**
     * Returns the list of data values from nodes along the shortest path
     * from the node with the provided start value through the node with the
     * provided end value. This list of data values starts with the start
     * value, ends with the end value, and contains intermediary values in the
     * order they are encountered while traversing this shorteset path. This
     * method uses Dijkstra's shortest path algorithm to find this solution.
     *
     * @param start the data item in the starting node for the path
     * @param end   the data item in the destination node for the path
     * @param costOrDistance true for cost mode, false for distance mode
     * @return list of data item from node along this shortest path
     */
    public List<NodeType> shortestPathData(NodeType start, NodeType end, boolean costOrDistance) {
        LinkedList<NodeType> list = new LinkedList<>();
        SearchNode node = computeShortestPath(start, end, costOrDistance);
        NodeType data = end;

        // backtracks from final node to its predecessor until it gets to startNode
        while (!data.equals(start)) {
            list.add(0, data);
            node = node.predecessor;
            data = node.node.data;
        }
        list.add(0, data);

        return list;
    }

    
    /**
     * Returns the cost of the path (sum over edge weights) of the shortest
     * path freom the node containing the start data to the node containing the
     * end data. This method uses Dijkstra's shortest path algorithm to find
     * this solution.
     *
     * @param start the data item in the starting node for the path
     * @param end   the data item in the destination node for the path
     * @param costOrDistance true for cost mode, false for distance mode
     * @return the cost of the shortest path between these nodes
     */
    public double shortestPathCost(NodeType start, NodeType end, boolean costOrDistance) {
        return computeShortestPath(start, end, costOrDistance).cost;
    }

    /**
     * This helper method creates a network of SearchNodes while computing the
     * shortest path between the provided start and end locations. The
     * SearchNode that is returned by this method is represents the end of the
     * shortest path that is found: it's cost is the cost of that shortest path,
     * and the nodes linked together through predecessor references represent
     * all of the nodes along that shortest path (ordered from end to start).
     *
     * @param start the data item in the starting node for the path
     * @param end   the data item in the destination node for the path
     * @param costOrDistance true for cost mode, false for distance mode
     * @return SearchNode for the final end node within the shortest path
     * @throws NoSuchElementException when no path from start to end is found
     *                                or when either start or end data do not
     *                                correspond to a graph node
     */
    protected SearchNode computeShortestPath(NodeType start, NodeType end, boolean costOrDistance) {
        // make sure start and end data correspond to graph nodes
        if (nodes.get(start) == null || nodes.get(end) == null) {
            throw new NoSuchElementException("either start or end data do not correspond to a graph node");
        }

        // return zero if start and end are the same
        if (start.equals(end))
            return new SearchNode(nodes.get(start), 0, null);

        // hashtable with a SearchNode for every node, initialized with cost being 0 for
        // the start SearchNode and Integer.MAX_VALUE for every other node
        Hashtable<Node, SearchNode> searchNodes = new Hashtable<>();
        for (Map.Entry<NodeType, Node> entry : nodes.entrySet()) {
            Node node = entry.getValue();

            if (entry.getKey().equals(start))
                searchNodes.put(node, new SearchNode(node, 0, null));
            else
                searchNodes.put(node, new SearchNode(node, Integer.MAX_VALUE, null));
        }

        // priority queue to greedily explore shorter path possibilities, initialized
        // with the starting SearchNode
        PriorityQueue<SearchNode> toVisit = new PriorityQueue<>();
        toVisit.add(searchNodes.get(nodes.get(start)));

        // hashtable to keep track of visited nodes
        Hashtable<Node, SearchNode> visited = new Hashtable<>();

        // visit the cheapest node until all nodes have been visited
        while (!toVisit.isEmpty()) {
            SearchNode startNode = toVisit.poll();
            visited.put(startNode.node, startNode);

            // if adjacent nodes are cheaper through current startNode, update its cost,
            // else do nothing
            for (Edge e : startNode.node.edgesLeaving) {
                if (visited.containsKey(e.successor))
                    continue;

                // check if adjacent node is cheaper based on either cost or distance
                SearchNode intermediate = searchNodes.get(e.successor);
                if (startNode.cost + (costOrDistance ? e.cost.doubleValue() : e.distance.doubleValue()) < intermediate.cost) {
                    intermediate.cost = startNode.cost + (costOrDistance ? e.cost.doubleValue() : e.distance.doubleValue());
                    intermediate.predecessor = startNode;
                }

                if (!toVisit.contains(intermediate))
                    toVisit.add(intermediate);
            }
        }

        // return the final SearchNode
        SearchNode endNode = searchNodes.get(nodes.get(end));
        if (endNode.predecessor == null)
            throw new NoSuchElementException("No path from start to end found");

        return endNode;
    }

}
