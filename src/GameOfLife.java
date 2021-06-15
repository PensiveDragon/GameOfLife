public class GameOfLife {

    public static void main(String[] args) {

        System.out.println("Moo.");

        int col = 100, row = 25;

        // Manual grid config - retired now

        Grid grid = new Grid(row,col);

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
                ".....................................................................",
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
                "..........",
                "..........",
                "..........",
                "..........",
                "..........",
                "..........",

        };

        String[] introScreen = {
                "..................................................",
                ".....xxx....xxx....x.x...xxxxx.....xxx...xxxxx....",
                "....x......x...x..x.x.x..x........x...x..x........",
                "....x..xx..xxxxx..x.x.x..xxx......x...x..xxx......",
                "....x...x..x...x..x...x..x........x...x..x........",
                ".....xxx...x...x..x...x..xxxxx.....xxx...x........",
                "..................................................",
                "..........x......xxxxx..xxxxx..xxxxx..............",
                "..........x........x....x......x..................",
                "..........x........x....xxx....xxx................",
                "..........x........x....x......x..................",
                "..........xxxxx..xxxxx..x......xxxxx..............",
                "..................................................",
        };

        //grid = grid.parseStringGridDesign(strings);
        //grid = grid.parseStringGridDesign(gospersGliderGun);
        //grid = grid.parseStringGridDesign(pentadecathlon);
        grid = grid.parseStringGridDesign(introScreen);

        System.out.println("Grid size (R|C): " + grid.grid.length + "|" + grid.grid[0].length);

        //grid.visualiseDisplayGrid(true);

        new GUI(grid);

        //simulate(grid, iterations, myGUI);

        /*
        Starting Features:
         X- Takes a starting seed and runs a set number of simulations of the rules, displaying each updated iteration.
         X -> Could be overloaded to accept a specific value of iterations to run. #replaced

         X- Life algorithm, upon checking a cell and its neighbours:
         X -> Any live cell with <2 live neighbours dies.
         X -> Any live cell with 2 or 3 live neighbours survives.
         X -> Any live cell with >3 live neighbours dies.
         X -> Any dead cell with 3 live neighbours becomes alive.
         X- All births and deaths apply simultaneously, creating the new seed, which will have the algorithm run on it again.

        Advanced Features:
        X - After simulation is complete, allow it to be run for a specified number more iterations #replaced
        X - Allow the ability to create a custom seed in the program, turning a grid of cells on/off
         - Button to output current design, when paused, into a text file.
         - Button to make text file openable into another instance of the program.
         - Boolean for looping grid world mode.
        X - Clear map when paused Button.
         */
    }
}