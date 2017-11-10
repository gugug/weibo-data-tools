package gdufs.iiip.weibo.data.entity;

import java.util.HashMap;
import java.util.List;

/**
 * 用于生成话题挖掘的json数据的封装类
 *
 * @author iiip
 */
public class TopicEntity {
    private String name;
    private List<HashMap<String, Object>> children;

    public TopicEntity() {
        super();
    }

    public TopicEntity(String name, List<HashMap<String, Object>> children) {
        super();
        this.name = name;
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<HashMap<String, Object>> getChildren() {
        return children;
    }

    public void setChildren(List<HashMap<String, Object>> children) {
        this.children = children;
    }

}
