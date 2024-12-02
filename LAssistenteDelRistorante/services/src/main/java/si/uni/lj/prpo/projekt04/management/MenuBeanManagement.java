package si.uni.lj.prpo.projekt04.management;



import si.uni.lj.prpo.projekt04.DTOs.MenuItemDTO;
import si.uni.lj.prpo.projekt04.DTOs.ReservationDTO;
import si.uni.lj.prpo.projekt04.MenuItem;
import si.uni.lj.prpo.projekt04.Reservation;
import si.uni.lj.prpo.projekt04.beans.MenuBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.logging.Logger;

@ApplicationScoped
public class MenuBeanManagement {
    private final Logger LOG = Logger.getLogger(MenuBeanManagement.class.getName());

    @Inject
    private MenuBean menuBean;

    public Optional<MenuItem> toMenuItemEntity(MenuItemDTO menuItemDto) {

        String name = menuItemDto.getName();
        String description = menuItemDto.getDescription();
        double price = menuItemDto.getPrice();
        String category = menuItemDto.getCategory();
        boolean isAvailable = menuItemDto.isAvailable();
        boolean isVegetarian = menuItemDto.isVegetarian();
        boolean isVegan = menuItemDto.isVegan();
        boolean isGlutenFree = menuItemDto.isGlutenFree();
        String ingredients = menuItemDto.getIngredients();
        int calories = menuItemDto.getCalories();

        if (name == null || description == null || category == null) {
            LOG.info("Not every parameter is present in MenuItemDTO");
            return Optional.empty();
        }

        MenuItem menuItemEntity = new MenuItem();
        menuItemEntity.setName(name);
        menuItemEntity.setDescription(description);
        menuItemEntity.setPrice(price);
        menuItemEntity.setCategory(category);
        menuItemEntity.setAvailable(isAvailable);
        menuItemEntity.setVegetarian(isVegetarian);
        menuItemEntity.setVegan(isVegan);
        menuItemEntity.setGlutenFree(isGlutenFree);
        menuItemEntity.setIngredients(ingredients);
        menuItemEntity.setCalories(calories);


        return Optional.of(menuItemEntity);
    }

    public boolean createNewMenuItem(MenuItemDTO menuItemDto){
        Optional<MenuItem> menuItem = toMenuItemEntity(menuItemDto);
        if(menuItem.isPresent()){
            return menuBean.createNewMenuItem(menuItem.get());
        } else {
            return false;
        }
    }

    public boolean updateMenuItem(Integer id, MenuItemDTO menuItemDTO){
        MenuItem currentMenuItem = menuBean.getMenuItemWithId(id);

        if (currentMenuItem != null) {
            if (menuItemDTO.getName() != null) {
                currentMenuItem.setName(menuItemDTO.getName());
            }
            if (menuItemDTO.getDescription() != null) {
                currentMenuItem.setDescription(menuItemDTO.getDescription());
            }
            if (menuItemDTO.getPrice() > 0) {
                currentMenuItem.setPrice(menuItemDTO.getPrice());
            }
            if (menuItemDTO.getCategory() != null) {
                currentMenuItem.setCategory(menuItemDTO.getCategory());
            }
            currentMenuItem.setAvailable(menuItemDTO.isAvailable());
            currentMenuItem.setVegetarian(menuItemDTO.isVegetarian());
            currentMenuItem.setVegan(menuItemDTO.isVegan());
            currentMenuItem.setGlutenFree(menuItemDTO.isGlutenFree());

            if (menuItemDTO.getIngredients() != null) {
                currentMenuItem.setIngredients(menuItemDTO.getIngredients());
            }
            if (menuItemDTO.getCalories() > 0) { // Assuming calories should be positive
                currentMenuItem.setCalories(menuItemDTO.getCalories());
            }

            currentMenuItem.setUpdatedAt(LocalDateTime.now()); // Assuming you have an updatedAt field for tracking

            return menuBean.updateMenuItem(currentMenuItem); // Assuming you have
        }

        return false;


    }

}
