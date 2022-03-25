package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.AddressDTO;
import dtos.PersonDTO;
import entities.Person;
import utils.EMF_Creator;
import facades.FacadeExample;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

//Todo Remove or change relevant parts before ACTUAL use
@Path("person")
public class PersonResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
       
    private static final FacadeExample FACADE =  FacadeExample.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getPersonCount() {
       
        long count = FACADE.getPersonCount();
        //System.out.println("--------------->"+count);
        return "{\"count\":"+count+"}";  //Done manually so no need for a DTO
    }
    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAll(){
        //String persons = String.valueOf(FACADE.getAll());
        return Response.ok(GSON.toJson(FACADE.getAll())).build();
    }
    @Path("{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getById(@PathParam("id") long id){
        PersonDTO personDTO = FACADE.getById(id);
        return Response.ok(personDTO).build();
    }

    @Path("delete/{id}")
    @DELETE
    @Produces({MediaType.APPLICATION_JSON})
    public Response deleteAPerson(@PathParam("id") long id){
        boolean person = FACADE.deleteAPerson(id);
        return Response.ok(person).build();
    }

    @Path("create")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(String person){
        PersonDTO p = GSON.fromJson(person,PersonDTO.class);
        PersonDTO pnew =FACADE.create(p);
        return Response.ok(pnew).build();
    }

    @Path("edit/{id}")
    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response editPerson(@PathParam("id") long id, String person){
        PersonDTO p = GSON.fromJson(person, PersonDTO.class);
        p.setId(id);
        PersonDTO pEdited = FACADE.editPerson(p);
        return Response.ok(pEdited).build();
    }
}
