package com.edusource.bc;

import com.edusource.bc.dao.UserMapper;
import com.edusource.bc.fabric.Init;
import com.edusource.bc.fabric.Query;
import com.edusource.bc.service.RoleService;
import com.edusource.bc.service.Stu_infoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BcApplicationTests {
    @Autowired
    UserMapper userMapper;
    @Autowired
    RoleService roleService;
    @Autowired
    Stu_infoService stu_infoService;

    @Autowired
    Init init;
    @Autowired
    Query query;

    private String id;

    @Test
    public void testM() throws IOException {
        //System.out.println(DigestUtils.DigestProducer(stu_infoService.findById(10)));
        System.out.println("http://192.168.47.129:8080/query/" + stu_infoService.findById(10).getId().toString());
        // System.out.println(query.query(stu_infoService.findById(10)));

    }

    @Test
    public void tet()
    {
        init.init();
    }



}
