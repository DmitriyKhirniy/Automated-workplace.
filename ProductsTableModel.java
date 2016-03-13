import javax.swing.table.AbstractTableModel;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.StringTokenizer;

public class ProductsTableModel extends AbstractTableModel {
    private List<Product> products;

    public ProductsTableModel(List<Product> products)
    {
        this.products = products;
    };

    @Override
    public int getRowCount() {
        return products.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public String getColumnName(int c) {
        String result = "";
        switch (c) {
            case 0:
                result = "Назва";
                break;
            case 1:
                result = "Кількість";
                break;
            case 2:
                result = "Група товарів";
                break;
            case 3:
                result = "Опис";
                break;
            case 4:
                result = "Ціна";
                break;
            case 5:
                result = "Виробник";
                break;
            case 6:
                result= "Загальна ціна";
                break;
        }
        return result;
    };

    @Override
    public Object getValueAt(int r, int c) {
        switch (c) {
            case 0:
                return products.get(r).getTitle();
            case 1:
                return products.get(r).getQuantity();
            case 2:
                return products.get(r).getGroup().getTitle();
            case 3:
                return products.get(r).getDescription();
            case 4:
                return products.get(r).getPrize();
            case 5:
                return products.get(r).getProducer();
            case 6:
                return products.get(r).getResultPrize();
            default:
                return "";
        }
    };

    @Override
    public boolean isCellEditable(int row, int column) {
        if (column == 6) return false;
        return true;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        try {
            switch (columnIndex) {
                case 0:
                    products.get(rowIndex).setTitle( value.toString() );
                    break;
                case 1:
                    products.get(rowIndex).setQuantity( Integer.valueOf( value.toString() ) );
                    fireTableCellUpdated(rowIndex, 6);
                    break;
                case 2:
                    products.get(rowIndex).setGroup( new ProductsGroup( value.toString() ) );
                    break;
                case 3:
                    products.get(rowIndex).setDescription( value.toString() );
                    break;
                case 4:
                    products.get(rowIndex).setPrize( Double.valueOf( value.toString() ) );
                    fireTableCellUpdated(rowIndex, 6);
                    break;
                case 5:
                    products.get(rowIndex).setProducer( value.toString() );
                    break;
            }

        }
        catch( Exception exc ){

        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }
}
