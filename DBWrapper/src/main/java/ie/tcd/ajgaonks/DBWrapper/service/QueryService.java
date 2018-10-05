package ie.tcd.ajgaonks.DBWrapper.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import ie.tcd.ajgaonks.DBWrapper.beans.MemberInfoObject;
import ie.tcd.ajgaonks.DBWrapper.beans.TaskDoc;
import ie.tcd.ajgaonks.DBWrapper.beans.TasksObject;

@PropertySource(value = "classpath:application.yml")

@Service
public class QueryService {

	@Value("${mongoDB.userName}")
	String mongoUserName;

	@Value("${mongoDB.password}")
	String mongoPassword;

	@Value("${mongoDB.databaseName}")
	String dbName;

	@Value("${mongoDB.memberCollection}")
	String memberCollection;

	@Value("${mongoDB.taskCollection}")
	String taskCollection;

	@Value("${mongoDB.clientURI}")
	String mongoClientURI;

	MongoClient mongoClient = new MongoClient(new MongoClientURI(mongoClientURI));

	public boolean dbInstanceExistence() {
		if (mongoClient != null) {
			return true;
		} else {
			createDbInstance();
			return true;
		}
	}

	public MongoClient createDbInstance() {
		mongoClient = new MongoClient(new MongoClientURI(mongoClientURI));
		return mongoClient;
	}

	public boolean addMemberData(MemberInfoObject memberInfo) {
		dbInstanceExistence();

		MongoDatabase database = mongoClient.getDatabase(dbName);
		MongoCollection<Document> collection = database.getCollection(memberCollection);

		Document member = new Document("memberId", memberInfo.getMemberId())
				.append("firstName", memberInfo.getFirstName()).append("lastName", memberInfo.getLastName())
				.append("email", memberInfo.getEmail()).append("password", memberInfo.getPassword());

		collection.insertOne(member);
		return true;

	}

	public MemberInfoObject getMemberData(String email) {

		MemberInfoObject memberInfo = null;
		dbInstanceExistence();

		MongoDatabase database = mongoClient.getDatabase(dbName);
		MongoCollection<Document> collection = database.getCollection(memberCollection);
		
		if(email != null)
		{
			Document member = new Document("email", email);
			FindIterable<Document> results = collection.find(member);

			for (Document doc : results) {
				memberInfo = new MemberInfoObject();
				memberInfo.setMemberId(doc.getString("memberId"));
				memberInfo.setFirstName(doc.getString("firstName"));
				memberInfo.setLastName(doc.getString("lastName"));
				memberInfo.setPassword(doc.getString("email"));
				memberInfo.setPassword(doc.getString("password"));
				break;
			}
		}
		else {
			//Error;
		}

		return memberInfo;

	}

	public void addMemberTasks(String memberId, TaskDoc taskObj) {
		dbInstanceExistence();

		MongoDatabase database = mongoClient.getDatabase(dbName);
		MongoCollection<Document> collection = database.getCollection(taskCollection);

		BasicDBObject addToSet = new BasicDBObject("$addToSet", new BasicDBObject("tasks", taskObj));
		BasicDBObject filter = new BasicDBObject("memberId", memberId);
		
		collection.updateOne(filter, addToSet);

	}

	public TasksObject getMemberTasks(String memberId) {

		TasksObject memberTasks = null;
		dbInstanceExistence();

		MongoDatabase database = mongoClient.getDatabase(dbName);
		MongoCollection<Document> collection = database.getCollection(taskCollection);

		Document member = new Document("memberId", memberId);
		FindIterable<Document> results = collection.find(member);

		for (Document doc : results) {
			memberTasks = new TasksObject();
			memberTasks.setMemberId(doc.getString("memberId"));
			ArrayList<TaskDoc> taskList = (ArrayList<TaskDoc>) doc.get("tasks");
			memberTasks.setTask(taskList);
			break;
		}

		return memberTasks;
	}

	public void deleteTask(String memberId, String taskId)
	{
		dbInstanceExistence();

		MongoDatabase database = mongoClient.getDatabase(dbName);
		MongoCollection<Document> collection = database.getCollection(taskCollection);
		
		BasicDBObject pull = new BasicDBObject("$pull", new BasicDBObject("tasks", new BasicDBObject("taskId",taskId)));
		BasicDBObject filter = new BasicDBObject("memberId", memberId);
		
		collection.updateOne(filter, pull);
	}
}
