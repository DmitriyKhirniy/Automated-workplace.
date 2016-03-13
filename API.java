import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;
import java.util.List;

public class API extends JFrame{

    private static Map<String, Product> hashTable;
    private static List<Product> products = new ArrayList<>();;
    private static List<ProductsGroup> productsGroups = new ArrayList<>();
    private static JComboBox comboBoxproductsGroups = new JComboBox();

    //The model of data
    private static ProductsTableModel model = new ProductsTableModel( products );

    //Main panel
    private static JPanel panel = new JPanel();

    //Main table
    private static JTable table;


    public void StartAPI()
    {
        loadAPI();
        for (Product product : products)
        {
            System.out.println(product.toString());
        }
        InitComponents();

    };

    public void InitComponents()
    {
        panel.setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1356, 370));

        table.setPreferredScrollableViewportSize(new Dimension(1040, 200) );
        JScrollPane ScrollPane = new JScrollPane(table);
        panel.add(ScrollPane).setBounds(230,90,1040,200);

        getContentPane().add(panel);

        initGUI();

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
              closeAPI();
            }
        });
    };

    private static void initGUI()
    {
        createAddProductButton();
        createREmoveProductButton();
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

    private static void createTable()
    {
        table = new JTable( model );
        setFont();
        setStyleOfTable();
    };

    private static void loadAPI()
    {
        //Initialize
      // addToList( new Product("Булка 'Малятко'", 5,new ProductsGroup("хлебобулочные изделия"), "Булка", 3.75, "ТМ ДАР" ) );
       //addToList( new Product("Пиво 'Оболонь'", 7,new ProductsGroup("алкогольніе напитки"), "Пиво", 10.25, "ТМ Оболонь" ) );
        loadData();

        createTable();
        setStartProductsGroup();
        setComboBoxProductsGroup();
    };


    public static void addToList( Product product )
    {
        products.add( product );
    };

    private static void closeAPI()
    {
        Files.write( products );
    };

    private static void setFont()
    {
        table.setFont( new Font("Serif", Font.BOLD, 16) );
    };

    private static void setStyleOfTable()
    {
        table.setRowHeight( 30 );
        table.getColumnModel().getColumn(0).setMinWidth(180);
        table.getColumnModel().getColumn(0).setMaxWidth(180);
        table.getColumnModel().getColumn(1).setMinWidth(40);
        table.getColumnModel().getColumn(1).setMaxWidth(40);
        table.getColumnModel().getColumn(2).setMinWidth(210);
        table.getColumnModel().getColumn(2).setMaxWidth(210);
        table.getColumnModel().getColumn(3).setMinWidth(250);
        table.getColumnModel().getColumn(3).setMaxWidth(250);
        table.getColumnModel().getColumn(4).setMinWidth(60);
        table.getColumnModel().getColumn(4).setMaxWidth(60);
        table.getColumnModel().getColumn(5).setMinWidth(210);
        table.getColumnModel().getColumn(5).setMaxWidth(210);
        table.getColumnModel().getColumn(6).setMinWidth(90);
        table.getColumnModel().getColumn(6).setMaxWidth(90);

        table.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(comboBoxproductsGroups));

    };

    public static  List<ProductsGroup> getProductsGroups()
    {
        return productsGroups;
    };

    public static void addProductGroup(ProductsGroup productsGroup)
    {
        productsGroups.add( productsGroup );
    };

    private static void setStartProductsGroup()
    {
        addProductGroup( new ProductsGroup("Хлібобулочні вироби") );
        addProductGroup( new ProductsGroup("Напої") );
        addProductGroup( new ProductsGroup("Спиртні напої") );
    };

    private static void setComboBoxProductsGroup()
    {
        for(int i=0;i<productsGroups.size();i++)
        {
            comboBoxproductsGroups.addItem( productsGroups.get(i).getTitle() );
        };
        comboBoxproductsGroups.addItem("Не визначено");
    };

    private static void createAddProductButton()
    {
        JButton btnAddProduct = new JButton("Додати товар");
        panel.add( btnAddProduct ).setBounds(200,30,150,35);

        btnAddProduct.addActionListener( event -> {
            addToList( new Product( "Назва продукту", 0 , new ProductsGroup( "Не визначено"), "Опис товару", 0, "Виробник" ) );
            model.fireTableDataChanged();
        } );
    };

    private static void createREmoveProductButton()
    {
        JButton btnRemoveProduct = new JButton("Видалити товар");
        panel.add( btnRemoveProduct ).setBounds(400,30,150,35);

        btnRemoveProduct.addActionListener( event -> {
            if (table.getSelectedRows().length > 0) products.remove(table.getSelectedRows()[0]);
            model.fireTableDataChanged();
        } );

    };

    private static void loadData()
    {
        try {
            Files.read();
        }catch (Exception exc)
        {
            System.out.println("File:"+exc.getMessage());
            System.out.println("File:"+exc.toString());
        }
    };
}
