/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.a00n.servlets;

import com.a00n.beans.VilleBean;
import com.a00n.entities.Hotel;
import com.a00n.entities.Ville;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import com.a00n.dao.VilleBeanRemote;

/**
 *
 * @author ay0ub
 */
//@Startup
//@Singleton
public class DataLoader {

//    @EJB
    private VilleBeanRemote<Ville> villeBean;

//    @PostConstruct
    public void init() {
        System.out.println("=================================================================================================");
        System.out.println(villeBean);
        System.out.println(villeBean.a00n());
        // Create Ville entities
        Ville ville1 = Ville.builder().nom("Paris").build();
        Ville ville2 = Ville.builder().nom("New York").build();
        Ville ville3 = Ville.builder().nom("Tokyo").build();
        Ville ville4 = Ville.builder().nom("London").build();
        Ville ville5 = Ville.builder().nom("Sydney").build();

        // Save Ville entities using the Ville bean
        ville1 = villeBean.create(ville1);
        ville2 = villeBean.create(ville2);
        ville3 = villeBean.create(ville3);
        ville4 = villeBean.create(ville4);
        ville5 = villeBean.create(ville5);

        // update ville1 
        ville1.setNom("updated");
        villeBean.update(ville1);

        // delete ville2
//        villeBean.delete(ville2.getId());
//        // get ville3 
        System.out.println(villeBean.findById(ville3.getId()));
//
//        // get all
//        System.out.println(villeBean.findAll());
        // Create Hotel entities
//        Hotel hotel1 = Hotel.builder().nom("Hotel A").address("123 Main St").phone("555-1234").ville(ville1).build();
//        Hotel hotel2 = Hotel.builder().nom("Hotel B").address("456 Broadway").phone("555-5678").ville(ville2).build();
//        Hotel hotel3 = Hotel.builder().nom("Hotel C").address("789 Downtown Ave").phone("555-9876").ville(ville3).build();
//        Hotel hotel4 = Hotel.builder().nom("Grand Hotel").address("101 Park Lane").phone("555-1111").ville(ville4).build();
//        Hotel hotel5 = Hotel.builder().nom("Ocean View Resort").address("200 Coastal Rd").phone("555-2222").ville(ville5).build();
        // Save Hotel entities using the Hotel bean
//        hotelBean.create(hotel1);
//        hotelBean.create(hotel2);
//        hotelBean.create(hotel3);
//        hotelBean.create(hotel4);
//        hotelBean.create(hotel5);
    }

}
