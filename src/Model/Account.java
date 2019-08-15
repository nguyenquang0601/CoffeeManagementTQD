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
public class Account {
    private String idAccount;
    private String userAccount;
    private String passAccout;
    private String emailAccount;
    private String idEmployee;

    public Account(String idAccount, String userAccount, String passAccout, String emailAccount, String idEmployee) {
        this.idAccount = idAccount;
        this.userAccount = userAccount;
        this.passAccout = passAccout;
        this.emailAccount = emailAccount;
        this.idEmployee = idEmployee;
    }

    public Account() {
    }

    public String getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(String idAccount) {
        this.idAccount = idAccount;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getPassAccout() {
        return passAccout;
    }

    public void setPassAccout(String passAccout) {
        this.passAccout = passAccout;
    }

    public String getEmailAccount() {
        return emailAccount;
    }

    public void setEmailAccount(String emailAccount) {
        this.emailAccount = emailAccount;
    }

    public String getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(String idEmployee) {
        this.idEmployee = idEmployee;
    }
    
}
