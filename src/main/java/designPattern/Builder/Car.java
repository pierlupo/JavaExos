package designPattern.Builder;

public class Car {

    private String brand;
    private String model;
    private String color;
    private int year;

        public Car(Builder builder){
            brand = builder.brand;
            model = builder.model;
            color = builder.color;
            year = builder.year;
        }
        public static class Builder {
            private String brand;
            private String model;
            private String color;
            private int year;

        public Builder brand(String brand){
            this.brand = brand;
            return this;
        }

        public Builder color(String color) {
            this.color = color;
            return this;
        }
        public Builder model(String model) {
            this.model = model;
            return this;
        }
        public Builder year(int year) {
            this.year = year;
            return this;
        }

        public Car build() {
            return new Car(this);
        }
    }
}

