package com.edusource.bc.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.edusource.bc.IPFS.IPFSUtils;
import com.edusource.bc.dao.CertMapper;
import com.edusource.bc.entity.Certificate;
import com.edusource.bc.entity.stu_info;
import com.edusource.bc.fabric.Invoke;
import com.edusource.bc.fabric.Query;
import com.edusource.bc.http.HttpResult;
import com.edusource.bc.page.PageRequest;
import com.edusource.bc.service.Stu_infoService;
import com.edusource.bc.service.impl.Stu_infoServiceImpl;
import com.edusource.bc.utils.CertProducer;
import com.edusource.bc.utils.DigestUtils;
import com.edusource.bc.utils.JwtTokenUtils;
import com.edusource.bc.utils.PdfUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Api(value = "/stu", tags = "学生信息操作")
@RestController
@RequestMapping("/stu")
public class Stu_infoController {


    @Autowired
    private Stu_infoService stu_infoService;
    @Autowired
    private Query query;
    @Autowired
    private Invoke invoke;
    @Autowired
    private CertMapper certMapper;

    public static Stu_infoController controller;

    @PostConstruct
    public void init() {
        controller = this;
        controller.stu_infoService = this.stu_infoService;
        controller.query = this.query;
        controller.invoke = this.invoke;
    }

    @ApiOperation(value = "查找所有学生信息")
    @GetMapping("/findAll")
    public HttpResult findAll() {
        return HttpResult.ok(stu_infoService.findAll());
    }

    @ApiOperation(value = "根据ID查找学生")
    @GetMapping("/findById/{id}")
    public HttpResult findById(@PathVariable("id") Integer id) {
        return HttpResult.ok(stu_infoService.findById(id));
    }

    @ApiOperation(value = "根据防伪码查找学生")
    @GetMapping("/findByTra_code/{tra_code}")
    public HttpResult findByCode(@PathVariable("tra_code") long tra_code)    {
        stu_info stu_info = stu_infoService.findByTra_code(tra_code);
        if (Objects.equals(query.query(stu_info), DigestUtils.DigestProducer(stu_info))) {
            return HttpResult.ok(stu_info);
        }

        return HttpResult.error("数据库信息与链上信息不匹配，数据被篡改");
    }

    @ApiOperation(value = "根据证书信息查找学生证书")
    @GetMapping("/findByCert_no/{no}")
    public HttpResult findByCert_no(@PathVariable("no") String no, HttpServletRequest request) throws IOException{
        Certificate certificate=certMapper.findCerByNo(no);
        stu_info stu=stu_infoService.findByCert_no(no);
        if(IPFSUtils.verify(certificate))
        {
            return HttpResult.ok(stu);
        }
        return HttpResult.error("数据与链上不符，核验失败！");
    }

    @ApiOperation(
            value = "学生信息入库",
            notes = "传入学生的所有信息，并且和登录信息相对比，如果匹配成功则进行入库上链操作"
    )
    @PostMapping(path = "/in", consumes = "application/json")
    public HttpResult In_modify(@RequestBody stu_info stu_info) {
        List<stu_info> list = stu_infoService.findByName(stu_info.getName());
        for (stu_info stu_info1 : list) {

            if (Objects.equals(stu_info1.getCertificate_no(), stu_info.getCertificate_no())) {
                HttpResult.error().setCode(403);
                return HttpResult.error("您的证书已存在库中！");
            }
        }
        stu_infoService.InUpdate(stu_info);
        stu_info = stu_infoService.findByTra_code(Stu_infoServiceImpl.code);
        return HttpResult.ok(invoke.invoke(stu_info));
    }


    @ApiOperation(
            value = "学生信息修改",
            notes = "传入需要修改的学生ID以及需要修改的数据，不修改的数据置空值"
    )
    @PutMapping("/update")
    public HttpResult update(@RequestBody stu_info stu_info) {
        return HttpResult.ok(stu_infoService.Update(stu_info));
    }

    @ApiOperation(value = "根据ID删除数据")
    @DeleteMapping("/deleteById/{id}")
    public HttpResult deleteById(@PathVariable("id") Integer id) {
        return HttpResult.ok(stu_infoService.deleteById(id));
    }

    /**
     * 分页查找
     *
     * @param pageRequest
     * @return
     */
    @ApiOperation(
            value = "分页查找",
            notes = "传入页码与每页数量"
    )
    @PostMapping(value = "/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        return HttpResult.ok(stu_infoService.findPage(pageRequest));
    }

