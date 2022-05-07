import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;

/**
 *
 * @author Pratz
 */
public class Minesweeper {

    /*
    1 to 8 = No. of bombs...
    0 = no bomb & unopened :) 
    -1 = BOMB!!!!!!!!!!
    -2 opened but no bomb
     */
    JFrame frame = new JFrame("MINESWEEPER");//making a JFrame
    JButton reset = new JButton("RESET");//creating a reset button
    JButton exit = new JButton("EXIT");//creating an exit button
    final int hei = 9, wid = 9, setBombs = 10;
    JToggleButton[][] blocksButton = new JToggleButton[hei][wid];//creating a reset button
    int[][] blocks = new int[hei][wid];//
    boolean fMove, canPlay;
    ImageIcon mine = new ImageIcon("src\\\\Mine.png");   // bomb image
    ImageIcon fIcon = new ImageIcon("src\\\\smile.jpg"); //frame Image
    String arial = "Arial";
    Container contain = new Container(); //creating a container
    int close = javax.swing.WindowConstants.EXIT_ON_CLOSE;

    public Minesweeper() {

        frame.setSize(800, 800);   //setting size of thr frame
        reset.setBackground(Color.green);
        exit.setBackground(Color.green);
        reset.setForeground(Color.WHITE);
        exit.setForeground(Color.red);
        reset.setFont(new Font(arial, Font.ITALIC, 18));//setting font
        exit.setFont(new Font(arial, Font.ITALIC, 18));//setting font
        frame.setLayout(new BorderLayout()); //setting layout of the frame
        frame.add(reset, BorderLayout.NORTH);// adding reset button to the 
        frame.add(exit, BorderLayout.SOUTH);// adding reset button to the frame
        reset.addActionListener(listen);     //creating an action listener for the button
        exit.addActionListener(listen);     //creating an action listener for the button
        contain.setLayout(new GridLayout(9, 9)); //dividing the container in a grid of 9 by 9
        for (int i = 0; i < hei; i++) {
            for (int j = 0; j < wid; j++) {
                blocksButton[i][j] = new JToggleButton();     // generating an array of toggle buttons
                blocksButton[i][j].addActionListener(listen); //listening to action of the user
                contain.add(blocksButton[i][j]);              // adding toggle buttons to the container
            }
        }

        frame.add(contain, BorderLayout.CENTER);         // adding container to the frame
        frame.setDefaultCloseOperation(close);
        fMove = false;                                  //first move
        canPlay = true;
        frame.setIconImage(fIcon.getImage());           //setting JFrame's Icon
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Minesweeper();
    }

    private void boom(int y, int x) {                // generating bombs randomly after the first move
        for (int b = 1; b <= setBombs; b++) {
            int i, j;
            do {
                i = (int) (Math.random() * (wid - .01));
                j = (int) (Math.random() * (hei - .01));
            } while (blocks[i][j] == -1 || (j == x && i == y));        //no multiple mines on same toggle button and 1st selected toggle button
            blocks[i][j] = -1;
        }
    }

    private void reveal() {                               // revealing th toggle button info based on which kind of toggle button was selected
        for (int i = 0; i < hei; i++) {
            for (int j = 0; j < wid; j++) {
                if (blocks[i][j] == 0) {
                    blocksButton[i][j].setText("");
                    blocksButton[i][j].setSelected(false);
                }
                if (blocks[i][j] == -2) {
                    blocksButton[i][j].setText("");
                    blocksButton[i][j].setSelected(true);
                }
                if (blocks[i][j] > 0) {

                    blocksButton[i][j].setText("" + blocks[i][j]);
                    blocksButton[i][j].setFont(new Font(arial, Font.PLAIN, 40));
                    blocksButton[i][j].setSelected(true);
                }
                if (!canPlay && blocks[i][j] == -1) {
                    blocksButton[i][j].setSelected(true);
                }
            }
        }

    }

    private void open(int y, int x) {                               //to check set values according to the toggle button clicked
        if (y < 0 || x < 0 || x > wid - 1 || y > hei - 1 || blocks[y][x] != 0) {
            return;
        }
        int bombs = 0;
        for (int i = y - 1; i <= y + 1; i++) {                         //Checking for bombs around the clicked Toggle button
            for (int j = x - 1; j <= x + 1; j++) {
                if (!(j < 0 || i < 0 || j > wid - 1 || i > hei - 1) && blocks[i][j] == -1) {
                    bombs++;
                }
            }
        }
        if (bombs == 0) {
            blocks[y][x] = -2;
            for (int i = y - 1; i <= y + 1; i++) {
                for (int j = x - 1; j <= x + 1; j++) {
                    if (!(j < 0 || i < 0 || j > wid - 1 || i > hei - 1)) {
                        if (i != y || j != x) {
                            open(i, j);                    //recursive call so that all the toggle buttons which as 0 mines as neighbour will open up
                        }
                    }
                }
            }
        } else {
            blocks[y][x] = bombs;
        }
    }

    private void checkWin() {            // checking the win condition
        boolean won = true;
        for (int i = 0; i < hei; i++) {
            for (int j = 0; j < wid; j++) {
                if (blocks[i][j] == 0) {
                    won = false;
                    break;
                }
            }
            if (!won) {
                break;
            }
        }
        if (won) {
            JOptionPane.showMessageDialog(frame, "YOU WON!!");
            canPlay = false;
        }
    }

    private void lose() {                                //checking if you laded on a mine
        canPlay = false;
        for (int i = 0; i < hei; i++) {
            for (int j = 0; j < wid; j++) {
                if (blocks[i][j] == -1) {
                    blocksButton[i][j].setIcon(mine);
                    blocksButton[i][j].setSelected(true);
                }
            }
        }
        JOptionPane.showMessageDialog(frame, "Better luck next time");

    }


    ActionListener listen = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == reset) {
                int a = JOptionPane.YES_NO_OPTION;
                if (JOptionPane.showConfirmDialog(frame, "Do you want to Reset?", "Warning", a) == JOptionPane.YES_NO_OPTION) {
                    System.out.println("" + a);                    
                    blocks = new int[hei][wid];
                    reveal();
                    canPlay = true;
                    fMove = false;
                    for (int p = 0; p < hei; p++) {
                        for (int o = 0; o < wid; o++) {
                            blocksButton[p][o].setIcon(null);
                            blocksButton[p][o].setSelected(false);
                        }
                    }
                }
            } else if (e.getSource() == exit) {
                int a = JOptionPane.YES_NO_OPTION;
                if (JOptionPane.showConfirmDialog(frame, "Do you want to exit?", "Warning", a) == JOptionPane.YES_NO_OPTION) {
                    System.out.println("" + a);
                    System.exit(0);
                }
            } else {
                boolean found = false;
                int i, j = 0;
                for (i = 0; i < hei; i++) {
                    for (j = 0; j < wid; j++) {
                        if (e.getSource() == blocksButton[i][j]) {
                            found = true;
                            blocksButton[i][j].setSelected(false);
                            break;
                        }

                    }

                    if (found) {
                        break;
                    }
                }

                if (canPlay) {
                    blocksButton[i][j].setSelected(true);
                    if (!fMove) {
                        boom(i, j);
                        fMove = true;
                    }
                    if (blocks[i][j] != -1) {
                        open(i, j);
                        reveal();
                    } else {
                        lose();
                    }
                    checkWin();
                } else {
                    reveal();
                }
            }

        }
    };
}
