import javax.swing.table.*;
import java.util.*;

//Utworzenie własnego modelu tablicy
public class RepertuarTableModel extends AbstractTableModel{

    //private List<Film> films;
    private LinkedHashMap<Integer, Film> films;
    private String[] columns;

    public RepertuarTableModel(LinkedHashMap<Integer, Film> filmList){
        super();
        columns = new String[]{"Kod","Nazwa", "Gatunek", "Rok wydania", "Godzina", "Nr sali", "Cena"};
        films = filmList;
    }

    @Override
    public int getRowCount() {
        // TODO Auto-generated method stub
        return films.size();
    }

    @Override
    public int getColumnCount() {
        // TODO Auto-generated method stub
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        // TODO Auto-generated method stub
        Film film = films.get(rowIndex);
        switch(columnIndex){
            case 0: return film.getKod();
            case 1: return film.getNazwa();
            case 2: return film.getGatunek();
            case 3: return film.getRok();
            case 4: return film.getGodzina();
            case 5: return film.getNrSali();
            case 6: return film.getCena();
            default: return null;
        }
    }
    
    @Override
    public String getColumnName(int col){
        return columns[col];
    }


}

//public static class JTableButtonRenderer implements TableCellRenderer{
   // @Override public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
     //   JButton button = (JButton)value;
      //  return button;
   // }
//}