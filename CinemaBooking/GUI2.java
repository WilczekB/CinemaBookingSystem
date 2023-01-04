import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import javax.swing.*;


public class GUI2 extends JFrame implements ActionListener{

    User user;
    Film film;
    JTextField seat, totalcost;
    Choice time, type;
    JLabel typelbl, timelbl, lblbl, mblbl, rblbl, inputSeatlbl, titlelbl, title, totallbl;
    JButton sumbitButton, showButton, saveButton;
    String selectedtime, selectedseat, total, tickettype;
    int timechoice, convertedseat1, convertedseat2, totalprice;
    int currenti, totaltickets = 0, nrSala;
    String kodFilmu, tytulFilmu, gatunekFilmu, rokFilmu, godzina, cenaFilmu;

    //Tworzenie sal dla poszczególnych godzin
    Seat sala1[][] = new Seat[4][4];
    Seat sala3[][] = new Seat[4][4];
    Seat sala5[][] = new Seat[4][4];
    Seat sala7[][] = new Seat[4][4];
    Seat sala9[][] = new Seat[4][4];

    //Tworzenie jednowymiarowej tablicy typu Bilety
    Bilet bilety[] = new Bilet[15];
    
    //Konstruktor
    public GUI2(Film movie, User us){
       
        setSize(800,500);
        setLocation(200,200);
        setResizable(true);
        setLayout(new FlowLayout());

        film = movie;
        user = us;

        typelbl = new JLabel("Rodzaj biletu: ");
        typelbl.setFont(new Font("Verdana",Font.BOLD, 13));

        //Tworzenie wyboru typu biletu
        type = new Choice();
        type.add("Dorosly");
        type.add("Student");
        type.add("Dziecko");

        timelbl = new JLabel("Godzina: ");
        timelbl.setFont(new Font("Verdana", Font.BOLD,18));

        //Wybór godziny seansu
        time = new Choice();
        time.add("13:00");
        time.add("15:00");
        time.add("17:00");
        time.add("19:00");
        time.add("21:00");

        inputSeatlbl = new JLabel("Wprowadz nr miejsca: ");
        inputSeatlbl.setFont(new Font("Verdana", Font.BOLD,18));

        //Wybór miejsca do siedzenia
        seat = new JTextField(3);
        seat.setHorizontalAlignment(seat.CENTER);

        totallbl = new JLabel("Cena: ");
        totallbl.setFont(new Font("Verdana", Font.BOLD, 18));

        //Pole do wyświetlania sumy pieniędzy
        totalcost =  new JTextField(3);
        totalcost.setHorizontalAlignment(totalcost.CENTER);
        totalcost.setEditable(false);

        //Przycisk zatwierdzający wybór miejsca
        sumbitButton = new JButton("Zatwierdz");
        //Przycisk pokazujący dostępne miejsca
        showButton = new JButton("Pokaz");
        //Przycisk zapisujący dane biletu
        saveButton = new JButton("Zapisz");

        //Pobranie wymiaru okna
        Dimension size = getSize();

        //Tworzenie Sali dla godziny 13:00
        int x = (int)(size.getWidth()/2.7);
        int y = (int)(size.getHeight()/1.7);
        int totalside = 0;

        for(int i=0; i < sala1.length; i++)
        {
            for(int j=0; j < 4; j++)
            {
                sala1[i][j] = new Seat(totalside, 0, x,y);
                x += 50;
                totalside++;
            }
            x = (int)(size.getWidth()/2.7);
            y += 30;
        }

        //Tworzenie Sali dla godziny 15:00
        x = (int)(size.getWidth()/2.7);
        y = (int)(size.getHeight()/1.7);
        int totalside3 = 0; 
        for(int i=0; i < sala3.length; i++)
        {
            for(int j=0; j < 4; j++)
            {
                sala3[i][j] = new Seat(totalside3, 0, x,y);
                x += 50;
                totalside3++;
            }
            x = (int)(size.getWidth()/2.7);
            y += 30;
        }

        //Tworzenie Sali dla godziny 17:00
        x = (int)(size.getWidth()/2.7);
        y = (int)(size.getHeight()/1.7);
        int totalside5 = 0;
        for(int i=0; i < sala5.length; i++)
        {
            for(int j=0; j < 4; j++)
            {
                sala5[i][j] = new Seat(totalside5, 0, x,y);
                x += 50;
                totalside5++;
            }
            x = (int)(size.getWidth()/2.7);
            y += 30;
        }

        //Tworzenie Sali dla godziny 19:00
        x = (int)(size.getWidth()/2.7);
        y = (int)(size.getHeight()/1.7);
        int totalside7 = 0;
        for(int i=0; i < sala7.length; i++)
        {
            for(int j=0; j < 4; j++)
            {
                sala7[i][j] = new Seat(totalside7, 0, x,y);
                x += 50;
                totalside7++;
            }
            x = (int)(size.getWidth()/2.7);
            y += 30;
        }

        //Tworzenie Sali dla godziny 21:00
        x = (int)(size.getWidth()/2.7);
        y = (int)(size.getHeight()/1.7);
        int totalside9 = 0;
        for(int i=0; i < sala9.length; i++)
        {
            for(int j=0; j < 4; j++)
            {
                sala9[i][j] = new Seat(totalside9, 0, x,y);
                x += 50;
                totalside9++;
            }
            x = (int)(size.getWidth()/2.7);
            y += 30;
        }
    
        //Dodanie elementów do panelu
        getContentPane().add(typelbl);
        getContentPane().add(type);
        getContentPane().add(timelbl);
        getContentPane().add(time);
        getContentPane().add(showButton);
        getContentPane().add(inputSeatlbl);
        getContentPane().add(seat);
        getContentPane().add(sumbitButton);
        getContentPane().add(totallbl);
        getContentPane().add(totalcost);
        getContentPane().add(saveButton);
        setTitle("Rezerwacja miejsc");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
 
        //Dodanie ActionListenerów do przycisków
        sumbitButton.addActionListener(this);
        showButton.addActionListener(this);
        saveButton.addActionListener(this);
        
    }

