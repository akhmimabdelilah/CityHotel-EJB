package com.a00n.beans;

import com.a00n.entities.Ville;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import com.a00n.dao.VilleBeanLocal;
import com.a00n.dao.VilleBeanRemote;
import jakarta.transaction.Transactional;

/**
 *
 * @author ay0ub
 */
@Stateless
//@Transactional
public class VilleBean implements VilleBeanLocal<Ville>, VilleBeanRemote<Ville> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Ville create(Ville ville) {
        entityManager.persist(ville);
        return ville;
    }

    @Override
    public Ville update(Ville ville) {
        return entityManager.merge(ville);
    }

    @Override
    public Ville findById(Long villeId) {
        return entityManager.find(Ville.class, villeId);
    }

    @Override
    public List<Ville> findAll() {
        return entityManager.createQuery("SELECT v FROM Ville v", Ville.class)
                .getResultList();
    }

    @Override
    public boolean delete(Long villeId) {
        try {
            Ville ville = entityManager.find(Ville.class, villeId);
            if (ville != null) {
                entityManager.remove(ville);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public String a00n() {
        return "hello ayoub";
    }

}
