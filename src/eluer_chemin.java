
import java.util.Iterator;

public class eluer_chemin {
    private Graph_eleur g;

    public eluer_chemin(Graph_eleur g) {
        super();
        this.g = g;
    }

    public int eulerianCircuit() {
        int result = isEulerian(g);
        if (result == 0) {
            System.out.println("Graph is not Eulerian");
            return result;
        } else if (result == 1) {
            System.out.println("Graph contains a Euler path");
            return result;
        } else {// 2{
            System.out.println("Graph contains a Euler cycle");
            return result;
        }
    }

    // returns 0: not eulerian, 1: euler path, 2: euler cycle
    public int isEulerian(Graph_eleur g) {
        // Check if all non-zero degree vertices are connected
        try {
            if (!isConnected(g))
                return 0;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Count vertices with odd degree
        int odd = 0;
        if (g.getvCount() == 0) {
            return 0;
        } else {
            for (int i = 0; i < g.getvCount(); i++)
                // check number of neighbours or degree
                if (g.neighbours(i).size() % 2 != 0)
                    odd++;

            // Check number of odd vertices
            if (odd > 2) { // non-eulerian
                return 0;
            } else if (odd == 2) { // semi-eulerian
                return 1;
            } else { // eulerian
                return 2;
            }
        }
    }

    public boolean isConnected(Graph_eleur g) throws Exception {
        // array to store if vertices where visited
        if (g == null) {
            return false;
        } else {
            boolean visited[] = new boolean[g.getvCount()];

            // initialze all to non-visited
            int i;
            for (i = 0; i < g.getvCount(); i++) {
                visited[i] = false;
            }

            // Find vertex with non-zero degree
            for (i = 0; i < g.getvCount(); i++) {
                if (g.neighbours(i).size() != 0)
                    break;
            }

            // If there are no edges in the graph (special case) then return true
            if (i == g.getvCount())
                return true;

            // DFS Traversal starting from non-zero vertex
            DFS(g, i, visited);

            // Check if all non-zero degree vertices are visited
            for (i = 0; i < g.getvCount(); i++)
                if (!visited[i] && g.neighbours(i).size() > 0)
                    return false;
            // if at least one was not visited false, else we return true
            return true;
        }

    }

    public void DFS(Graph_eleur g, int sourceVertex, boolean visited[]) {
        // Mark source node as visited
        visited[sourceVertex] = true;

        // recursion for all the vertices adjacent to this one
        Iterator<Integer> it = g.neighbours(sourceVertex).iterator();
        while (it.hasNext()) {
            int nextVertex = it.next() - 1;
            if (!visited[nextVertex])
                DFS(g, nextVertex, visited);
        }
    }
}
