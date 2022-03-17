package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.FacadeExample;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("phone")
public class PhoneResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
       
    private static final FacadeExample FACADE =  FacadeExample.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();


    @Path("delete/{id}")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    public boolean deleteAPhone(@PathParam("id") long id){
        boolean phone = FACADE.deleteAPhone(id);
        return phone;
    }
}
