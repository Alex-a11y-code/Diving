package com.dive.lib;

import java.util.List;

public class ContestDetailed {
    private String fullName;  // 选手姓名
    private List<Contest> contests;  // 存储初赛、半决赛、决赛成绩的 Contest 对象列表

    // Constructor
    public ContestDetailed(String fullName, List<Contest> contests) {
        this.fullName = fullName;
        this.contests = contests;
    }

    // Getters and setters
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<Contest> getContests() {
        return contests;
    }

    public void setContests(List<Contest> contests) {
        this.contests = contests;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Full Name: ").append(fullName).append("\n");

        // 输出每个比赛阶段的成绩
        for (int i = 0; i < contests.size(); i++) {
            sb.append(contests.get(i).toString()).append("\n");
            if (i < contests.size() - 1) {
                sb.append("-----\n");
            }
        }
        return sb.toString();
    }
}