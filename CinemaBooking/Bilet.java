class Bilet
{
    private int price;
    private String tickettype;
    private int seatnum;
    private String timeticket;

    //Konstruktor
    public Bilet(String type, int seatnumber, String time)
    {
        seatnum = seatnumber;
        tickettype = type;
        timeticket = time;

        //Ustawienie ceny biletu na podstawie wybranego typu biletu
        if(type == "Dorosly")
        {
            price = 50;
        }

        if(type == "Student")
        {
            price = 25;
        }

        if(type == "Dziecko")
        {
            price = 15;
        }
    }

    //Gettery
    public int getSeatPrice()
    {
        return price;
    }

    public String getTime()
    {
        return timeticket;
    }

    public String getType()
    {
        return tickettype;
    }

    public int getSeatNum()
    {
        return seatnum;
    }


}