import org.json.JSONException;

import javax.swing.*;
import java.io.IOException;

/**
 * Created by przemek on 09.12.16.
 */
public class Main {
    public static void main(String[] args) throws IOException, JSONException {

        if (args.length>0) {
            if (args[0].equals("-u"))
            ResourceUpdater.buildResources();

        } else {
            JFrame mainframe = new JFrame("SejmoMetr");
            mainframe.setContentPane(new GUI().panel1);
            mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainframe.pack();
            mainframe.setVisible(true);
        }
    }
}
