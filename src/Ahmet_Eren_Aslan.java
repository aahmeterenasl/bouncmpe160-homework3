/**
 * Program read the input file, takes the user input, modifies the map using inputs and prints the final score
 * @author Ahmet Eren Aslan, Student ID: 2021400126
 * @since Date: 04.05.2023
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Ahmet_Eren_Aslan {
    /**
     * Reads the input file and calling other methods modifies the map and prints the final score
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {

        String fileName = "input.txt";// Name of the file
        File file = new File(fileName);// File object

        // Checks if the file exists and exits if it is not
        if(!file.exists()){
            System.out.printf("%s can not be found.", fileName);
            System.exit(1);
        }

        Scanner inputFile = new Scanner(file);// Opens the scanner object

        int columnNum = inputFile.nextInt();// Number of columns that read from the input file
        int rowNum = inputFile.nextInt();// Number of rows that read from the input file


        TerrainMap terrainMap = new TerrainMap(rowNum,columnNum);// Creates the terrainMap object
        Region.setMaxColumn(columnNum);// Sets the number of columns for terrainMap
        Region.setMaxRow(rowNum);// Sets the number of rows for terrainMap

        // Adds the region objects to the terrainMap Object using the input file
        for (int currentRowIndex = 0; currentRowIndex < rowNum; currentRowIndex++) {
            for (int currentColumnIndex = 0; currentColumnIndex < columnNum; currentColumnIndex++) {
                // Uses anonymous objects
                terrainMap.addRegion(currentRowIndex,currentColumnIndex,new Region(inputFile.nextInt(),currentRowIndex,currentColumnIndex));
            }
        }
        inputFile.close();// Closes the scanner object

        terrainMap.printTerrainMap();// Prints the map, using only the heights of the regions

       modification(terrainMap);// Modifies the map 10 times using user input

        terrainMap.addMoneyApplier();// Adds money lakes to the map
        terrainMap.allLakeNumberSetter();// Assigns numbers to the lakes
        terrainMap.printMoney();// Prints the map, uses the lake name if it exists, otherwise uses the height

        System.out.printf("Final score: %.2f",terrainMap.score());// Prints the final score with 2 significant figure after decimal

    }

    /**
     * Modifies the given terrainMap object ten times, by taking input from the console
     * @param terrainMap TerrainMap object wanted to be modified
     */
    public static void modification(TerrainMap terrainMap){
        int rowNum = terrainMap.ROW_NUM;// Number of rows of terrainMap
        int columnNum = terrainMap.COLUMN_NUM;// Number of columns of terrainMap
        Scanner userInput = new Scanner(System.in);// Opens the scanner object

        //Modifies the map 10 times
        for (int modificationNum = 1; modificationNum <= 10; modificationNum++) {
            System.out.printf("Add stone %d / 10 to coordinate: ",modificationNum);// Prints the number of modification
            String coordinate = userInput.next();// Coordinate of the region that will be modified
            String columnName = "";// Name of the column for the target region
            int rowIndex = -1;// Index of the row for the target region

            // Separating the column name and row index from the taken user input
            for (int coordinateIndex = 0; coordinateIndex < coordinate.length(); coordinateIndex++) {
                if(((int)coordinate.charAt(coordinateIndex) >= (int)'0') && ((int)coordinate.charAt(coordinateIndex) <= (int)'9')){
                    columnName = coordinate.substring(0,coordinateIndex);
                    rowIndex = Integer.parseInt(coordinate.substring(coordinateIndex));
                    break;
                }
            }

            int columnIndex = Utilities.alphabeticStringToNumber(columnName,"column");// Index of the column for the target region

            // Checks for the coordinate, and warns the user if it is wrong
            if((rowIndex < 0) || (rowIndex >= rowNum) || (columnIndex < 0) || (columnIndex >= columnNum)){
                modificationNum--;
                System.out.println("Wrong input!");
                continue;
            }

            terrainMap.modificationTerrainMap(rowIndex,columnIndex);// Modifies the target region
            // Prints the map, using only height
            terrainMap.printTerrainMap();
            System.out.println("---------------");
        }
        userInput.close();// Closes the scanner object
    }
}
