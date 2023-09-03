package rbt.log.agent.process;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileProcessTest {
    @Test
    public void read_file_success() {

        String filePath = "./log.txt";//getClass().getResource("/log.txt").getPath();
        // String filePath= "/home/pltmuser/vrbt/openas/logs/videousdp/videousdp.error.log";
        // String filePath= "log.txt";
        String url = ".";
        FileProcess.readTxt(url,filePath);
    }
}