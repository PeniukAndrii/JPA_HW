package com.example.demo1.dao;

import com.example.demo1.models.Contact;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ContactDAOImpl implements ContactDAO{
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
    EntityManager entityManager = emf.createEntityManager();

    @Override
    public void save(Contact contact) {
        entityManager.getTransaction().begin();
        entityManager.persist(contact);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Contact> findAll() {
        return entityManager.createNativeQuery("SELECT * FROM contacts",Contact.class).getResultList();
    }

    @Override
    public Contact findById(int id) {
        return entityManager.find(Contact.class, id);
    }

    @Override
    public void delete(Contact contact) {
        entityManager.getTransaction().begin();
        entityManager.remove(contact);
        entityManager.getTransaction().commit();
    }
}
