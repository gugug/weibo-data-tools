package gdufs.iiip.weibo.data.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
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

    public String getTABLE_NAME() {
        return TABLE_NAME;
    }

    public void setTABLE_NAME(String TABLE_NAME) {
        this.TABLE_NAME = TABLE_NAME;
    }

    public boolean insert(Map<String, Object> map) {
        return mongoDaoImpl.insert(mongoDataBase, TABLE_NAME, new Document(map));
    }

    public boolean insertMany(List<Document> documents) {
        return mongoDaoImpl.insertMany(mongoDataBase, TABLE_NAME, documents);
    }

    public boolean deleteOne(Map<String, Object> map) {
        //   根据map 删除mongodb里找到的第一个
        return mongoDaoImpl.deleteOne(mongoDataBase, TABLE_NAME, new BasicDBObject(map));
    }

    public boolean deleteMany(Map<String, Object> map) {
        return mongoDaoImpl.delete(mongoDataBase, TABLE_NAME, new BasicDBObject(map));
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
