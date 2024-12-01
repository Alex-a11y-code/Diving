package com.dive.utils;

import com.dive.lib.ContestDetailed;
import com.dive.lib.Player;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

// 自定义异常
class InvalidEventNameException extends Exception {}
class InvalidDetailParameterException extends Exception {}

public class CoreModule {
    private DataParser dataParser = new DataParser();
    private ResultFormatter resultFormatter = new ResultFormatter();

    // 处理命令
    public String processCommand(String command) {
        try {
            // 将所有指令转为小写字母
            command = command.toLowerCase().trim();

            // 检查 players 命令
            if (command.equals("players")) {
                List<Player> players = dataParser.getAllPlayers();

                // 排序
                players.sort((p1, p2) -> p1.getCountryName().compareTo(p2.getCountryName()) != 0 ?
                        p1.getCountryName().compareTo(p2.getCountryName()) :
                        p1.getPreferredLastName().compareTo(p2.getPreferredLastName()));
                // 格式化并返回结果
                return resultFormatter.formatPlayers(players);
            }

            // 检查 result 命令
            else if (command.startsWith("result")) {
                String[] parts = command.split(" ", 3);

                if (parts.length < 3) {
                    return "Error\n-----";  // 未正确输入比赛项目或参数
                }

                String eventName = parts[1];
                String detail = parts[2];

                // 设定有效的比赛项目名称列表
                List<String> validEvents = Arrays.asList(
                        "women 1m springboard", "women 3m springboard", "women 10m platform",
                        "women 3m synchronised", "women 10m synchronised",
                        "men 1m springboard", "men 3m springboard", "men 10m platform",
                        "men 3m synchronised", "men 10m synchronised"
                );

                // 检查比赛项目名称是否正确，抛出异常
                if (!validEvents.contains(eventName)) {
                    throw new InvalidEventNameException();
                }

                // 检查 detail 参数是否正确，抛出异常
                if (!"detail".equals(detail)) {
                    throw new InvalidDetailParameterException();
                }

                // 获取比赛结果
                List<ContestDetailed> contestResults = dataParser.getEventResult(eventName, true);

                // 格式化结果并返回
                return resultFormatter.formatDetailedContestResult(contestResults);
            }

            // 处理无法识别的指令
            return "Error\n-----";

        } catch (InvalidEventNameException e) {
            return "N/A\n-----";  // 比赛项目名称无效
        } catch (InvalidDetailParameterException e) {
            return "N/A\n-----";  // "detail" 参数不正确
        } catch (FileNotFoundException e) {
            return "N/A\n-----";  // 文件未找到
        } catch (Exception e) {
            return "N/A\n-----";  // 其他错误统一返回 "N/A"
        }
    }
}
