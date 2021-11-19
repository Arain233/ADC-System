package com.edusource.bc.controller;


import com.edusource.bc.bean.LoginBean;
import com.edusource.bc.entity.User;
import com.edusource.bc.fabric.Init;
import com.edusource.bc.http.HttpResult;
import com.edusource.bc.security.JwtAuthenticatioToken;
import com.edusource.bc.service.UserService;
import com.edusource.bc.utils.PasswordEncoder;
import com.edusource.bc.utils.SecurityUtils;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

@Api(value = "/api/login",tags = "登陆操作")
@RestController
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private Init init;
    @Autowired
    private Producer producer;

    @PostMapping(value = "/api/login")
    public HttpResult login(@RequestBody LoginBean loginBean,HttpServletRequest request){
        String username = loginBean.getName();
        String password = loginBean.getPassword();
        String kap=loginBean.getKapt();
        Object kaptcha = request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if(kap == null){
            return HttpResult.error("验证码已失效");
        }
        if(!kap.equals(kaptcha)){
            return HttpResult.error("验证码不正确");
        }

        if(userService.findByName("admin")==null){
            userService.save(new User("admin","admin","adhiuqh123","abc@163.com","13130847823",1));
        }
        User user = userService.findByName(username);
        if(user == null){
            return HttpResult.error("账号不存在");
        }
        if(!Objects.equals(userService.findByName(username).getPassword(), PasswordEncoder.Encrypt(password,user.getSalt()))){
            return HttpResult.error("密码不正确");
        }
        System.out.println(username);
        HttpResult r = new HttpResult();
        JwtAuthenticatioToken token = SecurityUtils.login(request,username,password);
        //r.setMsg(JwtTokenUtils.getRoleFromToken(token.getToken()));
        r.setData(token);
        //init.init();
        return r;
    }

    @ApiOperation(value = "注册用户")
    @PostMapping("/api/save")
    public HttpResult save(@RequestBody User user){
        if (userService.save(user) == 0) {
            return HttpResult.error(400, "账号已存在");
        }
        return HttpResult.ok();
        }

    @GetMapping("/api/captcha.jpg")
    public void captcha(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        // 生成文字验证码
        String text = producer.createText();
        // 生成图片验证码
        BufferedImage image = producer.createImage(text);
        // 保存到验证码到 session
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, text);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);

        try {
            if (out != null) {
                out.close();
            }
        } catch (final IOException ioe) {
        }
    }
}
