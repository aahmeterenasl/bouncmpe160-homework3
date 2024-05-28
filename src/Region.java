/**
 * Class containing the information about one coordinate of the map
 * @author Ahmet Eren Aslan, Student ID: 2021400126
 * @since Date: 04.05.2023
 */
public class Region {
    private int height;// Height of the region
    private int row;// Row index of the region
    private int column;// Row index of the region
    private int moneyVolume;// Volume of the money in the region
    private boolean applied;// Boolean data field indicating whether the region is used before in a method in TerrainMap class
    private static int maxRow;// Number of rows
    private static int maxColumn;// Number of columns
    private int lakeNum;// Index of the lake containing the region, if its -1 the region is not a lake

    /**
     * Constructor for the region class
     * @param height Height of the region
     * @param row Row index of the region
     * @param column Row index of the region
     */
    Region(int height,int row, int column){
        this.height = height;
        this.row = row;
        this.column = column;
        moneyVolume = -1;// Assigns the moneyVolume to -1 in the creation
        applied = false;// Assigns the applied to false in the creation
        lakeNum = -1;// Assigns the lakeNum to -1 in the creation
    }

    /**
     * Returns the height of the region
     * @return The height of the region
     */
    public int getHeight() {
        return height;
    }

    /**
     * Changes the height of the region
     * @param height The wanted height of the region
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Returns the row index of the region
     * @return The row index of the region
     */
    public int getRow() {
        return row;
    }

    /**
     * Returns the column index of the region
     * @return The column index of the region
     */
    public int getColumn() {
        return column;
    }
    /**
     * Returns the boolean value indicating the region used before
     * @return The boolean value indicating the region used before
     */
    public boolean isApplied() {
        return applied;
    }

    /**
     * Changes the boolean value indicating the region used before
     * @param applied the boolean value indicating the region used before
     */
    public void setApplied(boolean applied) {
        this.applied = applied;
    }

    /**
     * Returns the volume of the money in the region
     * @return The volume of the money in the region
     */
    public int getMoneyVolume() {
        return moneyVolume;
    }

    /**
     * Changes the volume of the money in the region
     * @param moneyVolume the wanted volume of the money in the region
     */
    public void setMoneyVolume(int moneyVolume) {
        this.moneyVolume = moneyVolume;
    }

    /**
     * Changes the number of rows
     * @param maxRow The number of rows
     */
    public static void setMaxRow(int maxRow) {
        Region.maxRow = maxRow;
    }

    /**
     * Changes the number of column
     * @param maxColumn The number of column
     */
    public static void setMaxColumn(int maxColumn) {
        Region.maxColumn = maxColumn;
    }

    /**
     * Returns the index of the lake containing the region
     * @return The index of the lake containing the region
     */
    public int getLakeNum() {
        return lakeNum;
    }

    /**
     * Changes the index of the lake containing the region
     * @param lakeNum The wanted index of the lake containing the region
     */
    public void setLakeNum(int lakeNum) {
        this.lakeNum = lakeNum;
    }

    /**
     * Checks and returns if the region is on the edge
     * @return boolean value indicating if the region is on the edge
     */
    public boolean isEdge(){
        if ((row == 0) || (column == 0) || (row == maxRow - 1) || (column == maxColumn - 1)) {
            return true;
        }
        return false;
    }

    /**
     * Checks and returns if the region is lake
     * @return boolean value indicating if the region is lake
     */
    public boolean isLake() {
        if(this.moneyVolume > 0)
            return true;
        return false;
    }

}
