package com.launchcode.helloagainspring.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;

@Controller // recognize this as a controller class
//@RequestMapping("hello") // mapping base path to start with "/hello"
public class HelloController {


    @RequestMapping(value="")
    @ResponseBody
    public String index(HttpServletRequest request) {

        String name = request.getParameter("name");

        if (name == null) {
            name = "World";
        }

        return "Hello, " + name + "!";
    }

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    @ResponseBody
    public String helloForm() {

        String html = "<form method='post'>" +
                "<input type='text' name='name' />" +
                "<input type='submit' value='Greet Me!'/>" +
                "</form>";
        return html;

    }



    @RequestMapping(value="studio_form")
    @ResponseBody
    public String studioForm() {

        String html = "<form method='post'>" +
                "<input type='text' name='name'/>" +
                "<select name='lang'>" +
                    "<option value='en'>English</option>" +
                    "<option value='fr'>French</option>" +
                    "<option value='sp'>Spanish</option>" +
                    "<option value='gr'>German</option>" +
                    "<option value='it'>Italian</option>" +
                "</select>" +
                "<input type='submit' value='Greet Me!'/>" +
                "<form>";
        return html;
    }


    @RequestMapping(value="studio_form", method=RequestMethod.POST)
    @ResponseBody
    public String studioForm(HttpServletRequest request) {
        HashMap<String,String> greetings = new HashMap<>();
        greetings.put("en", "Hello");
        greetings.put("fr", "Bonjour");
        greetings.put("sp", "Hola");
        greetings.put("gr", "Hallo");
        greetings.put("it", "Ciao");
        String name = request.getParameter("name");
        String greeting = greetings.get(request.getParameter("lang"));

        return createMessage(greeting, name);
    }


    @RequestMapping(value="hello", method = RequestMethod.POST)
    @ResponseBody
    public String helloPost(HttpServletRequest request) {
        String name = request.getParameter("name");

        return "Hello " + name;
    }


    @RequestMapping(value="hello/{name}")
    @ResponseBody
    public String helloUrlSegment(@PathVariable String name) {
        return "hello " + name;
    }


    @RequestMapping(value="goodbye")
    public String goodbye() {

        return "redirect:/";
    }

    public static String createMessage(String greeting, String name) {
        return String.format("%s, %s!", greeting, name);
    }
}
