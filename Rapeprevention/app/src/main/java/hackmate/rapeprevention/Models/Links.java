package hackmate.rapeprevention.Models;

public class Links {
    public String id;
    public String confirmLink;
    public String denyLink;
    public String chatLink;

    public Links(String id, String confirmLink, String denyLink, String chatLink){
        this.id = id;
        this.confirmLink = confirmLink;
        this.denyLink = denyLink;
        this.chatLink = chatLink;
    }

}
