package rbt.log.agent.process;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import rbt.log.agent.dto.LogInfoDTO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import static rbt.log.agent.process.LogProcessor.*;

public class FileProcess {
    private static Logger logger = Logger.getLogger(FileProcess.class);

    public static Set<String> readTxt(String url, String filePath) {
        // 2、读取文件 去重
        Set<String> lines = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // 3.1、 数据处理 去掉时间
                String lineWithoutTime = removeTime(line);
                lines.add(lineWithoutTime);
                // 不去掉时间
                // lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int len = lines.size();
        System.out.println("这一批有"+ len + "条异常日志！");

        String batchID = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(new Date() );;
        // 去重后 ,逐行发送post请求 ，这里只是简单地打印出来
        for (String uniqueLine : lines) {

            System.out.println(uniqueLine);
            // 3.2、 数据处理
            JSONObject json = analysisToJson(batchID, uniqueLine);
            System.out.println("Java对象转化为JSON对象\n" + json);

        }

        return lines;
    }


    public static String readTxt(String filePath) {
        // 读取文件 去重
        Set<String> lines = new HashSet<>();
        logger.info("【2：文件读取：】开始读取:" + filePath + "\n");
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // 去掉时间
                String lineWithoutTime = removeTime(line);
                lines.add(lineWithoutTime);
                // 不去掉时间
                // lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int len = lines.size();
        logger.info("【3：数据处理：】这一批有"+ len + "条异常日志！");

        String batchID = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(new Date() );;
        // 去重后 ,逐行发送post请求 ，这里只是简单地打印出来
        List<LogInfoDTO > list = new ArrayList<LogInfoDTO >();

        for (String uniqueLine : lines) {

            LogInfoDTO logInfoDTO = analysisToDTO(batchID, uniqueLine);
            list.add(logInfoDTO);
        }
        logger.info("【3：数据处理：】数据处理完毕");
        // 转换成 json 数组
        JSONArray array= JSON.parseArray(JSON.toJSONString(list));
        String jsonString = array.toString();

        return jsonString;
    }


}