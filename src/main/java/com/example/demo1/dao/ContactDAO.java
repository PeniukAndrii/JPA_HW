package com.example.demo1.dao;
import com.example.demo1.models.Contact;

import java.util.List;

public interface ContactDAO{
    void save(Contact contact);

    List<Contact> findAll();

    Contact findById(int id);

    void delete(Contact contact);
}
