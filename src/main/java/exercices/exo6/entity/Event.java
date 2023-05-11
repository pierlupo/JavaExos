package exercices.exo6.entity;

public class Event {

    private int id;
    private Customer customer;
    private String name;
    private String date;
    private String hour;
    private Place place;
    private Float price;
    private int numOfTicketsSold;

    public Event(Customer customer, String name, String date, String hour, Place place, Float price, int numOfTicketsSold) {
        this.customer = customer;
        this.name = name;
        this.date = date;
        this.hour = hour;
        this.place = place;
        this.price = price;
        this.numOfTicketsSold = numOfTicketsSold;
    }

    public Event(int id, Customer customer, String name, String date, String hour, Place place, Float price, int numOfTicketsSold) {
        this(customer,name,date,hour,place,price,numOfTicketsSold);
        this.id = id;
    }


    public Event(String name, String date, String hour, Place place, float price) {
        this.name = name;
        this.date = date;
        this.hour = hour;
        this.place = place;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

//    public int getPlace() {
//        return place;
//    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public int getNumOfTicketsSold() {
        return numOfTicketsSold;
    }

    public void setNumOfTicketsSold(int numOfTicketsSold) {
        this.numOfTicketsSold = numOfTicketsSold;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", customer=" + customer +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", hour='" + hour + '\'' +
                ", place=" + place +
                ", price=" + price +
                ", numOfTicketsSold=" + numOfTicketsSold +
                '}';
    }
}
