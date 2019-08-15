/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author BoomIT wait MTV
 */
public class Order {
//    private int idIndentity;

    private String idBillOrder;
    private String nameMenuFood;
    private int countOrder;
    private float priceOrder;
    private float promotion;

    public float getPromotion() {
        return promotion;
    }

    public void setPromotion(float promotion) {
        this.promotion = promotion;
    }

    public Order() {
    }

    public Order(String nameMenuFood, int countOrder, float priceOrder) {

        this.nameMenuFood = nameMenuFood;
        this.countOrder = countOrder;
        this.priceOrder = priceOrder;
    }

    public String getIdBillOrder() {
        return idBillOrder;
    }

    public void setIdBillOrder(String idBillOrder) {
        this.idBillOrder = idBillOrder;
    }

    public Order(String idBillOrder, String nameMenuFood, int countOrder, float priceOrder) {
        this.idBillOrder = idBillOrder;
        this.nameMenuFood = nameMenuFood;
        this.countOrder = countOrder;
        this.priceOrder = priceOrder;
    }

    public String getNameMenuFood() {
        return nameMenuFood;
    }

    public void setNameMenuFood(String nameMenuFood) {
        this.nameMenuFood = nameMenuFood;
    }

    public int getCountOrder() {
        return countOrder;
    }

    public void setCountOrder(int countOrder) {
        this.countOrder = countOrder;
    }

    public float getPriceOrder() {
        return priceOrder;
    }

    public void setPriceOrder(float priceOrder) {
        this.priceOrder = priceOrder;
    }

}
