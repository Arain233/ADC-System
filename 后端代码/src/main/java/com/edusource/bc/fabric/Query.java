package com.edusource.bc.fabric;
import com.edusource.bc.entity.stu_info;
import com.edusource.bc.http.HttpResult;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;


@Service
public class Query {

    @Autowired
    RestTemplate restTemplate;
    private String id;
    public String query(stu_info stu_info){
        id = stu_info.getId().toString();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(URI.address+"query/"+id);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            if(response.getStatusLine().getStatusCode() == 200){
                String content = EntityUtils.toString(response.getEntity(),"utf-8");
                return HttpResult.ok(content).getMsg();
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
        return HttpResult.error().getMsg();
    }
}
