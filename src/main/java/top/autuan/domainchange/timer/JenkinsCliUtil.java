package top.autuan.domainchange.timer;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import lombok.SneakyThrows;

import java.io.*;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class JenkinsCliUtil {
    // todo 配置文件配置
    static String cliJarPath="F:/OneDrive/企岛/jenkins/jenkins-cli.jar";
    static String serverAddress = "";
    static String auth = "";

    public static void runCli(){

    }

    @SneakyThrows
    public static void build(String project, String branch){
        String branchStr = StrUtil.isBlank(branch) ? "" : "-p=branch="+branch;

        serverAddress = FileUtil.readUtf8String("F:\\OneDrive\\企岛\\jenkins\\serverAddress.txt");
        auth = FileUtil.readUtf8String("F:\\OneDrive\\企岛\\jenkins\\auth.txt");
        String command = StrUtil.format("java -jar {} -s {} -webSocket -auth {} build {} {}", cliJarPath,serverAddress,auth,project,branchStr);
        System.out.println(command);

        Process exec = Runtime.getRuntime().exec(command);

        exec.waitFor();

        exec.destroy();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
//        //外部jar所在位置
//        String path="F:\\OneDrive\\企岛\\jenkins\\jenkins-cli.jar";
//        Process exec = Runtime.getRuntime().exec("java -jar " + path + " -s http://47.102.145.80:8080/ -webSocket help");
//        int i1 = exec.waitFor();
//        System.out.println(i1);
//        print(exec);
//        exec.destroy();


        build("uat-qidao-manager","master");
    }


    @SneakyThrows
    public static void print(Process process) {
        // 打印到控制台
        String line;



        BufferedReader error = new BufferedReader(new InputStreamReader(process.getErrorStream()));

        while((line = error.readLine()) != null){

            System.out.println(line);

        }

        error.close();



        BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));

        while((line=input.readLine()) != null){

            System.out.println(line);

        }



        input.close();



        OutputStream outputStream = process.getOutputStream();

        PrintStream printStream = new PrintStream(outputStream);

        printStream.println();

        printStream.flush();

        printStream.close();
    }

}
