public class Solution {
    double z;
    double[] x;
    boolean solved;

    public Solution() {
        this.solved = false;
    }

    public Solution(double z, double[] x) {
        this.z = z;
        this.x = x;
        this.solved = true;
    }

    public double[] print() {
        if (solved) {
            System.out.println("find the optimal solution！");
            System.out.print("The optimal solution is：");
            for (int i = 0; i < x.length; i++) {
                System.out.print(x[i] + " ");
            }
            System.out.println();
            System.out.println("The optimal objective function value is:" + z);
        } else {
            System.out.println("no optimal solution！");
        }
        return x;
    }
}