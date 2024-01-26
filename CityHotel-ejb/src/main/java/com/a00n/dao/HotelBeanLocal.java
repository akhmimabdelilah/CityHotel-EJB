package com.a00n.dao;

import jakarta.ejb.Local;
import java.util.List;

/**
 *
 * @author ay0ub
 * @param <T>
 */
@Local
public interface HotelBeanLocal<T> extends IDao<T> {

    List<Object[]> nbHotels();
}
