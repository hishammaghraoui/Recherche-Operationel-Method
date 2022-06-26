
import java.util.Arrays;

public class Main {

	Solution simplex(double[][] A, double[] b, double[] c, boolean needInitialize, boolean[] BI_input) {

		int m = A.length;
		int n = A[0].length;
		double[] x = new double[n];
		double z = 0;
		double[] delta = new double[m];

		boolean[] BI;
		if (needInitialize) {
			BI = new boolean[n];
			Arrays.fill(BI, false);
			z = initializeSimplex(A, b, c, BI);
		} else
			BI = BI_input;
		if (z == Double.MAX_VALUE)
			return new Solution();
		while (true) {

			int e = -1;
			for (int i = 0; i < n; i++)
				if (c[i] < 0) {
					e = i;// break;
				}
			if (e == -1) {
				calculateX(A, b, c, BI, x);
				return new Solution(-z, x);
			}

			Arrays.fill(delta, Double.MAX_VALUE);
			int li = 0;
			for (int i = 0; i < m; i++) {
				if (A[i][e] > 0) {
					delta[i] = b[i] / A[i][e];
					li = (delta[li] > delta[i]) ? i : li;
				}
			}
			if (delta[li] == Double.MAX_VALUE)
				return new Solution();

			int lj = 0;
			for (int j = 0; j < n; j++) {
				if (BI[j] == true && A[li][j] == 1) {
					lj = j;
				}
			}
			z = pivot(A, b, c, BI, e, li, lj, z);
		}
	}

	private double initializeSimplex(double[][] A, double[] b, double[] c, boolean[] BI) {

		for (int i = BI.length - 1; i >= BI.length - b.length; i--)
			BI[i] = true;

		int bi = 0;
		for (int i = 1; i < b.length; i++)
			bi = (b[bi] > b[i]) ? i : bi;
		if (b[bi] >= 0)
			return 0;

		int m = A.length;
		int n = A[0].length;
		double[] c_aux = new double[n + 1];
		Arrays.fill(c_aux, 0);
		c_aux[0] = 1;// 目标函数为x0
		double[] b_aux = new double[m];
		double[][] A_aux = new double[m][n + 1];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n + 1; j++) {
				if (j == 0) {
					A_aux[i][j] = -1;
				} else
					A_aux[i][j] = A[i][j - 1];
			}
			b_aux[i] = b[i];
		}
		boolean[] BI_aux = new boolean[n + 1];
		BI_aux[0] = false;
		for (int j = 1; j < n + 1; j++)
			BI_aux[j] = BI[j - 1];

		int bj = 0;
		for (int j = 0; j < BI_aux.length; j++) {
			if (BI_aux[j] == true && A_aux[bi][j] == 1) {
				bj = j;
			}
		}
		double z = 0;

		z = pivot(A_aux, b_aux, c_aux, BI_aux, 0, bi, bj, z);
		Solution solution = simplex(A_aux, b_aux, c_aux, false, BI_aux);

		solution.z = z - solution.z;
		if (solution.z == 0) {

			for (int j = 1; j < n + 1; j++) {
				BI[j - 1] = BI_aux[j];
			}
			for (int i = 0; i < m; i++) {
				for (int j = 1; j < n + 1; j++) {
					A[i][j - 1] = A_aux[i][j];
				}
				b[i] = b_aux[i];
			}
			z = solution.z;

			for (int e = 0; e < n; e++) {
				if (BI[e] == true && c[e] != 0) {
					int li = 0;

					for (int i = 0; i < m; i++) {
						if (A[i][e] == 1)
							li = i;
					}

					z = z - b[li] * c[e];
					double ce = c[e];
					for (int j = 0; j < n; j++)
						c[j] = c[j] - ce * A[li][j];

				}
			}
			return z;
		} else {
			return Double.MAX_VALUE;
		}
	}

	private void calculateX(double[][] A, double[] b, double[] c, boolean[] BI, double[] x) {

		for (int j = 0; j < BI.length; j++) {
			if (BI[j] == false) {
				x[j] = 0;
			} else {
				for (int i = 0; i < A.length; i++) {
					if (A[i][j] == 1)
						x[j] = b[i];
				}
			}
		}
	}

	private double pivot(double[][] A, double[] b, double[] c, boolean[] BI, int e, int li, int lj, double z) {

		int m = A.length;
		int n = A[0].length;
		b[li] = b[li] / A[li][e];

		double Alie = A[li][e];
		for (int j = 0; j < n; j++)
			A[li][j] = A[li][j] / Alie;

		for (int i = 0; i < m; i++) {
			if (i != li) {
				double Aie = A[i][e];
				b[i] = b[i] - Aie * b[li];
				for (int j = 0; j < n; j++)
					A[i][j] = A[i][j] - Aie * A[li][j];
			}
		}

		z = z - b[li] * c[e];
		double ce = c[e];
		for (int j = 0; j < n; j++)
			c[j] = c[j] - ce * A[li][j];

		BI[lj] = false;
		BI[e] = true;
		return z;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Main s = new Main();
		Solution solution;
		// Non-slack variables come first, slack variables come after
		double[][] A = { { 1, 1, 1, 1, 0, 0, 0 }, { 1, 0, 0, 0, 1, 0, 0 }, { 0, 0, 1, 0, 0, 1, 0 },
				{ 0, 3, 1, 0, 0, 0, 1 } };
		double[] b = { 4, 2, 3, 6 };
		double[] c = { -1, -14, -6, 0, 0, 0, 0 };
		solution = s.simplex(A, b, c, true, null);
		solution.print();

	}

}
