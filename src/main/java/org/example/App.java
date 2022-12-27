package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.sound.midi.Soundbank;
import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App
{
    public static String getdta(String c) throws Exception{

        StringBuffer sb = new StringBuffer();
        sb.append("<html>"+
                "<body style= 'text-align:center;color:red;'>");

        sb.append(c.toUpperCase()+"<br>");

        String url="https://www.worldometers.info/coronavirus/country/"+ c+ "/";
        Document doc = Jsoup.connect(url).get();
        // main counter-wrap
        Elements elements = doc.select("#maincounter-wrap");
        elements.forEach((element -> {
           String text = element.select("h1").text();
           String count = element.select(".maincounter-number>span").text();
           sb.append(text).append(" ").append(count).append("<br>");
        }));
        sb.append("</body>"+
                "</html>");

        return sb.toString();
    }



    public static void main( String[] args ) throws Exception {
//        System.out.println( "Hello World!" );
//
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Enter a country : ");
//        String country = sc.nextLine();
//        System.out.println(getdta(country));


        JFrame root = new JFrame("details of Country");
        root.setSize(500, 500);

        Font f = new Font("poppins", Font.BOLD, 30);

        JTextField field = new JTextField();

        JLabel dataL = new JLabel();
        field.setFont(f);
        field.setHorizontalAlignment(SwingConstants.CENTER);

        dataL.setFont(f);
        dataL.getHorizontalAlignment();

        JButton button = new JButton("Get");
        button.setFont(f);
        button.addActionListener(event ->{
            //click:
            try {
                String mainData = field.getText();
                String result = getdta(mainData);
                dataL.setText(result);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        root.setLayout(new BorderLayout());

        root.add(field, BorderLayout.NORTH);
        root.add(dataL, BorderLayout.CENTER);
        root.add(button, BorderLayout.SOUTH);

        root.setVisible(true);
    }
}
