package dbfacade;

import entity.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;


public class CustomerFacade {
    private static EntityManagerFactory emf;
    private static CustomerFacade instance;
    
    private CustomerFacade() {}
    
    public static CustomerFacade getCustomerFacade(EntityManagerFactory em) {
        if(instance == null) {
            emf = em;
            instance = new CustomerFacade();
        }
        return instance;
    }
    
    public Customer findById(long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Customer.class, id);
        } finally {
            em.close();
        }
    }
    
    public List<Customer> findByLastName(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT c FROM Customer c WHERE c.lastName = :name", Customer.class)
                    .setParameter("Name", name).getResultList();
        } finally {
            em.close();
        }
    }
    
    public int getNumberOfCustomers() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT count(c) FROM Customer c", Long.class).getSingleResult().intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Customer> getAllCustomers() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
        } finally {
            em.close();
        }
    }
    
    public Customer addCustomer(String fistName, String lastName) {
        EntityManager em = emf.createEntityManager();
        try {
            Customer c = new Customer(fistName, fistName);
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
            return c;
        } finally {
            em.close();
        }
    }
    

}
