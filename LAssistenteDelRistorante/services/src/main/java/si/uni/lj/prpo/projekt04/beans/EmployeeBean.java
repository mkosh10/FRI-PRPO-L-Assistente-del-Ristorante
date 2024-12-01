package si.uni.lj.prpo.projekt04.beans;


import si.uni.lj.prpo.projekt04.Employee;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import java.util.List;
import java.util.logging.Logger;

@RequestScoped
public class EmployeeBean {
    private final Logger LOG = Logger.getLogger(ReservationBean.class.getName());
    @PersistenceContext(unitName = "l-assistente-del-ristorante-jpa")
    private EntityManager em;

    public List<Employee> getAllEmployees(){
        TypedQuery<Employee> employeeList = em.createNamedQuery("Employee.getAll",Employee.class);
        return employeeList.getResultList();
    }

    public int getEmployeesListSize(){
        return getAllEmployees().size();
    }

    public Employee getEmployeeWithId(Integer id){
        try {

            TypedQuery<Employee> employee = em.createNamedQuery("Employee.getEmployee", Employee.class);
            employee.setParameter("id", id);
            return employee.getSingleResult();
        } catch(Exception e){
            LOG.info("Can't get employee info with id " + id + e.getMessage());
            return null;
        }
    }


    @Transactional
    public boolean addNewEmployee(Employee newEmployee){
        try {
            em.persist(newEmployee);
            return true;
        } catch (Exception e){
            LOG.info("Can't add new employee " + e.getMessage());
            return false;
        }
    }

    @Transactional
    public boolean updateEmployeeInfo(Employee employee){
        try {
            em.merge(employee);
            return true;
        } catch(Exception e){
            LOG.info("Something went wong "+ e.getMessage());
            return false;
        }
    }


    @Transactional
    public boolean deleteEmployee(Integer id){
        Employee employee = getEmployeeWithId(id);
        if(employee != null){
            em.remove(employee);
            return true;
        } else {
            return false;
        }
    }


}
