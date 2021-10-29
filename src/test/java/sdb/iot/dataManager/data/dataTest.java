package sdb.iot.dataManager.data;

import com.sdb.iot.SdbIotApplication;
import com.sdb.iot.dataManager.domain.SdbIotData;
import com.sdb.iot.dataManager.service.impl.SdbIotDataServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yangshiwei
 * @Description
 * @date 2021/10/27-15:59
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SdbIotApplication.class)
public class dataTest {
    @Resource
    private SdbIotDataServiceImpl sdbIotDataService;

    @Test
    public void testFindSdbIotDataById(){
        SdbIotData sdbIotData = sdbIotDataService.findSdbIotDataById(1L);
        System.out.println(sdbIotData);
    }

    @Test
    public void testFindSdbIotDataList(){
        List<SdbIotData> sdbIotDataList = sdbIotDataService.findSdbIotDataList();
        System.out.println(sdbIotDataList);
    }
}
