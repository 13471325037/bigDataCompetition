package faultstatistic.test;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sgmw.bigDataCompetition.SpringApplication;
import com.sgmw.bigDataCompetition.dao.entity.SignUpTable;
import com.sgmw.bigDataCompetition.dao.mapper.SignUpTableMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= SpringApplication.class)
public class AppTest {
    @Autowired
    private SignUpTableMapper mapper;

    @Test
    public void update(){

        SignUpTable signUpTable = mapper.selectById(1);
        signUpTable.setLeaderName("向龙祥");
        signUpTable.setLeaderDepartment("大数据应用部");
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id",1);
        mapper.update(signUpTable,wrapper);

    }

    @Test
    public void delete(){
        mapper.deleteById(61);
    }

    @Test
    public void save(){

    }

}
