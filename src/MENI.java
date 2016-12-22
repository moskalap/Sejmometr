import org.json.JSONException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * Created by przemek on 18.12.16.
 */
public class MENI {
    public JPanel panel1;
    private JComboBox kadencjaBox;
    private JProgressBar progressBar1;
    private JLabel Stan;
    private JLabel Kadencja;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JCheckBox najdłuższaPodróżCheckBox;
    private JCheckBox najdroższaPodróżCheckBox;
    private JCheckBox średniaWydatkówCheckBox;
    private JCheckBox najwięcejPodrózyCheckBox;
    private JCheckBox podróżowałDoCheckBox;
    private JComboBox comboBox5;
    private JButton OKButton;
    private JLabel partia;
    private JLabel posel;
    private JLabel wydatki;
    private JTextArea out;
    private String[] expenses;
    DataDownloader dd;
    Parliament parliament=new Parliament();


  /*  public MENI() {
       // comboBox4.setEnabled(false);
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                firstTriggered();
            }
        });
        comboBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

              secondTriggered();


            }

        });
        comboBox3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                thirdTriggered();
            }
        });
        comboBox4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                fourthTriggered();
            }
        });
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                out.setText("Pobieranie danych...");
                RequestedData requestedData=saveSelections();
                QueryInterpreter queryInterpreter=new queryInterpreter();
                out.setText(queryInterpreter.interpret(requestedData));
            }
        });
    }

    private RequestedData saveSelections() {
        return new RequestedData()
    }


    private void fourthTriggered() {
        String key=((String)(comboBox4.getSelectedItem())).split("\\.")[0];
        String politican=(String) comboBox3.getSelectedItem();
        // out.append(politican+" "+ key);
        try {
            if(!politican.equals("Wszyscy")) {
                out.setText(politican+" wydał\n "+dd.downloadExpense(parliament.politicansbyName.get(politican).getID(), key)+" na\n "+expenses[Integer.parseInt(key)-1]);


                // / out.append(dd.downloadExpense(parliament.politicansbyName.get(politican).getID(), key));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void thirdTriggered() {
        if (((String) (comboBox3.getSelectedItem())).equals("Wszyscy")) {
            hide();
        } else {
            unhide();

        }
    }

    private void secondTriggered() {
        //  comboBox3.addItem("Wszyscy");
        LinkedList<String> list=new LinkedList<>();
        for(Politican politican:parliament.getPartybyName((String)comboBox2.getSelectedItem()).getPoliticans())
            list.add(politican.getName());

        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Collator.getInstance().compare(o1, o2);
            }
        });

        for(String s:list)
            comboBox3.addItem(s);

        posel.setVisible(true);
        comboBox3.setVisible(true);
    }


    private void firstTriggered(){
        if(comboBox1.getSelectedItem().equals("7")) {
            dd = new DataDownloader("https://api-v3.mojepanstwo.pl/dane/poslowie.json?conditions[poslowie.kadencja]=7");
        }
        else{
            dd = new DataDownloader("https://api-v3.mojepanstwo.pl/dane/poslowie.json?conditions[poslowie.kadencja]=8");
        }

        try {   expenses=dd.downloadArrayExp(174);
            for (String s : dd.downloadListofExpenses(174))
                comboBox4.addItem(s);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
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
        partia.setVisible(true);
        comboBox2.setVisible(true);
    }

    private void hide(){
        najdroższaPodróżCheckBox.setVisible(true);
        najdłuższaPodróżCheckBox.setVisible(true);
        najwięcejPodrózyCheckBox.setVisible(true);
        podróżowałDoCheckBox.setVisible(true);
        comboBox4.setEnabled(false);
    }

    private void unhide(){

        najdroższaPodróżCheckBox.setVisible(false);
        najdłuższaPodróżCheckBox.setVisible(false);
        najwięcejPodrózyCheckBox.setVisible(false);
        podróżowałDoCheckBox.setVisible(false);
        comboBox4.setEnabled(true);

        wydatki.setVisible(true);
        comboBox4.setVisible(true);

    }
    */
}
