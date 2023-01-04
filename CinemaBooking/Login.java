import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;


public class Login extends JFrame implements ActionListener {
    
    LinkedHashMap<Integer,Film> filmy;
    User user;
    JTextField nameField, surnameField, emailField;
    JLabel namelbl, surnamelbl, emaillbl, infolbl;
    JButton loginButton;

    //Konstruktor
    public Login(LinkedHashMap<Integer, Film> films){
        setSize(800,500);
        setLocation(200,200);
        setResizable(true);
        setLayout(new GridBagLayout());

        filmy = films;

        GridBagConstraints c = new GridBagConstraints();

        //Tworzenie elementów GUI
        infolbl = new JLabel("Logowanie do systemu");
        infolbl.setFont(new Font("Verdana", Font.BOLD, 10));

        namelbl = new JLabel("Imie: ");
        namelbl.setFont(new Font("Verdana", Font.BOLD, 10));

        surnamelbl = new JLabel("Nazwisko: ");
        surnamelbl.setFont(new Font("Verdana", Font.BOLD, 10));

        emaillbl = new JLabel("email: ");
        emaillbl.setFont(new Font("Verdana", Font.BOLD, 10));

        nameField = new JTextField(13);
        nameField.setHorizontalAlignment(nameField.CENTER);
        nameField.setEditable(true);

        surnameField = new JTextField(35);
        surnameField.setHorizontalAlignment(surnameField.CENTER);
        surnameField.setEditable(true);

        emailField = new JTextField(30);
        emailField.setHorizontalAlignment(emailField.CENTER);
        emailField.setEditable(true);

        loginButton = new JButton("Zaloguj");

        //Odstępy między elementami
        c.insets = new Insets(10,10,10,10);

        //Pozycjonowanie elementów GUI
        c.gridx = 1;
        c.gridy = 0;
        getContentPane().add(infolbl,c);
        c.gridx = 0;
        c.gridy = 1;
        getContentPane().add(namelbl,c);
        c.gridx = 1;
        c.gridy = 1;
        getContentPane().add(nameField,c);
        c.gridx = 0;
        c.gridy = 2;
        getContentPane().add(surnamelbl,c);
        c.gridx = 1;
        c.gridy = 2;
        getContentPane().add(surnameField,c);
        c.gridx = 0;
        c.gridy = 3;
        getContentPane().add(emaillbl,c);
        c.gridx = 1;
        c.gridy = 3;
        getContentPane().add(emailField,c);
        c.gridx = 1;
        c.gridy = 4;
        getContentPane().add(loginButton,c);

        setTitle("Logowanie");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        loginButton.addActionListener(this);

    }
/////Wykonywanie poleceń po wciśnięciu danego przycisku
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == loginButton)
        {
            String name, surname, email;

            name = nameField.getText();
            surname = surnameField.getText();
            email = emailField.getText();

            //Sprawdzenie poprawnego podania imienia
            Wyjatki wyjatek = new Wyjatki();

            boolean conditionForName = wyjatek.checkForInvalidName(name);
            boolean conditionForSurname  = wyjatek.checkForInvalidName(surname);
            boolean conditionForEmail = wyjatek.checkForInvalidEmail(email);

            if(conditionForName == true && conditionForSurname == true && conditionForEmail){

                user = new User(name, surname, email);
                GUI gui = new GUI(filmy, user);

            }else if(conditionForName == false){
            
                wyjatek.errorNameHandler();
            
            }else if(conditionForSurname == false){

                wyjatek.errorSurnameHandler();

            }else if(conditionForEmail == false){
                wyjatek.errorEmailHandler();
            }
        }
    }

}
