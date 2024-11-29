package com.dive.utils;

import com.dive.lib.ContestDetailed;
import com.dive.lib.Player;

import java.io.*;
import java.util.*;

public class CoreModule {
    private DataParser dataParser = new DataParser();
    private ResultFormatter resultFormatter = new ResultFormatter();

    // 处理命令
    public String processCommand(String command) {
        try {

            if (command.equals("players")) {
                List<Player> players = dataParser.getAllPlayers();

                // 排序
                players.sort(Comparator.comparing(Player::getCountryName).thenComparing(Player::getPreferredLastName));

                // 格式化并写入文件
                String result = resultFormatter.formatPlayers(players);
                writeToFile(result);
                return result;
            }

            else if (command.startsWith("result")) {
                String[] parts = command.split(" ", 3);
                if (parts.length < 3) {
                    return "Error: Invalid result command .'";
                }

                String eventName = parts[1] + " " + parts[2];
                boolean detailed = command.endsWith("detail"); // 判断是否为 "detail" 命令

                // 获取结果
                List<ContestDetailed> contestResults = dataParser.getEventResult(eventName, detailed);

                // 格式化结果
                String result;
                if (detailed) {
                    result = resultFormatter.formatDetailedContestResult(contestResults);
                } else {
                    result = resultFormatter.formatEventResult(contestResults);
                }


                writeToFile(result);
                return result;
            }

            return "Error: Invalid command";
        } catch (FileNotFoundException e) {
            return "Error: Event file not found: " + e.getMessage();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    // 写入结果到文件
    private void writeToFile(String content) {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt"), "UTF-8"))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
