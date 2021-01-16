package database;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.List;

public class DatabaseService {
    private static DatabaseService dbInstance;
    private static MongoClient client;
    private static MongoCollection<Document> collection;

    public static DatabaseService getInstance() {
        if (dbInstance == null) {
            dbInstance = new DatabaseService();
            client = createClient();
            createDatabase();
        }

        return dbInstance;
    }

    public MongoCollection<Document> getCollection() {
        return collection;
    }

    public void insertRecords(MongoCollection<Document> collection, List<Document> documents) {
        collection.insertMany(documents);
    }

    private static void createDatabase() {
        MongoDatabase db = client.getDatabase("test");
        collection = createCollection(db);
    }

    private static MongoCollection<Document> createCollection(MongoDatabase db) {
        if (!db.listCollections().iterator().hasNext()) {
            db.createCollection("zoo");
        }

        return db.getCollection("zoo");
    }

    private static MongoClient createClient() {
        return new MongoClient("localhost", 27017);
    }
}
