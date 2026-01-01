/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package RMI3;

/**
 *
 * @author nvqua
 */
import java.rmi.*;
import java.io.*;
public interface DataService extends Remote {

    public Object requestData(String studentCode, String qCode) throws RemoteException;

    public void submitData(String studentCode, String qCode, Object data) throws RemoteException;
}
