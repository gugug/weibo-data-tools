package gdufs.iiip.weibo.data.tools.age8sex;

import gdufs.iiip.weibo.data.config.DaoConfig;
import gdufs.iiip.weibo.data.dao.MongoHelper;
import org.bson.Document;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gu on 2017/11/10.
 */
public class CRUDManagerTest {
    DaoConfig daoConfig = new DaoConfig("weibo", "add", 27017);
    MongoHelper mongoHelper = new MongoHelper(daoConfig);
    CRUDManager crudManager = new CRUDManager(mongoHelper, "age");

    @Test
    public void insert() throws Exception {
        Map<String, Object> ageMap = new HashMap<String, Object>();
        ageMap.put("_id", 311);
        ageMap.put("a79", 1283);
        ageMap.put("a80", 728);
        ageMap.put("a90", 1502);
        ageMap.put("a95", 2303);
        ageMap.put("anull", 4181);
        crudManager.insert(ageMap);
        crudManager.closeMongoClient();
    }

    @Test
    public void insertMany() throws Exception {
        Map<String, Object> ageMap = new HashMap<String, Object>();
        List<Document> docList = new ArrayList<Document>();
        docList.add(new Document(ageMap));
        crudManager.insertMany(docList);
    }

    @Test
    public void deleteOne() throws Exception {
    }

    @Test
    public void deleteMany() throws Exception {
    }

    @Test
    public void updateMany() throws Exception {
    }

    @Test
    public void updateOne() throws Exception {
    }

    @Test
    public void printAll() throws Exception {
    }

    @Test
    public void closeMongoClient() throws Exception {
    }

}