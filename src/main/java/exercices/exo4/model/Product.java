package exercices.exo4.model;

public class Product {
    private int idProduct;
    private String name;
    private float price;
    private int quantity;
    private String description;

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public Product(String name, float price, int quantity, String description) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
    }

    public Product(int idProduct, String name, float price, int quantity, String description) {
        this(name,price,quantity,description);
        this.idProduct = idProduct;

    }

    public int getIdProduct() {
        return idProduct;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "Product{" +
                "idProduct=" + idProduct +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", description='" + description + '\'' +
                '}';
    }
}
