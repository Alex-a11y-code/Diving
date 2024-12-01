package com.dive.lib;
import com.google.gson.annotations.SerializedName;

public class Player {
    @SerializedName("PreferredFirstName")  // 映射 JSON 中的 PreferredFirstName 字段
    private String preferredFirstName;

    @SerializedName("PreferredLastName")  // 映射 JSON 中的 PreferredLastName 字段
    private String preferredLastName;

    @SerializedName("Gender")  // 映射 JSON 中的 Gender 字段
    private int gender;  // 如果 Gender 是 0 或 1，可以选择直接存储为 int，或者映射为 String

    @SerializedName("CountryName")  // 映射 JSON 中的 CountryName 字段
    private String countryName;

    // 其他字段（如生日、照片等）如果有需要，继续添加

    // Getter 和 Setter 方法
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

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    // 拼接运动员姓名
    public String getFullName() {
        return this.preferredFirstName + " " + this.preferredLastName;
    }
    @Override
    public String toString() {
        return "Full Name: " + getFullName() + "\n" +
                "Gender: " + gender + "\n" +
                "Country: " + countryName;
    }
}
