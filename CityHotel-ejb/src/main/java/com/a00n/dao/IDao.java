/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.a00n.dao;

import java.util.List;

/**
 *
 * @author ay0ub
 * @param <T>
 */
public interface IDao<T> {

    T create(T entity);

    T update(T entity);

    T findById(Long id);

    List<T> findAll();

    boolean delete(Long id);
}
