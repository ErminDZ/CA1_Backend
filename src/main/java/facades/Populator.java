/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.PersonDTO;
//import dtos.RenameMeDTO;
import entities.Address;
import entities.Person;
//import entities.RenameMe;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

import java.util.ArrayList;

/**
 *
 * @author tha
 */
public class Populator {
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        FacadeExample fe = FacadeExample.getFacadeExample(emf);
//        fe.create(new RenameMeDTO(new RenameMe("First 1", "Last 1")));
        fe.create(new PersonDTO(new Person("hej@Gmail.com","Per","Lars")));
        fe.create(new PersonDTO(new Person("hey@Gmail.com","Peter","Petersen")));

    }
    
    public static void main(String[] args) {
        populate();
    }
}
