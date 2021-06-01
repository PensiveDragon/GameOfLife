public class GameOfLife {

    private static void simulate(Grid grid, int iterations, GUI myGUI) {

        for (int i = 0; i < iterations; i++) {
            //grid.clearDisplayGrid(true);
            //myGUI.updateGUI(grid);
            grid = simulateTick(grid);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Main Thread Interrupted");
            }
        }
    }

    private static Grid simulateTick(Grid input) {

        Grid output = new Grid(input.x, input.y);

        // iterate 2d array, for each number:
        for (int row = 0; row < input.x; row++) {
            for (int col = 0; col < input.y; col++) {
                //System.out.println("Co'ord: " + row + "|" + col);
                // check & sum adjacent numbers (x,y +/-1)
                //System.out.println("Sum of cells around " + row + "|" + col + " = " + sumAdjacent(input, row, col));
                // depending on sum value, assign 1 or 0 to cell co'ord in output array.
                output.grid[row][col] = updateCellStatus(sumAdjacent(input, row, col), input.grid[row][col]);
                //System.out.println(row + "|" + col + " is now " + output.grid[row][col]);
            }
        }

        return output;
    }

    private static int sumAdjacent(Grid input, int x, int y) {
        int sum = 0;

        //System.out.println("Checking: " + x + "|" + y);
        for (int a = -1; a < 2; a++) {
            //System.out.println("a = " + a);
            for (int b = -1; b < 2; b++) {
                //System.out.println("b = " + b);
                if ((a != 0) | (b != 0)) {
                    if (((x + a >= 0) && (y + b >= 0) && (x + a < input.x) && (y + b < input.y))) {
                        sum += input.grid[x + a][y + b];
                    }
                }
            }
            //System.out.println("sum = " + sum + " so far...");
        }

        return sum;
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

    public static void main(String[] args) {

        System.out.println("Moo.");

        int col = 50, row = 13, iterations = 50;

        Grid grid = new Grid(row,col);

        grid.grid[0][1] = 1;
        grid.grid[0][2] = 1;
        grid.grid[1][1] = 1;
        grid.grid[1][2] = 1;
        grid.grid[2][3] = 1;
        grid.grid[2][4] = 1;
        grid.grid[3][3] = 1;
        grid.grid[3][4] = 1;

        grid.grid[0][8] = 1;
        grid.grid[1][8] = 1;
        grid.grid[2][8] = 1;

        grid.grid[6][1] = 1;
        grid.grid[6][4] = 1;
        grid.grid[7][5] = 1;
        grid.grid[8][1] = 1;
        grid.grid[8][5] = 1;
        grid.grid[9][2] = 1;
        grid.grid[9][3] = 1;
        grid.grid[9][4] = 1;
        grid.grid[9][5] = 1;

        System.out.println("Grid size (R|C): " + grid.grid.length + "|" + grid.grid[0].length);

        GUI myGUI = new GUI(grid);

        simulate(grid, iterations, myGUI);


        /*
        Starting Features:
         - Takes a starting seed and runs a set number of simulations of the rules, displaying each updated iteration.
          -> Could be overloaded to accept a specific value of iterations to run.

         - Life algorithm, upon checking a cell and its neighbours:
          -> Any live cell with <2 live neighbours dies.
          -> Any live cell with 2 or 3 live neighbours survives.
          -> Any live cell with >3 live neighbours dies.
          -> Any dead cell with 3 live neighbours becomes alive.
         - All births and deaths apply simultaneously, creating the new seed, which will have the algorithm run on it again.

        Advanced Features:
         - After simulation is complete, allow it to be run for a specified number more iterations
         - Allow the ability to create a custom seed in the program, turning a grid of cells on/off
         */
    }
}