package com.dive.lib;

public class Contest {
    private int rank;  // 排名
    private String totalPoints;  // 比赛成绩，格式：score1 + score2 + ... = totalPoint

    // Constructor
    public Contest(int rank, String totalPoints) {
        this.rank = rank;
        this.totalPoints = totalPoints;
    }

    // Getters and setters
    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(String totalPoints) {
        this.totalPoints = totalPoints;
    }

    @Override
    public String toString() {
        return "Rank:" + rank + "\n" +
                "Score:" + totalPoints;
    }
}
