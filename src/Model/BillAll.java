/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.time.LocalDateTime;

/**
 *
 * @author BoomIT wait MTV
 */
public class BillAll {
    private String idBill;
    private String idEmpOrder;
//    private LocalDateTime timeOrder;
    private String timeOrder;
    private float total;

    public BillAll() {
    }

    public BillAll(String idBill, String idEmpOrder, String timeOrder, float total) {
        this.idBill = idBill;
        this.idEmpOrder = idEmpOrder;
        this.timeOrder = timeOrder;
        this.total = total;
    }

    public String getIdBill() {
        return idBill;
    }

    public void setIdBill(String idBill) {
        this.idBill = idBill;
    }

    public String getIdEmpOrder() {
        return idEmpOrder;
    }

    public void setIdEmpOrder(String idEmpOrder) {
        this.idEmpOrder = idEmpOrder;
    }

    public String getTimeOrder() {
        return timeOrder;
    }

    public void setTimeOrder(String timeOrder) {
        this.timeOrder = timeOrder;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    
}
