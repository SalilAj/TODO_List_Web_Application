package ie.tcd.ajgaonks.DBWrapper.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import static com.mongodb.client.model.Filters.*;

import ie.tcd.ajgaonks.DBWrapper.beans.MemberInfoObject;
import ie.tcd.ajgaonks.DBWrapper.beans.TaskDoc;
import ie.tcd.ajgaonks.DBWrapper.beans.TasksObject;

@PropertySource(value = "classpath:application.yml")
@Service
public class QueryService {

	private static final Logger LOGGER = LoggerFactory.getLogger(QueryService.class);

	MongoClient mongoClient;

	@Value("${mongoDB.databaseName}")
	String dbName;

	@Value("${mongoDB.memberCollection}")
	String memberCollection;

	@Value("${mongoDB.taskCollection}")
	String taskCollection;

	@Value("${mongoDB.host}")
	String mongoHost;

	@Value("${mongoDB.port}")
	Integer mongoPort;

	public boolean dbInstanceExistence() {
		if (mongoClient != null) {
			return true;
		} else {
			try {
				createDbInstance();
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
				return false;
			}
			return true;
		}
	}

	public MongoClient createDbInstance() {
		mongoClient = new MongoClient("localhost", 27017);
		return mongoClient;
	}

	public boolean addMemberData(MemberInfoObject memberInfo) {
		dbInstanceExistence();

		MongoDatabase database = mongoClient.getDatabase(dbName);
		MongoCollection<Document> collection = database.getCollection("memberInfo");

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
		MongoCollection<Document> collection = database.getCollection("memberInfo");

		Document member = new Document("email", email);
		FindIterable<Document> results = collection.find(member);

		for (Document doc : results) {
			memberInfo = new MemberInfoObject();
			memberInfo.setMemberId(doc.getString("memberId"));
			memberInfo.setFirstName(doc.getString("firstName"));
			memberInfo.setLastName(doc.getString("lastName"));
			memberInfo.setEmail(doc.getString("email"));
			memberInfo.setPassword(doc.getString("password"));
			break;
		}

		return memberInfo;

	}

	public void addMemberTasks(String memberId, TaskDoc taskObj) {
		dbInstanceExistence();

		MongoDatabase database = mongoClient.getDatabase(dbName);
		MongoCollection<Document> collection = database.getCollection("memberTasks");

		UpdateOptions updateOp = new UpdateOptions();
		collection.updateOne(eq("memberId", memberId),
				new Document("$addToSet",
						new Document("tasks", new Document("id", taskObj.getId()).append("task", taskObj.getTask()))),
				updateOp.upsert(true));

	}

	public TasksObject getMemberTasks(String memberId) {

		TasksObject memberTasks = null;
		dbInstanceExistence();
		ArrayList<TaskDoc> tasks = new ArrayList<TaskDoc>();

		MongoDatabase database = mongoClient.getDatabase(dbName);
		MongoCollection<Document> collection = database.getCollection("memberTasks");

		Document member = new Document("memberId", memberId);
		FindIterable<Document> results = collection.find(member);

		for (Document doc : results) {
			memberTasks = new TasksObject();
			memberTasks.setMemberId(doc.getString("memberId"));
			ArrayList<Document> taskList = (ArrayList<Document>) doc.get("tasks");
			for (Document task : taskList) {
				TaskDoc taskDoc = new TaskDoc();
				taskDoc.setId(task.getString("id"));
				taskDoc.setTask(task.getString("task"));
				tasks.add(taskDoc);
			}
			memberTasks.setTask(tasks);
			break;
		}

		return memberTasks;
	}

	public void deleteTask(String memberId, String taskId) {
		dbInstanceExistence();

		MongoDatabase database = mongoClient.getDatabase(dbName);
		MongoCollection<Document> collection = database.getCollection("memberTasks");

		collection.updateOne(eq("memberId", memberId),
				new Document("$pull", new Document("tasks", new Document("id", taskId))));
	}
}
