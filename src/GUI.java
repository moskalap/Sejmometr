import org.json.JSONException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * Created by przemek on 18.12.16.
 */
public class GUI {
    public JPanel panel1;
    private JComboBox kadencjaBox;
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
    private JPanel image;
    private JTextPane img2;
    private JLabel podroze;
    private String[] expenses;
    DataDownloader dd;
    Parliament parliament=new Parliament();
    private RequestedData requestedData=new RequestedData();
    private QueryInterpreter queryInterpreter;
    private boolean thirdListenerNotDeaf=false;


  public GUI() {
        enableAll();
        comboBox2.addItem("Wszystkie");
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                try {
                    firstTriggered();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        comboBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                thirdListenerNotDeaf=false;
              secondTriggered();
                thirdListenerNotDeaf=true;



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
                
                prepareRequestedDatatoSendIntoInterpereter();
                try {
                    out.setText(queryInterpreter.interpret(requestedData));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                

                String queryToGoogle;
                if(requestedData.wantsPolitican) queryToGoogle=requestedData.politic;
                else queryToGoogle=requestedData.party+"+logo";

                ImageIcon downloadedImage=new ImageIcon();
                int index=0;
                try {

                    downloadedImage = downloadImage(queryToGoogle, index);

                }
                catch(IllegalArgumentException e){
                    index++;
                    index++;
                    try {
                        downloadedImage = downloadImage(queryToGoogle, index);
                    } catch (MalformedURLException e1) {
                        e1.printStackTrace();
                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                img2.setText("");
                img2.insertIcon(downloadedImage);

            }
        });
      podróżowałDoCheckBox.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent actionEvent) {

                  comboBox5.setEnabled(podróżowałDoCheckBox.isSelected());
                  comboBox5.setVisible(podróżowałDoCheckBox.isSelected());

          }
      });
  }
    private void prepareRequestedDatatoSendIntoInterpereter() {
        if(comboBox3.getSelectedItem().equals("Wszyscy") && comboBox2.getSelectedItem().equals("Wszystkie")) {
            requestedData.wantsAll=true;
            requestedData.wantsPolitican=false;
            requestedData.wantsParty=false;
            requestedData.party="sejm";
        }
        if(requestedData.wantsParty || requestedData.wantsAll){
            requestedData.wantsTheMostExpensiveTravel=najdroższaPodróżCheckBox.isSelected();
            requestedData.wantsTheLongestTravel=najdłuższaPodróżCheckBox.isSelected();
            requestedData.country=(String)comboBox5.getSelectedItem();
            requestedData.wantsTheGreatestTraveller=najwięcejPodrózyCheckBox.isSelected();
            requestedData.wantsListofTravellersTo_=podróżowałDoCheckBox.isSelected();
            requestedData.wantsAverage=średniaWydatkówCheckBox.isSelected();
        }
        queryInterpreter=new QueryInterpreter(requestedData);
        queryInterpreter.setParliament(parliament);
        queryInterpreter.setDownloader(dd);
    }
    private void enableAll() {
        comboBox4.setVisible(false);
        comboBox2.setVisible(false);
        comboBox3.setVisible(false);
        najdroższaPodróżCheckBox.setVisible(false);
        podróżowałDoCheckBox.setVisible(false);
        najwięcejPodrózyCheckBox.setVisible(false);
        najdłuższaPodróżCheckBox.setVisible(false);
        średniaWydatkówCheckBox.setVisible(false);
        wydatki.setVisible(false);
        posel.setVisible(false);
        partia.setVisible(false);
        comboBox5.setVisible(false);
        podroze.setVisible(false);

    }
    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }
    private ImageIcon downloadImage(String query, int index) throws IOException, JSONException, IllegalArgumentException {

        String path=dd.downloadimage(query, index);

        URL u=new URL(path);
        ImageIcon img=new ImageIcon(u);



            BufferedImage bi = new BufferedImage(
                    img.getIconWidth(),
                    img.getIconHeight(),
                    BufferedImage.TYPE_INT_RGB);


            Graphics g = bi.createGraphics();
            img.paintIcon(null, g, 0, 0);
            g.dispose();


            double ratio = 1;
            System.out.println("img w h " + img.getIconWidth() + " " + img.getIconHeight());
            if (img.getIconWidth() > img.getIconHeight()) {
                ratio = 300.0 / img.getIconWidth();
                System.out.println("first");
            } else {
                ratio = 300.0 / img.getIconHeight();
                System.out.println("sec");
            }
            System.out.println("ratio:" + ratio + "wh" + new Double(ratio * img.getIconWidth()).intValue() + " " + new Double(ratio * img.getIconHeight()).intValue());
            bi = resize(bi, new Double(ratio * img.getIconWidth()).intValue(), new Double(ratio * img.getIconHeight()).intValue());
            img = new ImageIcon(bi);


        return img;
    }
    private void fourthTriggered() {
        requestedData.expenseID = ((String) (comboBox4.getSelectedItem())).split("\\.")[0];
    }
    private void thirdTriggered() {
        if(thirdListenerNotDeaf) {
            boolean cond=(comboBox3.getSelectedItem()).equals("Wszyscy");
            setVisibilityOfAgregatingOptions(cond);
            setVisibilityOfPoliticanOptions(!cond);
            requestedData.wantsPolitican=!cond;

            if(cond){

                requestedData.wantsParty=true;
            }
            else{
                requestedData.politic=(String) comboBox3.getSelectedItem();
                requestedData.politicanid=parliament.politicansbyName.get(requestedData.politic).getID();
            }


        }
    }
    private void secondTriggered() {
        boolean cond=!((String) (comboBox2.getSelectedItem())).equals("Wszystkie");
        setVisibilityOfAgregatingOptions(true);
        setVisibilityOfPoliticanOptions(false);
        LinkedList<String> list = new LinkedList<>();
        requestedData.wantsParty = cond;
        requestedData.wantsAll=!cond;
        requestedData.wantsPolitican=false;

        if(cond) {
            requestedData.party = (String) comboBox2.getSelectedItem();
            for (Politican politican : parliament.getPartybyName((String) comboBox2.getSelectedItem()).getPoliticans())
                     list.add(politican.getName());


        }else {
              for (Politican politican : parliament.politicans.values()){
                    list.add(politican.getName());
            }
        }

        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Collator.getInstance().compare(o1, o2);
            }
        });

        comboBox3.removeAllItems();
        comboBox3.addItem("Wszyscy");
        comboBox3.setVisible(false);
        for (String s : list)
            comboBox3.addItem(s);

        posel.setVisible(true);
        comboBox3.setVisible(true);

    }
    private void firstTriggered() throws FileNotFoundException, JSONException {


        Kadencja.setVisible(false);
        comboBox1.setVisible(false);
        if(comboBox1.getSelectedItem().equals("7")) {
            requestedData.term= String.valueOf(7);
        }
        else{

            requestedData.term=String.valueOf(8);
        }

        try {



            dd=new DataDownloader("https://api-v3.mojepanstwo.pl/dane/poslowie.json?conditions[poslowie.kadencja]="+requestedData.term);
            dd.term=requestedData.term;
            expenses=dd.downloadArrayExp(174);




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
        queryInterpreter=new QueryInterpreter(requestedData);
        queryInterpreter.setParliament(parliament);
        dd.setParliament(parliament);
        queryInterpreter.setDownloader(dd);
        LinkedList<String> countries=dd.downloadCountries();

        for(String country:countries)
            comboBox5.addItem(country);

       
        comboBox2.setVisible(false);

        for(Object obj:parliament.showParties()) {
            PoliticalParty o = (PoliticalParty) obj;
            comboBox2.addItem(o.getName());
            System.out.println("Dodałem"+o.getName());
        }
        partia.setVisible(true);
        comboBox2.setVisible(true);
    }
    private void setVisibilityOfPoliticanOptions(boolean cond){
        wydatki.setVisible(cond);
        comboBox4.setVisible(cond);
    }
    private void setVisibilityOfAgregatingOptions(boolean cond){
        średniaWydatkówCheckBox.setVisible(cond);
       podróżowałDoCheckBox.setVisible(cond);
        comboBox5.setVisible(cond);
        najdroższaPodróżCheckBox.setVisible(cond);
        najwięcejPodrózyCheckBox.setVisible(cond);
        najdłuższaPodróżCheckBox.setVisible(cond);
        podroze.setVisible(cond);
    }
}
