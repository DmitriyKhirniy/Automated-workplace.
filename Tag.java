
public class Tag {
    private String title;
    private String openTag;
    private String closeTag;

    public Tag( String title, String openTag , String closeTag )
    {
        this.title = title;
        this.openTag = openTag;
        this.closeTag = closeTag;
    };

    public String getCloseTag() {
        return closeTag;
    };

    public String getOpenTag() {
        return openTag;
    };

    public String getTitle() {
        return title;
    };
}
