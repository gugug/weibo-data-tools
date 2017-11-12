package gdufs.iiip.weibo.data.tools.chinamap;

import gdufs.iiip.weibo.data.config.DaoConfig;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by gu on 2017/11/12.
 */
public class ChinaMapUtilTest {
    @Test
    public void insertArea() throws Exception {
        DaoConfig daoConfig = new DaoConfig("aa", "sss", 33);
        String areapath = "/media/iiip/Elements/WeiboData/WeiboNew/教师殴打环卫/area.txt";
        String eid = "594";
        ChinaMapUtil.insertArea(daoConfig, areapath, eid);
    }

}