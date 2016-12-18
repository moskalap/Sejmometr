import org.json.JSONException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by przemek on 17.12.16.
 */
public class MAIN {
   /* public JPanel main;
    Parliament parliament=new Parliament();
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JCheckBox checkBox1;
    private JComboBox comboBox4;
    private JComboBox comboBox5;
    private JButton lustrujButton;
    private DataDownloader dd;

    public MAIN() {
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                System.out.println("combobox1 triggered\n"+
                        comboBox1.getSelectedItem()+
                        "\n"+ comboBox2.getSelectedItem()+"\n"+
                        comboBox3.getSelectedItem()

                );
if(comboBox1.getSelectedItem().equals("VII")) {
    dd = new DataDownloader("https://api-v3.mojepanstwo.pl/dane/poslowie.json?conditions[poslowie.kadencja]=7");
}
else{
   dd = new DataDownloader("https://api-v3.mojepanstwo.pl/dane/poslowie.json?conditions[poslowie.kadencja]=8");
}
                try {
                    dd.downloadParties(parliament);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                for(Object obj:parliament.showParties()) {
                    PoliticalParty o = (PoliticalParty) obj;
                    comboBox2.addItem(o.getName());
                }


            }
        });
        comboBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("combobox2 triggered\n"+
                        comboBox1.getSelectedItem()+
                        "\n"+ comboBox2.getSelectedItem()+"\n"+
                        comboBox3.getSelectedItem()

                );
                //comboBox3.removeAllItems();
                //comboBox3.addItem("WSZYSTKIE");
               // comboBox3.removeAll();
                for(Politican politican:parliament.getPartybyName((String)comboBox2.getSelectedItem()).getPoliticans()) {
                    comboBox3.addItem(politican.getName());
                System.out.println("dodałem "+politican.getName());
                }





            }
        });
        comboBox3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("combobox3 triggered\n"+
                comboBox1.getSelectedItem()+
                "\n"+ comboBox2.getSelectedItem()+"\n"+
                                comboBox3.getSelectedItem()

                );
                checkBox1.setEnabled(false);

               try {
                    for(String s:dd.downloadListofExpenses(174)){
                        comboBox5.addItem(s);
                        System.out.println("ddo");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }



        });
    }

*/
}
