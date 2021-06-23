/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package restWorld;

import java.util.Calendar;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

/**
 * REST Web Service
 *
 * @author vedan
 */
@Path("restWorld")
public class RestWorld {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RestWorld
     */
    public RestWorld() {
    }

    /**
     * Retrieves representation of an instance of restWorld.RestWorld
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/html")
    public String getHtml() {
        //TODO return proper representation object
        //throw new UnsupportedOperationException();
        return "<html><body><h1> This is Vedant's code </h1></body></html>";
    }
    
    @GET
    @Path(value="/add")
    @Produces("text/html")
    public String add(@HeaderParam("id1") final int i, @HeaderParam("id2") final int j) {
        //TODO return proper representation object
        //throw new UnsupportedOperationException();
        return "<html><body> <h1>This is a REST Server.</h1><br><b>Time ::"+Calendar.getInstance().getTime()+"<br><br>Addition of "+i+" & "+j+" = <span styles={color:'red'}>"+(i+j)+"</span></b>";
    }

    @POST
    @Consumes("text/html")
    @Path(value = "/ids")
    public void postByids(@HeaderParam("id") final String id)  {
        System.out.println("This post was made by ID :"+id);
    }
    /**
     * PUT method for updating or creating an instance of RestWorld
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
//    @PUT
//    @Consumes("text/html")
//    public void putHtml(String content) {
//    }
}
