package gdufs.iiip.weibo.data.tools.transformtree;

/**
 * Created by gu on 2017/11/27.
 */

/**
 * 节点类
 */
public class Node {
    /**
     * 节点编号
     */
    public Integer id;
    /**
     * 节点内容
     */
    public String name;
    /**
     * 父节点编号
     */
    public String parentId;
    /**
     * 孩子节点列表
     */
    private Children children = new Children();

    // 先序遍历，拼接JSON字符串
    public String toString() {
        String result = "{"
                + "\"id\" : \"" + id + "\""
                + ", \"name\" : \"" + name + "\""
                + ", \"pId\" : \"" + parentId + "\"";

        if (children != null && children.getSize() != 0) {
            result += ", \"children\" : " + children.toString();
        } else {
            result += ", \"leaf\" : \"true\"";
        }

        return result + "}";
    }

    // 兄弟节点横向排序
    public void sortChildren() {
        if (children != null && children.getSize() != 0) {
            children.sortChildren();
        }
    }

    // 添加孩子节点
    public void addChild(Node node) {
        this.children.addChild(node);
    }
}