/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP_Obj3;

import java.io.Serializable;

/**
 *
 * @author nvqua
 */
public class Student implements Serializable{
    public String id, code, name, email;
    private static final long serialVersionUID = 20171107;

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", code=" + code + ", name=" + name + ", email=" + email + '}';
    }

    public Student(String id, String code, String name, String email) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.email = email;
    }

    public Student(String code) {
        this.code = code;
    }
    
}
