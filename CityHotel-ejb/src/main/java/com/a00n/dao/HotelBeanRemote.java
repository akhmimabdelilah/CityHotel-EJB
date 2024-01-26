/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.a00n.dao;

import jakarta.ejb.Remote;
import java.util.List;

/**
 *
 * @author ay0ub
 * @param <T>
 */
@Remote
public interface HotelBeanRemote<T> extends IDao<T> {

    List<Object[]> nbHotels();
}
