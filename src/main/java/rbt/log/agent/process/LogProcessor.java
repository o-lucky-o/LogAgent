package rbt.log.agent.process;

import com.alibaba.fastjson.JSONObject;
import rbt.log.agent.dto.LogInfoDTO;

public class LogProcessor {

    static int logIdLen = 6;  // "logId:"长度为6
    static int transactionIdLen = 15; // "TransactionID:" 长度14

    public static LogInfoDTO analysis (String batchID, String line){

        // 使用split()函数根据不同的分隔符分割字符串
        String[] parts = line.split("\\|");

        // 提取各个部分的信息
        String fileName = parts[0];

        //String dataTime = parts[0].substring(fileName.length()+1);
        //String dataTime = parts[0].split(":")[1];
        String logLevel = parts[1].trim();  // 时间
        String threadName = parts[2].trim();  // 最后的信息
        String classAndMethod = parts[3].trim();
        String lineNum = parts[4].trim();

        //String logID = parts[5].split(":")[1].trim();
        String logID = parts[5].substring(logIdLen);
        String transactionID = parts[6].substring(transactionIdLen);
        String message = parts[7].trim();


        // 赋值
        LogInfoDTO logInfoDTO = new LogInfoDTO();
        logInfoDTO.setBatchId(batchID);
        logInfoDTO.setFile(fileName);
        //logInfoDTO.setDataTime(dataTime);
        logInfoDTO.setLogLevel(logLevel);
        logInfoDTO.setThread(threadName);
        logInfoDTO.setClassAndMethod(classAndMethod);
        logInfoDTO.setLineNum(lineNum);
        logInfoDTO.setLogId(logID);
        logInfoDTO.setTransactionId(transactionID);
        logInfoDTO.setMessage(message);

        return logInfoDTO;
    }


    public static JSONObject analysisToJson (String batchID , String line){

        // 使用split()函数根据不同的分隔符分割字符串
        String[] parts = line.split("\\|");

        // 提取各个部分的信息
        String fileName = parts[0].trim();

        String logLevel = parts[1].trim();

        String threadName = parts[2].trim();
        String classAndMethod = parts[3].trim();
        String lineNum = parts[4].trim();

        //String logID = parts[5].split(":")[1].trim();
        String logID = parts[5].substring(logIdLen);
        String transactionID = parts[6].substring(transactionIdLen);
        String message = parts[7].trim();

        // 其他信息

        // 赋值

        JSONObject params = new JSONObject();
        params.put("batchId", batchID);
        params.put("file", fileName);
        params.put("logLevel", setLogLevel(logLevel));
        params.put("thread", threadName);
        params.put("classAndMethod", classAndMethod);
        params.put("lineNum", lineNum);
        params.put("logId", logID);
        params.put("transactionId", transactionID);
        params.put("message", message);

        return params;


    }


    public static LogInfoDTO analysisToDTO(String batchID , String line){

        // 使用split()函数根据不同的分隔符分割字符串
        String[] parts = line.split("\\|");

        String fileName = null;
        // 提取各个部分的信息
        if(parts[0].contains("/")){
            int lastSlashIndex = parts[0].lastIndexOf('/');
            fileName = parts[0].substring(lastSlashIndex + 1);
        }else{
            fileName = parts[0].trim();
        }

        String logLevel = parts[1].trim();

        String threadName = parts[2].trim();
        String classAndMethod = parts[3].trim();
        String lineNum = parts[4].trim();

        //String logID = parts[5].split(":")[1].trim();
        String logID = parts[5].substring(logIdLen);
        String transactionID = parts[6].substring(transactionIdLen);
        String message = parts[7].trim();

        // 其他信息

        // 赋值

        LogInfoDTO logInfoDTO = new LogInfoDTO();
        logInfoDTO.setBatchId(batchID);
        logInfoDTO.setFile(fileName);
        logInfoDTO.setLogLevel(logLevel);
        logInfoDTO.setThread(threadName);
        logInfoDTO.setClassAndMethod(classAndMethod);
        logInfoDTO.setLineNum(lineNum);
        logInfoDTO.setLogId(logID);
        logInfoDTO.setTransactionId(transactionID);
        logInfoDTO.setMessage(message);

        return logInfoDTO;

    }



    public static String removeTime (String line){

        // 使用split()函数根据不同的分隔符分割字符串
        String[] parts = line.split("\\|",2);

        // 提取各个部分的信息
        String fileName = parts[0].split(":")[0];

        // String dataTime = parts[0].substring(fileName.length()+1);

        StringBuffer br = new StringBuffer(fileName + '|' + parts[1]);


        return br.toString();
    }

    public static LogInfoDTO lineProcess (String line){

        // 使用split()函数根据不同的分隔符分割字符串
        String[] parts = line.split("\\|");

        // 提取各个部分的信息
        String fileName = parts[0].split(":")[0];

        String dataTime = parts[0].substring(fileName.length()+1);

        String logLevel = parts[1].trim();  // 时间
        String threadName = parts[2].trim();  // 最后的信息
        String classAndMethod = parts[3].trim();
        String lineNum = parts[4].trim();

        //String logID = parts[5].split(":")[1].trim();
        String logID = parts[5].substring(logIdLen);
        String transactionID = parts[6].substring(transactionIdLen);
        String message = parts[7].trim();


        // 赋值
        LogInfoDTO logInfoDTO = new LogInfoDTO();
        logInfoDTO.setFile(fileName);
        logInfoDTO.setDataTime(dataTime);
        logInfoDTO.setLogLevel(logLevel);
        logInfoDTO.setThread(threadName);
        logInfoDTO.setClassAndMethod(classAndMethod);
        logInfoDTO.setLineNum(lineNum);
        logInfoDTO.setLogId(logID);
        logInfoDTO.setTransactionId(transactionID);
        logInfoDTO.setMessage(message);

        return logInfoDTO;
    }


    public static String setLogLevel(String logLevel) {
        String ans;
        if (logLevel.equals("ERROR")) {
            return ans = "0";
        } else if (logLevel.equals("WARN")) {
            return ans = "1";
        } else if (logLevel.equals("INFO")) {
            return ans = "2";
        } else if (logLevel.equals("DEBUG")) {
            return ans = "3";
        } else {
            throw new IllegalArgumentException("Invalid log level: " + logLevel);
        }
    }
}