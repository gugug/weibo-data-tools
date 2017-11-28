package gdufs.iiip.weibo.data.tools.transformtree;

import com.alibaba.fastjson.JSON;
import gdufs.iiip.weibo.data.utils.FileUtil;
import gdufs.iiip.weibo.data.entity.TreeEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;

import static com.google.common.base.Preconditions.checkNotNull;

@Deprecated
public class TransformTreeUtil {

    private static final Logger LOG = LoggerFactory.getLogger(TransformTreeUtil.class);

//    public static void main(String[] args) {
//        String transformtxtpath = "C:\\Users\\gu\\Desktop\\incint_path2.txt";
//        String eventName = "杭州保姆纵火";
//        String save = "C:\\Users\\gu\\Desktop";
//        String transformJson = genTransformJson(eventName, transformtxtpath, save, "transform1.json");
//        System.out.println(transformJson);
//    }

    /**
     * 生成转发路径json数据
     *
     * @param eventName        事件名字
     * @param transformtxtpath 转发关键文件的路径
     * @param saveDir          保存结果的文件夹
     * @param filename         文件的命名
     */
    public static String genTransformJson(String eventName, String transformtxtpath, String saveDir, String filename) {
        checkNotNull(transformtxtpath, "Provided file name for writing must NOT be null.");
        checkNotNull(saveDir, "Provided dir name for writing must NOT be null.");
        try {
            FileUtil.judgeFileExist(transformtxtpath);
            FileUtil.judgeDirExist(saveDir);
            Object transformJson = getTransformJson(transformtxtpath, eventName);
            FileUtil.rwFile(transformJson.toString(), saveDir, filename);
            return transformJson.toString();
        } catch (Exception ex) {
            LOG.error("ERROR trying to genTransformJson");
            LOG.error(ex.getMessage(), ex);
        }
        return null;
    }

    /**
     * 生成传播树状图的json数据格式
     *
     * @param transformtxtpath 传播路径txt的文件所在地
     * @param eventName        事件名字
     * @return
     */
    private static Object getTransformJson(String transformtxtpath, String eventName) {
        String text = readTftxt(transformtxtpath);
        // 赋值给每一个节点一个id标识
        Map<String, Integer> nodeMap = new HashMap<String, Integer>();
        int headNum = 1;
        String[] line = text.split("\n");
        for (int i = 0; i < line.length; i++) {
            String[] Name = line[i].split("~");
            for (int j = 0; j < Name.length; j++) {
                if (!nodeMap.containsKey(Name[j])) {
                    nodeMap.put(Name[j], headNum);
                    headNum++;
                }
            }
        }
        // 子节点对应的多个父节点id列表
        Map<String, Set<Integer>> nameIdMap = new HashMap<String, Set<Integer>>();
        for (int i = 0; i < line.length; i++) {
            String[] nameList = line[i].split("~");
            Set<Integer> headList = new HashSet<Integer>();
            headList.add(0);
            nameIdMap.put(nameList[0], headList);
            for (int j = 1; j < nameList.length; j++) {
                if (nameIdMap.containsKey(nameList[j])) {
                    nameIdMap.get(nameList[j]).add(nodeMap.get(nameList[j - 1]));
                } else {
                    Set<Integer> idList = new HashSet<Integer>();
                    idList.add(nodeMap.get(nameList[j - 1]));
                    nameIdMap.put(nameList[j], idList);
                }
            }
        }
        // 生成树状图
        Set<String> nameIdMapKeySet = nameIdMap.keySet();
        Map<Integer, TreeEntity> map = new HashMap<Integer, TreeEntity>();
        for (String name : nameIdMapKeySet) {
            Set<Integer> pIdList = nameIdMap.get(name);
            // System.out.println("name+pidList"+name +" "+pIdList);
            for (Integer id : pIdList) {
                TreeEntity terr = new TreeEntity(nodeMap.get(name), id, name);
                map.put(terr.getId(), terr);
            }
        }
        List<TreeEntity> li = TreeEntity.getChildren(map, 0, 1);
        // 包装根节点
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("name", eventName);
        resultMap.put("children", li);
        resultMap.put("id", -1);
        resultMap.put("pId", 0);
        return JSON.toJSON(resultMap);
    }

    /**
     * 读取传播路径的txt
     *
     * @param transformtxtpath 传播路径的文件所在地
     * @return 文本内容
     */
    private static String readTftxt(String transformtxtpath) {
        return readTftxt(transformtxtpath, 1);
    }

    /**
     * 隔N行读取文件
     *
     * @param transformtxtpath 传播路径的文件所在地
     * @param skipLineNum      每隔skipLineNum行读
     * @return 读取的内容
     */
    private static String readTftxt(String transformtxtpath, int skipLineNum) {
        FileReader fr = null;
        BufferedReader br = null;
        StringBuffer sb = new StringBuffer();
        int i = 0;
        try {
            File f = new File(transformtxtpath);
            fr = new FileReader(f);
            br = new BufferedReader(fr);
            String str;
            while ((str = br.readLine()) != null) {
                if (i % skipLineNum == 0) {
                    sb.append(str);
                    sb.append("\n");
                }
                i++;
            }
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
        return sb.toString();
    }

    public static void main(String[] args) {
        String transformtxtpath = "F:\\WeiboData\\WeiboNew\\豫章书院\\incident_path.txt";   //修改
        String tftxt = readTftxt(transformtxtpath, 5);
        FileUtil.rwFile(tftxt, "F:\\WeiboData\\WeiboNew\\豫章书院", "incident_path1.txt");
    }

}
