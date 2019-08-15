/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nguyenquang
 */
@XmlRootElement(namespace = "https://gpcoder.com/jaxb")
@XmlAccessorType(XmlAccessType.FIELD)
public class ConnectSQL {
    @XmlElement(name = "localhost")
    private String localhost;
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "password")
    private String password;

    public String getLocalhost() {
        return localhost;
    }

    public void setLocalhost(String localhost) {
        this.localhost = localhost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ConnectSQL() {
    }

    public ConnectSQL(String localhost, String name, String password) {
        this.localhost = localhost;
        this.name = name;
        this.password = password;
    }

   
}
