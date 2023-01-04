public class Sala {
    private int liczbaMiejsc;
    private int rzedy;
    private int iloscBiletow;
    private int nrSali;

    //Konstruktor
    public Sala(int lm, int r, int ws, int nr){
        this.liczbaMiejsc = lm;
        this.rzedy = r;
        this.iloscBiletow = r*lm;
        this.nrSali = nr;
    }

    //Settery i gettery
    public int getLiczbaMiejsc(){
        return liczbaMiejsc;
    }

    public void setLiczbaMiejsc(int liczbaMiejsc){
        this.liczbaMiejsc = liczbaMiejsc;
    }

    public int getRzedy(){
        return rzedy;
    }

    public void setRzedy(int rzedy){
        this.rzedy = rzedy;
    }

    public int getIloscBiletow(){
        return iloscBiletow;
    }

    public void setIloscBiletow(int liczbaMiejsc, int rzedy){
        this.iloscBiletow = liczbaMiejsc*rzedy;
    }

    public int getNrSali(){
        return nrSali;
    }

    public void setNrSali(int nrSali){
        this.nrSali = nrSali;
    }

    //Utworzenie sali na podstawie numeru sali
    public void createSala(int numer){
        switch (numer) {
            case 1:
                setLiczbaMiejsc(15);
                setRzedy(7);
                break;
            case 2:
                setLiczbaMiejsc(12);
                setRzedy(5);
                break;
            case 3:
                setLiczbaMiejsc(10);
                setRzedy(6);
            default:
                setLiczbaMiejsc(4);
                setRzedy(3);
                break;
        }
    }

    
}
