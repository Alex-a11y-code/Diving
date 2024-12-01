package com.dive;

import com.dive.utils.CoreModule;

import java.io.*;

public class DWASearch {

    public static void main(String[] args) {
        String line;
        CoreModule core = new CoreModule();

  // 获取输入文件和输出文件路径，通过命令行传递给程序
        String inputFile = args[0];
        String outputFile = args[1];
// 使用 BufferedReader 和 BufferedWriter 来读取和写入文件
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

//设置循环以读取每一行的命令
            while ((line = reader.readLine()) != null) {
                String result = core.processCommand(line.trim());
                writer.write(result);
                writer.newLine();
            }

        } catch (IOException e) {
            System.err.println("Error reading or writing files: " + e.getMessage());
        }
    }
}
