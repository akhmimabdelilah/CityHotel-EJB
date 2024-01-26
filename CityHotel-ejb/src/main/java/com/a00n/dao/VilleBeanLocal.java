/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.a00n.dao;

import jakarta.ejb.Local;

/**
 *
 * @author ay0ub
 * @param <T>
 */
@Local
public interface VilleBeanLocal<T> extends IDao<T> {

    String a00n();
}
