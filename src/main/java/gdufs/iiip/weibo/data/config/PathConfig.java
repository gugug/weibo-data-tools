package gdufs.iiip.weibo.data.config;

public class PathConfig {

    private String ROOT_PATH = "/home/iiip/java/workspace/weibo/WeiboNewsProject/WebContent/";
    private String TOPIC_JSON_PATH = "Documents/TopicJson/";
    private String TOPIC_XML_PATH = "Documents/TopicXML/";
    private String TRANSFORM_JSON_PATH = "Documents/TransformJson/";
    private String CHARACTER_TXT_PATH = "Documents/CharacterTXT/";
    private String TRANSFORM_TXT_PATH = "Documents/TransformTXT/";

    public PathConfig(String ROOT_PATH) {
        this.ROOT_PATH = ROOT_PATH;
    }

    public String getROOT_PATH() {
        return ROOT_PATH;
    }

    public void setROOT_PATH(String ROOT_PATH) {
        this.ROOT_PATH = ROOT_PATH;
    }

    public String getTOPIC_JSON_PATH() {
        return ROOT_PATH + TOPIC_JSON_PATH;
    }

    public void setTOPIC_JSON_PATH(String TOPIC_JSON_PATH) {
        this.TOPIC_JSON_PATH = TOPIC_JSON_PATH;
    }

    public String getTOPIC_XML_PATH() {
        return ROOT_PATH + TOPIC_XML_PATH;
    }

    public void setTOPIC_XML_PATH(String TOPIC_XML_PATH) {
        this.TOPIC_XML_PATH = TOPIC_XML_PATH;
    }

    public String getTRANSFORM_JSON_PATH() {
        return ROOT_PATH + TRANSFORM_JSON_PATH;
    }

    public void setTRANSFORM_JSON_PATH(String TRANSFORM_JSON_PATH) {
        this.TRANSFORM_JSON_PATH = TRANSFORM_JSON_PATH;
    }

    public String getCHARACTER_TXT_PATH() {
        return ROOT_PATH + CHARACTER_TXT_PATH;
    }

    public void setCHARACTER_TXT_PATH(String CHARACTER_TXT_PATH) {
        this.CHARACTER_TXT_PATH = CHARACTER_TXT_PATH;
    }

    public String getTRANSFORM_TXT_PATH() {
        return ROOT_PATH + TRANSFORM_TXT_PATH;
    }

    public void setTRANSFORM_TXT_PATH(String TRANSFORM_TXT_PATH) {
        this.TRANSFORM_TXT_PATH = TRANSFORM_TXT_PATH;
    }

    public static void main(String[] args) {
        PathConfig pathConfig = new PathConfig("/home/iiip/java/workspace/weibo/WeiboNewsProject/WebContent/");
        System.out.println(pathConfig.getCHARACTER_TXT_PATH());
    }
}
