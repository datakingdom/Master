/**
 * Created by SophieGao on 3/15/16.
 */


import java.lang.Math;
        import java.util.Random;
        import java.util.List;
        import java.util.ArrayList;
        import java.io.File;
        import java.io.BufferedWriter;
        import java.io.FileWriter;
        import java.io.IOException;

public class MonteCarIoQueen  {

    private int[] q;
    private int N;
    private long count;

    public MonteCarIoQueen (int N) {
        this.N = N;
        q = new int[N + 1];
        count = 1;
        // calc monte carlo
        int numOfTrial = 10000000;
        long sum = 0;
        for (int i = 0; i < numOfTrial; i++) {
            sum = sum + montecarlo();
        }

        // reset after monte carlo

        q = new int[N + 1];

        // nqueens(0);

        System.out.println("MonteCarlo Estimation: " + (sum / numOfTrial));
        System.out.println("Nodes visited: " + count / numOfTrial);
        System.out.println("Total nodes: " + Math.pow(N, N) + "\n");

        // writeData(timetaken, "queens_time.txt");
        // writeData(sum, "queens_estimate.txt");
        // writeData(count, "queens_visited.txt");

    }

    public boolean promising(int n) {
        count++;

        for (int i = 0; i < n; i++) {

            if (q[i] == q[n]) {
                return false;
            }

            if (Math.abs(q[n] - q[i]) == (n - i)) {
                return false;
            }
        }

        return true;
    }

    public int montecarlo() {
        Random r = new Random();
        int i, j;
        List<Integer> prom_child = new ArrayList<Integer>();

        i = 0;
        int numnodes = 1;
        int m = 1;
        int mprod = 1;

        while (m != 0 && i != N) {

            mprod = mprod * m;
            numnodes = numnodes + mprod * N;
            i++;
            m = 0;
            prom_child.clear();

            for (j = 0; j < N; j++) {
                q[i] = j;
                if (promising(i)) {
                    m++;
                    prom_child.add(j);
                }
            }

            if (m != 0) {
                j = prom_child.get(r.nextInt(prom_child.size()));
                q[i] = j;
            }

        }
        return numnodes;
    }

    public void nqueens(int n) {

        if (n == N) {
            // display();
            return;
        }

        else {
            for (int i = 0; i < N; i++) {
                q[n] = i;
                if (promising(n)) {
                    nqueens(n + 1);
                }
            }
        }

    }

    public void display() {

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (q[i] == j) {
                    System.out.print("Q ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println("");
        }
        System.out.println("");

    }

    public void writeData(long datum, String filename) {

        try {
            File file = new File(filename);

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getName(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            String content = "" + datum + "\n";
            bw.write(content);
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new Queens(8);
    }

}