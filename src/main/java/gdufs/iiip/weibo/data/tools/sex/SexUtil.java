package gdufs.iiip.weibo.data.tools.sex;

import gdufs.iiip.weibo.data.config.DaoConfig;
import gdufs.iiip.weibo.data.dao.CRUDManager;
import gdufs.iiip.weibo.data.dao.MongoHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gu on 2017/11/12.
 */
public class SexUtil {
    public static void insertSex(DaoConfig daoConfig, Map<String, Object> sexMap) {
        MongoHelper mongoHelper = new MongoHelper(daoConfig);
        CRUDManager crudManager = new CRUDManager(mongoHelper, "sex");
        crudManager.insert(sexMap);
        crudManager.closeMongoClient();
    }

    public static void main(String[] args) {
        DaoConfig daoConfig = new DaoConfig("aa", "sss", 33);
        Map<String, Object> sexMap = new HashMap<String, Object>();
        sexMap.put("_id", 311);
        sexMap.put("girl", 1283);
        sexMap.put("boy", 728);
        insertSex(daoConfig, sexMap);
    }

}
