/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author nguyenquang
 */
public class Sell {
    private  String foodTYPE;
    private  String foodNAME;
    private  float promotion;
    private  float price; 
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Sell(String foodTYPE, String foodNAME, float promotion, float price, int count) {
        this.foodTYPE = foodTYPE;
        this.foodNAME = foodNAME;
        this.promotion = promotion;
        this.price = price;
        this.count = count;
    }

    public String getFoodTYPE() {
        return foodTYPE;
    }

    public void setFoodTYPE(String foodTYPE) {
        this.foodTYPE = foodTYPE;
    }

    public String getFoodNAME() {
        return foodNAME;
    }

    public void setFoodNAME(String foodNAME) {
        this.foodNAME = foodNAME;
    }

    public float getPromotion() {
        return promotion;
    }

    public void setPromotion(float promotion) {
        this.promotion = promotion;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Sell() {
    }

    public Sell(String foodTYPE, String foodNAME, float promotion, float price) {
        this.foodTYPE = foodTYPE;
        this.foodNAME = foodNAME;
        this.promotion = promotion;
        this.price = price;
    }
            
    
}
