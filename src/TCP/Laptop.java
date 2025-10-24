/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP;

import java.io.Serializable;


/**
 *
 * @author nvqua
 */
public class Laptop implements Serializable{
   private static final long serialVersionUID = 20150711L;
   public int id;
   public String code, name;
   public int quantity;

    public Laptop(int id, String code, String name, int quantity) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.quantity = quantity;
    }

    

    @Override
    public String toString() {
        return "Laptop{" + "id=" + id + ", name=" + name + ", quantity=" + quantity + '}' + code;
    }
    
    
}
