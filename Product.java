
public class Product {
    private String title;
    private int quantity;
    private ProductsGroup group;
    private String description;
    private double prize;
    private String producer;

    public static Tag[] tags = {
            new Tag("title", " <title> " , " </title>"),
            new Tag("quantity", " <quantity> " , " </quantity>"),
            new Tag("group", " <group> " , " </group>"),
            new Tag("description", " <description> " , " </description>"),
            new Tag("prize", " <prize> " , " </prize> "),
            new Tag("producer", " <producer> " , " </producer> ")
    };


    public Product(String title, int quantity, ProductsGroup group ,String description, double prize , String producer )
    {
        setTitle(title);
        setQuantity(quantity);
        setGroup(group);
        setDescription(description);
        setPrize(prize);
        setProducer(producer);
    }

    public int getQuantity() {
        return quantity;
    };

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    };

    public void setTitle(String title) {
        this.title = title;
    };

    public double getPrize() {
        return prize;
    };

    public void setPrize(double prize) {
        this.prize = prize;
    }

    public ProductsGroup getGroup() {
        return group;
    };

    public String getDescription() {
        return description;
    };

    public String getProducer() {
        return producer;
    };

    public void setDescription(String description) {
        this.description = description;
    };

    public void setGroup(ProductsGroup group) {
        this.group = group;
    };

    public void setPrize(float prize) {
        this.prize = prize;
    };

    public void setProducer(String producer) {
        this.producer = producer;
    };

    public int getHashCode()
    {
        return this.hashCode();
    };

    @Override
    public String toString()
    {
        StringBuilder string = new StringBuilder();
        string.append( packPropertyValue("title", this.getTitle()) );
        string.append( packPropertyValue("quantity", Integer.toString( this.getQuantity()) ) );
        string.append( packPropertyValue("group", this.getGroup().getTitle()) );
        string.append( packPropertyValue("description", this.getDescription()) );
        string.append( packPropertyValue("prize", Double.toString( this.getPrize()) ) );
        string.append( packPropertyValue("producer", this.getProducer()) );
        return new String( string );
    };

    private String packPropertyValue(String property, String value)
    {
        String string = null;
        for (int i=0;i<tags.length;i++)
        {
            if(property.equals( tags[i].getTitle() ))
            {
                string = new String( tags[i].getOpenTag()+value+tags[i].getCloseTag() );
            }
        }
        return string;
    };
}
