/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.a00n.faces;

import com.a00n.dao.HotelBeanRemote;
import com.a00n.dao.VilleBeanRemote;
import com.a00n.entities.Hotel;
import com.a00n.entities.Ville;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.primefaces.PrimeFaces;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.optionconfig.animation.Animation;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;

/**
 *
 * @author ay0ub
 */
@Named(value = "hotelJSFBean")
@ViewScoped
public class HotelJSFBean implements Serializable {

    private List<Hotel> hotels;
    private List<Ville> villes;

    private Hotel selectedHotel;

    private List<Hotel> selectedHotels;

    @EJB
    private VilleBeanRemote<Ville> villeBean;
    @EJB
    private HotelBeanRemote<Hotel> hotelBean;

    private BarChartModel barModel;

    @PostConstruct
    public void init() {
        this.selectedHotel = Hotel.builder().ville(new Ville()).build();
        this.hotels = this.hotelBean.findAll();
        this.villes = this.villeBean.findAll();
        this.createBarModel();
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

    public void openNew() {
        this.selectedHotel = new Hotel();
        this.selectedHotel.setVille(new Ville());
    }

    public void saveHotel() {
        System.out.println(this.selectedHotel);
        if (this.selectedHotel.getId() != null) {
            hotelBean.update(this.selectedHotel);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Hotel Updated"));
        } else {
            this.selectedHotel.setVille(villeBean.findById(this.selectedHotel.getVille().getId()));
            hotelBean.create(this.selectedHotel);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Hotel Added"));
        }
        this.init();

        PrimeFaces.current().executeScript("PF('manageHotelDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-hotels");
    }

    public void deleteHotel() {
        System.out.println(this.selectedHotel);
        hotelBean.delete(hotelBean.findById(this.selectedHotel.getId()).getId());
        this.selectedHotel = null;
        this.init();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Hotel Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-hotels");
    }

    public String getDeleteButtonMessage() {
        if (hasSelectedHotels()) {
            int size = this.selectedHotels.size();
            return size > 1 ? size + " hotels selected" : "1 hotel selected";
        }

        return "Delete";
    }

    public boolean hasSelectedHotels() {
        return this.selectedHotels != null && !this.selectedHotels.isEmpty();
    }

    public void deleteSelectedHotels() {
        this.selectedHotels.forEach(hotel -> {
            hotelBean.delete(hotel.getId());
        });
        this.selectedHotels = null;
        this.init();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Hotels Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-hotels");
        PrimeFaces.current().executeScript("PF('dtHotels').clearFilters()");
    }

    public String a00n() {
        return "hello";
    }

    public List<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
    }

    public Hotel getSelectedHotel() {
        return selectedHotel;
    }

    public void setSelectedHotel(Hotel selectedHotel) {
        this.selectedHotel = selectedHotel;
        this.selectedHotel.setVille(new Ville());
    }

    public List<Hotel> getSelectedHotels() {
        return selectedHotels;
    }

    public void setSelectedHotels(List<Hotel> selectedHotels) {
        this.selectedHotels = selectedHotels;
    }

    public List<Ville> getVilles() {
        return villes;
    }

    public void setVilles(List<Ville> villes) {
        this.villes = villes;
    }

    public void createBarModel() {
        barModel = new BarChartModel();
        ChartData data = new ChartData();

        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Number of hotels by city");

        List<Number> values = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        List<String> bgColor = new ArrayList<>();
        List<String> borderColor = new ArrayList<>();
        for (Object[] emp : hotelBean.nbHotels()) {
            var s = String.valueOf(emp[0]);
            Number n = Long.valueOf(s);
            values.add(n);
            labels.add(String.valueOf(emp[1]));
            bgColor.add(generateColors(true));
            borderColor.add(generateColors(false));
        }
        barDataSet.setData(values);
        barDataSet.setBackgroundColor(bgColor);
        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);
        data.addChartDataSet(barDataSet);
        data.setLabels(labels);
        barModel.setData(data);

        //Options
        BarChartOptions options = new BarChartOptions();
//        options.setMaintainAspectRatio(false);
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
        linearAxes.setBeginAtZero(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
        title.setText("Bar Chart");
        options.setTitle(title);

        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("top");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("italic");
        legendLabels.setFontColor("#2980B9");
        legendLabels.setFontSize(24);
        legend.setLabels(legendLabels);
        options.setLegend(legend);

        // disable animation
        Animation animation = new Animation();
        animation.setDuration(0);
        options.setAnimation(animation);

        barModel.setOptions(options);
    }

    public String generateColors(boolean bgColor) {

        Random rand = new Random();

        int r = rand.nextInt(256);
        int g = rand.nextInt(256);
        int b = rand.nextInt(256);
        double alpha = 0.2; // You can adjust the alpha value if needed
        if (bgColor) {
            return String.format("rgba(%d, %d, %d, %.1f)", r, g, b, alpha);
        } else {
            return String.format("rgba(%d, %d, %d)", r, g, b);
        }
    }

    public VilleBeanRemote<Ville> getVilleBean() {
        return villeBean;
    }

    public void setVilleBean(VilleBeanRemote<Ville> villeBean) {
        this.villeBean = villeBean;
    }

    public HotelBeanRemote<Hotel> getHotelBean() {
        return hotelBean;
    }

    public void setHotelBean(HotelBeanRemote<Hotel> hotelBean) {
        this.hotelBean = hotelBean;
    }

}
