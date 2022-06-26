
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Graph_eleur {
    private int vCount;
    private List<Integer>[] adj;

    public int getvCount() {
        if (vCount == 0) {
            return 0;
        } else
            return vCount;
    }

    public Graph_eleur(int vCount) {
        this.vCount = vCount;
        adj = new List[vCount];
        for (int i = 0; i < vCount; i++)
            adj[i] = new ArrayList<>();
    }

    public void addEdge(int i, int j) {
        adj[i - 1].add(j);
        adj[j - 1].add(i);
    }

    public void removeEdge(int i, int j) {
        Iterator<Integer> it = adj[i].iterator();
        while (it.hasNext()) {
            if (it.next() == j) {
                it.remove();
                break;
            }
        }
        Iterator<Integer> it2 = adj[j].iterator();
        while (it.hasNext()) {
            if (it.next() == i) {
                it.remove();
                return;
            }
        }
    }

    public boolean hasEdge(int i, int j) {
        return adj[i].contains(j);
    }

    public List<Integer> neighbours(int vertex) {
        return adj[vertex];
    }

    public void printGraph() {
        for (int i = 0; i < vCount; i++) {
            List<Integer> edges = neighbours(i);
            System.out.print(i + ": ");
            for (Integer edge : edges) {
                System.out.print(edge + " ");
            }
            System.out.println();
        }
    }
}