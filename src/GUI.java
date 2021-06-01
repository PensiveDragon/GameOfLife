import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

    int gap = 1;
    int cell_size = 20;
    int grid_rows;
    int grid_cols;
    JLabel label;
    JPanel panel;

    public GUI(Grid grid) {

        grid_rows = grid.x;
        grid_cols = grid.y;

        this.setTitle("Game of Life");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(grid_cols * (gap + cell_size), grid_rows * (gap + cell_size));
        this.setResizable(true);


        this.getContentPane().setBackground(Color.GRAY);


        this.setLayout(new GridLayout(grid_rows, grid_cols, gap, gap));

        int count = 0;

        for (int row = 0; row < grid_rows; row++) {
            for (int col = 0; col < grid_cols; col++) {

                label = new JLabel();
                label.setBackground(Color.WHITE);
                label.setOpaque(true);

                //label.setText("" + ++count);
                //label.setText("o");

                if (grid.grid[row][col] == 1)
                    label.setBackground(Color.BLACK);

                label.setBounds(col * (cell_size+gap), row * (cell_size+gap), cell_size, cell_size);
                this.add(label);


            }
        }
        this.setVisible(true);
        //this.pack();


    }

    public void updateGUI(Grid grid) {

        //System.out.println("Update!");
        grid.clearDisplayGrid(true);

        this.removeAll();

        this.getContentPane().setBackground(Color.GRAY);

        for (int row = 0; row < grid_rows; row++) {
            for (int col = 0; col < grid_cols; col++) {

                label.removeAll();
                label.setBackground(Color.WHITE);
                label.setOpaque(true);

                //label.setText("" + ++count);
                //label.setText("o");

                if (grid.grid[row][col] == 1)
                    label.setBackground(Color.BLACK);

                label.setBounds(col * (cell_size+gap), row * (cell_size+gap), cell_size, cell_size);
                this.add(label);


            }
        }

        this.setVisible(true);
        this.revalidate();
    }
}