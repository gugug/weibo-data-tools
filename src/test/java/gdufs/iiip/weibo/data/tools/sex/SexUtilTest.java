package gdufs.iiip.weibo.data.tools.sex;

import gdufs.iiip.weibo.data.config.DaoConfig;
import gdufs.iiip.weibo.data.dao.CRUDManager;
import gdufs.iiip.weibo.data.dao.MongoHelper;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by gu on 2017/11/12.
 */
public class SexUtilTest {
    @Test
    public void insertSex() throws Exception {
        DaoConfig daoConfig = new DaoConfig();
//        Map<String, Object> sexMap = new HashMap<String, Object>();
//        sexMap.put("_id", 4);
//        sexMap.put("girl", 1283);
//        sexMap.put("boy", 728);
//        SexUtil.insertSex(daoConfig, sexMap);


        SexUtil.printSex(daoConfig);
    }

}