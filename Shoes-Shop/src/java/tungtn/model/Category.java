/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tungtn.model;

/**
 *
 * @author ASUS
 */
public class Category {
    
    private int cID;
    private String cName;

    public Category() {
    }

    @Override
    public String toString() {
        return "Category{" + "cID=" + cID + ", cName=" + cName + '}';
    }

    public Category(int cID, String cName) {
        this.cID = cID;
        this.cName = cName;
    }

    public int getcID() {
        return cID;
    }

    public void setcID(int cID) {
        this.cID = cID;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }
    
    
}
