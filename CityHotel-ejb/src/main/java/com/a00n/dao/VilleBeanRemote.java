/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.a00n.dao;

import jakarta.ejb.Remote;

/**
 *
 * @author ay0ub
 * @param <T>
 */
@Remote
public interface VilleBeanRemote<T> extends IDao<T> {

    String a00n();
}
