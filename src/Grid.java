public class Grid {

    int x, y;
    int[][] grid;


    Grid(int x, int y){
        int[][] grid = new int[x][y];
        this.x = x;
        this.y = y;
        this.grid = grid;
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

    public void encodeGridSeed() {
        // some crazy way of encoding a ?x? sized grid of 0s and 1s into a more manageable format.
        // - some kind of combination of nums and chars to indicate gaps between numbers?
    }

    public void decodeGridSeed() {
        // some crazy way of decoding a ?x? sized grid of 0s and 1s from a more manageable format.
    }

}
