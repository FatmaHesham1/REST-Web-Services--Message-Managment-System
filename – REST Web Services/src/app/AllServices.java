package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.AllDetails;
import model.Response;

public interface AllServices {

	public String[] StoreMessage(AllDetails p);
	
	public Response DeleteMessage(int id);
	
	public AllDetails RetrieveMessage(int id);
	
	public AllDetails[] GetAllMessages();
	


	


	List<String> getAllTopics();

	



	
	Map<Integer, String> getUsers();

	Map<String, String> AddSubscriber(String topic,String name);
	Set<String> ListAllSubScribers( String topicName);

	Set<String> RemoveSubscriber(String topic,String name);
	Set<AllDetails> getMessages_topics(String topic);

	Set<String> ShowUsersOfTopic(String topic);

	

}
