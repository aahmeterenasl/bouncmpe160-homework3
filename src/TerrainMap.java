/**
 * Class containing regions and methods to set lakes and calculate score
 * @author Ahmet Eren Aslan, Student ID: 2021400126
 * @since Date: 04.05.2023
 */
import java.util.ArrayList;
public class TerrainMap {
    private Region[][] regions;// Array storing all the regions
    public final int ROW_NUM;// Number of rows
    public final int COLUMN_NUM;// Number of rows
    private int lastLakeIndex = -1;// Index of the last named lake

    /**
     * Constructor for the TerrainMap class
     * @param ROW_NUM Number of rows
     * @param COLUMN_NUM Number of rows
     */
    TerrainMap(int ROW_NUM, int COLUMN_NUM) {
        regions = new Region[ROW_NUM][COLUMN_NUM];
        this.ROW_NUM = ROW_NUM;
        this.COLUMN_NUM = COLUMN_NUM;
    }

    /**
     * Method adds the given region to the indicated location
     * @param currentRow Row index of the region
     * @param currentColumn Column index of the region
     * @param region The region wanted to be added to the map
     */
    public void addRegion(int currentRow, int currentColumn, Region region) {
        regions[currentRow][currentColumn] = region;
    }

    /**
     * Prints the map using the height of the regions and the row indexes and column names to their respected places
     */
    public void printTerrainMap() {
        for (int rowIndex = 0; rowIndex < ROW_NUM; rowIndex++) {
            System.out.printf("%3d", rowIndex);// prints row indexes with the wanted spaces between the elements
            for (int columnIndex = 0; columnIndex < COLUMN_NUM; columnIndex++) {
                System.out.printf("%3d", regions[rowIndex][columnIndex].getHeight());// prints region heights with the wanted spaces between the elements
            }
            System.out.println(" ");
        }
        System.out.print("   ");
        for (int colIndex = 0; colIndex < COLUMN_NUM; colIndex++) {
            System.out.printf("%3s", Utilities.numberToAlphabeticString(colIndex, "column"));// prints column names with the wanted spaces between the elements
        }
        System.out.println(" ");
    }

    /**
     * Prints the map using the lake name of the lakes, the height of the other regions and the row indexes and column names to their respected places
     */
    public void printMoney(){
        for (int rowIndex = 0; rowIndex < ROW_NUM; rowIndex++) {
            System.out.printf("%3d", rowIndex);
            for (int columnIndex = 0; columnIndex < COLUMN_NUM; columnIndex++) {
                Region currentRegion = regions[rowIndex][columnIndex];
                if(currentRegion.getLakeNum()==-1)
                    System.out.printf("%3d",currentRegion.getHeight());
                else
                    System.out.printf("%3s",Utilities.numberToAlphabeticString(currentRegion.getLakeNum(),"lake"));
            }
            System.out.println(" ");
        }
        System.out.print("   ");
        for (int colIndex = 0; colIndex < COLUMN_NUM; colIndex++) {
            System.out.printf("%3s", Utilities.numberToAlphabeticString(colIndex, "column"));
        }
        System.out.println(" ");
    }

    public void modificationTerrainMap(int currentRow, int currentColumn) {
        regions[currentRow][currentColumn].setHeight(regions[currentRow][currentColumn].getHeight() + 1);
    }

    public boolean canAddMoney(Region region,int e) {
        if(region.isEdge())
            return false;

        if(region.getMoneyVolume() == 0)
            return false;


        ArrayList<Region> neighbors = neighbors(region);
        region.setApplied(true);
        for (Region neigh: neighbors) {
            if(neigh.isApplied())
                continue;
            if (region.getHeight() + e > neigh.getHeight()) {
                if (!canAddMoney(neigh, region.getHeight() + e - neigh.getHeight())){
                    return false;
                }
            }
        }

        return true;
    }

    public void addMoneyApplier(){
        for (int rowIndex = 0; rowIndex < ROW_NUM; rowIndex++) {
            for (int columnIndex = 0; columnIndex < COLUMN_NUM; columnIndex++) {
                Region currentRegion = regions[rowIndex][columnIndex];
                int currentMoneyVolume = 1;

                while (canAddMoney(currentRegion,currentMoneyVolume)){
                    currentMoneyVolume++;
                    setAllApplied();
                }
                currentRegion.setMoneyVolume(--currentMoneyVolume);
                setAllApplied();
            }
        }
    }

    private void setAllApplied(){
        for (int i = 0; i < ROW_NUM; i++) {
            for (int j = 0; j < COLUMN_NUM; j++) {
                regions[i][j].setApplied(false);
            }
        }
    }

    private ArrayList<Region> neighbors(Region region) {
        int currentRow = region.getRow();
        int currentColumn = region.getColumn();
        ArrayList<Region> neighbors = new ArrayList<>();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++){
                if((i==0)&&(j==0)){
                    continue;
                }
                if((currentRow+i < 0) || (currentRow+i >= ROW_NUM) || (currentColumn+j < 0) || (currentColumn+j >= COLUMN_NUM))
                    continue;
                neighbors.add(regions[currentRow + i][currentColumn + j]);
            }
        }
        return neighbors;
    }

    public void lakeNumberAssigner(Region region) {
        if(region.getLakeNum() != -1)
            return;
        region.setLakeNum(lastLakeIndex);
        for (Region neigh: neighbors(region)){
            if(neigh.isLake())
                lakeNumberAssigner(neigh);
        }

    }

    public void allLakeNumberSetter(){
        for (int i = 0; i < ROW_NUM; i++) {
            for (int j = 0; j < COLUMN_NUM; j++) {
                if((regions[i][j].isLake()) && (regions[i][j].getLakeNum() == -1)){
                    lastLakeIndex++;
                    lakeNumberAssigner(regions[i][j]);
                }
            }
        }
    }


    public double score() {
        double score = 0;
        int[] lakeVolumes = new int[lastLakeIndex + 1];
        for (int i = 0; i < ROW_NUM; i++) {
            for (int j = 0; j < COLUMN_NUM; j++) {
                Region currentRegion = regions[i][j];
                if(currentRegion.isLake()){
                    lakeVolumes[currentRegion.getLakeNum()] += currentRegion.getMoneyVolume();
                }
            }
        }
        for(int k = 0; k < lastLakeIndex + 1; k++){
            score += Math.sqrt(lakeVolumes[k]);
        }
        return score;
    }

}