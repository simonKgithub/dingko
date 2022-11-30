package plan;

import com.example.dingko.common.utils.HashParameterMap;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.Calendar;

@Log4j2
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "file:src/main/webapp/WEB-INF/spring-config/applicationContext.xml",
        "file:src/main/webapp/WEB-INF/spring-config/security-context.xml"
        })
public class PlannerTest {

    @Test
    public void getCalendar(){
        HashParameterMap params = new HashParameterMap();
        LocalDate currentDate = LocalDate.now();
        LocalDate firstDate = LocalDate.of(currentDate.getYear(), currentDate.getMonth(), 1);

        System.out.println(currentDate.getYear());
        System.out.println(currentDate.getMonthValue());
        System.out.println(currentDate.lengthOfMonth());
        System.out.println(firstDate.getDayOfWeek().getValue());
    }
}
