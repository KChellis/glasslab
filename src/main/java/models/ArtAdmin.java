package models;

public class ArtAdmin extends Art{
    private String[] styles;

    public ArtAdmin(String name, String type, String[] materials, int price, String[] images, String description, String[] keywords, String[] styles) {
        super(name, type, materials, price, images, description, keywords);
        this.styles = styles;
    }
}
