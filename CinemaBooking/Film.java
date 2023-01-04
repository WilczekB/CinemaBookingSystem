import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Film {
    
    String nazwa;
    int kod;
    String gatunek;
    String rok;
    String godzina;
    int nrSali;
    int cena;

    //Konstruktor
    public Film(String n, int k, String g, String r, String godz, int s, int c){
        this.kod = k;
        this.nazwa = n;
        this.gatunek = g;
        this.rok = r;
        this.godzina = godz;
        this.nrSali = s;
        this.cena = c;
    }

    //Settery i gettery
    public String getNazwa(){
        return nazwa;
    }

    public void setNazwa(String nazwa){
        this.nazwa = nazwa;
    }

    public int getKod(){
        return kod;
    }

    public Integer getKodAsInteger(){
        Integer ikod = Integer.valueOf(kod);
        return ikod;
    }

    public void setKod(Integer kod){
        this.kod = kod;
    }

    public String getGatunek(){
        return gatunek;
    }

    public void setGatunek(String gatunek){
        this.gatunek = gatunek;
    }

    public String getRok(){
        return rok;
    }

    public void setRok(String rok){
        this.rok = rok;
    }

    public String getGodzina(){
        return godzina;
    }

    public void setGodzina(String godzina){
        this.godzina = godzina;
    }

    public int getNrSali(){
        return nrSali;
    }

    public Integer getNrSaliAsInteger()
    {
        Integer inrSali = Integer.valueOf(nrSali);
        return inrSali;
    }

    public void setNrSali(Integer nrSali){
        this.nrSali = nrSali;
    }

    public int getCena(){
        return cena;
    }

    public Integer getCenaAsInteger(){
        Integer iCena = Integer.valueOf(cena);
        return iCena;
    }

    public void setCena(Integer cena){
        this.cena = cena;
    }

    //Funkcja pomocnicza do wczytywania filmów z pliku
    //Pobiera dane z tablicy stringów i na ich podstawie
    //tworzy odpowiednie atrybuty nowego filmu
    //na koniec zwraca utworzony obiekt Film
    private static Film createFilm(String[] metadata){
        
        Integer kod = Integer.parseInt(metadata[0]);
        String nazwa = metadata[1];
        String gatunek = metadata[2];
        String rok = metadata[3];
        String godzina = metadata[4];
        Integer nrSali = Integer.parseInt(metadata[5]);
        Integer cena = Integer.parseInt(metadata[6]);

        return new Film(nazwa, kod, gatunek, rok, godzina, nrSali, cena);
    }

    //Główna funkcja pobierająca dane dotyczące filmów z pliku
    //Funkcja przechodzi przez cały plik i tworzy LinkedHashMap
    //zawierającą cały repertuar filmów
    public static LinkedHashMap<Integer,Film> getDataFromFile(String path){
        LinkedHashMap<Integer,Film> films = new LinkedHashMap<>();
        String[] values;
        String line="";
        int key = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader((path)));

            while((line = br.readLine()) != null){
                values = line.split(",");
                Film film = createFilm(values);
                films.put(key,film);
                key++;
            }

            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return films;
    }

    //Funkcja wywołująca program
    public static void main(String[] args){
        LinkedHashMap<Integer, Film> films = getDataFromFile("Data/Repertuar.csv");
        Login log = new Login(films);

    }
}
