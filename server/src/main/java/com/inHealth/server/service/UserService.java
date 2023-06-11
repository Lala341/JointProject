package com.inHealth.server.service;

import com.inHealth.server.model.User;
import com.inHealth.server.model.graph.BodyMeasures;
import com.inHealth.server.model.graph.Demographics;
import com.inHealth.server.model.graph.Person;
import com.inHealth.server.repository.UserRepository;
import com.inHealth.server.repository.graph.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PersonRepository personRepository;


    public User save(User user) {
        User usert=userRepository.save(user);
        String date = (LocalDate.now()).toString();

        Person nperson= new Person();
        nperson.setId(usert.getId());
        nperson.setName(usert.getName());

        Demographics demot= new Demographics();
        demot.setCountry("2.0");
        demot.setRace("5.0");
       // demot.setAge(usert.getBirthday());
        demot.setGender(usert.getGender()=="female"?"1.0":"2.0");

        BodyMeasures bodyt= new BodyMeasures();
        bodyt.setDate(date);
        bodyt.setHeight(usert.getHeightCm()+0.1);
        bodyt.setWeight(usert.getWeightKg()+0.1);

        nperson.setDemographics(demot);
        nperson.addBodyMeasures(bodyt);
        personRepository.save(nperson);

        return usert;
    }
}
