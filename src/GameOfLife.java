public class GameOfLife {

    public static void main(String[] args) {

        System.out.println("Moo.");

        int col = 100, row = 25, iterations = 50;

        // Manual grid config - retired now

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

        // Visual grid config
        // Idea is to be able to draw a grid using . and x to symbolise alive and dead cells.
        // Method will read the strings input, determine the parameters of the grid (length of array / longest string).

        String[] strings = {
                "......................................",
                ".xx.....x.....xxx......x",
                ".xx.....x....xxx.......x",
                "...xx...x..............x",
                "...xx.......",
                "..........",
                "..........",
                "...x......",
                ".x...x....",
                "......x...",
                ".x....x...",
                "..xxxxx...",
                "........",
                "........",
                "..........",
        };

        // Two of the more complicated oscillators.
        String[] pentadecathlon = {
                "............",
                "............",
                "...............xxx...xxx..",
                "....xxx...................",
                ".....x.......x....x.x....x",
                ".....x.......x....x.x....x",
                "....xxx......x....x.x....x",
                "...............xxx...xxx..",
                "....xxx.......................",
                "....xxx........xxx...xxx..",
                ".............x....x.x....x",
                "....xxx......x....x.x....x",
                ".....x.......x....x.x....x",
                ".....x....................",
                "....xxx........xxx...xxx..",
                "...........",
                "...........",
                "...........",
        };

        // Maybe not 100%. Seems to create a block from the crashed glider that gets cleared from the next glider
        String[] gospersGliderGun = {
                ".............................................................",
                "..........................x...........",
                "..........................x.x.........",
                ".........xx..................xx",
                ".......x...x.................xx....xx",
                "......x.....x................xx....xx",
                ".xx..xx.x...x........x....x.x",
                ".xx...x.....x.........x...x",
                ".......x...x.....x..xxx",
                ".........xx",
                "..........",
                "..........",
                "..........",
                "..........",
                "..........",
                "..........",
                "..........",

        };

        //grid = grid.parseStringGridDesign(strings);
        //grid = grid.parseStringGridDesign(gospersGliderGun);
        grid = grid.parseStringGridDesign(pentadecathlon);

        System.out.println("Grid size (R|C): " + grid.grid.length + "|" + grid.grid[0].length);

        //grid.visualiseDisplayGrid(true);

        new GUI(grid);

        //simulate(grid, iterations, myGUI);

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
         - Output paused designs into a text file that can be opened into another instance of the program.
         - Boolean for looping grid world mode.
         - Clear map button.
         */
    }
}