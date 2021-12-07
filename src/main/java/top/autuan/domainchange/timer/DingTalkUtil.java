package top.autuan.domainchange.timer;

import cn.hutool.json.JSONObject;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;

public class DingTalkUtil {
    @SneakyThrows
    public static void send(String sendMsg){
        JSONObject jsonObject = new JSONObject();
        jsonObject.set("msgtype","text");
        JSONObject textObject = new JSONObject();
        textObject.set("content", sendMsg);
        jsonObject.set("text", textObject);

        System.out.println(jsonObject.toString());

        // todo 实现
//        String dingTalkUrl = "";
//        Jsoup.connect(dingTalkUrl)
//                .requestBody(jsonObject.toString())
//                .execute();
    }
}
