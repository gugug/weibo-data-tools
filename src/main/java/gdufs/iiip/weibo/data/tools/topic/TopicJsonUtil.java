package gdufs.iiip.weibo.data.tools.topic;

import com.alibaba.fastjson.JSON;
import gdufs.iiip.weibo.data.utils.FileUtil;
import gdufs.iiip.weibo.data.utils.XmlFileUtil;
import gdufs.iiip.weibo.data.entity.TopicEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 包装成话题对象需要的json数据
 *
 * @author iiip
 */
public class TopicJsonUtil {

    private static final Logger LOG = LoggerFactory.getLogger(TopicJsonUtil.class);

    public static void main(String[] args) {
        String path = "topic594.xml";
        genTopicJson(path, "dir", "topic594.json", "教师殴打环卫");
    }

    /**
     * 生成toopic的json数据
     * @param topciXmlPath 话题的xml文件路径
     * @param saveDir 保存结果的目录文件夹
     * @param eventName 事件名字
     * @param fileName 文件的命名
     * @return
     */
    public static String genTopicJson(String topciXmlPath, String saveDir, String eventName, String fileName) {
        checkNotNull(topciXmlPath, "Provided file name for writing must NOT be null.");
        checkNotNull(saveDir, "Provided dir name for writing must NOT be null.");
        try {
            FileUtil.judgeFileExist(topciXmlPath);
            FileUtil.judgeDirExist(saveDir);
            XmlFileUtil xmlFileUtil = new XmlFileUtil();
            xmlFileUtil.loadXml(topciXmlPath);
            Object topicJson = getTopicJson(xmlFileUtil.keyWordList, eventName);
            FileUtil.rwFile(topicJson.toString(), saveDir, fileName);
            return topicJson.toString();
        } catch (Exception ex) {
            LOG.error("ERROR trying to genTopicJson");
            LOG.error(ex.getMessage(), ex);
        }
        return null;
    }

    /**
     * 打包返回topic的json数据格式
     *
     * @param keyWordList
     * @param eventName
     * @return
     */
    private static Object getTopicJson(List<List<String>> keyWordList, String eventName) {
        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        ArrayList<TopicEntity> topicObjList = new ArrayList<TopicEntity>();
        for (int i = 0; i < keyWordList.size(); i++) {
            ArrayList<HashMap<String, Object>> kwList = new ArrayList<HashMap<String, Object>>();
            for (int j = 0; j < keyWordList.get(i).size(); j++) {
                HashMap<String, Object> kwMap = new HashMap<String, Object>();
                kwMap.put("name", keyWordList.get(i).get(j));
                kwMap.put("size", 1);
                kwList.add(kwMap);
            }
            TopicEntity topicObj = new TopicEntity("话题" + (i + 1), kwList);
            topicObjList.add(topicObj);
        }
        resultMap.put("name", eventName);
        resultMap.put("children", topicObjList);
        return JSON.toJSON(resultMap);
    }

}
