import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/*
 *
 * @author Pratz
 */
public class Instruction {

    JFrame frame1 = new JFrame("Minesweeper");//making a JFrame 
    JTextArea ta2 = new JTextArea("How to Play?");// Creating a text area
    JTextArea ta3 = new JTextArea("\n");// Creating a text area
    JButton back = new JButton("Back");//creating a play game button
    Container contain = new Container();

    ImageIcon f_icon = new ImageIcon("src\\smile.jpg"); //frame Image

    public Instruction () {
        frame1.setSize(800, 800);             //setting size of the frame
        contain.setLayout(null); //setting the border of the frame
        frame1.add(contain);//add a container to the frame
        ta2.setBounds(0, 0, 900, 100);
        ta2.setFont(new Font("Arial", Font.ITALIC, 50));
        ta2.setForeground(Color.red);//setting foreground colour
        ta2.setBackground(Color.black);//setting background colour
        ta3.append(
                "1]You are presented with a board of squares. Some squares contain \n mines (bombs), others don't. If you click on a  "
                + "square containing \n a bomb, you lose. If you manage to click all the squares \n (without clicking on any bombs) you win.\n"
                + "\n2]Clicking a square which doesn't have a bomb reveals the number \n of neighbouring squares containing bombs. Use this information plus \nsome  guess work to avoid the bombs.\n"
                + "\n3]To open a square, point at the square and click on it.\n"
                + "\n4]A squares \"neighbours\" are the squares adjacent above, below, left,\n right, and all 4 diagonals. Squares on the sides of the board or in a \n corner have fewer neighbors. The board does not wrap around the \n edges."
                + "\n\n5]If you open a square with 0 neighboring bombs, all its neighbors will \nautomatically open. This can cause a large area to automatically \nopen. The first square you open is never a bomb."
                + "\n\n6] Click the Reset button to start a new game.");
        ta3.setFont(new Font("Arial", Font.ITALIC, 25));
        ta3.setForeground(Color.CYAN);//setting foreground colour
        ta3.setBackground(Color.RED);//setting background colour
        ta3.setBounds(0, 100, 900, 700);// left + top + right + bottom
        ta2.setEditable(false);
        ta3.setEditable(false);
        back.setBounds(400, 0, 1000, 0);// left + top + right + bottom
        contain.add(ta2);
        contain.add(ta3);
        back.setForeground(Color.BLUE);//setting foreground colour
        back.setBackground(Color.GREEN);//setting background colour
        back.setFont(new Font("Arial", Font.ITALIC, 35));//setting font
        back.setBounds(200, 800, 400, 60);//setting the size and location of exit button // left + top + right + bottom
        contain.add(back);//adding exit button to panel           
        frame1.setIconImage(f_icon.getImage());//setting JFrame's Icon
        frame1.setVisible(true);//making the frame visible on the screen
        frame1.setResizable(false);//frame can't be resized anymore

        back.addActionListener(listen);     //creating an action listener for the button Exit                 
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Terminates the program on clicking the X(close) icon

    }

    public static void main(String[] args) {
        new Instruction();
    }

    ActionListener listen = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == back) {
                new Start();
                frame1.setVisible(false);
            } 
        }
    };

}
