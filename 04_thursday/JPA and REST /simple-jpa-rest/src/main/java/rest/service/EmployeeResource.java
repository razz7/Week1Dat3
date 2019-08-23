/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest.service;

import com.google.gson.Gson;
import dto.EmployeeDTO;
import entities.Employee;
import facades.EmployeeFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;


@Path("employee")
public class EmployeeResource {

    @Context
    private UriInfo context;

    private EmployeeFacade ef;
            
    /**
     * Creates a new instance of EmployeeResource
     */
    public EmployeeResource() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        ef = EmployeeFacade.getFacadeExample(emf);
    }
    
    @GET
    @Path("/setup")
    public String setupDatabase() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        em.persist(new Employee("Rasmus", "OJ stree", 10000000));
        em.persist(new Employee("Jakob ", "Somewhere", 80000));
        em.persist(new Employee("Peter", "WhereSome", 1000000));
        em.persist(new Employee("Ditlev", "Nowhere", 20000));
        em.persist(new Employee("Ava", "Right there", 500000));
        em.getTransaction().commit();
        em.close();
        
        return "Done";
    }
    
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllEmployees() {
        List<Employee> employees = ef.getAllEmployees();
        List<EmployeeDTO> employeesDTO = new ArrayList();
        for(Employee e : employees)
            employeesDTO.add(new EmployeeDTO(e));
        return new Gson().toJson(employeesDTO);
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getEmployeeById(
            @PathParam("id") long id) {
        Employee employee = ef.getEmployeeById(id);
        return new Gson().toJson(new EmployeeDTO(employee));
    }
    
    
    @GET
    @Path("/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getEmployeesByName(
            @PathParam("name") String name) {
        List<Employee> employees = ef.getEmployeesByName(name);
        List<EmployeeDTO> employeesDTO = new ArrayList();
        for(Employee e : employees)
            employeesDTO.add(new EmployeeDTO(e));
        
        return new Gson().toJson(employeesDTO);
    }
}
