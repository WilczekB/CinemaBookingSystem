import java.awt.*;

class Seat
{
    private final int boxheight = 30;
    private final int boxwidth = 50;
    private int seatnumber;
    private int seattaken;
    private int x;
    private int y;

    //Konstruktor
    public Seat(int number, int taken, int xstart, int ystart)
    {
        seatnumber = number;
        seattaken = taken;
        x = xstart;
        y = ystart;
    }

    //Funkcja rysująca wizualizację sali kinowej
    public void display(Graphics graf)
    {
        int xdraw, ydraw;
        switch (seattaken) {
            case 0:
                xdraw = x + boxwidth;
                ydraw = y + boxheight;
                graf.drawRect(x, y, boxwidth, boxheight);
                graf.drawString(Integer.toString(seatnumber), x+20, y+boxheight*3/4);
                break;
            case 1:
                xdraw = x + boxwidth;
                ydraw = y + boxheight;
                graf.drawRect(x, y, boxwidth, boxheight);
                String msg = "Zajete";
                graf.drawString(msg, x+20, y+boxheight*3/4);
                break;
        
            default:
                break;
        }
    }

    //Setter i getter
    public int isTaken()
    {
        return seattaken;
    }

    public void setSeat()
    {
        seattaken = 1;
    }
}