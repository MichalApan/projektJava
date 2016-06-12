/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Michał
 */
public class ScoutRank {

    private int scoutRankId;
    private String title;
    private final String[] columnNames = {"scoutRankId", "title"};

    public ScoutRank(int scoutRankId, String title) {
        this.scoutRankId = scoutRankId;
        this.title = title;
    }

    public ScoutRank() {
    }

    public int getScoutRankId() {
        return scoutRankId;
    }

    public void setScoutRankId(int scoutRankId) {
        this.scoutRankId = scoutRankId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "[" + scoutRankId + "] - " + title;
    }

    public String getColumnAt(int column) {
        return columnNames[column];
    }
}
