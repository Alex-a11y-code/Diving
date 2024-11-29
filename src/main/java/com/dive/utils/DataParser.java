package com.dive.utils;

import com.dive.lib.Contest;
import com.dive.lib.ContestDetailed;
import com.dive.lib.Player;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.*;

public class DataParser {
    private static final String PLAYERS_FILE = "players.json";
    private static final String RESULTS_DIRECTORY = "result\\";

    // 获取所有选手信息
    public List<Player> getAllPlayers() throws IOException {
        try (Reader reader = new FileReader(PLAYERS_FILE)) {
            return new Gson().fromJson(reader, new TypeToken<List<Player>>() {}.getType());
        }
    }

    // 获取比赛结果
    public List<ContestDetailed> getEventResult(String eventName, boolean detailed) throws IOException {
        String eventFileName = RESULTS_DIRECTORY + eventName + ".json";
        File eventFile = new File(eventFileName);

        if (!eventFile.exists()) {
            throw new FileNotFoundException("Event file not found: " + eventFileName);
        }

        try (Reader reader = new FileReader(eventFileName)) {
            List<Map<String, Object>> data = new Gson().fromJson(reader, new TypeToken<List<Map<String, Object>>>() {}.getType());
            return parseContestData(data, detailed);
        }
    }

    // 解析比赛数据
    private List<ContestDetailed> parseContestData(List<Map<String, Object>> data, boolean detailed) {
        List<ContestDetailed> contestDetailedList = new ArrayList<>();

        for (Map<String, Object> entry : data) {
            String fullName = (String) entry.get("fullName");
            List<Map<String, Object>> stages = (List<Map<String, Object>>) entry.get("stages");
            List<Contest> contests = new ArrayList<>();

            // 解析数据（初赛、半决赛、决赛）
            for (Map<String, Object> stage : stages) {
                int rank = ((Double) stage.get("rank")).intValue();
                String score = (String) stage.get("score");

                // 根据是否为详细信息来判断是否添加该阶段数据
                if (detailed || stages.size() == 1) {
                    contests.add(new Contest(rank, score));
                }
            }

            contestDetailedList.add(new ContestDetailed(fullName, contests));
        }
        return contestDetailedList;
    }
}
