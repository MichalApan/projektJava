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
public class GainedRank extends Achievement {

    public enum stat {
        OPEN, CLOSED, ACTUAL
    };
    private int pesel;
    private int scoutRankId;
    private String supervisor;
    private int s;

    public GainedRank(int pesel, int scoutRankId,
            String supervisor, String date, String commandNumber, int s) {
        super(date, commandNumber);
        this.pesel = pesel;
        this.scoutRankId = scoutRankId;
        this.supervisor = supervisor;
        this.s = s;
    }

    /**
     *
     */
    public GainedRank() {
        super("","");
    }

    public int getPesel() {
        return pesel;
    }

    public void setPesel(int pesel) {
        this.pesel = pesel;
    }

    public int getScoutRankId() {
        return scoutRankId;
    }

    public void setScoutRankId(int scoutRankId) {
        this.scoutRankId = scoutRankId;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public int getS() {
        return s;
    }

    public void setS(int s) {
        this.s = s;
    }
}
