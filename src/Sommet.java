
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class Sommet {
    private static int nbr = -1;
    private int index;
    private String nom;
    private double x;
    private double y;
    private boolean blocked;
    private static ArrayList<Sommet> sommets;
    private ArrayList<DistanceAdj> Adjasonts;

    static {
        sommets = new ArrayList<Sommet>();
    }

    public boolean isAdj(Sommet s) {
        for (DistanceAdj d : this.Adjasonts) {
            if (d.getSommet().equals(s)) {
                return true;
            }
        }
        return false;
    }

    // --contructor :
    public Sommet(String nom) {
        Adjasonts = new ArrayList<DistanceAdj>();
        this.nom = nom;
        this.x = 0;
        this.y = 0;
        this.blocked = false;
        this.index = ++nbr;

        sommets.add(this);
    }

    // --getters & setters :
    public int getIndex() {
        return index;
    }

    public static int getNbr() {
        return nbr;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public ArrayList<DistanceAdj> getAdjasonts() {
        return Adjasonts;
    }

    public void setAdjasonts(ArrayList<DistanceAdj> Adjasonts) {
        this.Adjasonts = Adjasonts;
    }

    public static ArrayList<Sommet> getSommets() {
        return sommets;
    }

    // --equals :
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sommet other = (Sommet) obj;
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        return true;
    }

    // --to string :

    @Override
    public String toString() {
        return "Sommet{" + "index=" + index + ", nom=" + nom + ", x=" + x + ", y=" + y + ", blocked=" + blocked + '}';
    }

    // ajouter Adj:
    public boolean addAdj(Sommet s, double d) {
        if (!this.equals(s)) {
            DistanceAdj dist = new DistanceAdj(s, d);
            this.Adjasonts.add(dist);
            return true;
        }
        System.out.println("meme sommet!!");
        return false;
    }

    // get Nom des sommer :
    public static ArrayList<String> getListNomsSommet() {
        ArrayList<String> nomSommets = new ArrayList<String>();

        for (Sommet s : Sommet.sommets) {
            nomSommets.add(s.getNom());
        }
        return nomSommets;
    }

    // get sommet by nom:
    public static Sommet getSommetByNom(String nomSommet) {
        for (Sommet s : Sommet.sommets) {
            if (s.getNom().equals(nomSommet)) {
                return s;
            }
        }
        return null;
    }

    // get sommet by index:
    public static Sommet getSommetByIndex(int index) {
        for (Sommet s : Sommet.sommets) {
            if (s.getIndex() == index) {
                return s;
            }
        }
        return null;
    }

    // get les nom des sommets restant (-listAdj and -this).
    public static ArrayList<String> getNomSommetRest(String nom) {

        ArrayList<String> nomSommetRest = new ArrayList<String>();
        // --get sommet by nom :
        Sommet a = Sommet.getSommetByNom(nom);
        Sommet[] listsommet = sommets.toArray(new Sommet[sommets.size()]);

        /*
         * Sommet[] adj = a.getAdjasonts().toArray( new
         * Sommet[a.getAdjasonts().size()]);
         * String[] louja = new String[sommets.size()];
         * String[] adjS = new String[a.getAdjasonts().size()];
         * 
         * for(int j=0; j<Sommet.getSommets().size();j++){
         * louja[j] = listsommet[j].getNom();
         * }
         * for(int j=0; j<a.getAdjasonts().size();j++){
         * adjS[j] = adj[j].getNom();
         * }
         */

        // ArrayList<Sommet> list ;

        // --fuction:
        for (int i = 0; i < listsommet.length; i++) {

            if (!listsommet[i].equals(a)) {
                nomSommetRest.add(listsommet[i].getNom());
            }
            /*
             * for(int j=0; j<a.getAdjasonts().size(); j++){
             * 
             * System.out.println("list [i] : ==>"+listsommet[i]);
             * System.out.println("h : ==>"+listsommet[i].getNom());
             * intList.add(listsommet[i].getNom());
             * }
             */
        }
        // intList.removeAll(nomSommetRest);
        // System.out.println("INT LIST : ==>"+intList);
        System.out.println("REST SOMMET : ==>" + nomSommetRest);

        return nomSommetRest;
    }

    public <T> ArrayList<T> intersection(ArrayList<T> list1, ArrayList<T> list2) {
        ArrayList<T> list = new ArrayList<T>();

        for (T t : list1) {
            if (list2.contains(t)) {
                list.add(t);
            }
        }
        return list;
    }

    // System.out.println("LIST ADJACENT Choix "+a.getAdjasonts());
    /*
     * 
     * 
     * 
     * if(!a.getAdjasonts().isEmpty()){
     * for(Sommet h: a.getAdjasonts()){
     * if(!listsommet[i].equals(h)){
     * nomSommetRest.add(listsommet[i].getNom());
     * }
     * }
     * }
     * else{
     * System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
     * }
     * 
     * }
     */

    // System.out.println("avant "+list.toString());
    /*
     * for(int k=0; k<help.length;k++){
     * if(!help[k].equals(a.getNom())){
     * for(int e=0;e<adjS.length;e++){
     * if(!help[k].equals(adjS[e])){
     * nomSommetRest.add(help[k]);
     * }
     * }
     * }
     */

    // System.out.println("aprés adj"+list.toString());

    // System.out.println("aprés adj+"+list.toString());
    // for(Sommet n: list){
    // }*/

    // supprimer sommet :
    public static boolean supprimerSommet(String nomsommet) {
        Sommet.nbr--;
        return Sommet.sommets.remove(Sommet.getSommetByNom(nomsommet));
    }

}
