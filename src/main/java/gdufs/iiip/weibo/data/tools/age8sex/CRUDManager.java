package gdufs.iiip.weibo.data.tools.age8sex;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import gdufs.iiip.weibo.data.dao.MongoDaoImpl;
import gdufs.iiip.weibo.data.dao.MongoHelper;
import org.bson.Document;

import java.util.List;
import java.util.Map;

/**
 * Created by gu on 2017/11/10.
 */
public class CRUDManager {

    private MongoHelper mongoHelper;
    private MongoClient mongoClient;
    private MongoDatabase mongoDataBase;
    private String TABLE_NAME;
    MongoDaoImpl mongoDaoImpl;

    public CRUDManager(MongoHelper mongoHelper, String TABLE_NAME) {
        this.mongoHelper = mongoHelper;
        this.mongoClient = mongoHelper.getMongoClient();
        this.mongoDataBase = mongoHelper.getMongoDataBase(mongoClient);
        this.mongoDaoImpl = new MongoDaoImpl();
        this.TABLE_NAME = TABLE_NAME;
    }

    public boolean insert(Map<String, Object> ageMap) {
        return mongoDaoImpl.insert(mongoDataBase, TABLE_NAME, new Document(ageMap));
    }

    public boolean insertMany(List<Document> documents) {
        return mongoDaoImpl.insertMany(mongoDataBase, TABLE_NAME, documents);
    }

    public boolean deleteOne(Map<String, Object> ageMap) {
        //   根据map 删除mongodb里找到的第一个
        return mongoDaoImpl.deleteOne(mongoDataBase, TABLE_NAME, new BasicDBObject(ageMap));
    }

    public boolean deleteMany(Map<String, Object> ageMap) {
        return mongoDaoImpl.delete(mongoDataBase, TABLE_NAME, new BasicDBObject(ageMap));
    }

    public boolean updateMany(Map<String, Object> updateDoc, Map<String, Object> wehereDoc) {
        return mongoDaoImpl.update(mongoDataBase, TABLE_NAME, new BasicDBObject(wehereDoc), new BasicDBObject(updateDoc));

    }

    public boolean updateOne(Map<String, Object> updateDoc, Map<String, Object> wehereDoc) {
        return mongoDaoImpl.updateOne(mongoDataBase, TABLE_NAME, new BasicDBObject(wehereDoc), new BasicDBObject(updateDoc));
    }

    public void printAll() {
        List<Map<String, Integer>> queryAllResult = mongoDaoImpl.queryAll(mongoDataBase, TABLE_NAME);
        mongoDaoImpl.printList(queryAllResult);
    }

    public void closeMongoClient() {
        mongoHelper.closeMongoClient(mongoDataBase, mongoClient);
    }

}
