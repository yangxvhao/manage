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
       int count=0;
        int sum1=4;
        double sum=0.0;

        sum=(count*1.0)/(sum1*1.0);
        System.out.println(String.valueOf(sum));
    }

}
