package top.autuan.domainchange.timer;

import ch.qos.logback.core.net.server.Client;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import com.aliyun.credentials.models.Config;
import com.aliyun.tea.TeaModel;
import com.google.gson.Gson;
import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.autuan.domainchange.domain.check.CheckItem;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 主机检查
 */
@Component
public class DomainTimer {
    @Autowired
    private AliyunDomainUtil aliyunDomainUtil;

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
        Map<String, String> accessMap = new HashMap();
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
//        managerCheck();
//        apiCheck();
    }


    public void apiCheck() {
        // api.keqidao.com 无法访问 且 主要机器无法响应
        // 处理策略：域名 api.keqidao.com 重新解析到 uat服务器
        try {

            String post = HttpUtil.post("https://api.keqidao.com/config/canal/verificationVersion", "{\n" +
                    "    \"canalName\":\"android\"\n" +
                    "}", 5);

            // todo 如果正常检查一下现在相关的解析是不是在主服务器上，如果不是，改到主服务器
            aliyunDomainUtil.modifyProd();
//            AliyunDomainUtil.modifyProd();

            System.out.println(post);

        } catch (IORuntimeException e) {
            if ("SocketTimeoutException: Read timed out".equals(e.getMessage())) {
                // todo 立即重新加载 ； 3次后 切换域名解析到腾讯云环境
                // todo 发送钉钉
                // todo 修改域名
                aliyunDomainUtil.modifyBackup();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void managerCheck() {
        List<CheckItem> list = new ArrayList<>(2);
        list.add(CheckItem.builder()
                .domain("https://umanager.keqidao.com/")
                .jobName("uat-qidao-manager")
                .branch("master")
                .build());
        list.add(CheckItem.builder()
                .domain("https://manager.keqidao.com/")
                .jobName("prod-qidao-manager")
                .branch("1.7.3")
                .build());

        for (CheckItem item : list) {
            try {
                Connection.Response response = Jsoup.connect(item.getDomain()).execute();
                int code = response.statusCode();
                String msg = response.statusMessage();
                System.out.println(code);
                System.out.println(msg);
            } catch (HttpStatusException e) {
                int statusCode = e.getStatusCode();
                // 500 或 502 重启
                if (statusCode == 500 || statusCode == 502) {
                    String template = "message ：服务器检查异常 \\n  "
                            + "检查时间 : {}  \\n"
                            + "访问域名 : {}  \\n\\n"
                            + "执行异常处理策略 ： 重启服务"
                            + "Jenkins 任务名称 : {}"
                            + "服务分支 : {}";

                    String sendMsg = StrUtil.format(template, LocalDateTime.now(), item.getDomain(), item.getJobName(), item.getBranch());
                    // 钉钉推送 todo
                    DingTalkUtil.send(sendMsg);

                    // jenkins 重启
                    JenkinsCliUtil.build(item.getJobName(), item.getBranch());
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
                DingTalkUtil.send(e.getMessage());
            }
        }
    }

    @Scheduled(cron = "0/5 * * * * ? ")
    public void test(){
        System.out.println("run test 0/5 * * * * ? ");
        aliyunDomainUtil.tempPrintf();
    }
}
