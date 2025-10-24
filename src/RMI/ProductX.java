/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RMI;

import java.io.Serializable;

/**
 *
 * @author nvqua
 */
public class ProductX implements Serializable{
    public String id, code, discountCode;
    public int discount;
    private static final long serialVersionUID = 20171107;

    @Override
    public String toString() {
        return "ProductX{" + "id=" + id + ", code=" + code + ", discountCode=" + discountCode + ", discount=" + discount + '}';
    }

    
    public ProductX(String id, String code, String discountCode, int discount) {
        this.id = id;
        this.code = code;
        this.discountCode = discountCode;
        this.discount = discount;
    }
    
}
