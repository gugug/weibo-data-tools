package gdufs.iiip.weibo.data.tools.age;

import gdufs.iiip.weibo.data.config.DaoConfig;
import gdufs.iiip.weibo.data.dao.CRUDManager;
import gdufs.iiip.weibo.data.dao.MongoHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gu on 2017/11/12.
 */
public class AgeUtil {

    public static void insertAge(DaoConfig daoConfig, Map<String, Object> ageMap) {
        MongoHelper mongoHelper = new MongoHelper(daoConfig);
        CRUDManager crudManager = new CRUDManager(mongoHelper, "age");
        crudManager.insert(ageMap);
        crudManager.closeMongoClient();
    }

    public static void printAge(DaoConfig daoConfig) {
        MongoHelper mongoHelper = new MongoHelper(daoConfig);
        CRUDManager crudManager = new CRUDManager(mongoHelper, "age");
        crudManager.printAll();
        crudManager.closeMongoClient();
    }

    public static void main(String[] args) {
        DaoConfig daoConfig = new DaoConfig("aa", "sss", 33);
        Map<String, Object> ageMap = new HashMap<String, Object>();
        ageMap.put("_id", 311);
        ageMap.put("a79", 1283);
        ageMap.put("a80", 728);
        ageMap.put("a90", 1502);
        ageMap.put("a95", 2303);
        ageMap.put("anull", 4181);
        insertAge(daoConfig, ageMap);
    }
}
