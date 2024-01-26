package com.a00n.cityhotel.web.resources;

import com.a00n.dao.HotelBeanRemote;
import com.a00n.dao.VilleBeanRemote;
import com.a00n.entities.Hotel;
import com.a00n.entities.Ville;
import jakarta.ejb.EJB;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 *
 * @author
 */
@Path("v1")
public class JakartaEE10Resource {

    @EJB
    private VilleBeanRemote<Ville> villeBean;
    @EJB
    private HotelBeanRemote<Hotel> hotelBean;

    @GET
    public Response ping() {
        return Response
                .ok("ping Jakarta EE")
                .build();
    }

    @GET
    @Path("villes")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVilles() {
        List<Ville> villes = villeBean.findAll();
        return Response.ok(villes).build();
    }

    @GET
    @Path("hotels")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHotels() {
        List<Hotel> hotels = hotelBean.findAll();
        return Response.ok(hotels).build();
    }
}
