/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author rh
 */
public class MakeTestData {
    private static EntityManagerFactory em;
    public static void main(String[] args) {
       EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(new BankCustomer("Jesper", "Hansen", "5423", 100.00, 10, "Tax evader"));
        em.persist(new BankCustomer("Mads", "Thomsen", "5128", 274.00, 10, "Taxidriver"));
        em.persist(new BankCustomer("Mark", "Jensen", "1234", 110.00, 12, "Noob"));
        em.persist(new BankCustomer("Johnny", "Greyson", "4444", 111.00, 12, "Yolo"));
        em.getTransaction().commit();
       
       
    }
    
}
