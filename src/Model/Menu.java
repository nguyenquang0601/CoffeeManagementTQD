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
public class Menu {

    private String idMenu;
    private String name;
    private String nameFood;
    private float price;
    private float promotion;
    private int countExcess;

    public Menu(String idMenu, String name, String nameFood, float price, float promotion, int countExcess) {
        this.idMenu = idMenu;
        this.name = name;
        this.nameFood = nameFood;
        this.price = price;
        this.promotion = promotion;
        this.countExcess = countExcess;
    }

    public int getCountExcess() {
        return countExcess;
    }

    public void setCountExcess(int countExcess) {
        this.countExcess = countExcess;
    }

   

    public Menu() {
    }

    public String getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(String idMenu) {
        this.idMenu = idMenu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameFood() {
        return nameFood;
    }

    public void setNameFood(String nameFood) {
        this.nameFood = nameFood;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getPromotion() {
        return promotion;
    }

    public void setPromotion(float promotion) {
        this.promotion = promotion;
    }

    
}
