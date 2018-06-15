package models;

public class ArtUser extends Art{
    private String style;

    public ArtUser(String name, String type, String[] materials, int price, String[] images, String description, String[] keywords, String style) {
        super(name, type, materials, price, images, description, keywords);
        this.style = style;
    }
}
