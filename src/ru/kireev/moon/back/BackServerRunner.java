package ru.kireev.moon.back;

import ru.kireev.moon.back.controller.ProfileController;
import ru.kireev.moon.back.dao.ProfileDao;
import ru.kireev.moon.back.service.ProfileService;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class BackServerRunner {
    public static  void  main(String[] args) throws IOException {

        ProfileController profileController = new ProfileController(new ProfileService(new ProfileDao()));


        try (ServerSocket serverSocket = new ServerSocket(8080);
             Socket socket = serverSocket.accept();
             DataOutputStream responseStream= new DataOutputStream(socket.getOutputStream());
             DataInputStream requestStream = new DataInputStream(socket.getInputStream());
        ) {
               String request = requestStream.readUTF();
               String response;



               while(!"stop".equals(request)){

                   String[] parts = request.split(" ", 2);
                   String command = parts[0];
                   String argument = parts.length > 1 ? parts[1]: "";

                   switch (command){
                       case "save" -> response = profileController.save(argument);
                       case "update" -> response = profileController.update(argument);
                       case "delete" -> response = profileController.delete(argument);
                       case "findAll" -> response = profileController.findAll(argument);
                       case "findById" -> response = profileController.findById(argument);
                       default ->  response = "Unsupported operation";

                   }

                   System.out.println("Client request: " + request);
                   responseStream.writeUTF(response);
                   request = requestStream.readUTF();
               }

        }
    }
}
