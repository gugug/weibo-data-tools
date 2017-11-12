package gdufs.iiip.weibo.data.tools.alltest;

import gdufs.iiip.weibo.data.config.DaoConfig;
import gdufs.iiip.weibo.data.config.PathConfig;
import gdufs.iiip.weibo.data.tools.age.AgeUtil;
import gdufs.iiip.weibo.data.tools.chinamap.ChinaMapUtil;
import gdufs.iiip.weibo.data.tools.sex.SexUtil;
import gdufs.iiip.weibo.data.tools.topic.TopicJsonUtil;
import gdufs.iiip.weibo.data.tools.transformtree.TransformTreeUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gu on 2017/11/10.
 */
public class AllToolsTest {

    public static void main(String[] args) {
        testTransform();
    }

    public static void testTransform() {
        String transformtxtpath = "C:\\Users\\gu\\Desktop\\incident_path2.txt";
        String eventName = "杭州保姆纵火";
        String save = "C:\\Users\\gu\\Desktop";
        String transformJson = TransformTreeUtil.genTransformJson(eventName, transformtxtpath, save, "transform1.json");
        System.out.println(transformJson);
    }

    public static void testChinaMap() {
        DaoConfig daoConfig = new DaoConfig("wb", "127.0.0.1", 2251);
        String areapath = "/media/iiip/Elements/WeiboData/WeiboNew/教师殴打环卫/area.txt";
        String eid = "594";
        ChinaMapUtil.insertArea(daoConfig, areapath, eid);
    }

    public static void testTopic() {
        String path = "topic594.xml";
        TopicJsonUtil.genTopicJson(path, "dir", "topic594.json", "教师殴打环卫");
    }

    public static void testPath() {
        PathConfig pathConfig = new PathConfig("/home/iiip/java/workspace/weibo/WeiboNewsProject/WebContent/");
        System.out.println(pathConfig.getCHARACTER_TXT_PATH());
    }

    public void testInsertAge() throws Exception {
        DaoConfig daoConfig = new DaoConfig("aa", "sss", 33);
        Map<String, Object> ageMap = new HashMap<String, Object>();
        ageMap.put("_id", 311);
        ageMap.put("a79", 1283);
        AgeUtil.insertAge(daoConfig, ageMap);
        AgeUtil.printAge(daoConfig);
    }

    public void testInsertSex() throws Exception {
        DaoConfig daoConfig = new DaoConfig("aa", "sss", 33);
        Map<String, Object> sexMap = new HashMap<String, Object>();
        sexMap.put("_id", 311);
        sexMap.put("girl", 1283);
        SexUtil.insertSex(daoConfig, sexMap);
        SexUtil.printSex(daoConfig);
    }

}
