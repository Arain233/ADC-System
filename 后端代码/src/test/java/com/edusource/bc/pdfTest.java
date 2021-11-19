package com.edusource.bc;

import com.edusource.bc.dao.CertMapper;
import com.edusource.bc.entity.Certificate;
import com.edusource.bc.entity.stu_info;
import com.edusource.bc.service.Stu_infoService;
import com.edusource.bc.utils.CertProducer;
import com.edusource.bc.utils.PdfUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class pdfTest {

    @Autowired
    Stu_infoService stu_infoService;

    @Autowired
    CertMapper certMapper;
    @Test
    public void certProduceTest()
    {
        stu_info stu_info=stu_infoService.findById(1);
        System.out.println(stu_info);
        CertProducer.certProduce(stu_info);
    }

    @Test
    public void convertTest()
    {
        System.out.println(PdfUtils.getPDFBinary("孟浩20191620310001.pdf"));
        PdfUtils.base64StringToPDF(PdfUtils.getPDFBinary("孟浩20191620310001.pdf"),"1.pdf");
    }

    @Test
    public void certTest()
    {
        Certificate certificate=new Certificate(PdfUtils.getPDFBinary("张沈晖20191620310046.pdf"),"20231620310046");
        certMapper.save(certificate);
    }
}
