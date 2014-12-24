package io.github.bytecomoficial.services;

import java.net.UnknownHostException;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

@Path("/securyte")
@Produces(MediaType.APPLICATION_JSON)
public class SecuryteService {

	@POST
	@Path("/login")
	public Response get(@QueryParam("usuario") String usuario,
			@QueryParam("senha") String senha) throws UnknownHostException {
		System.out.printf("Usuario: %s%nSenha: %s%n", usuario, senha);
//		MongoClient client = new MongoClient("localhost", 27017);
		MongoClient client = new MongoClient(new MongoClientURI("mongodb://bytecom:bytecom@ds063240.mongolab.com:63240/bytecom"));
		DB database = client.getDB("bytecom");
		DBCollection collection = database.getCollection("usuario");
		DBObject document = collection.findOne();
		System.out.println(document);
		return Response.ok()
		    .entity(document.toString())
		    .header("Access-Control-Allow-Origin","*")
            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
            .build();
	}

}
