package rbt.log.agent.dto;

import org.springframework.stereotype.Component;

import java.io.Serializable;


@Component
public class LogInfoDTO implements Serializable {

    /**
     * 批次id 1, 2, 3, ...
     */
    String batchId;

    /**
     * 文件名 service-system.log
     */
    String file;
    /**
     * 日期时间（\d{4}-\d{2}-\d{2}\s\d{2}:\d{2}:\d{2}）
     */
    String dataTime;

    /**
     * 日志等级（ERROR|WARN|INFO|DEBUG)
     */
    String logLevel;

    /**
     * 线程名（[^:]+）
     */
    String thread;

    String classAndMethod;


    /**
     * 行号（\d+）
     */
    String lineNum;

    /**
     * LogId
     */
    String logId;

    /**
     * TransactionID
     */
    String transactionId;


    /**
     * message 报错内容
     */
    String message;

    public String getBatchId() {
        return batchId;
    }

    public String getFile() {
        return file;
    }

    public String getDataTime() {
        return dataTime;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public String getThread() {
        return thread;
    }

    public String getClassAndMethod() {
        return classAndMethod;
    }

    public String getLineNum() {
        return lineNum;
    }

    public String getLogId() {
        return logId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getMessage() {
        return message;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public void setDataTime(String dataTime) {
        this.dataTime = dataTime;
    }


    public void setLogLevel(String logLevel) {
        if (logLevel.equals("ERROR")) {
            this.logLevel = "0";
        } else if (logLevel.equals("WARN")) {
            this.logLevel = "1";
        } else if (logLevel.equals("INFO")) {
            this.logLevel = "2";
        } else if (logLevel.equals("DEBUG")) {
            this.logLevel = "3";
        } else {
            throw new IllegalArgumentException("Invalid log level: " + logLevel);
        }
    }

    public void setThread(String thread) {
        this.thread = thread;
    }

    public void setClassAndMethod(String classAndMethod) {
        this.classAndMethod = classAndMethod;
    }

    public void setLineNum(String lineNum) {
        this.lineNum = lineNum;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "LogInfoDTO{" +
                "batchId='" + batchId + '\'' +
                ", file='" + file + '\'' +
                ", dataTime='" + dataTime + '\'' +
                ", logLevel='" + logLevel + '\'' +
                ", thread='" + thread + '\'' +
                ", classAndMethod='" + classAndMethod + '\'' +
                ", lineNum='" + lineNum + '\'' +
                ", logId='" + logId + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
