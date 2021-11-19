package com.edusource.bc.utils;

import com.edusource.bc.dao.CertMapper;
import com.edusource.bc.entity.stu_info;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CertProducer {

    @Autowired
    public static CertMapper certMapper;

    private static final String model="123.pdf";
    public static String newPDFPath;

    public static void certProduce(stu_info stu)
    {
        try {
            PdfReader reader = new PdfReader(model);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            PdfStamper ps = new PdfStamper(reader, bos);
            PdfContentByte under = ps.getUnderContent(1);
            System.out.println("采用PDF模板:" + model);

            BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            ArrayList<BaseFont> fontList = new ArrayList<BaseFont>();

            fontList.add(bf);
            AcroFields fields = ps.getAcroFields();
            fields.setSubstitutionFonts(fontList);
            fillData(fields, data(stu));
            ps.setFormFlattening(true);
            ps.close();
            OutputStream fos = new FileOutputStream(newPDFPath);
            fos.write(bos.toByteArray());
            System.out.println("新证书已生成:" + newPDFPath);
            fos.flush();
            fos.close();
            bos.close();
        } catch (IOException e) {
            System.out.println("证书生成失败,原因:" + e.getLocalizedMessage());
        } catch (DocumentException e) {
            System.out.println("证书生成失败,原因:" + e.getLocalizedMessage());
        }
    }


    public static void fillData(AcroFields fields, Map<String, String> data) throws IOException, DocumentException
    {
        for (String key : data.keySet()) {
            String value = data.get(key);
            System.out.println(key + "字段:" + value);
            fields.setField(key, value);
        }
    }

    // 为需要填入的数据value赋值
    public static Map<String, String> data(stu_info stu_info) {

        newPDFPath="certificates/"+stu_info.getName()+stu_info.getTra_code()+".pdf";
        Map<String, String> data = new HashMap<String, String>();
        data.put("name", stu_info.getName());
        data.put("sex", stu_info.getSex());
        data.put("birth", stu_info.getBirth());
        data.put("start_date", stu_info.getStart_date());
        data.put("end_date", stu_info.getEnd_date());
        data.put("university", stu_info.getUniversity());
        data.put("profession", stu_info.getProfession());
        data.put("type", stu_info.getType());
        data.put("school_system", stu_info.getSchool_system());
        data.put("form", stu_info.getForm());
        data.put("level", stu_info.getLevel());
        data.put("certificate_no", stu_info.getCertificate_no());
        data.put("graduation_attributes", stu_info.getGraduation_attributes());
        data.put("principaln_name",stu_info.getPrincipaln_name());
        data.put("digest",DigestUtils.DigestProducer(stu_info));
        return data;
    }
}
