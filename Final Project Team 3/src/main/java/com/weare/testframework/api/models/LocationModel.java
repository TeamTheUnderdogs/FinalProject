package com.weare.testframework.api.models;

public class LocationModel {
    private City city;
    private int id;

    // Getters and setters

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocationModel(int id, int cityId, String cityCity) {
        City city = new City();
        city.setId(cityId);
        city.setCity(cityCity);

        setId(id);
        setCity(city);
    }

    public class City {
        private String city;

        private int id;

        // Getters and setters

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
