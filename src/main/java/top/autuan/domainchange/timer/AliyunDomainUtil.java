package top.autuan.domainchange.timer;

import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONUtil;
import com.aliyun.alidns20150109.Client;
import com.aliyun.alidns20150109.models.DescribeCustomLineRequest;
import com.aliyun.alidns20150109.models.DescribeCustomLineResponse;
import com.aliyun.alidns20150109.models.UpdateDomainRecordRequest;
import com.aliyun.alidns20150109.models.UpdateDomainRecordResponse;
import com.aliyun.teaopenapi.models.Config;
import com.google.gson.Gson;
import lombok.SneakyThrows;

import java.rmi.ServerException;

public class AliyunDomainUtil {
    @SneakyThrows
    public static void main(String[] args) {
        // https://next.api.aliyun.com/api-tools/sdk/Alidns?spm=a2c4g.11186623.0.0.30bc12d4kjja9M&version=2015-01-09&language=java-tea
        String accessKeyId = "";
        accessKeyId = FileUtil.readUtf8String("F:\\OneDrive\\企岛\\domain\\key.txt");
        String accessKeySecret = "";
        accessKeySecret = FileUtil.readUtf8String("F:\\OneDrive\\企岛\\domain\\secret.txt");

        System.out.println(accessKeyId);
        System.out.println(accessKeySecret);


        Config config = new Config()
                // 您的AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "alidns.cn-hangzhou.aliyuncs.com";


        Client client = new Client(config);

//        UpdateDomainRecordRequest updateDomainRecordRequest = new UpdateDomainRecordRequest();
        UpdateDomainRecordRequest updateDomainRecordRequest = new UpdateDomainRecordRequest()
                .setRecordId("21553798018569216")
                .setType("A")
                .setValue("47.114.72.89")
                .setRR("test");
//        updateDomainRecordRequest
        // 复制代码运行请自行打印 API 的返回值
        UpdateDomainRecordResponse updateDomainRecordResponse = client.updateDomainRecord(updateDomainRecordRequest);
        System.out.println(JSONUtil.toJsonStr(updateDomainRecordResponse));

    }

}
