import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUI extends JFrame implements ActionListener {

    int gap = 1;
    int cell_size = 10;
    int grid_rows;
    int grid_cols;
    JLabel label;
    JPanel panel = new JPanel();
    Grid theGrid;
    JButton pauseButton;
    JButton clearButton;
    Boolean running = true;
    JPanel gridPanel = new JPanel();
    JPanel menuPanel = new JPanel();
    Timer timer;


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
        //this.getContentPane().setLayout(new GridLayout(grid_rows, grid_cols, gap, gap));

        //-- consider using box layout

        //menuPanel.setBounds(0,0,100,50);
        menuPanel.setLayout(new GridLayout(2,1,2,2));
        //gridPanel.setBounds(120,120,100,100);

        pauseButton = new JButton();
        pauseButton.addActionListener(this);

        clearButton = new JButton();
        clearButton.addActionListener(this);
        clearButton.setText("Clear");


        menuPanel.add(pauseButton);
        menuPanel.add(clearButton);
        //panel.setLayout(new GridLayout(1,2,2,2));

        panel.add(menuPanel);

        createUIGrid(grid);

        panel.add(gridPanel);

        timer = new Timer(250, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("I just got called by the timer!!!1");

                theGrid = theGrid.simulateTick();
                updateGUI(theGrid);
                //theGrid.visualiseDisplayGrid(true);
                checkRunning();
            }
        });

        this.add(panel);

        timer.start();

        this.pack();
        this.validate();
        this.setVisible(true);
    }

    private void checkRunning() {
        if (!running)
            timer.stop();
        else
            timer.start();
    }

    private void createUIGrid(Grid grid) {

        for (int row = 0; row < grid_rows; row++) {
            for (int col = 0; col < grid_cols; col++) {

                label = new JLabel();
                label.addMouseListener(new GridCellMouseListener(row, col, label));
                label.setBackground(Color.WHITE);
                label.setOpaque(true);

                if (!running) {
                    pauseButton.setText("Start");
                    clearButton.setEnabled(true);
                }
                else {
                    pauseButton.setText("Stop");
                    clearButton.setEnabled(false);
                }

                //label.setText("" + ++count);
                //label.setText("o");

                if (grid.grid[row][col] == 1)
                    label.setBackground(Color.BLACK);

                label.setPreferredSize(new Dimension(cell_size, cell_size));
                label.setBounds(col * (cell_size+gap), row * (cell_size+gap), cell_size, cell_size);
                gridPanel.add(label);
                gridPanel.setLayout(new GridLayout(grid_rows, grid_cols, gap, gap));

                //this.getContentPane().add(gridPanel);
            }
        }
    }

    public void updateGUI(Grid grid) {

        //System.out.println("Update!");
        grid.visualiseDisplayGrid(true);

        gridPanel.removeAll();

        //gridPanel.setBackground(Color.GRAY);

        pauseButton.setEnabled(true);
        createUIGrid(grid);
        gridPanel.repaint();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()== pauseButton) {
            if (running) {
                running = false;
                System.out.println("stopped");
                pauseButton.setEnabled(false);

            }
            else {
                running = true;
                System.out.println("started");
                pauseButton.setEnabled(false);
                checkRunning();
            }
        }

        if (e.getSource()== clearButton) {
            if (!running) {
                theGrid.clearGrid();
                updateGUI(theGrid);
                gridPanel.repaint();
            }
        }
    }


    private void toggleCell (int row, int col, JLabel label) {
        System.out.println("ToggleCell called at: " + row + "|" + col);

        // update array & update colour of cell
        if (theGrid.grid[row][col] == 1) {
            theGrid.grid[row][col] = 0;
            label.setBackground(Color.white);
        } else {
            theGrid.grid[row][col] = 1;
            label.setBackground(Color.black);
        }
    }

    private class GridCellMouseListener extends MouseAdapter {
        private final int row;
        private final int col;
        private final JLabel label;

        public GridCellMouseListener(int row, int col, JLabel label) {
            this.row = row;
            this.col = col;
            this.label = label;
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            System.out.println("Clicked " + e);
            System.out.println("Clicked on: " + row + "|" + col);
            if (!running) {
                toggleCell(row, col, label);
            }
        }
        //potentially more listeners
    }
}