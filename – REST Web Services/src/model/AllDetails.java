package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "test") // sending xml
public class AllDetails {

	public static HashMap<String, List<AllDetails>> Topics = new HashMap<String, List<AllDetails>>();

	public static List<String> UniqueTopics = new ArrayList<>();

	public static List<AllDetails> AllMessages = new ArrayList<>();

	public static List<String> Sub_Topics;

	public static Map<Integer, String> AllUsers = new HashMap<Integer, String>();

	public static List<AllDetails> people = new ArrayList<>();

	public String PersonName;
	
	private String content;
	
	private int id;
	
	private int topic_id;
	
	private int sender_id;
	
	private String timeStamp;
	
	private String topic_name;

	User user = new User();

	
	public AllDetails(String content, int id, int topic_id, int sender_id, String timeStamp, String TopicName,
			String PersonName) {
		super();
		this.content = content;
		this.id = id;
		this.topic_id = topic_id;
		sender_id++;
		this.PersonName = PersonName;

		this.timeStamp = LocalDateTime.now().toString().replace("T", " ").substring(0, 19);
	}
	
	
	public AllDetails() {
		super();
		sender_id++;

		// TODO Auto-generated constructor stub
	}
	
	public int getSender_id() {
		return sender_id;
	}

	public void setSender_id(int sender_id) {

		this.sender_id = sender_id;
		

	}

	public String getPersonName() {
		return PersonName;

	}

	public void setPersonName(String personName) {
		PersonName = personName;
		setAllUsers();

	}

	public void setAllUsers() {

		AllUsers.put(getSender_id(), getPersonName());
	}

	public static HashMap<Integer, String> getAllUsers() {
		return (HashMap<Integer, String>) AllUsers;
	}

	public static HashMap<String, List<AllDetails>> getTopics() {
		return Topics;
	}

	public static void setTopics(HashMap<String, List<AllDetails>> topics) {
		Topics = topics;
	}

	public static List<AllDetails> getPeople() {
		return people;
	}

	public static void setPeople(List<AllDetails> people) {
		AllDetails.people = people;
	}

	

	public String getTimeStamp() {
		return timeStamp;
	}

	public void subTopics() {

	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	
	

	public String getTopic_name() {
		return topic_name;
	}

	public void setTopic_name(String topic_name) {
		this.topic_name = topic_name;

		if (getUniqueTopics().contains(topic_name) == false) {
			setUniqueTopics(topic_name);
		}

	}

	public static List<String> getUniqueTopics() {
		return UniqueTopics;
	}

	public void setUniqueTopics(String topic) {

		UniqueTopics.add(topic);
	}

	



	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTopic_id() {
		return topic_id;
	}

	public void setTopic_id(int topic_id2) {
		this.topic_id = topic_id2;
	}

	public HashMap<String, List<AllDetails>> topics(AllDetails p) {

		if (Topics.containsKey(getTopic_name())) {
			people.add(p);

		} else {
			people.add(p);
			Topics.put(getTopic_name(), people);
		}

		return Topics;

	}

	public List<String> GetUniqueTopics() {
		UniqueTopics.add(getTopic_name());
		return UniqueTopics;
	}
	
	@Override
	public String toString() {
		return id + "::" + content + "::" + topic_id + "::" + PersonName + "::" + topic_name + "::" + timeStamp + "::";
	}

}
