package com.wooky.dao;

import com.wooky.model.Contact;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class ContactDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Long save(Contact c) {

        entityManager.persist(c);

        return c.getId();
    }

    public void delete(Long id) {

        final Contact c = entityManager.find(Contact.class, id);
        if (c != null) {
            entityManager.remove(c);
        }
    }

    public Contact update(Contact c) {
        return entityManager.merge(c);
    }

    public Contact findById(Long id) {
        return entityManager.find(Contact.class, id);
    }

    public List<Contact> findAll() {

        final Query query = entityManager.createQuery("SELECT c FROM Contact c");

        return query.getResultList();
    }
}
