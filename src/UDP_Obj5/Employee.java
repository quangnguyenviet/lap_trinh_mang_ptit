/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP_Obj5;

import java.io.Serializable;

/**
 *
 * @author nvqua
 */
public class Employee implements Serializable{
    private static final long serialVersionUID = 20261107L;
    public String id, name;
    public double salary;
    public String hireDate;

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", name=" + name + ", salary=" + salary + ", hireDate=" + hireDate + '}';
    }

    public Employee(String id, String name, double salary, String hireDate) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.hireDate = hireDate;
    }
    
}
