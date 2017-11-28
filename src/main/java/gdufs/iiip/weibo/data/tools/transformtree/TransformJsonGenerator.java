package gdufs.iiip.weibo.data.tools.transformtree;

import gdufs.iiip.weibo.data.utils.FileUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by gu on 2017/11/27.
 */
public class TransformJsonGenerator {
    private static final String SPILT_SYMBOL = "~";

//    public static void main(String[] args) {
//        String transformTxt = "C:\\Users\\gu\\Desktop\\incident_path.txt";
//        String eventName = "虐童";
//        String saveDir = "C:\\Users\\gu\\Desktop";
//        String saveFileName = "transform1.json";
//        transformJsonGenerator(transformTxt, eventName, saveDir, saveFileName);
//    }

    /**
     * 封装
     * @param transformTxt 转发链路的文件路径
     * @param eventName 事件名字
     * @param saveDir 保存jsons数据的文件夹
     * @param saveFileName json文件命名<transform+eId.json></>
     */
    public static void transformJsonGenerator(String transformTxt, String eventName, String saveDir, String saveFileName) {
        List<String> strings = subTxt(transformTxt);
        String tempPath = new File(transformTxt).getParentFile().getPath();
        String reWriteTxtPath = reWriteTxt(eventName, strings, tempPath, "incident_path_temp.txt");
        Map<String, Integer> allNode = getAllNode(reWriteTxtPath);
        List<List<Node>> tupleList = getRelation(reWriteTxtPath, allNode);
        String treeResult = genTreeResult(tupleList);
        new File(reWriteTxtPath).delete();
        FileUtil.rwFile(treeResult, saveDir, saveFileName);
    }

    /**
     * 把父子关系 转化为树状图
     *
     * @param tupleList
     * @return
     */
    private static String genTreeResult(List<List<Node>> tupleList) {
        List<HashMap<String, String>> dataList = getVirtualResult(tupleList);
        // 节点列表（散列表，用于临时存储节点对象）
        HashMap<Integer, Node> nodeList = new HashMap();
        // 根节点
        Node root = null;
        // 根据结果集构造节点列表（存入散列表）
        for (Iterator it = dataList.iterator(); it.hasNext(); ) {
            Map<String, String> dataRecord = (Map) it.next();
            Node node = new Node();
            node.id = Integer.parseInt(dataRecord.get("id"));
            node.name = dataRecord.get("name");
            node.parentId = dataRecord.get("parentId");
            nodeList.put(node.id, node);
        }
        // 构造无序的多叉树
        Set entrySet = nodeList.entrySet();
        for (Iterator it = entrySet.iterator(); it.hasNext(); ) {
            Node node = (Node) ((Map.Entry) it.next()).getValue();
            if (node.parentId == null || node.parentId.equals("")) {
                root = node;
            } else {
                (nodeList.get(Integer.parseInt(node.parentId))).addChild(node);
            }
        }
        // 输出无序的树形菜单的JSON字符串
        // System.out.println(root.toString());
        return root.toString();
    }

    /**
     * 把对应的<父，子>关系，使用结构体保存在字典里
     *
     * @param tupleList
     * @return
     */
    private static List<HashMap<String, String>> getVirtualResult(List<List<Node>> tupleList) {

        List dataList = new ArrayList();
        HashMap<String, String> headDataRecord = new HashMap();
        headDataRecord.put("id", tupleList.get(0).get(0).id.toString());
        headDataRecord.put("name", tupleList.get(0).get(0).name);
        dataList.add(headDataRecord);

        for (int i = 0; i < tupleList.size(); i++) {
            HashMap<String, String> dataRecord = new HashMap();
            dataRecord.put("id", tupleList.get(i).get(1).id.toString());
            dataRecord.put("name", tupleList.get(i).get(1).name);
            dataRecord.put("parentId", tupleList.get(i).get(0).id.toString());
            dataList.add(dataRecord);
        }
        return dataList;
    }

    /**
     * 读取裁剪后的链路关系，记录对应的<父，子>关系。 [[1,2],[2,3]....]
     *
     * @param source
     * @param nodeMap
     * @return
     */
    private static List<List<Node>> getRelation(String source, Map<String, Integer> nodeMap) {
        List<List<Node>> tupleList = new ArrayList<>();
        FileReader fr = null;
        BufferedReader br = null;
        try {
            File f = new File(source);
            fr = new FileReader(f);
            br = new BufferedReader(fr);
            String str;
            while ((str = br.readLine()) != null) {
                String[] nodes = str.split(SPILT_SYMBOL);
                for (int i = 0; i < nodes.length - 1; i++) {
                    String parNodeName = nodes[i];
                    int parNodeId = nodeMap.get(parNodeName);
                    String sonNodeName = nodes[i + 1];
                    int sonNodeId = nodeMap.get(sonNodeName);
                    if (sonNodeId > parNodeId) {
                        List<Node> relation = new ArrayList<>();
                        Node parNode = new Node();
                        parNode.id = parNodeId;
                        parNode.name = parNodeName;
                        Node sonNode = new Node();
                        sonNode.id = sonNodeId;
                        sonNode.name = sonNodeName;
                        sonNode.parentId = String.valueOf(parNodeId);
                        relation.add(parNode);
                        relation.add(sonNode);
                        tupleList.add(relation);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return tupleList;
    }

    /**
     * 读取裁剪后的链路文件，为每一个链路赋予id
     *
     * @param source
     * @return
     */
    private static Map<String, Integer> getAllNode(String source) {
        FileReader fr = null;
        BufferedReader br = null;
        Map<String, Integer> nodeMap = new HashMap<String, Integer>();
        try {
            File f = new File(source);
            fr = new FileReader(f);
            br = new BufferedReader(fr);
            String str;
            while ((str = br.readLine()) != null) {
                String[] nodes = str.split(SPILT_SYMBOL);
                for (int i = 0; i < nodes.length; i++) {
                    if (!nodeMap.containsKey(nodes[i])) {
                        nodeMap.put(nodes[i], nodeMap.size());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return nodeMap;
    }

    /**
     * 保存裁剪后的传播关系
     *
     * @param firstNodeName
     * @param strings
     * @param filePath
     * @param fileName
     */
    private static String reWriteTxt(String firstNodeName, List<String> strings, String filePath, String fileName) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strings.size(); i++) {
            sb.append(firstNodeName).append(SPILT_SYMBOL).append(strings.get(i)).append("\n");
        }
        FileUtil.rwFile(sb.toString(), filePath, fileName);
        return filePath + File.separator + fileName;
    }

    /**
     * 裁减重复的传播链路
     *
     * @param source
     * @return
     */
    private static List<String> subTxt(String source) {
        FileReader fr = null;
        BufferedReader br = null;
        List<String> results = new ArrayList<>();
        try {
            File f = new File(source);
            fr = new FileReader(f);
            br = new BufferedReader(fr);
            String str;
            String preLine = br.readLine();
            while ((str = br.readLine()) != null) {
                if (!str.contains(preLine)) {
                    results.add(preLine);
                }
                preLine = str;
            }
            results.add(preLine);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return results;
    }

}
