import javax.swing.*;

/**
 * Created by przemek on 09.12.16.
 */
public class Main {
    public static void main(String[] args){
JFrame mainframe=new JFrame("a");
        mainframe.setContentPane(new GUI().panel1);
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainframe.pack();
        mainframe.setVisible(true);
    }
}
