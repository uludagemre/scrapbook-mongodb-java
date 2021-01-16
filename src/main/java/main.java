import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import database.DatabaseService;
import org.bson.Document;

import java.util.ArrayList;

public class main {

    public static void main(String[] args) {

        DatabaseService service = DatabaseService.getInstance();
        MongoCollection<Document> collection = service.getCollection();

        Document document1 = new Document("family", "dogs").append("name", "punchy");
        Document document2 = new Document("family", "birds").append("name", "eagly");
        ArrayList<Document> documentList = new ArrayList<Document>();
        documentList.add(document1);
        documentList.add(document2);
        service.insertRecords(collection, documentList);

        FindIterable<Document> results = collection.find();
        for (Document result : results) {
            System.out.println(result);
        }
    }
}
