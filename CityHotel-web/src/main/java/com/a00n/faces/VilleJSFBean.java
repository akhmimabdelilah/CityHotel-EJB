/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.a00n.faces;

import com.a00n.dao.VilleBeanRemote;
import com.a00n.entities.Ville;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;
import org.primefaces.PrimeFaces;

/**
 *
 * @author ay0ub
 */
@Named(value = "villeJSFBean")
@ViewScoped
public class VilleJSFBean implements Serializable {

    private List<Ville> villes;

    private Ville selectedVille;

    private List<Ville> selectedVilles;

    @EJB
    private VilleBeanRemote<Ville> villeBean;

    @PostConstruct
    public void init() {
        this.villes = this.villeBean.findAll();
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(this.villes);
    }

    public void openNew() {
        this.selectedVille = new Ville();
    }

    public void saveVille() {
        System.out.println(this.selectedVille);
        if (this.selectedVille.getId() != null) {
            villeBean.update(this.selectedVille);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ville Updated"));
        } else {
            villeBean.create(this.selectedVille);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ville Added"));
        }
        this.init();

        PrimeFaces.current().executeScript("PF('manageVilleDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-villes");
    }

    public void deleteVille() {
        villeBean.delete(this.selectedVille.getId());
        this.selectedVille = null;
        this.init();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ville Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-villes");
    }

    public String getDeleteButtonMessage() {
        if (hasSelectedVilles()) {
            int size = this.selectedVilles.size();
            return size > 1 ? size + " villes selected" : "1 ville selected";
        }

        return "Delete";
    }

    public boolean hasSelectedVilles() {
        return this.selectedVilles != null && !this.selectedVilles.isEmpty();
    }

    public void deleteSelectedVilles() {
        this.selectedVilles.forEach(ville -> {
            villeBean.delete(ville.getId());
        });
        this.selectedVilles = null;
        this.init();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Villes Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-villes");
        PrimeFaces.current().executeScript("PF('dtVilles').clearFilters()");
    }

    public String a00n() {
        return "hello";
    }

    public List<Ville> getVilles() {
        return villes;
    }

    public void setVilles(List<Ville> villes) {
        this.villes = villes;
    }

    public Ville getSelectedVille() {
        return selectedVille;
    }

    public void setSelectedVille(Ville selectedVille) {
        this.selectedVille = selectedVille;
    }

    public List<Ville> getSelectedVilles() {
        return selectedVilles;
    }

    public void setSelectedVilles(List<Ville> selectedVilles) {
        this.selectedVilles = selectedVilles;
    }

    public VilleBeanRemote<Ville> getVilleBean() {
        return villeBean;
    }

    public void setVilleBean(VilleBeanRemote<Ville> villeBean) {
        this.villeBean = villeBean;
    }

}
