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

        HttpServer server = new HttpServer(5);
        server.start();
    }
}
