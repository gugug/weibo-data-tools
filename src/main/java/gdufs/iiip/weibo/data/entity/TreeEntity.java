package gdufs.iiip.weibo.data.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TreeEntity {
    private Integer id;
    private Integer pId;
    private String name;
    private List<TreeEntity> children;

    public TreeEntity() {

    }

    public TreeEntity(Integer id, Integer pId, String name) {
        super();
        this.id = id;
        this.pId = pId;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TreeEntity> getChildren() {
        return children;
    }

    public void setChildren(List<TreeEntity> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "TreeEntity [id=" + id + ", pId=" + pId + ", name=" + name
                + ", children=" + children + "]";
    }

    //递归树
    public static List<TreeEntity> getChildren(Map<Integer, TreeEntity> trees, Integer id, Integer leve) {
        List<TreeEntity> list = new ArrayList<TreeEntity>();
        for (TreeEntity tree : trees.values()) {
            if (id == tree.getpId()) {
                List<TreeEntity> chidren = getChildren(trees, tree.getId(), ++leve);
                list.add(tree);//本身
                tree.setChildren(chidren);//子节点
                leve--;
            }
        }
        return list;
    }
}
