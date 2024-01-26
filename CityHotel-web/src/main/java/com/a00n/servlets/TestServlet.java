package com.a00n.servlets;

import com.a00n.dao.HotelBeanRemote;
import com.a00n.entities.Ville;
import jakarta.ejb.EJB;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.a00n.dao.VilleBeanRemote;
import com.a00n.entities.Hotel;

/**
 *
 * @author ay0ub
 */
@WebServlet(name = "TestServlet", urlPatterns = {"/test"})
public class TestServlet extends HttpServlet {

    @EJB
    private VilleBeanRemote<Ville> villeBean;
    @EJB
    private HotelBeanRemote<Hotel> hotelBean;

    private void a00n() {
        System.out.println("=================================================================================================");
        System.out.println(villeBean);
        System.out.println(hotelBean);
        System.out.println(villeBean.a00n());
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
//        ville1.setNom("updated");
//        villeBean.update(ville1);
        // delete ville2
//        villeBean.delete(ville2.getId());
//        // get ville3 
//        System.out.println(villeBean.findById(ville3.getId()));
//         Create Hotel entities
        Hotel hotel1 = Hotel.builder().nom("Hotel A").address("123 Main St").phone("555-1234").ville(ville1).build();
        Hotel hotel2 = Hotel.builder().nom("Hotel B").address("456 Broadway").phone("555-5678").ville(ville2).build();
        Hotel hotel3 = Hotel.builder().nom("Hotel C").address("789 Downtown Ave").phone("555-9876").ville(ville3).build();
        Hotel hotel4 = Hotel.builder().nom("Grand Hotel").address("101 Park Lane").phone("555-1111").ville(ville4).build();
        Hotel hotel5 = Hotel.builder().nom("Ocean View Resort").address("200 Coastal Rd").phone("555-2222").ville(ville5).build();
//         Save Hotel entities using the Hotel bean
        hotelBean.create(hotel1);
        hotelBean.create(hotel2);
        hotelBean.create(hotel3);
        hotelBean.create(hotel4);
        hotelBean.create(hotel5);

        hotel5.setNom("a00n a00n");
        hotelBean.update(hotel5);

        System.out.println(hotelBean.findAll());

        System.out.println(hotelBean.nbHotels());
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        this.a00n();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TestServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TestServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
