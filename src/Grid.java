public class Grid {

    int x, y;
    int[][] grid;


    Grid(int x, int y){
        int[][] grid = new int[x][y];
        this.x = x;
        this.y = y;
        this.grid = grid;
    }

    private static int updateCellStatus (int neighbours, int current_status) {
        //System.out.println("Neighbours: " + neighbours + ". Current Status: " + current_status);
        int alive = 0;

        if ((current_status == 1) & ((neighbours == 2) | (neighbours == 3)))
            alive = 1;
        else if ((current_status == 0) & neighbours == 3)
            alive = 1;
        /*
        else if (current_status == 1)
            alive = 0;
        */
        return alive;
    }

    private int sumLivingNeighbours(int x, int y) {
        int sum = 0;

        //System.out.println("Checking: " + x + "|" + y);
        for (int a = -1; a < 2; a++) {
            //System.out.println("a = " + a);
            for (int b = -1; b < 2; b++) {
                //System.out.println("b = " + b);
                if ((a != 0) | (b != 0)) {
                    if (((x + a >= 0) && (y + b >= 0) && (x + a < this.x) && (y + b < this.y))) {
                        sum += grid[x + a][y + b];
                    }
                }
            }
            //System.out.println("sum = " + sum + " so far...");
        }

        return sum;
    }

    public Grid simulateTick() {

        Grid output = new Grid(x, y);

        // iterate 2d array, for each number:
        for (int row = 0; row < x; row++) {
            for (int col = 0; col < y; col++) {
                //System.out.println("Co'ord: " + row + "|" + col);
                // check & sum adjacent numbers (x,y +/-1)
                //System.out.println("Sum of cells around " + row + "|" + col + " = " + sumLivingNeighbours(input, row, col));
                // depending on sum value, assign 1 or 0 to cell co'ord in output array.
                output.grid[row][col] = updateCellStatus(this.sumLivingNeighbours(row, col), grid[row][col]);
                //System.out.println(row + "|" + col + " is now " + output.grid[row][col]);
            }
        }

        return output;
    }

    public void displayGrid() {
        for (int[] line : grid) {
            for (int cell : line) {
                System.out.print(cell + " ");

            }
            System.out.println();
        }
        System.out.println();
    }

    public void visualiseDisplayGrid(boolean borders) {

        if (!borders) {

            for (int[] line : grid) {
                for (int cell : line) {
                    if (cell == 0)
                        System.out.print("  ");
                    else
                        System.out.print("O ");
                }
                System.out.println();
            }
            System.out.println();
        }

        if (borders) {

            for (int[] line : grid) {
                for (int cell : line) {
                    if (cell == 0)
                        System.out.print("  ");
                    else
                        System.out.print("O ");
                }
                System.out.println();
            }
            for (int i = 0; i < grid[0].length; i++)
                System.out.print("+ ");
            System.out.println();
        }
    }

    public Grid parseStringGridDesign(String[] gridDesign) {

        int rows, cols = 0;

        rows = gridDesign.length;
        for (String string : gridDesign)
            if (string.length() > cols)
                cols = string.length();

        Grid grid = new Grid(rows, cols);

        int row = 0;

        for (String string : gridDesign) {

            for (int col = 0; col < string.length(); col++) {
                //System.out.println(string.charAt(col));
                if (string.charAt(col)!='.')
                    grid.grid[row][col] = 1;
            }

            row++;
        }

        return grid;
    }

    public void encodeGridSeed() {
        // some crazy way of encoding a ?x? sized grid of 0s and 1s into a more manageable format.
        // - some kind of combination of nums and chars to indicate gaps between numbers?
    }

    public void decodeGridSeed() {
        // some crazy way of decoding a ?x? sized grid of 0s and 1s from a more manageable format.
    }

}
