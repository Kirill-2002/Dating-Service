package ru.kireev.moon.back;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class BackServerRunner {
    public static  void  main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(8080);
             Socket socket = serverSocket.accept();
             DataOutputStream responseStream= new DataOutputStream(socket.getOutputStream());
             DataInputStream requestStream = new DataInputStream(socket.getInputStream());
             Scanner scanner = new Scanner(System.in)) {
            while(scanner.hasNextLine()){
               String request = requestStream.readUTF();

               while(!"stop".equals(request)){
                   System.out.println("Client request: " + request);
                   String response = scanner.nextLine();
                   responseStream.writeUTF(response);
                   request = requestStream.readUTF();
               }
            }
        }
    }
}
