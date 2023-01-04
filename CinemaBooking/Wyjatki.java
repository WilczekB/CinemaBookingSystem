import javax.swing.JOptionPane;

public class Wyjatki {
    

    public Wyjatki()
    {

    }
////////Wyjątek dla niepoprawnego wprowadzenia imienia i nazwiska
    public boolean checkForInvalidName(String element)
    {

        char[] chars = element.toCharArray();

        for(char c : chars)
        {
            if(Character.isDigit(c))
            {
                return false;
            }
        }

        return true;
    }
/////Sprawdzenie poprawności wprowadzenia adresu email, poprzez sprawdzenie czy zawiera @
    public boolean checkForInvalidEmail(String element)
    {

        String CONDITION = "@";
        if(!element.contains(CONDITION))
        {
            return false;
        }

        return true;
    }
////Wyświetlanie komunikatów 
    public void errorNameHandler()
    {
     
        JOptionPane.showMessageDialog(null, "Blad przy wprowadzaniu imienia, Prosze wpisac jeszcze raz");
        
    }

    public void errorSurnameHandler()
    {
        JOptionPane.showMessageDialog(null, "Blad przy wprowadzaniu nazwiska, Prosze wpisac jeszcze raz");
    }

    public void errorEmailHandler()
    {
        JOptionPane.showMessageDialog(null, "Blad przy wprowadzaniu emailu, brak znaku @. Prosze wpisac poprawny adres");
    }

}
