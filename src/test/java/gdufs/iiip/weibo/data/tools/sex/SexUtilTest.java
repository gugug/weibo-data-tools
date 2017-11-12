package gdufs.iiip.weibo.data.tools.sex;

import gdufs.iiip.weibo.data.config.DaoConfig;
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
        DaoConfig daoConfig = new DaoConfig("aa", "sss", 33);
        Map<String, Object> ageMap = new HashMap<String, Object>();
        ageMap.put("_id", 311);
        ageMap.put("girl", 1283);
        ageMap.put("boy", 728);
        SexUtil.insertSex(daoConfig, ageMap);
    }

}