    @ApiOperation(
            value = "从学信网拿数据"
    )
    @GetMapping("/findcert/{code}")
    public HttpResult infoImport(@PathVariable("code")String code) throws URISyntaxException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        URIBuilder uriBuilder = new URIBuilder("https://api.jshdz.cn/xxwyz");
        uriBuilder.setParameter("code",code);
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        List<NameValuePair> pairs = new ArrayList<>();
        pairs.add(new BasicNameValuePair("code",code));
        UrlEncodedFormEntity formEntity = null;
        try {
            formEntity = new UrlEncodedFormEntity(pairs,"utf-8");
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        CloseableHttpResponse response = null;
        try{
            response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200){
                String content = EntityUtils.toString(response.getEntity(),"utf-8");
                JSONObject jsonObject = JSON.parseObject(content);
                String data = jsonObject.getString("data");
                JSONObject data_json = JSONObject.parseObject(new String(data));

                String name = data_json.getString("name");
                System.out.println(name);
                String sex = data_json.getString("sex");
                System.out.println(sex);
                String birth = data_json.getString("birth");
                System.out.println(birth);
                String start_date = data_json.getString("start_date");
                System.out.println(start_date);
                String end_date = data_json.getString("end_date");
                System.out.println(end_date);
                String university = data_json.getString("university");
                System.out.println(university);
                String profession = data_json.getString("profession");
                System.out.println(profession);
                String type = data_json.getString("type");
                System.out.println(type);
                String school_system = data_json.getString("school_system");
                System.out.println(school_system);
                String form = data_json.getString("form");
                System.out.println(form);
                String level = data_json.getString("level");
                System.out.println(level);
                String certificate_no = data_json.getString("certificate_no");
                System.out.println(certificate_no);
                String gradu_attributes = data_json.getString("graduation_attributes");
                System.out.println(gradu_attributes);
                String principaln_name = data_json.getString("principaln_name");
                System.out.println(principaln_name);

                stu_info stu = new stu_info();
                stu.setName(name);
                stu.setSex(sex);
                stu.setBirth(birth);
                stu.setStart_date(start_date);
                stu.setEnd_date(end_date);
                stu.setUniversity(university);
                stu.setProfession(profession);
                stu.setType(type);
                stu.setSchool_system(school_system);
                stu.setForm(form);
                stu.setLevel(level);
                stu.setCertificate_no(certificate_no);
                stu.setGraduation_attributes(gradu_attributes);
                stu.setPrincipaln_name(principaln_name);
                stu_infoService.InUpdate(stu);
                CertProducer.certProduce(stu);
                Certificate certificate=new Certificate(PdfUtils.getPDFBinary(CertProducer.newPDFPath),stu.getCertificate_no());
                Certificate cer=certMapper.findCerByNo(certificate.getCertificate_no());
                if(!Objects.equals(cer,null))
                {
                    return HttpResult.error("证书已存在");
                }
                File file=new File(CertProducer.newPDFPath);
                String hash=IPFSUtils.upload(file);
                certificate.setHash(hash);
                certMapper.save(certificate);
                return HttpResult.ok(content);


            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                response.close();
                httpClient.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        return HttpResult.error("笨B，没拿到");

    }

    @ApiOperation(
            value = "证书生成"
    )
    @PostMapping("/cert")
    public HttpResult certProduce(@RequestBody stu_info stu_info) throws IOException
    {
        CertProducer.certProduce(stu_info);
        Certificate certificate=new Certificate(PdfUtils.getPDFBinary(CertProducer.newPDFPath),stu_info.getCertificate_no());
        Certificate cer=certMapper.findCerByNo(certificate.getCertificate_no());
        if(!Objects.equals(cer,null))
        {
            return HttpResult.error("证书已存在");
        }
        File file=new File(CertProducer.newPDFPath);
        String hash=IPFSUtils.upload(file);
        certificate.setHash(hash);
        stu_infoService.InUpdate(stu_info);
        certMapper.save(certificate);
        return HttpResult.ok();
    }

    @ApiOperation(
            value = "证书获取"
    )
    @GetMapping("/cert/{no}")
    public HttpResult certReturn(@PathVariable("no") String no,HttpServletRequest request)
    {
        if(!Objects.equals(JwtTokenUtils.getUsernameFromToken(request.getHeader("token")),stu_infoService.findByCert_no(no)))
        {
            return HttpResult.error(400,"偷看尼玛呢");
        }
        String bin=certMapper.findByNo(no);
        if(Objects.equals(bin,null))
        {
            return HttpResult.error("证书不存在");
        }
        return HttpResult.ok(bin);
    }

}
