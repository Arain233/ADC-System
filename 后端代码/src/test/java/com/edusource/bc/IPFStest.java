package com.edusource.bc;


import com.edusource.bc.IPFS.IPFSUtils;
import com.edusource.bc.dao.CertMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class IPFStest {

    @Autowired
    CertMapper certMapper;

    @Test
    public void uploadTest() throws IOException
    {
        File file=new File("certificates/孟浩20191620310001.pdf");
        System.out.println(IPFSUtils.upload(file));
    }

    @Test
    public void verifyTest() throws IOException
    {
        if(IPFSUtils.verify(certMapper.findCerByNo("string")))
        {
            System.out.println("success");
        }
        else{
            System.out.println("fail");
        }

    }
}
