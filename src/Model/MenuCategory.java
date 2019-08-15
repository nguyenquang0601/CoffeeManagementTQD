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
public class MenuCategory {
    private String idMenuCategory;
    private String nameMenuCategory;

    public MenuCategory() {
    }

    public MenuCategory(String idMenuCategory, String nameMenuCategory) {
        this.idMenuCategory = idMenuCategory;
        this.nameMenuCategory = nameMenuCategory;
    }

    public String getIdMenuCategory() {
        return idMenuCategory;
    }

    public void setIdMenuCategory(String idMenuCategory) {
        this.idMenuCategory = idMenuCategory;
    }

    public String getNameMenuCategory() {
        return nameMenuCategory;
    }

    public void setNameMenuCategory(String nameMenuCategory) {
        this.nameMenuCategory = nameMenuCategory;
    }
    
}
