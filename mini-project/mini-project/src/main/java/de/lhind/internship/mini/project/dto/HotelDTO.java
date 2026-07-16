package de.lhind.internship.mini.project.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;


@Getter
public class HotelDTO {

    private Long Id;
    @NotBlank
    private String name;

    private String city;
    @NotBlank
    private String address;

    private float starRating;

    public HotelDTO(Long id, String name, String city, String address, float starRating) {
        Id = id;
        this.name = name;
        this.city = city;
        this.address = address;
        this.starRating = starRating;
    }

    public HotelDTO(String name, String city, String address, float starRating) {

        this.name = name;
        this.city = city;
        this.address = address;
        this.starRating = starRating;
    }

    public HotelDTO() {
    }


    public Long getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public float getStarRating() {
        return starRating;
    }

    public void setId(Long id) {
        Id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setStarRating(float starRating) {
        this.starRating = starRating;
    }
}
