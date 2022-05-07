import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Pratz
 */
public class Start {

    JFrame frame1 = new JFrame("Minesweeper");//making a JFrame
    JTextArea ta1 = new JTextArea("\n");// Creating a text area 

    JButton pg = new JButton("Play Game");//creating a play game button
    JButton inst = new JButton("Instructions");//creating an instruction button
    JButton exit = new JButton("Exit");//creating an exit button
    Container contain = new Container();

    ImageIcon fIcon = new ImageIcon("src\\smile.jpg"); //frame Image

    public Start() {
        frame1.setSize(800, 800);             //setting size of the frame
        contain.setLayout(null); //setting the border of the frame
        frame1.add(contain);//add int container to the frame

        ta1.append("  WELCOME TO MINESWEEPER"); //Appending the text in the text Area
        String arial = "Arial";
        ta1.setFont(new Font(arial, Font.ITALIC, 50)); //setting font
        pg.setFont(new Font(arial, Font.ITALIC, 35));//setting font
        inst.setFont(new Font(arial, Font.ITALIC, 35));//setting font
        exit.setFont(new Font(arial, Font.ITALIC, 35));//setting font
        ta1.setForeground(Color.red);//setting foreground colour
        ta1.setBackground(Color.black);//setting background colour
        pg.setForeground(Color.BLUE);//setting foreground colour
        pg.setBackground(Color.GREEN);//setting background colour
        inst.setForeground(Color.BLUE);//setting foreground colour
        inst.setBackground(Color.GREEN);//setting background colour
        exit.setForeground(Color.BLUE);//setting foreground colour
        exit.setBackground(Color.GREEN);//setting background colour
        contain.add(ta1, BorderLayout.NORTH);//adiint text area in container and strring its border layout
        ta1.setEditable(false);//making the text area uneditable
        ta1.setBounds(0, 0, 800, 190);//setting the size and location of text area
        pg.setBounds(200, 250, 400, 125);//setting the size and location of play game button
        inst.setBounds(200, 450, 400, 125);//setting the size and location of instruction button
        exit.setBounds(200, 620, 400, 125);//setting the size and location of exit button
        contain.add(pg);   //adding play game button to panel      
        contain.add(inst);//adding instructions button to panel    
        contain.add(exit);//adding exit button to panel           
        frame1.setIconImage(fIcon.getImage());//setting JFrame's Icon
        frame1.setVisible(true);//making the frame visible on the screen
        frame1.setResizable(false);//frame can't be resized anymore 
        frame1.getContentPane().setBackground(Color.DARK_GRAY);
        pg.addActionListener(listen);     //creating an action listener for the button play game
        inst.addActionListener(listen);     //creating an action listener for the button instruction
        exit.addActionListener(listen);     //creating an action listener for the button Exit
        frame1.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
//        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//terminates the program on clicking the X(close) icon

    }

    public static void main(String[] args) {
        new Start();
    }

    ActionListener listen = e -> {
        if (e.getSource() == pg) {
            new Minesweeper();
            frame1.setVisible(false);
        }

        else if (e.getSource() == inst) {
            new Instruction();
            frame1.setVisible(false);
        }

        else if (e.getSource() == exit) {
            System.exit(0);
        }
    };

}
