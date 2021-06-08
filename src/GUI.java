import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

    int gap = 1;
    int cell_size = 10;
    int grid_rows;
    int grid_cols;
    JLabel label;
    JPanel panel;
    Grid theGrid;

    public GUI(Grid grid) {

        theGrid = grid;
        grid_rows = grid.x;
        grid_cols = grid.y;

        this.setTitle("Game of Life");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setSize(grid_cols * (gap + cell_size), grid_rows * (gap + cell_size));
        this.setResizable(false);

        //##
        this.getContentPane().setBackground(Color.GRAY);

        //##
        this.getContentPane().setLayout(new GridLayout(grid_rows, grid_cols, gap, gap));

        //int count = 0;

        createUIGrid(grid);

        Timer timer = new Timer(250, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("I just got called by the timer!!!1");

                theGrid = theGrid.simulateTick();
                updateGUI(theGrid);
                //theGrid.visualiseDisplayGrid(true);

            }
        });
        timer.start();

        this.pack();
        this.validate();
        this.setVisible(true);


    }

    private void createUIGrid(Grid grid) {
        for (int row = 0; row < grid_rows; row++) {
            for (int col = 0; col < grid_cols; col++) {

                label = new JLabel();
                label.setBackground(Color.WHITE);
                label.setOpaque(true);

                //label.setText("" + ++count);
                //label.setText("o");

                if (grid.grid[row][col] == 1)
                    label.setBackground(Color.BLACK);

                label.setPreferredSize(new Dimension(cell_size, cell_size));
                label.setBounds(col * (cell_size+gap), row * (cell_size+gap), cell_size, cell_size);
                this.getContentPane().add(label);


            }
        }
    }


    public void updateGUI(Grid grid) {

        //System.out.println("Update!");
        //grid.visualiseDisplayGrid(true);

        this.getContentPane().removeAll();

        this.getContentPane().setBackground(Color.GRAY);

        createUIGrid(grid);
        this.repaint();
    }
}