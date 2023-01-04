import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class GUI implements ActionListener{

    private JFrame frame;
    private JPanel panel1, panel2, panel4;
    private JLabel iconLabel, sortingLabel;
    private ImageIcon logo;
    private JButton bookButton, sortButton, testButton;
    private LinkedHashMap<Integer, Film> films;
    private User user;
    private JTable table;
    private JScrollPane panelTable;
    private JTextField kodFilmuField, nazwaFilmuField, gatunekField, rokField, godzinaField, nrSaliField, cenaField, szukajField;
    private Choice sortingValue;
    private TableRowSorter mySorter; 

    //Konstruktor
    public GUI(LinkedHashMap<Integer, Film> films, User u){
    
        this.films = films;
        this.user = u;
        setFrame();

    }

    //Tworzenie panelu o oznaczeniu nr 1
    //Panel zawiera logo kina, pole do wyszukiwanie konkretnej frazy
    //oraz sortowania realizowanego na podstawie LinkedHashMap
    public void setPanel1(){
        FlowLayout layout = new FlowLayout();
        panel1 = new JPanel();
        createImage("Graphics/kinoLogo.png");
        sortingValue = createChoice();
        
        szukajField = creaTextField(15);
        szukajField.setEditable(true);

        sortButton = createButton("Szukaj");
        sortButton.setSize(250, 100);
        sortButton.addActionListener(this);

        testButton = createButton("Map sorting");
        testButton.setSize(250, 100);
        testButton.addActionListener(this);

        panel1.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 40));
        panel1.setLayout(layout);
        panel1.add(iconLabel);
        panel1.add(szukajField);
        panel1.add(sortButton);
        panel1.add(sortingValue);
        panel1.add(testButton);
    }

    //Tworzenie panelu o oznaczeniu nr 2
    //Panel zawiera tablicę przechowującą cały repertuar kina
    public void setPanel2(){
        panel2 = new JPanel();
        createTable(films);
        createPaneWithTable();
        panel2.add(panelTable);
    }

    //Tworzenie panelu o oznaczeniu nr 4
    //Panel zawiera szczegółowe informacje o filmie,
    //który użytkownik wybrał z repertuaru
    public void setPanel4(){
        panel4 = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10,10,10,10);
        bookButton = createButton("Wybierz");
        bookButton.addActionListener(this);

        kodFilmuField = creaTextField(3);
        nazwaFilmuField = creaTextField(50);
        gatunekField = creaTextField(30);
        rokField = creaTextField(4);
        godzinaField = creaTextField(5);
        nrSaliField = creaTextField(1);
        cenaField = creaTextField(2);

        JLabel info = createLabel("Informacje o filmie");
        JLabel kodlbl = createLabel("Kod: ");
        JLabel nazwalbl = createLabel("Nazwa Filmu: ");
        JLabel gatuneklbl = createLabel("Gatunek Filmu: ");
        JLabel roklbl = createLabel("Rok wydania: ");
        JLabel godzinalbl = createLabel("Godzina seansu: ");
        JLabel nrSalilbl = createLabel("Nr sali: ");
        JLabel cenalbl = createLabel("Cena biletu: ");

        c.gridx = 0;
        c.gridy = 0;
        panel4.add(info, c);

        c.gridx = 0;
        c.gridy = 1;
        panel4.add(kodlbl,c);

        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 2;
        panel4.add(kodFilmuField,c);

        c.gridx = 0;
        c.gridy = 2;
        panel4.add(nazwalbl,c);

        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 2;
        panel4.add(nazwaFilmuField,c);

        c.gridx = 0;
        c.gridy = 3;
        panel4.add(gatuneklbl,c);

        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 2;
        panel4.add(gatunekField,c);

        c.gridx = 0;
        c.gridy = 4;
        panel4.add(roklbl,c);

        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 2;
        panel4.add(rokField,c);

        c.gridx = 0;
        c.gridy = 5;
        panel4.add(godzinalbl,c);

        c.gridx = 1;
        c.gridy = 5;
        c.gridwidth = 2;
        panel4.add(godzinaField,c);

        c.gridx = 0;
        c.gridy = 6;
        panel4.add(nrSalilbl,c);

        c.gridx = 1;
        c.gridy = 6;
        c.gridwidth = 2;
        panel4.add(nrSaliField,c);

        c.gridx = 0;
        c.gridy = 7;
        panel4.add(cenalbl,c);

        c.gridx = 1;
        c.gridy = 7;
        c.gridwidth = 2;
        panel4.add(cenaField,c);

        c.gridx = 1;
        c.gridy = 8;
        panel4.add(bookButton,c);

    }

    //Funkcja tworząca etykietę i zwracający ją
    public JLabel createLabel(String napis){
        JLabel label = new JLabel(napis);
        return label;
    }
    //Funkcja tworząca pole tekstowe i zwracający je
    public JTextField creaTextField(int columns){
        JTextField txt = new JTextField(columns);
        txt.setHorizontalAlignment(txt.CENTER);
        txt.setEditable(false);
        return txt;
    }

    //Funkcja tworząca pole wyboru i zwracający je
    public Choice createChoice()
    {
        Choice choice = new Choice();

        choice.add("Kod");
        choice.add("Tytul");
        choice.add("Gatunek");
        choice.add("Rok");
        choice.add("Godzina");
        choice.add("Sala");
        choice.add("Cena");

        return choice;
    }

    //Funkcja tworząca, na podstawie ścieżki do grafiki, obraz i zwracający go
    public void createImage(String source){
        logo = new ImageIcon(source);
        iconLabel = new JLabel(logo);
        iconLabel.setHorizontalAlignment(JLabel.LEFT);
        iconLabel.setVerticalAlignment(JLabel.TOP);
    }

    //Funkcja tworząca przycisk i zwracający go
    public JButton createButton(String napis){
        JButton button = new JButton(napis);
        return button;
    }

    //Funkcja tworząca okno i przyjmująca panele
    public void setFrame(){
        frame = new JFrame();
        setPanel1();
        setPanel2();
        setPanel4();
        frame.add(panel1,BorderLayout.NORTH);
        frame.add(panel2, BorderLayout.CENTER);
        frame.add(panel4,BorderLayout.EAST);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Cinema Booking system");
        frame.pack();
        frame.setVisible(true);
    }

    //Funkcja tworząca tablicę filmów 
    public void createTable(LinkedHashMap<Integer, Film> films){
        TableModel model = new RepertuarTableModel(films);
        //Implementacja obiektu odpowiedzalnego za sortowanie po każdej z cech filmu
        mySorter = new TableRowSorter<TableModel>(model);
        
        table = new JTable(model);
        //Ustawienie sortowania
        table.setRowSorter(mySorter);
        //Wybór konkretnego filmu z repertuaru
        table.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseClicked(java.awt.event.MouseEvent evt){
                jTableMouseClicked(evt);
            }
        });
    }

    //Funkcja tworząca panel z tablicą filmów
    public void createPaneWithTable(){
        panelTable = new JScrollPane(table);
    }
 
    //Funkcja, która po wybraniu konkretnego filmu z repertuaru
    //Wyświetla szczegółowe informacje o nim
    public void jTableMouseClicked(java.awt.event.MouseEvent evt){

        String kodFilmu = table.getValueAt(table.getSelectedRow(), 0).toString();
        String nazwaFilmu = table.getValueAt(table.getSelectedRow(), 1).toString();
        String gatunekFilmu = table.getValueAt(table.getSelectedRow(), 2).toString();
        String rok = table.getValueAt(table.getSelectedRow(), 3).toString();
        String godzina = table.getValueAt(table.getSelectedRow(), 4).toString();
        String nrsali = table.getValueAt(table.getSelectedRow(), 5).toString();
        String cenaBiletu = table.getValueAt(table.getSelectedRow(), 6).toString();

        kodFilmuField.setText(kodFilmu); 
        nazwaFilmuField.setText(nazwaFilmu); 
        gatunekField.setText(gatunekFilmu); 
        rokField.setText(rok);
        godzinaField.setText(godzina); 
        nrSaliField.setText(nrsali);
        cenaField.setText(cenaBiletu);

    }

    @Override
    public void actionPerformed(ActionEvent e){

        //Jeśli zostanie kliknięty przycisk "Wybierz", to przechodzimy rezerwacji miejsca
        //na wyszczególniony seans
        if(e.getSource() == bookButton)
        {
            
            int kod = Integer.parseInt(kodFilmuField.getText());
            String nazwa = nazwaFilmuField.getText();
            String gatunek = gatunekField.getText();
            String rok = rokField.getText();
            int nrSali = Integer.parseInt(nrSaliField.getText());
            int cena = Integer.parseInt(cenaField.getText());
            String godz = godzinaField.getText();
            
            Film film = new Film(nazwa, kod, gatunek, rok, godz, nrSali, cena);
            GUI2 gui2 = new GUI2(film, user);
        }

        //Jeśli zostanie kliknięty przycisk "Szukaj", to odbywa się wyszukiwanie tytułu filmu
        //po wpisanej przez użytkownika frazie
        if(e.getSource() == sortButton)
        {
 
            String text = szukajField.getText();
            mySorter.setRowFilter(new MyFilter(text));

        }

        //Jeśli zostanie kliknięty przycisk "Sortuj", to odbywa się proces sortowania
        //po wybranej przez użyktownika kategorii, na podstawie sortowania LinkedHashMap
        if(e.getSource() == testButton)
        {

            //Pobranie wybranej kategorii
            String type = sortingValue.getSelectedItem();
            //Przekonwertowanie mapy na listę
            Set<Map.Entry<Integer, Film>> entrySet = films.entrySet();
            List<Map.Entry<Integer, Film>> list = new ArrayList<>(entrySet);

            //Sortowanie w zależności od wybranej kategorii
            switch (type) {
                case "Kod":

                    Collections.sort(list, (o1, o2) -> o1.getValue().getKodAsInteger().compareTo(o2.getValue().getCenaAsInteger()));
                    
                    break;

                case "Tytul":

                    Collections.sort(list, (o1,o2) -> o1.getValue().getNazwa().compareTo(o2.getValue().getNazwa()));

                    break;

                case "Gatunek":

                    Collections.sort(list, (o1,o2) -> o1.getValue().getGatunek().compareTo(o2.getValue().getGatunek()));

                    break;

                case "Rok":

                    Collections.sort(list, (o1,o2) -> o1.getValue().getRok().compareTo(o2.getValue().getRok()));

                    break;

                case "Godzina":

                    Collections.sort(list, (o1,o2) -> o1.getValue().getGodzina().compareTo(o2.getValue().getGodzina()));

                    break;

                case "Sala":

                    Collections.sort(list, (o1,o2) -> o1.getValue().getNrSaliAsInteger().compareTo(o2.getValue().getNrSaliAsInteger()));

                    break;

                case "Cena":

                    Collections.sort(list, (o1,o2) -> o1.getValue().getCenaAsInteger().compareTo(o2.getValue().getCenaAsInteger()));

                    break;
            
                default:
                    break;
            }

            //Wypisanie na terminalu posortowanej listy
            System.out.println(Arrays.toString(list.toArray()));

        }

    }
}

//Klasa odpowiadająca za wyszukiwanie tytułu filmu na podstawie wpisanej 
//frazy przez użytkownika
class MyFilter extends RowFilter{

    private String searchText;

    MyFilter(String searchText)
    {
        this.searchText = searchText;
        
    }

    @Override
    public boolean include(Entry entry)
    {
        return entry.getStringValue(1).indexOf(searchText) >= 0;
    }

}