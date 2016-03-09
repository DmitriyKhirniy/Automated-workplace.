import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Files {
    private static String directoryPath = "E://laba/";
    private static String fileName = "Products1.txt";

    public static void write( Map<String, Product> hashTable ) {
        //Определяем файл
        File file = new File(directoryPath + fileName);

        try {
            //проверяем, что если файл не существует то создаем его
            if (!file.exists()) {
                file.createNewFile();
            }

            //PrintWriter обеспечит возможности записи в файл
            PrintWriter out = new PrintWriter(file.getAbsoluteFile());

            try {
                //Записываем текст у файл
                for (Map.Entry entry : hashTable.entrySet()) {
                    Object value = entry.getValue();
                    out.print(value.toString() + " | ");
                }
            } finally {
                //После чего мы должны закрыть файл
                //Иначе файл не запишется
                out.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void read() throws FileNotFoundException {

        File file = exists(directoryPath+fileName);
        try {
            //Объект для чтения файла в буфер
            BufferedReader in = new BufferedReader(new FileReader( file.getAbsoluteFile()));
            try {
                //В цикле построчно считываем файл
                String s;
                StringBuilder string = new StringBuilder();
                while ((s = in.readLine()) != null) {

                    for (int i=0;i<s.length();i++)
                    {
                        if( s.charAt(i) != '|' )string.append(s.charAt(i));
                        else {
                            System.out.println("In Files:"+string);
                            API.addToHashTable( getProduct(new String( string)) );
                            string = new StringBuilder();
                        }
                    }

                }
            } finally {
                //Также не забываем закрыть файл
                in.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static File exists(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        if (!file.exists()){
            throw new FileNotFoundException(file.getName());
        }
        else return file;
    }

    private static Product getProduct(String string)
    {
        String[] array = new String[6];
        StringTokenizer str = new StringTokenizer(string);
        StringBuilder current = new StringBuilder();
        int properties = 0;
        while (str.hasMoreTokens())
        {
            String s = str.nextToken();
            if(s.equals(Product.tags[properties].getOpenTag().replaceAll(" ", "")))
            {
                s = str.nextToken();
                current.append(s+" ");
                while(!s.equals( Product.tags[properties].getCloseTag().replaceAll(" ", "")) )
                {
                    s = str.nextToken();
                    if(!s.equals( Product.tags[properties].getCloseTag().replaceAll(" ", ""))) current.append(s+" ");
                }
            }
            array[properties++] = new String( current );
            current = new StringBuilder();
        }
       return createProduct( array );
    };

    private static Product createProduct( String[] array )
    {
       // System.out.println("|"+array[1]+"|");
        return new Product(array[0] , Integer.valueOf( array[1].replaceAll(" ", "")) , new ProductsGroup( array[2]) , array[3] , Double.valueOf( array[4] ) , array[5] );
    };
}
