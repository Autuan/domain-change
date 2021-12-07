package top.autuan.domainchange.timer;

import ch.qos.logback.core.net.server.Client;
import com.aliyun.credentials.models.Config;
import com.aliyun.tea.TeaModel;
import com.google.gson.Gson;
import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *  主机检查
 */
public class DomainTimer {
    public void check() throws IOException {
        String url = "app.keqidao.com";
        // 检查域名可访问性
        Connection.Response response = Jsoup.connect(url).execute();
        int code = response.statusCode();
        // 不可访问时，发送钉钉预警
        String body = "";
        String dingTalkUrl = "";
        Jsoup.connect(dingTalkUrl).execute();

        // 更改域名解析
        // 阿里云api:  https://next.api.aliyun.com/api-tools/sdk/Alidns?spm=a2c4g.11186623.0.0.30bc12d4kjja9M&version=2015-01-09&language=java-tea
    }

    private void updateDomain() throws Exception {
        Map<String,String> accessMap = new HashMap();
        accessMap.put("accessKeyId", "accessKeyId");
        accessMap.put("accessKeySecret", "accessKeySecret");

        Config config = Config

                .build(accessMap);
//        TeaModel.


//        Config config = new Config()
//                // 您的AccessKey ID
//                .setAccessKeyId(accessKeyId)
//                // 您的AccessKey Secret
//                .setAccessKeySecret(accessKeySecret);

// 访问的域名
//        config.endpoint = "alidns.cn-hangzhou.aliyuncs.com";
//
//
//        Client client = new Client(config);
//
//        DescribeCustomLineRequest request = new DescribeCustomLineRequest();
//
//        request.LineId = "1"; // 该参数值为假设值，请您根据实际情况进行填写
//
//        request.Lang = "test"; // 该参数值为假设值，请您根据实际情况进行填写
//
//        DescribeCustomLineResponse response = client.describeCustomLine(request);
//        System.out.println(new Gson().toJson(response.Body));
//
//        String requestId = response.Body.requestId;

    }

    public static void main(String[] args) throws IOException {
        String url = "https://umanager.keqidao.com/";
//        Connection.Response response = Jsoup.connect(url).execute();
        try {

        Connection.Response response = Jsoup.connect(url).execute();
        }
        catch (HttpStatusException e) {
            String reqUrl = e.getUrl();
            int statusCode = e.getStatusCode();
            String message = e.getMessage();

            System.out.println(reqUrl);
            System.out.println(statusCode);
            System.out.println(message);

            // 钉钉推送 todo
            DingTalkUtil.send("502ERROR");

            // jenkins 重启
        }
        catch (Exception e) {
            System.out.println(e.getMessage());

        }
//        String s = response.statusMessage();
//        System.out.println(s);
//        int code = response.statusCode();
//        System.out.println(code);
    }


    public void managerCheck(){
        String url = "https://umanager.keqidao.com/";
//        Connection.Response response = Jsoup.connect(url).execute();
        try {

            Connection.Response response = Jsoup.connect(url).execute();
        }
        catch (HttpStatusException e) {
            String reqUrl = e.getUrl();
            int statusCode = e.getStatusCode();
            String message = e.getMessage();

            System.out.println(reqUrl);
            System.out.println(statusCode);
            System.out.println(message);
            String sendMsg = "";
            // 钉钉推送 todo
            DingTalkUtil.send("502ERROR");

            // jenkins 重启
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            DingTalkUtil.send(e.getMessage());
        }
    }
}
