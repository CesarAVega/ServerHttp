/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puntoSeisCuatroUno;

import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 *
 * @author redes
 */
public interface EchoServer extends Remote{
    public String echo(String cadena) throws RemoteException;
}
