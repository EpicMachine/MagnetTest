package models;

public class Product {

    public Product() {

    }

    public Product(String dbUrl, String username, String password, int quantity) {
        this.dbUrl = dbUrl;
        this.username = username;
        this.password = password;
        this.quantity = quantity;
    }

    private int quantity;

    private String dbUrl;
    private String username;
    private String password;

    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
