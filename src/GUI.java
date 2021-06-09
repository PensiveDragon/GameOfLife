import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener {

    int gap = 1;
    int cell_size = 10;
    int grid_rows;
    int grid_cols;
    JLabel label;
    JPanel panel = new JPanel();
    Grid theGrid;
    JButton button;
    Boolean running = true;
    JPanel gridPanel = new JPanel();
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



        button = new JButton();

        //button.setSize(100,50);
        button.addActionListener(this);
        //button.setBounds(50,50,100,50);



        //panel.setLayout(null);
        panel.add(button);

        createUIGrid(grid);

        panel.add(gridPanel);



        timer = new Timer(1000, new ActionListener() {

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
                label.setBackground(Color.WHITE);
                label.setOpaque(true);

                if (!running) {
                    button.setText("Start");
                }
                else {
                    button.setText("Stop");
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

        button.setEnabled(true);
        createUIGrid(grid);
        gridPanel.repaint();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==button) {
            if (running) {
                running = false;
                System.out.println("stopped");
                button.setEnabled(false);

            }
            else {
                running = true;
                System.out.println("started");
                button.setEnabled(false);
                checkRunning();
            }
        }
    }
}