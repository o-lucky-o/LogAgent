package rbt.log.agent;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

import static rbt.log.agent.http.RestUtil.sendPost;
import static rbt.log.agent.process.FileProcess.readTxt;
import static rbt.log.agent.process.ShellUtils.collectErrorLog;

@SpringBootApplication
public class LogAgentApplication {

    private static Logger logger = Logger.getLogger(LogAgentApplication.class);
    public static void main(String[] args) {

        SpringApplication.run(LogAgentApplication.class, args);

        //日志记录
        logger.info("\n【输入参数】依次输入三个参数 待处理文件夹的路径 输出文件路径 post请求的URL\n" +
                "【输入参数】示例： --path=/home/pltmuser/vrbt/openas/logs/videousdp --out=/home/pltmuser/out.txt  --url=http://ip:8088/errorLog\n" +
                "说明： 表示 收集 /home/pltmuser/vrbt/openas/logs/videousdp 路径下的所有Error日志，并将收集结果保存在 /home/pltmuser/out.txt，最后将/home/pltmuser/out.txt 中的内容去重后 发送到http://ip:8088/errorLog");

        // 0、读取配置
        String processPath = args[0].split("=")[1];
        String outPath = args[1].split("=")[1];
        String postUrl = args[2].split("=")[1];
        System.out.println("\n【1：参数配置】你配置参数为" + Arrays.toString(args) + "\n");

        // 1、 执行  shell 语句 ：生成全量log文件
        // collectErrorLog(processPath,outPath);

        // 2、读取文件	 3、数据处理
        String jsonString = readTxt(outPath);

        // 4、发送http post
        if(jsonString != null){
            String result = sendPost(postUrl,jsonString);
            logger.info("【5：响应体：】post请求的响应结果为：" + result);
        }else{
            logger.info(" 文件内容为空,不需要处理!");
        }

    }

}