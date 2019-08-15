/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.jfoenix.controls.JFXTextField;
import java.sql.Date;

import javafx.scene.control.TableColumn;

/**
 *
 * @author nguyenquang
 */
public class Employee {

    private String codeEMP;
    private String name;
    private Date birthday;
    private String gender;
    private String idCard;
    private String phoneNumber;

    private String address;
    private String position;
    private String active;

    public Employee(String codeEMP, String name, Date birthday, String gender, String idCard, String phoneNumber, String address, String position, String active) {
        this.codeEMP = codeEMP;
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.idCard = idCard;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.position = position;
        this.active = active;
    }

   
    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Employee() {
    }

    public String getCodeEMP() {
        return codeEMP;
    }

    public void setCodeEMP(String codeEMP) {
        this.codeEMP = codeEMP;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

}
