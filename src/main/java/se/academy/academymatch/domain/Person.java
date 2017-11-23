package se.academy.academymatch.domain;

public class Person {
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String klass;
    private String presentation;
    private String imgLink;
    private String status;
    private String preference1;
    private String preference2;
    private String preference3;

    
    public Person(int id, String firstName, String lastName, int age, String klass, String presentation, String imgLink, String status, String preference1, String preference2, String preference3) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.klass = klass;
        this.presentation = presentation;
        this.imgLink = imgLink;
        this.status = status;
        this.preference1 = preference1;
        this.preference2 = preference2;
        this.preference3 = preference3;
    }

    public String getPreference1() {
        return preference1;
    }

    public void setPreference1(String preference1) {
        this.preference1 = preference1;
    }

    public String getPreference2() {
        return preference2;
    }

    public void setPreference2(String preference2) {
        this.preference2 = preference2;
    }

    public String getPreference3() {
        return preference3;
    }

    public void setPreference3(String preference3) {
        this.preference3 = preference3;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
