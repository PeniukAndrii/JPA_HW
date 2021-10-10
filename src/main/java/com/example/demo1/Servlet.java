package com.example.demo1;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@WebServlet(name = "Servlet", value = "/contacts")

public class Servlet extends HttpServlet {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
    EntityManager entityManager = emf.createEntityManager();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Contact entity = new Contact();
        Contact contact1 = entityManager.find(Contact.class, 1);
        System.out.println(contact1.getId());
        entity.setFirstName(req.getParameter("firstName"));
        entity.setLastName(req.getParameter("lastName"));
        entity.setPhone(req.getParameter("phone"));
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        PrintWriter writer = response.getWriter();
        if (!request.getPathInfo().substring(1).isEmpty()){
            Integer id = Integer.valueOf(request.getPathInfo().substring(1));
            Contact contact = entityManager.find(Contact.class, id);
            writer.println(contact.getId() + "\n" + contact.getFirstName() + "\n"+ contact.getLastName() + "\n" + contact.getPhone());
        }
        else {
            List<Contact> resultList = entityManager.createNativeQuery("SELECT * FROM contacts",Contact.class).getResultList();
            for (Contact contact : resultList) {
                writer.println(contact.getId());
                writer.println(contact.getFirstName());
                writer.println(contact.getLastName());
                writer.println(contact.getPhone());
            }
        }
        writer.flush();
        writer.close();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.valueOf(req.getPathInfo().substring(1));
        Contact contact = entityManager.find(Contact.class, id);
        entityManager.getTransaction().begin();
        entityManager.remove(contact);
        entityManager.getTransaction().commit();
    }


}
