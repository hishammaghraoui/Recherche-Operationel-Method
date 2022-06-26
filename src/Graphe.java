
import java.util.ArrayList;
import java.util.Collections;

public class Graphe {
    private double[][] matriceAdj;
    private int taille;
    private static final int NO_PARENT = -1;
    private static ArrayList<Integer> pluscc = new ArrayList<Integer>();

    public Graphe(int taille) {
        matriceAdj = new double[taille][taille];
        this.taille = taille;
        // Sommet[] listsommet = Sommet.getSommets().toArray( new
        // Sommet[Sommet.getSommets().size()]);
        // System.out.println("kkkk "+i+" :: "+j);
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                matriceAdj[i][j] = 0;
            }
        }

        remplireMatrice(taille);
        for (int r = 0; r < taille; r++) {
            for (int c = 0; c < taille; c++) {
                System.out.print(matriceAdj[r][c] + "\t");
            }
            System.out.println();
        }

        for (Sommet s : Sommet.getSommets()) {
            System.out.println("------- " + s.getIndex() + " nom : " + s.getNom());
            for (DistanceAdj d : s.getAdjasonts()) {
                System.out.println("nom----> " + d.getSommet().getNom() + " index : " + d.getSommet().getIndex()
                        + " distance : " + d.getDistance());
            }
        }

    }

    // ------------remplire------------------
    private void remplireMatrice(int taille) {

        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                if (i != j) {
                    matriceAdj[i][j] = help(i, j);
                }
            }
        }
    }

    // ------------- help -------------
    private double help(int i, int j) {
        for (Sommet s : Sommet.getSommets()) {
            if (s.getIndex() == i) {
                for (DistanceAdj d : s.getAdjasonts()) {
                    if (d.getSommet().getIndex() == j) {
                        return d.getDistance();
                    }
                }
            }
        }
        return 0.0;
    }

    // les getters & les setters :
    public double[][] getMatriceAdj() {
        return matriceAdj;
    }

    public void setMatriceAdj(double[][] matriceAdj) {
        this.matriceAdj = matriceAdj;
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    // to string :
    @Override
    public String toString() {
        return "Graphe{" + "matriceAdj=" + matriceAdj + ", taille=" + taille + '}';
    }

    // ===================================== Dijkstra
    // ===============================
    public static ArrayList<Integer> PlusCourtChemin(double[][] M_Adjacent, int pointDepart, int pointArrive) {

        ArrayList<Integer> lists_index = new ArrayList<Integer>();
        int nb_sommet = M_Adjacent[0].length;
        double[] Distances_PlusCourt = new double[nb_sommet];

        // b_sommet[i] sera vrai "true" si le sommet i est inclus dans l'arbre du chemin
        // le plus court
        // ou si la distance la plus courte de source à i est finalisée

        boolean[] b_sommet = new boolean[nb_sommet];

        // on Initialiser toutes les distances comme INFINITE avec Integer.MAX_VALUE
        // et b_sommet[] comme false pour dire que aucun indice de sommet est inclus
        // dans l'arbre
        // du chemain le plus court ** LOUJA **

        for (int index_Sommet = 0; index_Sommet < nb_sommet; index_Sommet++) {
            Distances_PlusCourt[index_Sommet] = Integer.MAX_VALUE;
            b_sommet[index_Sommet] = false;
        }

        // au debut on inisializer La distance de la source sommet à elle-même est
        // toujours 0
        // puisque le plus court chemain avec lui meme c'est zero 1--->1 2--->2
        Distances_PlusCourt[pointDepart] = 0;

        // dans le Tableau parent on stocker l'arbre du chemin le plus court
        int[] parents = new int[nb_sommet];

        // Bien sur Le sommet de départ n'a pas de parent
        parents[pointDepart] = NO_PARENT;

        // on cherche a Trouver le chemin le plus court pour tous les sommets avec la
        // source
        for (int i = 1; i < nb_sommet; i++) {
            // Choisir la distance minimale sommet de l'ensemble des sommets qui pas encore
            // traité.
            // le plus proche "PlusPoche" est toujours égal à startNode dans la première
            // itération.

            int PlusPoche = -1; // cas ou n'existe pas
            double dist_pc = Integer.MAX_VALUE; // Distance Plus Court
            for (int index_Sommet = 0; index_Sommet < nb_sommet; index_Sommet++) {
                if (!b_sommet[index_Sommet] &&
                        Distances_PlusCourt[index_Sommet] < dist_pc) {
                    PlusPoche = index_Sommet;
                    dist_pc = Distances_PlusCourt[index_Sommet];
                }
            }

            // Marquez le sommet choisi comme a ete traité rendre l'indice
            // de sommet dans la table b sommet 'true'
            b_sommet[PlusPoche] = true;

            // Mettre à jour la valeur dist du sommets adjacents du choisi sommet.
            for (int index_Sommet = 0; index_Sommet < nb_sommet; index_Sommet++) {
                // Distance au bord
                double Distance_bord = M_Adjacent[PlusPoche][index_Sommet];

                if (Distance_bord > 0
                        && ((dist_pc + Distance_bord) < Distances_PlusCourt[index_Sommet])) {
                    parents[index_Sommet] = PlusPoche;
                    Distances_PlusCourt[index_Sommet] = dist_pc + Distance_bord;
                }
            }
        }
        ArrayList<Integer> chemin = new ArrayList<Integer>();

        chemin = SetData(pointArrive, parents);

        Collections.reverse(chemin);

        return chemin;
    }

    // ===================================== setData===============================
    // Function to print shortest path
    // from source to SommetActuel
    // using parents array
    private static ArrayList<Integer> SetData(int SommetActuel, int[] parents) {

        ArrayList<Integer> lists = new ArrayList<Integer>();

        if (SommetActuel == NO_PARENT) {
            return null;
        }
        pluscc.add(SommetActuel);
        SetData(parents[SommetActuel], parents);
        return pluscc;
    }

}
