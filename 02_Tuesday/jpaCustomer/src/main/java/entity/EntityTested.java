package entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class EntityTested {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        
        List<Customer> customers = new ArrayList();
        customers.add(new Customer("Rasmus", "Rasmussen"));
        customers.add(new Customer("Casper", "Caspersen"));
        customers.add(new Customer("Jakob", "Jakobsen"));
        customers.add(new Customer("Ditlev" ,"Ditlevsen"));
        
        try {
            em.getTransaction().begin();
            for(Customer c : customers)
                em.persist(c);
            em.getTransaction().commit();
            
            TypedQuery<Customer> q = em.createQuery("SELECT c FROM Customer c", Customer.class);
            for(Customer c : q.getResultList())
                System.out.println(c);
        } finally {
            em.close();
            emf.close();
        }
    }
}
