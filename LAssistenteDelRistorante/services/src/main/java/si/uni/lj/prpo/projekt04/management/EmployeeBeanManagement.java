package si.uni.lj.prpo.projekt04.management;

import si.uni.lj.prpo.projekt04.DTOs.EmployeeDTO;
import si.uni.lj.prpo.projekt04.Employee;
import si.uni.lj.prpo.projekt04.beans.EmployeeBean;
import si.uni.lj.prpo.projekt04.beans.ReservationBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.logging.Logger;

@ApplicationScoped
public class EmployeeBeanManagement {

    private final Logger LOG = Logger.getLogger(ReservationBean.class.getName());
    @Inject
    private EmployeeBean employeeBean;

    public Optional<Employee> toEmployeeEntity(EmployeeDTO employeeDto) {

        String firstName = employeeDto.getFirstName();
        String lastName = employeeDto.getLastName();
        String email = employeeDto.getEmail();
        String position = employeeDto.getPosition();
        LocalDate hireDate = employeeDto.getHireDate();
        Double salary = employeeDto.getSalary();

        if (firstName == null || lastName == null || email == null || position == null || hireDate == null || salary == null) {
            LOG.info("Not every parameter is present in EmployeeDTO");
            return Optional.empty();
        }

        Employee employeeEntity = new Employee();
        employeeEntity.setFirstName(firstName);
        employeeEntity.setLastName(lastName);
        employeeEntity.setEmail(email);
        employeeEntity.setPosition(position);
        employeeEntity.setHireDate(hireDate);
        employeeEntity.setSalary(salary);

        return Optional.of(employeeEntity);
    }

    public boolean addNewEmployee(EmployeeDTO employeeDTO){
        Optional<Employee> employee = toEmployeeEntity(employeeDTO);
        if(employee.isPresent()){
            employeeBean.addNewEmployee(employee.get());
            return true;
        } else {
            return false;
        }

    }

    public boolean updateEmployeeInfo(Integer id, EmployeeDTO employeeDTO){
        Employee currentEmployee = employeeBean.getEmployeeWithId(id);
        if(currentEmployee != null){
            if (employeeDTO.getFirstName() != null) {
                currentEmployee.setFirstName(employeeDTO.getFirstName());
            }
            if (employeeDTO.getLastName() != null) {
                currentEmployee.setLastName(employeeDTO.getLastName());
            }
            if (employeeDTO.getEmail() != null) {
                currentEmployee.setEmail(employeeDTO.getEmail());
            }
            if (employeeDTO.getPosition() != null) {
                currentEmployee.setPosition(employeeDTO.getPosition());
            }
            if (employeeDTO.getHireDate() != null) {
                currentEmployee.setHireDate(employeeDTO.getHireDate());
            }
            if (employeeDTO.getSalary() != null) {
                currentEmployee.setSalary(employeeDTO.getSalary());
            }

            currentEmployee.setUpdatedAt(LocalDateTime.now());
            return employeeBean.updateEmployeeInfo(currentEmployee);
        }

        return false;
    }





}
