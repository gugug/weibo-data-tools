package gdufs.iiip.weibo.data.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 读取xml文件，加载相关数据
 *
 * @author iiip
 */
public class XmlFileUtil {
    private static final Logger LOG = LoggerFactory.getLogger(XmlFileUtil.class);

    public List<List<String>> keyWordList = new ArrayList<List<String>>();//value是评论关键词

    /**
     * 读取xml的文件内容
     *
     * @param path xml所在路径
     * @return
     */
    public List<String> loadXml(String path) {
        try {
            FileUtil.judgeFileExist(path);
        } catch (FileNotFoundException e) {
            LOG.error(e.getMessage(), e);
            return null;
        }
        Element element = null;
        File f = new File(path);
        DocumentBuilder db = null;
        DocumentBuilderFactory dbf = null;
        List<String> topicList = new ArrayList<String>();
        try {
            dbf = DocumentBuilderFactory.newInstance();
            db = dbf.newDocumentBuilder();
            Document dt = db.parse(f);
            element = dt.getDocumentElement();
            NodeList childNodes = element.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node node1 = childNodes.item(i);
                if ("topic".equals(node1.getNodeName())) {
                    NodeList nodeDetail = node1.getChildNodes();
                    String str = null;
                    for (int j = 0; j < nodeDetail.getLength(); j++) {
                        Node detail = nodeDetail.item(j);
                        if ("keyword".equals(detail.getNodeName())) {
                            str = "keyword: " + detail.getTextContent() + "<br />";
                            keyWordList.add(Arrays.asList(detail.getTextContent().split(" ")));
                        } else if ("represent".equals(detail.getNodeName())) {
                            str += "represent: " + detail.getTextContent();
                        }
                    }
                    topicList.add(str);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return topicList;
    }

}
