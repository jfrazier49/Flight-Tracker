
import java.util.List;

public interface AlgorithmEngineerInterface<NodeType, EdgeType extends Number> {
    public boolean insertNode(NodeType data);

    public boolean removeNode(NodeType data);

    public boolean containsNode(NodeType data); // checks if graph contains
                                                // given node

    public int getNodeCount(); // returns number of nodes in graph

    public boolean insertEdge(NodeType pred, NodeType succ, EdgeType time, EdgeType cost);

    public boolean removeEdge(NodeType pred, NodeType succ);

    public boolean containsEdge(NodeType pred, NodeType succ);

    public List<EdgeType> getEdge(NodeType pred, NodeType succ); // returns
                                                                 // EdgeType
                                                                 // list with
                                                                 // two
                                                                 // elements,
                                                                 // first is
                                                                 // cost and
                                                                 // second is
                                                                 // distance

    public int getEdgeCount(); // returns number of edges in graph

    public List<NodeType> shortestPathData(NodeType start, NodeType end, boolean costOrDistance); // costOrDistance:
                                                                                                  // true
                                                                                                  // for
                                                                                                  // cost
                                                                                                  // mode,
                                                                                                  // false
                                                                                                  // for
                                                                                                  // distance
                                                                                                  // mode

    public double shortestPathCost(NodeType start, NodeType end, boolean costOrDistance); // costOrDistance:
                                                                                          // true
                                                                                          // for
                                                                                          // cost
                                                                                          // mode,
                                                                                          // false
                                                                                          // for
                                                                                          // distance
                                                                                          // mode
}
