import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class API extends JFrame{
    private static Map<String, Product> hashTable;

    public void StartAPI()
    {
        loadAPI();
        for (Map.Entry entry: hashTable.entrySet()) {
            Object value = entry.getValue();
            System.out.println(" || "+ value.toString() );
        }
        InitComponents();
        //closeAPI();
    };

    public void InitComponents()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1356, 370));
       // panel.setLayout(null);
    };

    public API()
    {
        super("Automated workplace");
        StartAPI();
    };

    public static void main( String[] args )
    {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame.setDefaultLookAndFeelDecorated(true);
                API  frame = new API();
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    };

    private static void loadAPI()
    {
        //Initialize
        initializeHashTable();
       //addToHashTable( new Product("Булка 'Малятко'", 5,new ProductsGroup("хлебобулочные изделия"), "Булка", 3.75, "ТМ ДАР" ) );
       //addToHashTable( new Product("Пиво 'Оболонь'", 7,new ProductsGroup("алкогольніе напитки"), "Пиво", 10.25, "ТМ Оболонь" ) );
        try {
            Files.read();
        }catch (Exception exc)
        {
            System.out.println("File:"+exc.getMessage());
            System.out.println("File:"+exc.toString());
        }
    };
    public static void initializeHashTable()
    {
        hashTable = new HashMap<String , Product>();
    };

    public static void addToHashTable( Product product )
    {
        String key = String.valueOf( product.getHashCode() );
        hashTable.put( key , product );
    };
    private static void closeAPI()
    {
        Files.write( hashTable );
    };
}
