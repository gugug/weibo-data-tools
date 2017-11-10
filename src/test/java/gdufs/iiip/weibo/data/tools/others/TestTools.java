package gdufs.iiip.weibo.data.tools.others;

import gdufs.iiip.weibo.data.config.DaoConfig;
import gdufs.iiip.weibo.data.config.PathConfig;
import gdufs.iiip.weibo.data.tools.chinamap.ChinaMapUtil;
import gdufs.iiip.weibo.data.tools.topic.TopicJson;
import gdufs.iiip.weibo.data.tools.transformtree.TransformTree;

/**
 * Created by gu on 2017/11/10.
 */
public class TestTools {

    public static void main(String[] args) {
        testTransform();
    }

    public static void testTransform() {
        String transformtxtpath = "C:\\Users\\gu\\Desktop\\incident_path2.txt";
        String eventName = "杭州保姆纵火";
        String save = "C:\\Users\\gu\\Desktop";
        String transformJson = TransformTree.genTransformJson(eventName, transformtxtpath, save, "transform1.json");
        System.out.println(transformJson);
    }

    public static void testChina() {
        DaoConfig daoConfig = new DaoConfig("weibo", "127.0.0.1", 27017);
        String areapath = "/media/iiip/Elements/WeiboData/WeiboNew/教师殴打环卫/area.txt";
        String eid = "594";
        ChinaMapUtil.insertArea(daoConfig, areapath, eid);
    }

    public static void testTopic() {
        String path = "topic594.xml";
        TopicJson.genTopicJson(path, "dir", "topic594.json", "教师殴打环卫");
    }

    public static void testPath() {
        PathConfig pathConfig = new PathConfig("/home/iiip/java/workspace/weibo/WeiboNewsProject/WebContent/");
        System.out.println(pathConfig.getCHARACTER_TXT_PATH());

    }
}
