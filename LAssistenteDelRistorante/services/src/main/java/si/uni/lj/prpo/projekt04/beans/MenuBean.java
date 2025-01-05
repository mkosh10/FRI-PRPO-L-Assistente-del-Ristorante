package si.uni.lj.prpo.projekt04.beans;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import org.json.simple.parser.JSONParser;
import si.uni.lj.prpo.projekt04.DTOs.external.Item;
import si.uni.lj.prpo.projekt04.DTOs.external.Nutrition;
import si.uni.lj.prpo.projekt04.MenuItem;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.stream.JsonLocation;
import javax.json.stream.JsonParser;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;

@RequestScoped
public class MenuBean {

    private Client httpClient;
    private String API_URL;
    private String API_KEY;

    private JsonParser parser;
    @PersistenceContext(unitName = "l-assistente-del-ristorante-jpa")
    private EntityManager em;

    @PostConstruct
    public void init(){
        httpClient = ClientBuilder.newClient();
        API_URL = "https://api.calorieninjas.com/v1/nutrition";
        API_KEY = "7WOjamiscs0YLNXltficlg==1Axu7GutiFny1nJe";
    }

    private final Logger LOG = Logger.getLogger(MenuBean.class.getName());

    public List<MenuItem> getAllMenuItems(){
        TypedQuery<MenuItem> query = em.createNamedQuery("Menu.getAll", MenuItem.class);
        return  query.getResultList();
    }

    public int getMenuListSize(){
        return getAllMenuItems().size();
    }

    public List<MenuItem> getAllMenuItems(QueryParameters query) {
        return JPAUtils.queryEntities(this.em, MenuItem.class, query);
    }

    public long getMenuListSize(QueryParameters query) {
        return JPAUtils.queryEntitiesCount(this.em, MenuItem.class, query);
    }

    public MenuItem getMenuItemWithId(Integer id){
        try {
            TypedQuery<MenuItem> query = em.createNamedQuery("Menu.getMenuItem", MenuItem.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public boolean createNewMenuItem(MenuItem menuItem){
        try {
            int totalCalories = getCalories(menuItem.getIngredients());
            menuItem.setCalories(totalCalories);
            em.persist(menuItem);
            return true;

        } catch(Exception e){
            LOG.info("Can't create new menu item" + e );
            return false;
        }

    }


public int getCalories(String ingredients){
//    Client client = ClientBuilder.newClient();
    WebTarget target = httpClient.target(API_URL).queryParam("query", ingredients);
    try {
        String response = target.request(MediaType.APPLICATION_JSON)
                .header("X-Api-Key", API_KEY)
                .get(String.class);
        LOG.info("response" + response);

        ObjectMapper mapper = new ObjectMapper();
        Nutrition nutritionObject = mapper.readValue(response, Nutrition.class);
        LOG.info("nutritionObject e" + nutritionObject);
        int totalCalories = 0;
        if(nutritionObject!= null && nutritionObject.getItems() != null){
            for(Item item : nutritionObject.getItems()){
                totalCalories += item.getCalories();
            }
        } else {
            LOG.info("NutritionObject == null");
        }

        return totalCalories;

    } catch(Exception e){
        e.printStackTrace();
    } finally {
        httpClient.close();
    }
    return -1;
}


    @Transactional
    public boolean updateMenuItem(MenuItem menuItem){
        try {
            em.merge(menuItem);
            return true;
        } catch(Exception e) {
            LOG.info("Can't update menu item" + e.getMessage());
            return false;
        }
    }

    @Transactional
    public boolean deleteMenuItem(int menuItemId){

        MenuItem menuItem = getMenuItemWithId(menuItemId);

        if(menuItem != null){
            try {
                em.remove(menuItem);
                return true;
            } catch(Exception e){
                LOG.info("Exception while trying to delete meal with id " + menuItemId  + e.getMessage());
                return false;
            }
        } else {
            LOG.info("Can't find meal with id " + menuItemId);
            return false;
        }

    }


}
