package com.example.demo1;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class Service {

    @PersistenceContext
    EntityManager entityManager;

}
