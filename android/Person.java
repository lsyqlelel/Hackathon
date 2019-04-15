package team.dmqqd.chengjitong;

import java.util.HashMap;

public class Person {
    private float aver;
    private float rank_percent;
    private int rank;
    private Term term;
    private Integer score;

    public HashMap<String, Integer> getHmSubScore() {
        return hmSubScore;
    }

    public void setHmSubScore(HashMap<String, Integer> hmSubScore) {
        this.hmSubScore = hmSubScore;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    private HashMap<String,Integer> hmSubScore;

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int grade) {
        this.score = grade;
    }

    public float getAver() {
        return aver;
    }

    public void setAver(float aver) {
        this.aver = aver;
    }

    public float getRank_percent() {
        return rank_percent;
    }

    public void setRank_percent(float rank_percent) {
        this.rank_percent = rank_percent;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }


}
