/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;

/**
 *
 * @author BoomIT wait MTV
 */
public class Ingredients {

    private String idIngre;
    private String nameIngre;
    private float priceIngre;
    private Date dateInputIngre;
    private int countInput;

    public Ingredients(String idIngre, String nameIngre, float priceIngre, Date dateInputIngre, int countInput) {
        this.idIngre = idIngre;
        this.nameIngre = nameIngre;
        this.priceIngre = priceIngre;
        this.dateInputIngre = dateInputIngre;
        this.countInput = countInput;
    }

    public int getCountInput() {
        return countInput;
    }

    public void setCountInput(int countInput) {
        this.countInput = countInput;
    }

    public Ingredients() {
    }

    public String getIdIngre() {
        return idIngre;
    }

    public void setIdIngre(String idIngre) {
        this.idIngre = idIngre;
    }

    public String getNameIngre() {
        return nameIngre;
    }

    public void setNameIngre(String nameIngre) {
        this.nameIngre = nameIngre;
    }

    public float getPriceIngre() {
        return priceIngre;
    }

    public void setPriceIngre(float priceIngre) {
        this.priceIngre = priceIngre;
    }

    public Date getDateInputIngre() {
        return dateInputIngre;
    }

    public void setDateInputIngre(Date dateInputIngre) {
        this.dateInputIngre = dateInputIngre;
    }

}
