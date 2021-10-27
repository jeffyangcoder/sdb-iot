package sdb.iot.dataManager.data;

import com.sdb.iot.SdbIotApplication;
import com.sdb.iot.dataManager.domain.sdbIotData;
import com.sdb.iot.dataManager.service.impl.sdbIotDataServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author yangshiwei
 * @Description
 * @date 2021/10/27-15:59
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SdbIotApplication.class)
public class dataTest {
    @Autowired
    private sdbIotDataServiceImpl sdbIotDataService;

    @Test
    public void testSelectDatafindAll(){
        List<sdbIotData> dataList = sdbIotDataService.findDataList();
        System.out.println(dataList);
    }
}
