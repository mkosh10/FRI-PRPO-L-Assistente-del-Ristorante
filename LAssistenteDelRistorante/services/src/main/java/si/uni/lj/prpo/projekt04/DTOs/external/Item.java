package si.uni.lj.prpo.projekt04.DTOs.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {
    private String name;
    private Float calories;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Float getCalories() { return calories; }
    public void setCalories(Float calories) { this.calories = calories; }
}
