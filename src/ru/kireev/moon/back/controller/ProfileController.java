package ru.kireev.moon.back.controller;

import ru.kireev.moon.back.model.Profile;
import ru.kireev.moon.back.service.ProfileService;

import java.util.Optional;

public class ProfileController {

    private final ProfileService service;

    public ProfileController(ProfileService service) {
        this.service = service;
    }

    public String work(String request){
        return null;
    }

    public String save(String request) {
        String [] params =request.split(",");

        if(params.length < 4) return "Bad request";

        Profile profile =  new Profile();
        profile.setEmail(params[0]);
        profile.setName(params[1]);
        profile.setSurname(params[2]);
        profile.setAbout(params[3]);

        return service.save(profile).toString();
    }

    public String update(String request) {
        String [] params =request.split(",");

        if(params.length!= 5){
            return  "Bad request: need five correct parameters";
        }

        long id;

        try {
            id = Long.parseLong(params[0]);
        }catch (NumberFormatException e){
            return "Bad request: can't parse string [" + params[0] + "] to long";
        }

        Profile profile = new Profile();
        profile.setId(id);
        profile.setEmail(params[1]);
        profile.setName(params[2]);
        profile.setSurname(params[3]);
        profile.setAbout(params[4]);

        service.update(profile);

        return "Update success";




    }

    public String delete(String request) {
        String [] params =request.split(",");

        if (params.length !=1) {
            return  "Bad request: need one number parameter";
        }

        long id;

        try {
            id = Long.parseLong(params[0]);
        }catch (NumberFormatException e){
            return "Bad request: can't parse string [" + params[0] + "] to long";
        }

        boolean delete = service.delete(id);

        if(!delete){
            return  "Not found";
        }

        return "Delete success";


    }

    public String findAll(String request) {
        return  service.findAll().toString();
    }

    public String findById(String request) {
        String [] params =request.split(",");
        if (params.length !=1) {
            return  "Bad request: need one number parameter";
        }

        long id;
        try {
            id = Long.parseLong(params[0]);
        }catch (NumberFormatException e){
            return "Bad request: can't parse string [" + params[0] + "] to long";
        }

        Optional<Profile> maybeProfile = service.findById(id);

        if(maybeProfile.isEmpty()){
            return  "Not found";
        }

        return maybeProfile.get().toString();
    }
}
