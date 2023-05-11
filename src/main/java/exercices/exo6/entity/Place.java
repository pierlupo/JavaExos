package exercices.exo6.entity;

public class Place {

    private int id;
    private Event event;
    private String name;
    private String address;
    private int capacity;

    public Place(int id, Event event, String name, String address, int capacity) {
        this(event,name,address,capacity);
        this.id = id;
    }

    public Place(Event event, String name, String address, int capacity) {
        this.event = event;
        this.name = name;
        this.address = address;
        this.capacity = capacity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Place{" +
                "id=" + id +
                ", event=" + event +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}
