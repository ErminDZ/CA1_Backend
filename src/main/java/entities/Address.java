package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "street")
    private String street;

    @Column(name = "additional_info")
    private String additionalInfo;

    //relationer
    @OneToMany(mappedBy = "address")
    private List<Person> persons = new ArrayList<>();

    //Constructor
    public Address() {
    }

    public Address(Long id, String street, String additionalInfo, List<Person> persons) {
        this.id = id;
        this.street = street;
        this.additionalInfo = additionalInfo;
        this.persons = persons;
    }

    public Address(String street) {
        this.street = street;
    }

    //Getter og setter
    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public void addPerson(Person person){
        this.persons.add(person);
        if (person.getAddress() !=this){
            person.setAddress(this);
        }
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", additionalInfo='" + additionalInfo + '\'' +
                ", persons=" + persons +
                '}';
    }
}
