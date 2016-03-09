import java.util.HashMap;
import java.util.Map;

public class API {
    private static Map<String, Product> hashTable;
    public static void main( String[] args )
    {

        loadAPI();
        for (Map.Entry entry: hashTable.entrySet()) {
            Object value = entry.getValue();
            System.out.println(" || "+ value.toString() );
        }
        //closeAPI();


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
