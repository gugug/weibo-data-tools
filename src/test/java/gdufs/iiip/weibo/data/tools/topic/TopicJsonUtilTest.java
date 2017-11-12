package gdufs.iiip.weibo.data.tools.topic;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by gu on 2017/11/12.
 */
public class TopicJsonUtilTest {
    @Test
    public void genTopicJson() throws Exception {
        String path = "topic594.xml";
        TopicJsonUtil.genTopicJson(path, "dir", "topic594.json", "教师殴打环卫");
    }

}