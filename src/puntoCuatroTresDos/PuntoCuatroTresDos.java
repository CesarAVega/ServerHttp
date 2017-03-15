/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puntoCuatroTresDos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author 2105533
 */
public class PuntoCuatroTresDos {
    private static char f;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Throwable{
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
        f = 'c';
        while (inputLine!= null) {
            
            System.out.println("Mensaje:" + inputLine);
            try{
                double cu = Double.parseDouble(inputLine);
                outputLine = "Respuesta :" + (fun(cu)) ;
                out.println(outputLine);
                if (outputLine.equals("Respuesta adios"))
                    break;
                inputLine = in.readLine();
            }catch(NumberFormatException ex){
                String m[] = inputLine.split(":");
                if(m[1].equalsIgnoreCase("sin")){
                    f = 's';
                }else if(m[1].equalsIgnoreCase("cos")){
                    f = 'c';
                }else if(m[1].equalsIgnoreCase("tan")){
                    f = 't';
                }
            }
        }
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }

    private static double fun(double cu) {
        if('c' == f){
            return Math.cos(Math.toRadians(cu));
        }else if('s' == f){
            return Math.sin(Math.toRadians(cu));
        }else{
            return Math.tan(Math.toRadians(cu));
        }
    }
}
