
public class DistanceAdj {
    private Sommet s;
    private double distance;

    public DistanceAdj(Sommet s, double distance) {
        this.s = s;
        this.distance = distance;
    }

    // getters & setters:
    public Sommet getSommet() {
        return s;
    }

    public void setS(Sommet s) {
        this.s = s;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

}