    //Funkcja rysująca salę w zależności od wybranej godziny
    public void paint(Graphics graf)
    {
       super.paint(graf);

        switch(timechoice)
        {
            case 1:
                for(int i=0; i < sala1.length; i++)
                {
                    for(int j=0; j<4; j++)
                    {
                        sala1[i][j].display(graf);
                    }
                }
                break;
            case 3:
                for(int i=0; i<sala3.length; i++)
                {
                    for(int j=0; j<4; j++)
                    {
                        sala3[i][j].display(graf);
                    }
                }
                break;
            case 5:
                for(int i=0; i<sala5.length; i++)
                {
                    for(int j=0; j<4; j++)
                    {
                        sala5[i][j].display(graf);
                    }
                }
                break;
            case 7:
                for(int i=0; i<sala7.length; i++)
                {
                    for(int j=0; j<4; j++)
                    {
                        sala7[i][j].display(graf);
                    }
                }
                break;
            case 9:
                for(int i=0; i<sala9.length; i++)
                {
                    for(int j=0; j<4; j++)
                    {
                        sala9[i][j].display(graf);
                    }
                }
                break;
                
        }
    }

   
    @Override
    public void actionPerformed(ActionEvent ev){
        //Pobranie wybranej godziny oraz typu biletu
        selectedtime = time.getSelectedItem();
        tickettype = type.getSelectedItem();

        //Jeśli zostanie kliknięty przycisk "Zatwierdź"
        if(ev.getSource() == sumbitButton)
        {
            //Pobranie wybranego miejsca przez użytkownika
            int selectedseat = Integer.parseInt(seat.getText());

            //Poszukiwanie wybranego miejsca i przypisanie go do miejsca w sali
            if(selectedseat==0){
                convertedseat1=0;
                convertedseat2=0;
            }

            if(selectedseat==1){
                convertedseat1=0;
                convertedseat2=1;
            }
            if(selectedseat==2){
                convertedseat1=0;
                convertedseat2=2;
            }
            if(selectedseat==3){
                convertedseat1=0;
                convertedseat2=3;
            }
            if(selectedseat==4){
                convertedseat1=1;
                convertedseat2=0;
            }
            if(selectedseat==5){
                convertedseat1=1;
                convertedseat2=1;
            }
            if(selectedseat==6){
                convertedseat1=1;
                convertedseat2=2;
            }
            if(selectedseat==7){
                convertedseat1=1;
                convertedseat2=3;
            }
            if(selectedseat==8){
                convertedseat1=2;
                convertedseat2=0;
            }
            if(selectedseat==9){
                convertedseat1=2;
                convertedseat2=1;
            }
            if(selectedseat==10){
                convertedseat1=2;
                convertedseat2=2;
            }
            if(selectedseat==11){
                convertedseat1=2;
                convertedseat2=3;
            }
            if(selectedseat==12){
                convertedseat1=3;
                convertedseat2=0;
            }
            if(selectedseat==13){
                convertedseat1=3;
                convertedseat2=1;
            }
            if(selectedseat==14){
                convertedseat1=3;
                convertedseat2=2;
            }
            if(selectedseat==15){
                convertedseat1=3;
                convertedseat2=3;
            }

            //Warunek sprawdzający wybraną godzinę
            if(selectedtime == "13:00")
            {
                timechoice = 1;
                repaint();
                //Sprawdzenie czy miejsce nie jest już zajęte i zależności od warunku
                //Wyświetlenie okna z wiadomością, że miejsce jest już zajęte
                //Lub zarezerwowanie wybranego miejsca i dodanie biletu do tablicy biletów
                int iftaken = sala1[convertedseat1][convertedseat2].isTaken();
                if(iftaken==1)
                {
                    JOptionPane.showMessageDialog(null, "Miejsce jest juz zajete");

                }
                else if(iftaken==0)
                {
                    sala1[convertedseat1][convertedseat2].setSeat();
                    repaint();
                    bilety[totaltickets] = new Bilet(tickettype, selectedseat, selectedtime);
                    int temprice = bilety[totaltickets].getSeatPrice();
                    totalprice = totalprice + temprice;
                    totalcost.setText(Integer.toString(totalprice)+"zl");
                    totaltickets += 1;
                    seat.setText("");
                }
            }

            if(selectedtime == "15:00")
            {
                timechoice = 3;
                repaint();
                int iftaken = sala3[convertedseat1][convertedseat2].isTaken();
                if(iftaken==1)
                {
                    JOptionPane.showMessageDialog(null, "Miejsce jest juz zajete");

                }
                else if(iftaken==0)
                {
                    sala3[convertedseat1][convertedseat2].setSeat();
                    repaint();
                    bilety[totaltickets] = new Bilet(tickettype, selectedseat, selectedtime);
                    int temprice = bilety[totaltickets].getSeatPrice();
                    totalprice = totalprice + temprice;
                    totalcost.setText(Integer.toString(totalprice)+"zl");
                    totaltickets += 1;
                    seat.setText("");
                }
            }

            if(selectedtime == "17:00")
            {
                timechoice = 5;
                repaint();
                int iftaken = sala5[convertedseat1][convertedseat2].isTaken();
                if(iftaken==1)
                {
                    JOptionPane.showMessageDialog(null, "Miejsce jest juz zajete");

                }
                else if(iftaken==0)
                {
                    sala5[convertedseat1][convertedseat2].setSeat();
                    repaint();
                    bilety[totaltickets] = new Bilet(tickettype, selectedseat, selectedtime);
                    int temprice = bilety[totaltickets].getSeatPrice();
                    totalprice = totalprice + temprice;
                    totalcost.setText(Integer.toString(totalprice)+"zl");
                    totaltickets += 1;
                    seat.setText("");
                }
            }

            if(selectedtime == "19:00")
            {
                timechoice = 7;
                repaint();
                int iftaken = sala7[convertedseat1][convertedseat2].isTaken();
                if(iftaken==1)
                {
                    JOptionPane.showMessageDialog(null, "Miejsce jest juz zajete");

                }
                else if(iftaken==0)
                {
                    sala7[convertedseat1][convertedseat2].setSeat();
                    repaint();
                    bilety[totaltickets] = new Bilet(tickettype, selectedseat, selectedtime);
                    int temprice = bilety[totaltickets].getSeatPrice();
                    totalprice = totalprice + temprice;
                    totalcost.setText(Integer.toString(totalprice)+"zl");
                    totaltickets += 1;
                    seat.setText("");
                }
            }

            if(selectedtime == "21:00")
            {
                timechoice = 9;
                repaint();
                int iftaken = sala9[convertedseat1][convertedseat2].isTaken();
                if(iftaken==1)
                {
                    JOptionPane.showMessageDialog(null, "Miejsce jest juz zajete");

                }
                else if(iftaken==0)
                {
                    sala9[convertedseat1][convertedseat2].setSeat();
                    repaint();
                    bilety[totaltickets] = new Bilet(tickettype, selectedseat, selectedtime);
                    int temprice = bilety[totaltickets].getSeatPrice();
                    totalprice = totalprice + temprice;
                    totalcost.setText(Integer.toString(totalprice)+"zl");
                    totaltickets += 1;
                    seat.setText("");
                }
            }

        }

        //Jeśli zostanie kliknięty przycisk "Pokaz" to zostaje wyświetlona sala
        //w zależności od wybranej godziny
        if(ev.getSource() == showButton)
        {
            if(selectedtime == "13:00")
            {
                timechoice = 1;
                repaint();
            }

            if(selectedtime == "15:00")
            {
                timechoice = 3;
                repaint();
            }

            if(selectedtime == "17:00")
            {
                timechoice = 5;
                repaint();
            }

            if(selectedtime == "19:00")
            {
                timechoice = 7;
                repaint();
            }

            if(selectedtime == "21:00")
            {
                timechoice = 9;
                repaint();
            }

        }

        //Jeśli zostanie kliknięty przycisk "Zapisz" to następuje zapis wszystkich
        //najważniejszych danych dotyczących rezerwacji do pliku o rozszerzeniu .txt
        if(ev.getSource() == saveButton)
        {

            try {
                FileWriter stream = new FileWriter("rezerwacja.txt");
                BufferedWriter writer = new BufferedWriter(stream);

                for(int i=0; i < totaltickets; i++)
                {
                    int ticketnumber = bilety[i].getSeatNum();
                    int ticketprice = bilety[i].getSeatPrice();
                    String tickettype = bilety[i].getType();
                    String tickettime = bilety[i].getTime();
                    String userName = user.getName();
                    String userSurname = user.getSurname();
                    String userEmail = user.getEmail();
                    String movieName = film.getNazwa();
                    System.out.println(movieName);
                    String newline = System.getProperty("line.separator");

                    writer.write(newline);
                    writer.write("Dane biletu: ");
                    writer.write(newline);
                    writer.write(newline);
                    writer.write("Tytul filmu: " + film.getNazwa());
                    writer.write(newline);
                    writer.write("Numer siedzenia: " + ticketnumber);
                    writer.write(newline);
                    writer.write("Cena biletu: " + ticketprice);
                    writer.write(newline);
                    writer.write("Rodzaj biletu: " + tickettype);
                    writer.write(newline);
                    writer.write("Godzina seansu: " + tickettime);
                    writer.write(newline);
                    writer.write("Imie: " + userName);
                    writer.write(newline);
                    writer.write("Nazwisko: " + userSurname);
                    writer.write(newline);
                    writer.write("Email: " + userEmail);
                    writer.write(newline);
                    writer.write("___________________");
                    writer.write(newline);
                }

                writer.close();

            } catch (Exception e) {
                System.err.println("ERROR:" + e.getMessage());
            }

            //Wyświetlenie komunikatu o udanej rezerwacji
            JOptionPane.showMessageDialog(null, "Gratulujemy! Udalo sie zarezerwowac bilet");

            //Czyszczenie tablicy z biletami
            for(int i=0; i < bilety.length; i++)
            {
                bilety[i] = null;
            }

            //Czyszczenie pól tekstowych i buforów
            totaltickets = 0;
            totalprice = 0;
            seat.setText("");
            totalcost.setText("");

        }

    }
 
}
