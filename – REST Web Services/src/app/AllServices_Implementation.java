package app;


import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.internal.guava.Maps;

import model.AllDetails;
import model.Response;

@Path("/test")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AllServices_Implementation implements AllServices {
	
	static int sender_id=0;
	static int topic_id=0;

	 private static Map<Integer,AllDetails> Messages = new HashMap<Integer,AllDetails>();
	  public static Map<Integer,String> AllUsers=new HashMap<Integer,String>();
	  public static Map<String,String> AllTopics_Users= new HashMap<String, String>();
	  public static Map<String,Set<String>> AllSub= new HashMap< String,Set<String>>();
	  public static Map<AllDetails,String> Messages_Topics= new HashMap<AllDetails ,String>();  
	public static   ArrayList<String> Sub=new ArrayList<String>();
	
	
	 ArrayList<String> list=new ArrayList<String>();
	

//store message
	@Override
	@POST
    @Path("/add")
	
	public String[] StoreMessage(AllDetails p) {
		Response response = new Response();
		if(Messages.get(p.getId()) != null){
			response.setStatus(false);
			
			String array[]= {"Message id already Exists....It should be unique"};
			return array;
		}
		
		p.setTimeStamp( LocalDateTime.now().toString().replace("T", " ").substring(0, 19));
		
	list.add(p.getTopic_name());
       p.topics(p);
       
       sender_id++;
       topic_id++;
       p.setTopic_id(topic_id);
       AllTopics_Users.put(p.getPersonName(),p.getTopic_name());
       
		p.setSender_id(sender_id);
		AllUsers.put(p.getSender_id(),p.getPersonName());
		Messages_Topics.put(p,p.getTopic_name());
	
		
       
		Messages.put(p.getId(), p);
		response.setStatus(true);
		
		 
		    String[]array= {"Your userName:",p.getPersonName(),"-------------------------",
		    		"Your Id:",String.valueOf(p.getSender_id()), "This your personal information",
		    		"----------------------------","This is your message id:",String.valueOf(p.getId()),
		    		"----------------------------"," Message stored successfully"};
		
	
		return array;
	}
	
	
	
	
	
	
	

	//DeletingMessage
	
	@Override
	@GET
    @Path("/{id}/delete")
	public Response DeleteMessage(@PathParam("id") int id) {
		Response response = new Response();
		if(Messages.get(id) == null){
			response.setStatus(false);
			response.setMessage("The Message Doesn't Exists");
			return response;
		}
		Messages.remove(id);
		response.setStatus(true);
		response.setMessage("Message deleted successfully");
		return response;
	}

	
	
	//get message by id
	@Override
	@GET
	@Path("/{id}/get")
	public AllDetails RetrieveMessage(@PathParam("id") int id) {
		
		return Messages.get(id);
	}
	

	
	
	
	
	
	

	//getall messages on Server
	@Override
	@GET
	@Path("/getAll")
	public AllDetails[] GetAllMessages() {
		Set<Integer> ids = Messages.keySet();
		AllDetails[] p = new AllDetails[ids.size()];
		int i=0;
		for(Integer id : ids){
			p[i] = Messages.get(id);
			i++;
		}
		return p;
	}

	
	
	
	
	
	//Getting all the unique topics
	@Override
	@GET
	@Path("/getAllTopics")
	public List<String> getAllTopics() {
		
		return AllDetails.getUniqueTopics();
		
	}
	
	
	
	//Getting all users on the server
	@GET
	@Path("/getAllUsers")
	public Map<Integer, String> getUsers(){
		
		
		return AllUsers;
	}
	
	
	////////////////////////////////////////////////////////
	//adding subscriber to a certain topic
	
	@Override
	@POST
	@Path("{name}/{topic}/addSub")
	
	public Map<String, String> AddSubscriber(@PathParam("topic") String topic,@PathParam("name")String name) {
		
	
	 
		Set<String> keyss = new HashSet<>();

		if(AllSub.containsKey(topic)&&!AllSub.containsValue(name)) {
		  
			  keyss=AllSub.get(topic);

		 keyss.add(name);
		
			AllSub.put( topic,keyss);}
		
		else {
			 keyss.add(name);
				
				AllSub.put( topic,keyss);}
		return AllTopics_Users;
		}
		


			

			
		
	
	
	
	
	/////////////////////////////////////////////////////////////////////////
	//Show users of a certain topic
	@Override
	@GET
	@Path("/{topic}/ShowUsers")
	
	public Set<String> ShowUsersOfTopic(@PathParam("topic") String topic) {
	
		Set<String> keys = new HashSet<>();
	    for (Entry<String, String> entry : AllTopics_Users.entrySet()) {
	        if (entry.getValue().equals(topic)) {
	            keys.add(entry.getKey());
	        }
	    }

		return keys;
	

	
	}
	
	
	
	
	////////////////////////////////////////////
	//List subscribers of a certain topic
	@Override
	@GET
	@Path("{topicName}/ListSub")
	
	public Set<String> ListAllSubScribers(@PathParam("topicName") String topicName){
		
		return AllSub.get(topicName);
	}
	  
		
	
	////////////////////////////////////////////
	//Remove subscriber under a certain topic
	@Override
	@POST
	@Path("{name}/{topicname}/RemoveSub")
	public Set<String> RemoveSubscriber(@PathParam("topicname") String topicname,@PathParam("name") String name) {
		
		Set<String> keys =AllSub.get(topicname);
		keys.remove(name);
		return AllSub.get(topicname);
		
			
			
			
		
	}
	
	////////////////////////////////////////////////
	//List messages of a certain topic
	@Override
	@GET
	@Path("/{topicName}/getMessages")
	public Set<AllDetails> getMessages_topics(@PathParam("topicName") String topicName) {
		Set<AllDetails> keys = new HashSet<>();
	    for (Entry<AllDetails, String> entry : Messages_Topics.entrySet()) {
	        if (entry.getValue().equals(topicName)) {
	            keys.add(entry.getKey());
	        }
	    }
	    return keys;
	}
	

	
}
