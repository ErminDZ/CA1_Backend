package facades;

import dtos.AddressDTO;
import dtos.PersonDTO;
//import dtos.RenameMeDTO;
import entities.Address;
import entities.Person;
//import entities.RenameMe;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

//import errorhandling.RenameMeNotFoundException;
import entities.Phone;
import utils.EMF_Creator;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class FacadeExample {

    private static FacadeExample instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private FacadeExample() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static FacadeExample getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new FacadeExample();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public PersonDTO create(PersonDTO p){
        Person person = new Person(p.getEmail(),p.getFirstname(), p.getLastname());
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new PersonDTO(person);
    }
    public PersonDTO getById(long id) { //throws RenameMeNotFoundException {
        EntityManager em = emf.createEntityManager();
        Person person = em.find(Person.class, id);
//        if (rm == null)
//            throw new RenameMeNotFoundException("The RenameMe entity with ID: "+id+" Was not found");
        return new PersonDTO(person);
    }
    
    //TODO Remove/Change this before use
    public long getPersonCount(){
        EntityManager em = getEntityManager();
        try{
            long personCount = (long)em.createQuery("SELECT COUNT(p) FROM Person p").getSingleResult();
            return personCount;
        }finally{  
            em.close();
        }
    }
    
    public List<PersonDTO> getAll(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);
        List<Person> p = query.getResultList();
        return PersonDTO.getDtos(p);
    }

    public boolean deleteAAddress(long id){
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Query q = em.createQuery("DELETE FROM Address a WHERE a.id = :id").setParameter("id",id);

            int deletedAddress = q.executeUpdate();
            System.out.println("You deleted a address: " + deletedAddress);

            em.getTransaction().commit();
            return true;
        }finally {
            em.close();
        }
    }

    public boolean deleteAPhone(long id){
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Query q = em.createQuery("DELETE FROM Phone  p WHERE p.id = :id").setParameter("id",id);

            int deletePhone = q.executeUpdate();
            System.out.println("You have deleted a phone: " + deletePhone);

            em.getTransaction().commit();
            return true;
        }finally {
            em.close();
        }
    }

    public boolean deleteAPerson(long id){
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Query q = em.createQuery("DELETE FROM Person  p WHERE p.id = :id").setParameter("id",id);

            int deletePerson = q.executeUpdate();
            System.out.println("You have deleted a phone: " + deletePerson);

            em.getTransaction().commit();
            return true;
        }finally {
            em.close();
        }
    }

    public PersonDTO editPerson(PersonDTO personDTO){
        EntityManager em = emf.createEntityManager();
        try {
            Person person = em.find(Person.class,personDTO.getId());

            person.setEmail(personDTO.getEmail());
            person.setFirstName(personDTO.getFirstname());
            person.setLastName(personDTO.getLastname());

            em.getTransaction().begin();
            em.merge(person);
            em.getTransaction().commit();
            return new PersonDTO(person);
        }finally {
            em.close();
        }
    }
    
    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        FacadeExample fe = getFacadeExample(emf);
        fe.getAll().forEach(dto->System.out.println(dto));
    }

}
