package cn.wannengde.community.provider;

import cn.wannengde.community.dto.AccessTokenDTO;
import cn.wannengde.community.dto.GitHubUser;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/*
@author kbq
@date  2019/6/29 - 18:08
*/
@Component
public class GitHubProvider {

    public String getAccessToken(AccessTokenDTO accessTokenDTO){
       MediaType mediaType  = MediaType.get("application/json; charset=utf-8");

       OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            String tokenstr = string.split("&")[0];
            String token = tokenstr.split("=")[1];
            return token;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public GitHubUser getUser(String accessToken){
        if(accessToken == null) return null;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();
        String documentation_url = null;
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            JSONObject stringJson = JSONObject.parseObject(string);
            GitHubUser gitHubUser = JSON.parseObject(string,GitHubUser.class);
            return gitHubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
