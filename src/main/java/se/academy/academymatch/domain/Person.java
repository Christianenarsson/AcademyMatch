package se.academy.academymatch.domain;

public class Person {
    private int id;
    private String firstName;
    private String lastName;
    private int born;
    private String klass;
    private String presentation;
    private String imgLink;
    private String status;

    public Person(int id, String firstName, String lastName, int born, String klass, String presentation, String imgLink, String status) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.born = born;
        this.klass = klass;
        this.presentation = presentation;
        this.imgLink = imgLink;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public int getBorn() {
        return born;
    }

    public void setBorn(int age) {
        this.born = age;
    }

    public String getKlass() {
        return klass;
    }

    public void setKlass(String klass) {
        this.klass = klass;
    }

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
