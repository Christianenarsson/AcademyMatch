package se.academy.academymatch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import se.academy.academymatch.domain.Person;
import se.academy.academymatch.repository.Repository;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Queue;

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
            session.setAttribute("persons",repository.createPool());
            session.setAttribute("chosen", new List<Person>);

        }
        if (session.getAttribute("current") == null) {
            Queue<Person> persons = (Queue<Person>) session.getAttribute("persons");
            session.setAttribute("current", persons.remove());
        }
        session.setAttribute("current", );
        return new ModelAndView("swipe");
    }




}
