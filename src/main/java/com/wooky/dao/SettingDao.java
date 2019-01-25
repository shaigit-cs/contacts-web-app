package com.wooky.dao;

import com.wooky.model.Setting;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class SettingDao {

    @PersistenceContext
    private EntityManager entityManager;

    public int save(Setting s) {

        entityManager.persist(s);

        return s.getId();
    }

    public void delete(int id) {

        final Setting s = entityManager.find(Setting.class, id);
        if (s != null) {
            entityManager.remove(s);
        }
    }

    public Setting update(Setting s) {
        return entityManager.merge(s);
    }

    public Setting findById(int id) {
        return entityManager.find(Setting.class, id);
    }

    public List<Setting> findAll() {

        final Query query = entityManager.createQuery("SELECT s FROM Setting s");

        return query.getResultList();
    }
}
