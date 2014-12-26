package io.github.bytecomoficial.services;

import java.net.UnknownHostException;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

@Path("/securyte")
@Produces(MediaType.APPLICATION_JSON)
public class SecuryteService {

	private static final String URL = "mongodb://bytecom:bytecom@ds063240.mongolab.com:63240/bytecom";

	@POST
	@Path("/login")
	public Response get(@QueryParam("login") String login,
			@QueryParam("password") String passwordHash)
			throws UnknownHostException {
		MongoClient client = null;
		try {
			MongoClientURI uri = new MongoClientURI(URL);
			client = new MongoClient(uri);
			DB database = client.getDB("bytecom");
			DBCollection collection = database.getCollection("usuario");
			DBObject document = collection.findOne(new BasicDBObject("login",
					login).append("password", passwordHash));
			if (document == null) {
				return Response
						.status(Status.NO_CONTENT)
						.header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods",
								"GET, POST, DELETE, PUT").build();
			}
			return Response
					.ok()
					.entity(document.toString())
					.header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods",
							"GET, POST, DELETE, PUT").build();
		} finally {
			if (client != null) {
				client.close();
			}
		}
	}
}
