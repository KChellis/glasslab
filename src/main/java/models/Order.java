package models;

public class Order {
    private boolean isNew = true;
    private boolean sent = false;
    private String fname;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String email;
    private String phone;
    private long createdAt;
    private String formattedCreatedAt;

    public Order(String fname, String street, String city, String state, String zip, String email, String phone) {
        this.fname = fname;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.email = email;
        this.phone = phone;
        this.createdAt = System.currentTimeMillis();
        setFormattedCreatedAt();
    }

    public String getFname() {
        return fname;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }

    public long getCreatedAt() {
        return createdAt;
    }


    public String getFormattedCreatedAt() {
        return formattedCreatedAt;
    }

    public void setFormattedCreatedAt() {

    }
}
