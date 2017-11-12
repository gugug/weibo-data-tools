package gdufs.iiip.weibo.data.tools.transformtree;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by gu on 2017/11/12.
 */
public class TransformTreeUtilTest {
    @Test
    public void genTransformJson() throws Exception {
        String transformtxtpath = "C:\\Users\\gu\\Desktop\\incint_path2.txt";
        String eventName = "杭州保姆纵火";
        String save = "C:\\Users\\gu\\Desktop";
        String transformJson = TransformTreeUtil.genTransformJson(eventName, transformtxtpath, save, "transform1.json");
        System.out.println(transformJson);
    }

}