import com.sun.org.apache.xpath.internal.SourceTree;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by HOME on 11/6/2017.
 */
public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        float[][] centroids = new float[2][2];
        int nSamples;

        System.out.println("Please enter J");
        float jPrev = scan.nextFloat();

        System.out.println("Please enter centroid 1 x & y");
        centroids[0][0] = scan.nextFloat();
        centroids[0][1] = scan.nextFloat();

        System.out.println("Please enter centroid 2 x & y ");
        centroids[1][0] = scan.nextFloat();
        centroids[1][1] = scan.nextFloat();

        System.out.println("Please enter number of samples: ");
        nSamples = scan.nextInt();

        int flag[] = new int[nSamples];
        float[][] samples = new float[nSamples][2];
        ArrayList newCentroid1 = new ArrayList();
        ArrayList newCentroid2 = new ArrayList();
        double j = 0;

        for (int i = 0; i < nSamples; i++){
            System.out.println("\nSample " + (i + 1) + ", Enter x & y");
            samples[i][0] = scan.nextFloat();
            samples[i][1] = scan.nextFloat();
        }

//        for (int x = 0; x < nSamples; x++){
//            System.out.println("sample " + (x + 1) + " (" + samples[x][0] +","+ samples[x][1] +")\n");
//        }

        System.out.println("Centroid 1: (" + centroids[0][0] +","+ centroids [0][1] +")\n");
        System.out.println("Centroid 2: (" + centroids[1][0] +","+ centroids [1][1] +")\n");

        for (int z = 0; z < nSamples; z++){

//            double mew1 = round(Math.sqrt(Math.pow(samples[z][0] - centroids[0][0], 2) + Math.pow(samples[z][1] - centroids[0][1], 2)), 2);
//            double mew2 = round(Math.sqrt(Math.pow(samples[z][0] - centroids[1][0], 2) + Math.pow(samples[z][1] - centroids[1][1], 2)), 2);

            double mew1 = Math.sqrt(Math.pow(samples[z][0] - centroids[0][0], 2) + Math.pow(samples[z][1] - centroids[0][1], 2));
            double mew2 = Math.sqrt(Math.pow(samples[z][0] - centroids[1][0], 2) + Math.pow(samples[z][1] - centroids[1][1], 2));

            String smew1 = String.format("%.2f", mew1);
            String smew2 = String.format("%.2f", mew2);

            if (mew1 > mew2){
                newCentroid2.add(z);
                flag[z] = 2;
                j = j + mew2;
            }else{
                newCentroid1.add(z);
                flag[z] = 1;
                j = j + mew1;
            }

            System.out.println("sample " + (z + 1));
            System.out.println("mu 1 = sqrt ( (" + samples[z][0] + " - " + centroids[0][0] + ")^2 "
                + "+ " + "(" +samples[z][1] + " - " + centroids[0][1] + ")^2 ) = " + smew1);

            System.out.println("mu 2 = sqrt ( (" + samples[z][0] + " - " + centroids[1][0] + ")^2 "
                    + "+ " + "(" +samples[z][1] + " - " + centroids[1][1] + ")^2 ) = " + smew2);

            if (flag[z] == 1){
                System.out.println("assigned cluster is mu 1\n");
            }else{
                System.out.println("assigned cluster is mu 2\n");
            }


        }

        float newXCoordinate = 0;
        float newYCoordinate = 0;

//        System.out.println("\n\n\n\n\n\n\n\n\n\n\n" + newCentroid1.size() + "     " + newCentroid2.size());

        String equationx = "[ ";
        String equationy = "[ ";
        for (int k = 0; k < newCentroid1.size(); k++){
//            System.out.println(samples[(int)newCentroid1.get(k)][0]);
//            System.out.println(samples[(int)newCentroid1.get(k)][1]);
            if (k == 0){
                equationx = equationx + samples[(int)newCentroid1.get(k)][0];
                equationy = equationy + samples[(int)newCentroid1.get(k)][1];
            }else{
                equationx = equationx+ " + " + samples[(int)newCentroid1.get(k)][0];
                equationy = equationy+ " + " + samples[(int)newCentroid1.get(k)][1];
            }
            newXCoordinate = newXCoordinate + samples[(int)newCentroid1.get(k)][0];
            newYCoordinate = newYCoordinate + samples[(int)newCentroid1.get(k)][1];
        }



        newXCoordinate = newXCoordinate/newCentroid1.size();
        newYCoordinate = newYCoordinate/newCentroid1.size();

        equationx = equationx + " ] / " +newCentroid1.size() + "= " + newXCoordinate;
        equationy = equationy + " ] / " +newCentroid1.size() + "= " + newYCoordinate;
        System.out.println(equationx);
        System.out.println(equationy);

        System.out.print("mu1: (" + newXCoordinate + "," + newYCoordinate +")");

        if ((centroids[0][0] != newXCoordinate) || (centroids[0][1] != newYCoordinate)){
            System.out.println("  Centroid 1 moved");
        }else{
            System.out.println("  Centroid 1 is stable");
        }

        newXCoordinate = 0;
        newYCoordinate = 0;

        equationx = "[ ";
        equationy = "[ ";

        for (int l = 0; l <newCentroid2.size(); l++){

            if (l == 0){
                equationx = equationx + samples[(int)newCentroid2.get(l)][0];
                equationy = equationy + samples[(int)newCentroid2.get(l)][1];
            }else{
                equationx = equationx+ " + " + samples[(int)newCentroid2.get(l)][0];
                equationy = equationy+ " + " + samples[(int)newCentroid2.get(l)][1];
            }

            newXCoordinate = newXCoordinate + samples[(int)newCentroid2.get(l)][0];
            newYCoordinate = newYCoordinate + samples[(int)newCentroid2.get(l)][1];
        }

        newXCoordinate = newXCoordinate/newCentroid2.size();
        newYCoordinate = newYCoordinate/newCentroid2.size();

        equationx = equationx + " ] / " +newCentroid2.size() + "= " + newXCoordinate;
        equationy = equationy + " ] / " +newCentroid2.size() + "= " + newYCoordinate;
        System.out.println(equationx);
        System.out.println(equationy);

        System.out.print("mu2: (" + newXCoordinate + "," + newYCoordinate +")");

        if ((centroids[1][0] != newXCoordinate) || (centroids[1][1] != newYCoordinate)){
            System.out.println("  Centroid 2 moved");
        }else{
            System.out.println("  Centroid 2 is stable");
        }

        System.out.print("J = " + (j/nSamples));

    }
}
