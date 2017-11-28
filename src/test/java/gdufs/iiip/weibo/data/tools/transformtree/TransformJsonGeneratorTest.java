package gdufs.iiip.weibo.data.tools.transformtree;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by gu on 2017/11/28.
 */
public class TransformJsonGeneratorTest {
    @Test
    public void transformJsonGenerator() throws Exception {
        String transformTxt = "C:\\Users\\gu\\Desktop\\incident_path.txt";
        String eventName = "虐童";
        String saveDir = "C:\\Users\\gu\\Desktop";
        String saveFileName = "test.json";
        TransformJsonGenerator.transformJsonGenerator(transformTxt, eventName, saveDir, saveFileName);
    }

}