package models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Order {
    private boolean isNew = true;
    private boolean sent = false;
    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private String state;
    private int zip;
    private String email;
    private String phone;
    private long createdAt;
    private String formattedCreatedAt;
    private int id;

    public Order(String firstName, String lastName, String street, String city, String state, int zip, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.email = email;
        this.phone = phone;
        this.createdAt = System.currentTimeMillis();
        setFormattedCreatedAt();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
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

    public int getZip() {
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
        Date date = new Date(this.createdAt);
        String datePatternToUse = "MM/dd/yyyy @ K:mm a";
        SimpleDateFormat sdf = new SimpleDateFormat(datePatternToUse);
        this.formattedCreatedAt = sdf.format(date);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
