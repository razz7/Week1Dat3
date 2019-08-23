/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Employee;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class EmployeeFacade {

    private static EmployeeFacade instance;
    private static EntityManagerFactory emf;
    

    public static EmployeeFacade getFacadeExample(EntityManagerFactory em) {
        if (instance == null) {
            emf = em;
            instance = new EmployeeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Employee getEmployeeById(long id) {
        return getEntityManager().find(Employee.class, id);
    }
    
    public List<Employee> getEmployeesByName(String name) {
        return getEntityManager().createQuery("SELECT e FROM Employee e WHERE e.name = :name", Employee.class)
                .setParameter("name", name).getResultList();
    }
    
    public List<Employee> getAllEmployees() {
        return getEntityManager().createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
    }
    
    
}
