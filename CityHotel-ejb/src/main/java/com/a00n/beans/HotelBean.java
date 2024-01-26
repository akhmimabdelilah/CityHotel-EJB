package com.a00n.beans;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import com.a00n.dao.HotelBeanLocal;
import com.a00n.dao.HotelBeanRemote;
import com.a00n.entities.Hotel;
import jakarta.ejb.Local;
import jakarta.ejb.Remote;

/**
 *
 * @author ay0ub
 */
@Stateless
@Local(HotelBeanLocal.class)
@Remote(HotelBeanRemote.class)
public class HotelBean implements HotelBeanLocal<Hotel>, HotelBeanRemote<Hotel> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Hotel create(Hotel hotel) {
        entityManager.persist(hotel);
        return hotel;
    }

    @Override
    public Hotel update(Hotel hotel) {
        return entityManager.merge(hotel);
    }

    @Override
    public Hotel findById(Long hotelId) {
        return entityManager.find(Hotel.class, hotelId);
    }

    @Override
    public List<Hotel> findAll() {
        return entityManager.createQuery("SELECT v FROM Hotel v", Hotel.class)
                .getResultList();
    }

    @Override
    public boolean delete(Long hotelId) {
        try {
            Hotel hotel = entityManager.find(Hotel.class, hotelId);
            if (hotel != null) {
                entityManager.remove(hotel);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public List<Object[]> nbHotels() {
        List<Object[]> hotels = null;
        hotels = entityManager.createQuery("select count(h.id),v.nom from Hotel h, Ville v where h.ville.id=v.id group by v.nom").getResultList();
        return hotels;
    }
}
