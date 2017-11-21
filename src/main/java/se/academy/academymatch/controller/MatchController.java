package se.academy.academymatch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import se.academy.academymatch.domain.Person;
import se.academy.academymatch.repository.Repository;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import static javax.swing.text.html.CSS.getAttribute;

@Controller
public class MatchController {

    private Repository repository;

    public MatchController() {
        repository = new Repository();
    }

    @GetMapping ("/")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @GetMapping ("/start")
    public ModelAndView start (HttpSession session) {
        if (session.getAttribute("persons") == null) {
            session.setAttribute("persons", repository.createPool());
            session.setAttribute("chosen", new ArrayList<Person>());
        }
        if (session.getAttribute("current") == null) {
            Queue<Person> persons = (Queue<Person>) session.getAttribute("persons");
            session.setAttribute("current", persons.remove());
        }
        return new ModelAndView("swipe");
    }

    @GetMapping ("/swipeNo")
    @ResponseBody
    public String swipeNo(HttpSession session){

        return getNextPerson(session);
    }

    @GetMapping ("/swipeYes")
    @ResponseBody
    public String swipeYes(HttpSession session){
        session.setAttribute("chosen", session.getAttribute("current"));
        return getNextPerson(session);
    }

    @GetMapping ("/final")
    public ModelAndView finalpage (HttpSession session){
        return new ModelAndView("final");
    }

    public String getNextPerson (HttpSession session){
        Queue<Person> queuePerson = (Queue<Person>) session.getAttribute("persons");
        Person currentPerson = queuePerson.remove();
        session.setAttribute("current", currentPerson);

        String returncurrentPerson = currentPerson.getName() +  ":" + currentPerson.getBorn() + ":" + currentPerson.getText() + ":" + currentPerson.getImage() ;

        return returncurrentPerson;
    }




}
