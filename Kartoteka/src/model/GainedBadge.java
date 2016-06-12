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
public class GainedBadge extends Achievement {

    private int pesel;
    private int badgeId;

    public GainedBadge(int pesel, int badgeId, String commandNumber, String date) {
        super(date, commandNumber);
        this.pesel = pesel;
        this.badgeId = badgeId;
    }

    public int getBadgeId() {
        return badgeId;
    }

    public void setBadgeId(int badgeId) {
        this.badgeId = badgeId;
    }

    public int getPesel() {
        return pesel;
    }

    public void setPesel(int scoutPesel) {
        this.pesel = scoutPesel;
    }
}
