package org.concatel.backEndExam3.controllers;

import java.security.Principal;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.concatel.backEndExam3.repositories.RebelDAO;
import org.concatel.backEndExam3.responses.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/addRebel")
public class RebelController {

    @Autowired
    RebelDAO rebelDAO;
    final static Logger logger = Logger.getLogger(RebelController.class);

    @GetMapping
    public String addRebel(Locale locale, Model model, Principal principal) {

        return "index";
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<Response> addRebel(@RequestParam("name") String name,
            @RequestParam("planet") String planet) {

        Response response = new Response();

        try {
            rebelDAO.save(name, planet); 
            response.setSuccess(Boolean.TRUE);
            response.setMessage("Rebel logged.");
        } catch (Exception e) {
            logger.error(String.format("Couldn't log rebel.") + e.toString());
            response.setSuccess(Boolean.FALSE);
            response.setMessage("Error." + e.toString());
        }
        if (response.getSuccess()) {
            System.out.println(name);
            System.out.println(planet);
        }
        return new ResponseEntity<Response>(response, HttpStatus.OK);

    }

}
