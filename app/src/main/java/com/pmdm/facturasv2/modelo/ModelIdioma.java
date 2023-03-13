package com.pmdm.facturasv2.modelo;

public class ModelIdioma {
    private int id;
    private String countryCode;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ModelIdioma(int id, String countryCode, String description) {
        this.id = id;
        this.countryCode = countryCode;
        this.description = description;
    }
}
