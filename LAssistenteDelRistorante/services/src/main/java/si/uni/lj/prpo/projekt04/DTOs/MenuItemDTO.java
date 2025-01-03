package si.uni.lj.prpo.projekt04.DTOs;

public class MenuItemDTO {

    private String name;
    private String description;
    private double price;
    private boolean isAvailable;
    private boolean isVegetarian;
    private boolean isVegan;
    private boolean isGlutenFree;
    private String ingredients;
    private int calories;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public boolean isVegetarian() {
        return isVegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        isVegetarian = vegetarian;
    }

    public boolean isVegan() {
        return isVegan;
    }

    public void setVegan(boolean vegan) {
        isVegan = vegan;
    }

    public boolean isGlutenFree() {
        return isGlutenFree;
    }

    public void setGlutenFree(boolean glutenFree) {
        isGlutenFree = glutenFree;
    }



    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }
}
