import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class CsvStuff {
    /**
     * @param pairs - number of pairs of matrices we want the CSV to have
     * @param n - size of n we want
     * @param twoToTheN - 2^n. this is the size of the matrices in the CSV
     * @param csvLocation - where we want the file to be stored.
     * @throws IOException
     *
     * This function creates a CSV file with paris of 2^nx2^n matrices.
     */
    public static void createCSVMatricies(int pairs, int n, int twoToTheN, String csvLocation) throws IOException {
        FileWriter writer = new FileWriter(csvLocation);

        String[] header = {"n", "2^n", "number of pairs"}; //Creating first line of CSV
        String[] record1 = {String.valueOf(n), String.valueOf(twoToTheN), String.valueOf(pairs)}; //Second line of CSV
        String StringHeader = String.join(",", header);
        String StringRecord1 = String.join(",", record1);

        writer.write(StringHeader + "\n"); //writing first line to CSV
        writer.write(StringRecord1 + "\n"); ////writing second line to CSV

        String[] matrixLineSplit = new String[0];
        for (int i = 0; i < pairs; i++) { //We are doing this process for the amount of pairs wanted
            for(int x = 0; x < 2;x++) { //Do twice because we want pairs of matrices
                for (int k = 0; k < twoToTheN; k++) { //Do for the length of twoToTheN
                    String matrixLine = "";
                    for (int j = 0; j < twoToTheN; j++) {
                        matrixLine += (int) Math.floor(Math.random() * 9) + " "; //creating a string of random single digit values
                        matrixLineSplit = matrixLine.split(" "); //turning the string into an array of Strings.

                    }
                    String maxtrixLineJoined = String.join(",", matrixLineSplit); //joining the aray back.
                    writer.write(maxtrixLineJoined + "\n"); //writing this line to the CSV.
                }
                String[] emptyLineSplit = new String[1];
                if (x==0) {
                    emptyLineSplit[0] = " ";
                }
                else {
                    emptyLineSplit[0] = "~" + String.valueOf(i + 1);
                }
                /*for (int j = 0; j < twoToTheN; j++) {
                    String emptyLine = "";
                    emptyLine += ", ";
                    emptyLineSplit = emptyLine.split(" ");
                }*/
                writer.write(emptyLineSplit[0] + "\n"); //Wwriting either an empty line if it's the middle of a pair, or an indicator of what pair we are on.
            }


        }

        writer.close();


    }

    /**
     *
     * @param file - file location in form of string
     * This function is reading in the file, creating the pairs of matrices, and then multiplying them by a
     *  specified algorithm (either strasses with cutoff, strassens with no cutoff, or brute force)
     */
    public static void readingDataAndCallingMultiply(String file) {
        int [][] result = new int[0][];
        int n;
        int twoToTheN;
        int numOfPairs;
        try {

            //File fileTobeRead = new File("/Users/socce2/Downloads/example - single martix pair.csv");
            File fileTobeRead = new File(file);
            Scanner scanner = new Scanner(fileTobeRead);
            scanner.useDelimiter(", | \n | ");
            scanner.nextLine(); //Skips first line
            String paramters = scanner.nextLine(); //gets second line
            String[] paramterList = paramters.toString().split(","); //splits up 2n line
            int[] ints = Arrays.stream(paramterList).mapToInt(Integer::parseInt).toArray(); //converts Strings to ints
            n = ints[0];
            twoToTheN = ints[1];
            numOfPairs = ints[2];

            for (int x = 0; x < numOfPairs; x++) {

                String[][] matrixOfStrings = new String[twoToTheN][twoToTheN];
                String[] row;
                for (int i = 0; i < twoToTheN; i++) { //reading in first matrix or pair as Strings
                    row = scanner.nextLine().split(",");
                    matrixOfStrings[i] = row;
                }

                int[][] matrix = new int[twoToTheN][twoToTheN];

                for (int i = 0; i < twoToTheN; i++) {
                    for (int j = 0; j < twoToTheN; j++) {
                        matrix[i][j] = Integer.parseInt(matrixOfStrings[i][j]); //turning matrix of strings into matrix of ints
                    }
                }

                scanner.nextLine(); //Skips line full of commas

                String[][] matrixOfStrings2 = new String[twoToTheN][twoToTheN];
                String[] row2 = new String[0];
                for (int i = 0; i < twoToTheN; i++) { //reading in second matrix of pair as Strings
                    row2 = scanner.nextLine().split(",");
                    matrixOfStrings2[i] = row2;
                }

                int[][] matrix2 = new int[twoToTheN][twoToTheN];

                for (int i = 0; i < twoToTheN; i++) {
                    for (int j = 0; j < twoToTheN; j++) {
                        matrix2[i][j] = Integer.parseInt(matrixOfStrings2[i][j]); //turning matrix of strings into matrix of ints
                    }
                }

                double startTime = System.nanoTime(); //timing the called algorithm

                //This is where we are calling a specified algorithm and timing how long it takes to execute.

                int[][] Result = MatrixMultiplication.strassensWithCutoff(matrix, matrix2, 32);
                //int[][] Result = MatrixMultiplication.strassensNoCutoff(matrix, matrix2);
                //int[][] Result = MatrixMultiplication.bruteForce(matrix, matrix2);

                double endTime = System.nanoTime();
                double duration = ((endTime - startTime)/1000000);
                System.out.println(duration);


/*                for (int j = 0; j<Result.length; j++) { //printing out the result matrix
                    for(int k = 0; k<Result.length; k++) {
                        System.out.print(Result[j][k] + " ");
                        if(k == Result.length - 1) {
                            System.out.println();
                        }
                    }
                }*/

                scanner.nextLine(); //Skips over line that looks like ~1

                //System.out.println(); //Separating printed matrices
            }

        } catch(FileNotFoundException e){
            e.printStackTrace();
        }

    }
}
