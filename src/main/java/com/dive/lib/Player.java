package com.dive.lib;

public class Player {
    private String preferredLastName;
    private String preferredFirstName;

    private String gender;
    private String countryName;

    public String getPreferredFirstName() {
        return preferredFirstName;
    }
    public void setPreferredFirstName(String preferredFirstName) {
        this.preferredFirstName = preferredFirstName;
    }
    public String getPreferredLastName() {
        return preferredLastName;

    }
    public void setPreferredLastName(String preferredLastName) {
        this.preferredLastName = preferredLastName;
    }

    public String getGender() {

        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public String getCountryName() {

        return countryName;
    }
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
    @Override
    public String toString() {
        return "Player{" +
                "preferredFirstName='" + preferredFirstName + '\'' +
                ", preferredLastName='" + preferredLastName + '\'' +
                ", gender='" + gender + '\'' +
                ", countryName='" + countryName + '\'' +
                '}';
    }
}
