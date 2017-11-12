package gdufs.iiip.weibo.data.tools.alltest;

import gdufs.iiip.weibo.data.config.DaoConfig;
import gdufs.iiip.weibo.data.dao.CRUDManager;
import gdufs.iiip.weibo.data.dao.MongoHelper;
import gdufs.iiip.weibo.data.tools.chinamap.ChinaMapUtil;
import gdufs.iiip.weibo.data.tools.topic.TopicJsonUtil;
import gdufs.iiip.weibo.data.tools.transformtree.TransformTreeUtil;

import java.util.HashMap;
import java.util.Map;

public class MainTool {

    public static void main(String[] args) {
        DaoConfig daoConfig = new DaoConfig("wb", "112.*.*.*", 12345);
        int eventId = 596;
        String eventName = "柯洁 AlphaGo";
        String saveDir = "F:\\WeiboData\\WeiboNew\\柯洁 AlphaGo";
        //年龄和性别，mongo
        Map<String, Object> ageMap = new HashMap<String, Object>();
        ageMap.put("_id", eventId);
        ageMap.put("a79", 711);
        ageMap.put("a80", 268);
        ageMap.put("a90", 1152);
        ageMap.put("a95", 4525);
        ageMap.put("anull", 3375);
        Map<String, Object> sexMap = new HashMap<String, Object>();
        sexMap.put("_id", eventId);
        sexMap.put("girl", 6800);
        sexMap.put("boy", 3220);

        MongoHelper mongoHelper = new MongoHelper(daoConfig);
        CRUDManager crudManager = new CRUDManager(mongoHelper, "age");
        crudManager.insert(ageMap);
        crudManager.printAll();

        crudManager = new CRUDManager(mongoHelper, "sex");
        crudManager.insert(sexMap);
        crudManager.printAll();
        crudManager.closeMongoClient();


        //地区,mongo
        String areapath = "F:\\WeiboData\\WeiboNew\\柯洁 AlphaGo\\area.txt";   //修改
        String eid = Integer.toString(eventId);
        ChinaMapUtil.insertArea(daoConfig, areapath, eid);

        //转发路径txt转json
        String transformtxtpath = "F:\\WeiboData\\WeiboNew\\柯洁 AlphaGo\\incident_path.txt";   //修改
        String saveFileName = "transform" + Integer.toString(eventId) + ".json";
        String transformJson = TransformTreeUtil.genTransformJson(eventName, transformtxtpath, saveDir, saveFileName);
        System.out.println(transformJson);

        String topicXmlPath = "F:\\WeiboData\\WeiboNew\\柯洁 AlphaGo\\topic" + Integer.toString(eventId) + ".xml";
        String saveTopicFileName = "topic" + Integer.toString(eventId) + ".json";
        TopicJsonUtil.genTopicJson(topicXmlPath, saveDir, eventName, saveTopicFileName);
    }
}
