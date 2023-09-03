package rbt.log.agent.process;


import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class ShellUtils {

    public static void collectErrorLog(String processPath, String outPath) {

        Runtime r = Runtime.getRuntime();
        try {
            // 指定生成的路径 和 文件名
            String cmd = java.lang.String.format("grep -r '|ERROR|' %s > %s",processPath,outPath);
            Process proc = r.exec(new String[]{"/bin/sh","-c",cmd}); // 假设该操作为造成大量内容输出
            BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream(), "iso-8859-1"));

/*
            String cmd = "/bin/sh /c echo 'hello' >  %dest%";
            String op = java.lang.String.format("dest=%s",outPath);
            String[] outputPath = new String[]{op};

            //File runDir = new File("C:\\Users\\l50034609\\Desktop\\\\log\\\\Agent\\\\LogAgent\\\\src\\\\test\\\\resources");
            File runDir = new File(processPath);
            Process proc = r.exec(cmd,outputPath,runDir); // 假设该操作为造成大量内容输出

            Process proc = r.exec("cmd /c dir"); // 假设该操作为造成大量内容输出
            BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream(), "gbk"));
*/




            // 采用字符流读取缓冲池内容，腾出空间

            String line = null;
            while ((line = reader.readLine()) != null){
                System.out.println(line);
            }


        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
