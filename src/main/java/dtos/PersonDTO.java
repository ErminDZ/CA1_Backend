package dtos;

import entities.Person;
//import entities.RenameMe;

import java.util.ArrayList;
import java.util.List;

public class PersonDTO {
    private long id;
    private String email;
    private String firstname;
    private String lastname;

    public PersonDTO(String email, String firstname, String lastname) {
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public static List<PersonDTO> getDtos(List<Person> ps){
        List<PersonDTO> pdtos = new ArrayList();
        ps.forEach(p->pdtos.add(new PersonDTO(p)));
        return pdtos;
    }

    public PersonDTO(Person p) {
        //if(p.getId() != null)
        this.id = p.getId();
        this.email = p.getEmail();
        this.firstname = p.getFirstName();
        this.lastname = p.getLastName();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
