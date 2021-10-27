package com.example.demo1.servlet;

import com.example.demo1.dao.ContactDAOImpl;
import com.example.demo1.models.Contact;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@WebServlet(name = "MainServlet", value = "/contacts")
public class MainServlet extends HttpServlet {
    ContactDAOImpl contactDAO = new ContactDAOImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        String firstName = (String) req.getAttribute("firstName");
        String lastName = (String) req.getAttribute("lastName");
        String phone = (String) req.getAttribute("phone");
        Contact contact = new Contact(firstName,lastName,phone);
        System.out.println(contact);
        contactDAO.save(contact);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp){
            int id = Integer.parseInt((String) req.getAttribute("id"));
            Contact contact = contactDAO.findById(id);
            String firstName = (String) req.getAttribute("firstName");
            String lastName = (String) req.getAttribute("lastName");
            String phone = (String) req.getAttribute("phone");
            contact.setFirstName(firstName);
            contact.setLastName(lastName);
            contact.setPhone(phone);
            contactDAO.save(contact);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        if (!request.getPathInfo().substring(1).isEmpty()){
            int id = Integer.parseInt(request.getPathInfo().substring(1));
            Contact contact = contactDAO.findById(id);
            writer.println(contact.getId() + "\n" + contact.getFirstName() + "\n"+ contact.getLastName() + "\n" + contact.getPhone());
        }
        else {
            List<Contact> resultList = contactDAO.findAll();
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
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp){
        int id = Integer.parseInt(req.getPathInfo().substring(1));
        Contact contact = contactDAO.findById(id);
        contactDAO.delete(contact);
    }
}
