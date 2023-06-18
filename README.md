Project Description: 
- This program can take in a set of pairs of matrices and multiply each pair together using a specified algirithm of either, Strassens Algrithm with no cutoff, Strassens Algirithm with a specified cuttoff, or brute force. This program can also create csv files containing a selected number of pairs of matrices of a selected size. The matricies are filled with random single digit integers. 

Purpose of project: 
- This was a programming assignment done for my algorithms class. 

How to run:
1. Create java project with given .java files
2. Download example CSV files provided that contain a specified number of pairs of matrices. 
3. Go to Main class and change the file path to where you downloaded the CSV files when calling CsvStuff.readingDataAndCallingMultiply("Specified path");
   4. You could also create new CSV files using CsvStuff.createCSVMatricies(pairs, n, twoToTheN,"Path you want the CSV file to land");
5. You should be able to run Main now and everything should work. 


To change what algorithm you are using:
1. Go to line 128 of CsvStuff.java and comment out the method calls of the algorithms you don't want and keep the one you do want. 
   2. Your options are "bruteForce", "strassensWithCutoff", and "strassensNoCutoff".

- Note: I provided a few csv files if you would like to test with them (I used the 1024x1024 with testing as you will see in my Excel file)
  - test0.csv is 20 pairs of 256x256 single digit matrices
  - test0.csv is 20 pairs of 512x512 single digit matrices
  - test3.csv is 20 pairs of 1024x1024 single digit matrices

    
