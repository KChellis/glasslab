package models;

public class Art {
    private String name;
    private String type;
    private String[] materials;
    private int price;
    private String[] images;
    private String description;
    private String[] keywords;
    private int id;

    public Art(String name, String type, String[] materials, int price, String[] images, String description, String[] keywords) {
        this.name = name;
        this.type = type;
        this.materials = materials;
        this.price = price;
        this.images = images;
        this.description = description;
        this.keywords = keywords;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String[] getMaterials() {
        return materials;
    }

    public int getPrice() {
        return price;
    }

    public String[] getImages() {
        return images;
    }

    public String getDescription() {
        return description;
    }

    public String[] getKeywords() {
        return keywords;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
