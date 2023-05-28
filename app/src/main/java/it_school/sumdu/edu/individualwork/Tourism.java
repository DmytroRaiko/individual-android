package it_school.sumdu.edu.individualwork;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tourism")
public class Tourism {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String country;
    private String city;
    private String title;
    private String description;
    private Double price;
    private Double rating;
    private String dateStart;
    private String dateEnd;

    public Tourism(int id, String country, String city, String title, String description, Double price, double rating, String dateStart, String dateEnd) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.title = title;
        this.description = description;
        this.price = price;
        this.rating = rating;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }
}
