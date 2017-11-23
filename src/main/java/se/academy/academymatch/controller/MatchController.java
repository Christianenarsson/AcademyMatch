package se.academy.academymatch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import se.academy.academymatch.domain.Commons;
import se.academy.academymatch.domain.Person;
import se.academy.academymatch.repository.Repository;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

@Controller
public class MatchController implements Commons{

    @Autowired
    private Repository repository;

    public MatchController() {
        repository = new Repository();
    }

    @GetMapping ("/")
    public ModelAndView index() {
        List<String> preferences = new ArrayList<>();
        preferences.add(Java);
        preferences.add(JavaScript);
        preferences.add(HTMLCSS);
        preferences.add(UX);
        preferences.add(Databases);
        preferences.add(ProjectManagement);

        return new ModelAndView("index").addObject("preferences", preferences);
    }

    @PostMapping("/setPref")
    @ResponseBody
    public String setPref(HttpSession session, @RequestParam(value = "preference", defaultValue = "Java") String preference) {
        session.setAttribute("preference", preference);
        return "done";
    }

    @GetMapping ("/start")
    @ResponseBody
    public String start (HttpSession session) {
        if (session.getAttribute("persons") == null) {
            session.setAttribute("persons", repository.createPool((String) session.getAttribute("preference")));
            session.setAttribute("chosen", new ArrayList<Person>());
        }
        if (session.getAttribute("current") == null && ! ((Queue<Person>) session.getAttribute("persons")).isEmpty()) {
            Queue<Person> persons = (Queue<Person>) session.getAttribute("persons");
            session.setAttribute("current", persons.remove());
        }
        return "done";
    }

    @GetMapping("/swipe")
    public ModelAndView swipe (HttpSession session) {
        if (session.getAttribute("current") != null) {
            return new ModelAndView("swipe").addObject("person", session.getAttribute("current"))
                    .addObject("nrChosen", (((List<Person>) session.getAttribute("chosen")).size()));
        } else {
            return new ModelAndView("noswipe").addObject("nrChosen", (((List<Person>) session.getAttribute("chosen")).size()));
        }
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
        List<Person> chosen = (List<Person>) session.getAttribute("chosen");
        return new ModelAndView("final").addObject("chosen", chosen);
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
            session.setAttribute("current", null);
            return "final";
        } else {
            Person currentPerson = queuePerson.remove();
            session.setAttribute("current", currentPerson);
            
            returncurrentPerson = "{ \"name\": \"" + currentPerson.getName() + "\", \"age\": \"" + currentPerson.getAge() + "\", \"klass\": \"" + currentPerson.getKlass() + "\", \"text\": \"" + currentPerson.getPresentation() + "\", \"img\": \"" + currentPerson.getImgLink() + "\", \"chosen\": \"" + ((List<Person>) session.getAttribute("chosen")).size() + "\", \"preference1\": \"" +currentPerson.getPreference1()+ "\" , \"preference2\": \"" +currentPerson.getPreference2()+ "\" , \"preference3\": \"" +currentPerson.getPreference3()+ "\" , \"profilelink\": \"" +currentPerson.getProfilelink() + "\"  }";
        }
        return returncurrentPerson;
    }
}
