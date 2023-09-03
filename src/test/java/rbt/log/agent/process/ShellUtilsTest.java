package rbt.log.agent.process;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import static org.junit.jupiter.api.Assertions.*;

class ShellUtilsTest {

    public void shell(){
        // String outPath = " ";
        // String statement = String.format("grep -r \'|ERROR|\' > %s",outPath);
        //String[] cmd = {"/bin/sh", "-c", ""};
        // 不显示结果
        //String[] cmdCD = {"cmd","/c", "cd","C:\\Users\\l50034609\\Desktop\\log\\Agent\\LogAgent\\src\\test\\resources"};
        //String[] cmdCD = {"cmd","/c", "dir"};
        //Runtime.getRuntime().exec(cmdCD);

        //String cmd = "cmd /c cd %path%";
        //r.exec(cmd,new String[]{"path=C:\\Users\\l50034609\\Desktop\\log\\Agent\\LogAgent\\src\\test\\resources"}); // 假设该操作为造成大量内容输出

        /*
            // 显示结果
            String[] cmd = {"cmd","/c", " echo 'hello world' > test.go"};
            InputStream is = Runtime.getRuntime().exec(cmd).getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String line = br.readLine();
            while (line != null){
                System.out.println(line);
                line = br.readLine();
            }
           */
    }

    @Test
    public void shellMakeFileInRoot() {

        Runtime r = Runtime.getRuntime();

        try {
            // 在根目录下生成文件
            String cmdMd = "cmd /c echo 'hello world' > %dest% ";

            String outPath = "test.txt";
            String op = java.lang.String.format("dest=.\\%s", outPath);
            String[] outputPath = new String[]{op};

            File runDir = new File(".");

            Process proc = r.exec(cmdMd,outputPath,runDir); // 假设该操作为造成大量内容输出

            //Process proc = r.exec("cmd /c dir"); // 假设该操作为造成大量内容输出
            // 采用字符流读取缓冲池内容，腾出空间
            BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream(), "gbk"));
            String line = null;
            while ((line = reader.readLine()) != null){
                System.out.println(line);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }


    @Test
    public void shellMakeFileInSet() {

        Runtime r = Runtime.getRuntime();
        try {
            // 指定生成的路径 和 文件名
            String cmdMd = "cmd /c echo \'hello world\' > %dest% ";

            String outPath = "test.txt";
            String op = java.lang.String.format("dest=C:\\Users\\l50034609\\Desktop\\log\\Agent\\LogAgent\\src\\test\\resources\\%s", outPath);
            String[] outputPath = new String[]{op};

            File runDir = new File("C:\\Users\\l50034609\\Desktop\\\\log\\\\Agent\\\\LogAgent\\\\src\\\\test\\\\resources");

            Process proc = r.exec(cmdMd,outputPath,runDir); // 假设该操作为造成大量内容输出

            //Process proc = r.exec("cmd /c dir"); // 假设该操作为造成大量内容输出
            // 采用字符流读取缓冲池内容，腾出空间
            BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream(), "gbk"));
            String line = null;
            while ((line = reader.readLine()) != null){
                System.out.println(line);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

}