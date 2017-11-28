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
public class CleanDataGenerator {
    private static final String SPILT_SYMBOL = "~";

    public static void main(String[] args) {
        //List<String> strings = subTxt("C:\\Users\\gu\\Desktop\\incident_path.txt");
        // System.out.println(strings);
        //reWriteTxt("虐童", strings, "C:\\Users\\gu\\Desktop", "incident_path_temp.txt");
        Map<String, Integer> allNode = getAllNode("C:\\Users\\gu\\Desktop\\incident_path1.txt");
//        System.out.println(allNode);
        List<List<Node>> tupleList = getRelation("C:\\Users\\gu\\Desktop\\incident_path1.txt", allNode);
//        System.out.println(tupleList);
        genTreeResult(tupleList);
    }

    public static void genTreeResult(List<List<Node>> tupleList) {

        List<HashMap<String, String>> dataList = getVirtualResult(tupleList);

        // 节点列表（散列表，用于临时存储节点对象）
        HashMap<Integer,Node> nodeList = new HashMap();
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
        System.out.println(nodeList);
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
        System.out.println(root.toString());
    }

    public static List<HashMap<String, String>> getVirtualResult(List<List<Node>> tupleList) {

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

    public static List<List<Node>> getRelation(String source, Map<String, Integer> nodeMap) {
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

    public static Map<String, Integer> getAllNode(String source) {
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

    public static void reWriteTxt(String firstNodeName, List<String> strings, String filePath, String fileName) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strings.size(); i++) {
            sb.append(firstNodeName).append(SPILT_SYMBOL).append(strings.get(i)).append("\n");
        }
        FileUtil.rwFile(sb.toString(), filePath, fileName);

    }

    public static List<String> subTxt(String source) {
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
