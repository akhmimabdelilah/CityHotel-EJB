package com.a00n.cityhotel.desktop;

import com.a00n.dao.HotelBeanRemote;
import com.a00n.entities.Hotel;
import javax.naming.InitialContext;

/**
 *
 * @author ay0ub
 */
public class CityHotelDesktop {

    public static void main(String[] args) {
        try {
            InitialContext ctx = new InitialContext();
            System.out.println(((HotelBeanRemote<Hotel>) ctx.lookup("java:global/CityHotel-ear/CityHotel-web-1.0/HotelBean!com.a00n.dao.HotelBeanRemote")).findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
