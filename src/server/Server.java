/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.*;
import java.net.*;
import java.util.Date;
import javax.jws.WebMethod;
import javax.xml.ws.WebServiceException;

/**
 *
 * @author 2105533
 */
public class Server {
    private static char f;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Throwable{
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(new Integer(System.getenv("PORT")));
        } catch (IOException e) {
            System.err.println("Could not listen on port: "+System.getenv("PORT")+".");
            System.exit(1);
        }
        Socket clientSocket = null;
        for(;;){
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");   
                System.exit(1);
            }
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine, outputLine,get="", get2="";
            
            while ((inputLine = in.readLine()) != null) {
                String[] m = inputLine.split(" "); 
                if(m[0].equals("GET")){
                    get=m[1];
                }
                get2 = get.replace("/", "");
                System.out.println(get);
                System.out.println("Received: " + inputLine);
                if (!in.ready()) {
                    break;
                }
            }
            if(get.equals("/")){
                get2="index.html";
            }
            FileInputStream fin;
            try{
                fin = new FileInputStream(get2);
            }catch(FileNotFoundException ex ){
                fin = new FileInputStream("error404.html");
            }
            sendFile(fin,new DataOutputStream(clientSocket.getOutputStream()));
            outputLine = "HTTP/1.1 200 OK\r\n"
		 + "Content-Type: text/html\r\n"
		 + "\r\n"
		 + "<!DOCTYPE html>\n"
		 + "<html>\n"
		 + "<head>\n"
		 + "<meta charset=\"UTF-8\">\n"
		 + "<title>Title of the document</title>\n"
		 + "</head>\n"
		 + "<body>\n"
		 + "<h1>Mi propio mensaje</h1>\n"
		 + "</body>\n"
		 + "</html>\n" + inputLine;
            out.println(outputLine);
            out.flush();
            out.close();
            in.close();
            clientSocket.close();
        }
    }
    public static void sendFile (FileInputStream fin, DataOutputStream out){
        byte[] buffer = new byte[2000000];
        int bytesRead;
        int strCnt = 0;
        try{
            int cnt = 0;
            while ((bytesRead = fin.read(buffer)) != -1){
                 out.write(buffer, 0, bytesRead);
            }
            fin.close();
        }
        catch (IOException ex){

        }
    }
}
