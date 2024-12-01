package com.dive.utils;

import com.dive.lib.Contest;
import com.dive.lib.Player;
import com.dive.lib.ContestDetailed;

import java.util.List;

public class ResultFormatter {



        // 格式化选手列表输出
        public String formatPlayers(List<Player> players) {
            StringBuilder sb = new StringBuilder();
            for (Player player : players) {
                sb.append(player.toString()).append("\n");
                sb.append("-----\n");
            }
            sb.append("-----\n"); // 最后一个选手后添加分割线
            return sb.toString();
        }

        // 格式化比赛结果输出
        public String formatEventResult(List<ContestDetailed> contestDetailedList) {
            StringBuilder sb = new StringBuilder();
            for (ContestDetailed contestDetailed : contestDetailedList) {
                sb.append(contestDetailed.toString());
                sb.append("-----\n");
            }
            return sb.toString();
        }

        // 格式化详细的比赛结果
        public String formatDetailedContestResult(List<ContestDetailed> contestDetailedList) {
            StringBuilder sb = new StringBuilder();
            for (ContestDetailed contestDetailed : contestDetailedList) {
                sb.append("Full Name:").append(contestDetailed.getFullName()).append("\n");

                // 输出初赛、半决赛和决赛成绩
                String rank = "* | * | *";  // 假设默认值是 '*'
                String preliminaryScore = "*";
                String semifinalScore = "*";
                String finalScore = "*";

                // 每个阶段成绩
                for (int i = 0; i < contestDetailed.getContests().size(); i++) {
                    Contest contest = contestDetailed.getContests().get(i);
                    if (i == 0) {  // 初赛
                        rank = contest.getRank() + " | " + rank.split(" | ")[1] + " | " + rank.split(" | ")[2];
                        preliminaryScore = contest.getTotalPoints();
                    } else if (i == 1) {  // 半决赛
                        rank = rank.split(" | ")[0] + " | " + contest.getRank() + " | " + rank.split(" | ")[2];
                        semifinalScore = contest.getTotalPoints();
                    } else if (i == 2) {  // 决赛
                        rank = rank.split(" | ")[0] + " | " + rank.split(" | ")[1] + " | " + contest.getRank();
                        finalScore = contest.getTotalPoints();
                    }
                }

                sb.append("Rank:").append(rank).append("\n");
                sb.append("Preliminary Score:").append(preliminaryScore).append("\n");
                sb.append("Semifinal Score:").append(semifinalScore).append("\n");
                sb.append("Final Score:").append(finalScore).append("\n");
                sb.append("-----\n");
            }
            return sb.toString();
        }
    }

