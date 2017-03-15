/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puntoCuatroTres;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 *
 * @author 2105533
 */
public class punto43 {
    public void punto4() throws Throwable{
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String inputLine, outputLine;
        inputLine = in.readLine();
        while (inputLine!= null) {
            System.out.println("Mensaje:" + inputLine);
            double cu = Integer.parseInt(inputLine);
            outputLine = "Respuesta" + (cu*cu) ;
            out.println(outputLine);
            if (outputLine.equals("Respuesta adios"))
                break;
            inputLine = in.readLine();
        }
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}
