package si.uni.lj.prpo.projekt04.beans;

import si.uni.lj.prpo.projekt04.MenuItem;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@RequestScoped
public class MenuBean {
    @PersistenceContext(unitName = "l-assistente-del-ristorante-jpa")
    private EntityManager em;

    private final Logger LOG = Logger.getLogger(MenuBean.class.getName());

    public List<MenuItem> getAllMenuItems(){
        TypedQuery<MenuItem> query = em.createNamedQuery("Menu.getAll", MenuItem.class);
        return  query.getResultList();
    }

    public int getMenuListSize(){
        return getAllMenuItems().size();
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
            em.persist(menuItem);
            return true;

        } catch(Exception e){
            LOG.info("Can't create new menu item" + e );
            return false;
        }

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
