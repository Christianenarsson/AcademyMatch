package se.academy.academymatch.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
public class MatchController {

    @Autowired
    private Repository repository;

    public MatchController() {
        repository = new Repository();
    }

    @GetMapping ("/")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @GetMapping ("/start")
    @ResponseBody
    public String start (HttpSession session) {
        if (session.getAttribute("persons") == null) {
            session.setAttribute("persons", repository.createPool());
            session.setAttribute("chosen", new ArrayList<Person>());
        }
        if (session.getAttribute("current") == null) {
            Queue<Person> persons = (Queue<Person>) session.getAttribute("persons");
            session.setAttribute("current", persons.remove());
        }
        return "done";
    }

    @GetMapping("/swipe")
    public ModelAndView swipe (HttpSession session) {

        return new ModelAndView("swipe").addObject("person", session.getAttribute("current"));
    }

    @GetMapping ("/swipeNo")
    @ResponseBody
    public String swipeNo(HttpSession session){

        return getNextPerson(session);
    }

    @GetMapping ("/swipeYes")
    @ResponseBody
    public String swipeYes(HttpSession session){
        ((List<Person>) session.getAttribute("chosen")).add((Person) session.getAttribute("current"));
        return getNextPerson(session);
    }

    @GetMapping ("/final")
    public ModelAndView finalpage (HttpSession session){
        return new ModelAndView("final");
    }

    @GetMapping ("/loading")
    public ModelAndView loading (HttpSession session){
        return new ModelAndView("loading");
    }

    @GetMapping ("/intro")
    public ModelAndView intro (HttpSession session){
        return new ModelAndView("intro");
    }

    public String getNextPerson (HttpSession session){
        Queue<Person> queuePerson = (Queue<Person>) session.getAttribute("persons");
        String returncurrentPerson;
        if (queuePerson.isEmpty()) {
            return "final";
        } else {
            Person currentPerson = queuePerson.remove();
            session.setAttribute("current", currentPerson);

            returncurrentPerson = currentPerson.getName() + ";" + currentPerson.getAge() + ";" +
                    currentPerson.getPresentation() + ";" + currentPerson.getImgLink() + ";" +
                    ((List<Person>) session.getAttribute("chosen")).size();
        }
        return returncurrentPerson;
    }
}
