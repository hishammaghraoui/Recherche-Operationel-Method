/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class Graphe1 {
    private double[][] matriceAdj;
    private int taille;

    public Graphe1(int taille) {
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

        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {

                for (Sommet s : Sommet.getSommets()) {
                    if (s.getIndex() == i) {
                        System.out.println("i=  " + i + "  j=  " + j + "  s.getIndex()= " + s.getIndex());
                        for (DistanceAdj d : s.getAdjasonts()) {

                            if (d.getSommet().getIndex() == j) {

                                System.out.println("i=  " + i + "  j=  " + j + "  s.getIndex()= " + s.getIndex());
                                matriceAdj[i][j] = d.getDistance();
                            } // else
                              // matriceAdj[i][j]=-1;
                        }
                    }

                }

            }

        }

        for (int r = 0; r < taille; r++) {
            for (int c = 0; c < taille; c++) {
                System.out.print(matriceAdj[r][c] + "\t");
            }
            System.out.println();
        }
    }

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

    @Override
    public String toString() {
        return "Graphe{" + "matriceAdj=" + matriceAdj + ", taille=" + taille + '}';
    }

}
