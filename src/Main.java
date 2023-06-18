import java.io.IOException;
public class Main {
    public static void main(String[] args) throws IOException {

        //Function calls to create marices

        //CsvStuff.createCSVMatricies(20, 8, 256,"/Users/socce2/Downloads/test0.csv");
        //CsvStuff.createCSVMatricies(20, 9, 512,"/Users/socce2/Downloads/test1.csv");
        //CsvStuff.createCSVMatricies(20, 11, 2049,"/Users/socce2/Downloads/test3.csv");
        //CsvStuff.createCSVMatricies(20, 10, 1024,"/Users/socce2/Downloads/test2.csv");
        //CsvStuff.createCSVMatricies(20, 12, 4096,"/Users/socce2/Downloads/test4.csv");

        /***
         Function calls to read in matrices from CSV and multiply the pairs. The time it takes to
         fully complete each matrix multiplication will be printed in the console.
         */
        //CsvStuff.readingDataAndCallingMultiply("/Users/socce2/Downloads/example - 20 pairs (1).csv");
        CsvStuff.readingDataAndCallingMultiply("/Users/socce2/Downloads/test2.csv");


    }
}