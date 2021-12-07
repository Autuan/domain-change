package top.autuan.domainchange.timer;

import lombok.SneakyThrows;
import org.jsoup.Jsoup;

public class DingTalkUtil {
    @SneakyThrows
    public static void send(String msg){
        System.out.println(msg);
        String dingTalkUrl = "";
        Jsoup.connect(dingTalkUrl)
                .requestBody(msg)
                .execute();
    }
}
