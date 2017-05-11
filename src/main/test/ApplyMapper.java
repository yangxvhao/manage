import com.credit.service.IApplyService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @date：2017/5/9
 * @author:yangxvhao
 */

public class ApplyMapper {
@Autowired
    private IApplyService applyMapper;
    @Test
    public void test1(){
        List<Object> list= this.applyMapper.selectByApplyMember("小红");

        System.out.println(list.toString());
    }

}
