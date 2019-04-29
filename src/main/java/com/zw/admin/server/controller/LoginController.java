package com.zw.admin.server.controller;

import net.minidev.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.web.bind.annotation.*;

import com.zw.admin.server.annotation.LogAnnotation;
import com.zw.admin.server.dto.Token;
import com.zw.admin.server.model.User;
import com.zw.admin.server.service.TokenManager;
import com.zw.admin.server.utils.UserUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * 登陆相关接口
 * 
 * @author 小威老师 xiaoweijiagou@163.com
 *
 */
@CrossOrigin
@Api(tags = "登陆")
@RestController
@RequestMapping

public class LoginController {

	@Autowired
	private TokenManager tokenManager;
	@Autowired
	private ServerProperties serverProperties;

	@LogAnnotation
	@ApiOperation(value = "web端登陆")
	@PostMapping("/sys/login")
	public void login(String username, String password) {
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
		SecurityUtils.getSubject().login(usernamePasswordToken);
		// 设置shiro的session过期时间
		SecurityUtils.getSubject().getSession().setTimeout(serverProperties.getServlet().getSession().getTimeout().toMillis());
	}

	@LogAnnotation
	@ApiOperation(value = "Restful方式登陆,前后端分离时登录接口")
	@PostMapping("/sys/login/restful")
	public Token restfulLogin(@RequestBody User user) {
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUsername(), user.getPassword());
		SecurityUtils.getSubject().login(usernamePasswordToken);

		return tokenManager.saveToken(usernamePasswordToken);
	}

	@ApiOperation(value = "当前登录用户")
	@GetMapping("/sys/login")
	public User getLoginInfo() {
		return UserUtil.getCurrentUser();
	}

	@ApiOperation(value = "短信发送验证码")
	@PostMapping("/sys/login/getCode")
	public int getCode(@RequestBody JSONObject jsonObj) {
		HttpPost post = new HttpPost("https://open.ucpaas.com/ol/sms/sendsms");
		HttpClient httpClient  = HttpClientBuilder.create().build();
		HttpResponse response = null;
		System.out.println(jsonObj);
		// 构建消息实体
		StringEntity entity = new StringEntity(jsonObj.toString(), Charset.forName("UTF-8"));
		entity.setContentEncoding("UTF-8");
		// 发送Json格式的数据请求
		entity.setContentType("application/json");
		post.setEntity(entity);
		try {
			response = httpClient.execute(post);
		} catch (IOException e) {
			System.out.println(response);
			return 0;
		}
		System.out.println(response);
		return 1;
	}

}
