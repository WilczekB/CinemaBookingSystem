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
    //Panel zawiera tablic?? przechowuj??c?? ca??y repertuar kina
    public void setPanel2(){
        panel2 = new JPanel();
        createTable(films);
        createPaneWithTable();
        panel2.add(panelTable);
    }

    //Tworzenie panelu o oznaczeniu nr 4
    //Panel zawiera szczeg????owe informacje o filmie,
    //kt??ry u??ytkownik wybra?? z repertuaru
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

    //Funkcja tworz??ca etykiet?? i zwracaj??cy j??
    public JLabel createLabel(String napis){
        JLabel label = new JLabel(napis);
        return label;
    }
    //Funkcja tworz??ca pole tekstowe i zwracaj??cy je
    public JTextField creaTextField(int columns){
        JTextField txt = new JTextField(columns);
        txt.setHorizontalAlignment(txt.CENTER);
        txt.setEditable(false);
        return txt;
    }

    //Funkcja tworz??ca pole wyboru i zwracaj??cy je
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

    //Funkcja tworz??ca, na podstawie ??cie??ki do grafiki, obraz i zwracaj??cy go
    public void createImage(String source){
        logo = new ImageIcon(source);
        iconLabel = new JLabel(logo);
        iconLabel.setHorizontalAlignment(JLabel.LEFT);
        iconLabel.setVerticalAlignment(JLabel.TOP);
    }

    //Funkcja tworz??ca przycisk i zwracaj??cy go
    public JButton createButton(String napis){
        JButton button = new JButton(napis);
        return button;
    }

    //Funkcja tworz??ca okno i przyjmuj??ca panele
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

    //Funkcja tworz??ca tablic?? film??w 
    public void createTable(LinkedHashMap<Integer, Film> films){
        TableModel model = new RepertuarTableModel(films);
        //Implementacja obiektu odpowiedzalnego za sortowanie po ka??dej z cech filmu
        mySorter = new TableRowSorter<TableModel>(model);
        
        table = new JTable(model);
        //Ustawienie sortowania
        table.setRowSorter(mySorter);
        //Wyb??r konkretnego filmu z repertuaru
        table.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseClicked(java.awt.event.MouseEvent evt){
                jTableMouseClicked(evt);
            }
        });
    }

    //Funkcja tworz??ca panel z tablic?? film??w
    public void createPaneWithTable(){
        panelTable = new JScrollPane(table);
    }
 
    //Funkcja, kt??ra po wybraniu konkretnego filmu z repertuaru
    //Wy??wietla szczeg????owe informacje o nim
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

        //Je??li zostanie klikni??ty przycisk "Wybierz", to przechodzimy rezerwacji miejsca
        //na wyszczeg??lniony seans
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

        //Je??li zostanie klikni??ty przycisk "Szukaj", to odbywa si?? wyszukiwanie tytu??u filmu
        //po wpisanej przez u??ytkownika frazie
        if(e.getSource() == sortButton)
        {
 
            String text = szukajField.getText();
            mySorter.setRowFilter(new MyFilter(text));

        }

        //Je??li zostanie klikni??ty przycisk "Sortuj", to odbywa si?? proces sortowania
        //po wybranej przez u??yktownika kategorii, na podstawie sortowania LinkedHashMap
        if(e.getSource() == testButton)
        {

            //Pobranie wybranej kategorii
            String type = sortingValue.getSelectedItem();
            //Przekonwertowanie mapy na list??
            Set<Map.Entry<Integer, Film>> entrySet = films.entrySet();
            List<Map.Entry<Integer, Film>> list = new ArrayList<>(entrySet);

            //Sortowanie w zale??no??ci od wybranej kategorii
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

//Klasa odpowiadaj??ca za wyszukiwanie tytu??u filmu na podstawie wpisanej 
//frazy przez u??ytkownika
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