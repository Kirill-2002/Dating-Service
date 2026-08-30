package ru.kireev.moon.back.controller;

import ru.kireev.moon.back.service.ProfileService;

public class ProfileController {

    private final ProfileService service;

    public ProfileController(ProfileService service) {
        this.service = service;
    }

    public String work(String request){
        return null;
    }
}
