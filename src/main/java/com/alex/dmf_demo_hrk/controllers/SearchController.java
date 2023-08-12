package com.alex.dmf_demo_hrk.controllers;

import com.alex.dmf_demo_hrk.classes.Person;
import com.alex.dmf_demo_hrk.classes.ReturnField;
import com.alex.dmf_demo_hrk.classes.SSN;
import com.alex.dmf_demo_hrk.classes.Seq;

import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.io.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;


@Controller
public class SearchController {

    /**
     * Shows form to user;
     * */
    @GetMapping("/search")
    protected String showForm (Model model) {
        model.addAttribute("ssn", new SSN());
        return "search";
    }

    /**
     * Handles submitted form from user;
     * */
    @PostMapping("/search")
    protected String submitForm (@ModelAttribute("ssn") SSN ssn, Model model) throws IOException {
        System.out.println(ssn);
        String searchSSN = ssn.getSocialNum();

        File f = new File("src/main/webapp/WEB-INF/dmf1.csv");
        String absolute = f.getAbsolutePath();

        Seq<String> info = ReturnField.ssnInfo(absolute, searchSSN);

        if (info != null) {
            Person person = new Person();
            ReturnField.restorePerson(person, info);
            model.addAttribute("person", person);
            return "result";
        } else {
            return "notFound";
        }
    }

    @GetMapping("/hello")
    public String hello(Model model){
        model.addAttribute("message", "Hello World");
        return "helloworld";
    }

}
