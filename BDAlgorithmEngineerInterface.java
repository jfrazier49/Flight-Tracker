
import java.util.ArrayList;
import java.util.List;

public class BDAlgorithmEngineerInterface<NodeType, EdgeType extends Number> implements AlgorithmEngineerInterface<NodeType, EdgeType> {

    public ArrayList<NodeType> dataList; // List for hardcoded AE interface instead of an MST
    public ArrayList<NodeType> edgeList; // List for hardcoded AE interface instead of an MST

    // COnstructor for class, initializes the lists
    public BDAlgorithmEngineerInterface() {
        dataList = new ArrayList<NodeType>();
        edgeList = new ArrayList<NodeType>();
    }

    public boolean insertNode(NodeType data) {
        dataList.add(data);
        return true;
    }

    public boolean removeNode(NodeType data) {
        dataList.remove(data);
        return true;
    }

    public boolean containsNode(NodeType data) {
        if (dataList.contains(data)) {
            return true;
        } else {
            return false;
        }

    }

    public int getNodeCount() {
        return dataList.size();
    }

    public boolean insertEdge(NodeType pred, NodeType succ, EdgeType time, EdgeType cost) {
        edgeList.add(succ);
        return true;
    }

    public boolean removeEdge(NodeType pred, NodeType succ) {
        edgeList.remove(succ);
        return true;
    }

    public boolean containsEdge(NodeType pred, NodeType succ) {
        if (edgeList.contains(succ)) {
            return true;
        } else {
            return false;
        }
    }

    public List<EdgeType> getEdge(NodeType pred, NodeType succ) {
        return new ArrayList<EdgeType>();
    }

    public int getEdgeCount() {
        return edgeList.size();
    }

    public List<NodeType> shortestPathData(NodeType start, NodeType end, boolean costOrDistance) {
        if (costOrDistance) {
            return dataList;
        } else {
            return edgeList;
        }

    }

    public double shortestPathCost(NodeType start, NodeType end, boolean costorDistance) {
        return edgeList.indexOf(end) - edgeList.indexOf(start);
    }

}
