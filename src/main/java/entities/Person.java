package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;


@Entity
@NamedQuery(name = "Person.deleteAllRows", query = "DELETE from Person ")
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String firstName;
    private String lastName;

    //Constructor
    public Person() {
    }

    public Person(long id, String email, String firstName, String lastName, List<Phone> phones, Address address) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phones = phones;
        this.address = address;
    }

    //Relation
    @OneToMany(mappedBy = "person")
    private List<Phone> phones = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    public Person(String email, String fName, String lName) {
        this.email = email;
        this.firstName = fName;
        this.lastName = lName;
    }


    //Getter og setter
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

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void addPhone(Phone phone){
        this.phones.add(phone);
        if (phone.getPerson() != this){
            phone.setPerson(this);
        }
    }

    public void addAddress(Address address){
        this.address = address;
        if (!address.getPersons().contains(this)){
            address.getPersons().add(this);
        }
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phones=" + phones +
                ", address=" + address +
                '}';
    }
}
