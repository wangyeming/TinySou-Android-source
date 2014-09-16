package Help;

import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HTTP;
import org.json.JSONStringer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TinySouClient {
	//权限验证
    protected String auth_token = null;
    //HTTP 请求方法 get 或 post
    protected String method = null;
    //HTTP 微搜索url
    protected String url = null;
    //参数
    protected List params = new ArrayList();
    //参数q 待搜索的内容，必需
    protected String params_q = null;

    public TinySouClient(String auth_token, String method, String url, String params_q){
        this.auth_token = auth_token;
        this.method = method;
        this.url = url;
        this.params_q = params_q;
    }

    public void check_method(){
        if(this.method != "get" && this.method == "post") {
            System.exit(0);
        }
    }

    public String buildUrl(){
        if(this.method == "get"){
            return this.url + "?q=" + this.params_q;
        }else{
            return this.url;
        }
    }

    public HttpHelp buildRequest(){
        final String AuthToken = this.auth_token;
        final String SearchContent = this.params_q;
        if(this.method == "get"){
            HttpHelp get_request = new HttpHelp();
            get_request
                    .setCharset(HTTP.UTF_8)
                    .setConnectedTimeout(5000)
                    .setSoTimeout(5000);
            get_request.setOnHttpRequestListener(new HttpHelp.OnHttpRequestListener() {
                @Override
                public void onRequest(HttpHelp request) throws Exception{
                    request.addHeader("Content-Type", "application/json");
                    request.addHeader("Authorization", AuthToken);
                }
                public String onSucceed(int statusCode, HttpHelp request) throws Exception{
                    return request.getInputStreamJson();
                }
                public String onFailed(int statusCode, HttpHelp request) throws Exception{
                    return "GET 请求失败：statusCode "+ statusCode;
                }
            });
            return get_request;
        }
        else {
            HttpHelp post_request = new HttpHelp();
            post_request
                    .setCharset(HTTP.UTF_8)
                    .setConnectedTimeout(5000)
                    .setSoTimeout(10000);
            post_request.setOnHttpRequestListener(new HttpHelp.OnHttpRequestListener() {
                private String CHARSET = HTTP.UTF_8;
                @Override
                public void onRequest(HttpHelp request) throws Exception {
                    // 设置发送请求的 header 信息
                    request.addHeader("Content-Type", "application/json");
                    request.addHeader("Authorization", AuthToken);

                    // 配置要 POST 的数据
                    JSONStringer search_content = new JSONStringer().object()
                            .key("q").value(SearchContent);
                    search_content.endObject();
                    String body = search_content.toString();
                    StringEntity entity = new StringEntity(body);
                    request.buildPostEntity(entity);
                }

                @Override
                public String onSucceed(int statusCode, HttpHelp request) throws Exception {
                    return request.getInputStreamJson();
                }

                @Override
                public String onFailed(int statusCode, HttpHelp request) throws Exception {
                    return "GET 请求失败：statusCode "+ statusCode;
                }
            });
            return post_request;
        }
    }

    public String Search(){
        //this.check_method();
        String SearchResult = this.buildUrl();
        HttpHelp request = this.buildRequest();
        String content = null;
        try {
            if(this.method == "get"){
                content = request.get(SearchResult);
            }
            else{
                content = request.post(SearchResult);
            }
        } catch (IOException e) {
            content = "IO异常：" + e.getMessage();
        } catch (Exception e) {
            content = "异常：" + e.getMessage();
        }
        return content;
    }
}
