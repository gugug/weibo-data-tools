package gdufs.iiip.weibo.data.tools.chinamap;

import java.util.Map;

import gdufs.iiip.weibo.data.config.DaoConfig;
import gdufs.iiip.weibo.data.dao.CRUDManager;
import gdufs.iiip.weibo.data.utils.FileUtil;
import gdufs.iiip.weibo.data.dao.MongoHelper;

public class ChinaMapUtil {

    /**
     * 插入mongodb中的area表
     *
     * @param daoConfig 数据库的配置
     * @param areapath  map.txt的路径
     * @param eid       对应的事件id
     */
    public static void insertArea(DaoConfig daoConfig, String areapath, String eid) {
        MongoHelper mongoHelper = new MongoHelper(daoConfig);
        CRUDManager crudManager = new CRUDManager(mongoHelper, "area");
        Map<String, Object> areaMap = FileUtil.readAreaTxt(areapath);
        areaMap.put("_id", Integer.parseInt(eid));
        crudManager.insert(areaMap);
        crudManager.closeMongoClient();
    }

    public static void main(String[] args) {
        DaoConfig daoConfig = new DaoConfig("aa", "sss", 33);
        String areapath = "/media/iiip/Elements/WeiboData/WeiboNew/教师殴打环卫/area.txt";
        String eid = "594";
        insertArea(daoConfig, areapath, eid);
    }

}
