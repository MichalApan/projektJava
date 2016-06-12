/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Michał
 */
public class Achievement {

    private String date;
    private String commandNumber;

    Achievement(String date, String commandNumber) {
        this.date = date;
        this.commandNumber = commandNumber;
    }

    public String getCommandNumber() {
        return commandNumber;
    }

    public void setCommandNumber(String commandNumber) {
        this.commandNumber = commandNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        if (checkDate(date)) {
            this.date = date;
        }
    }

    private boolean checkDate(String date) {
        Pattern p = Pattern.compile("(31|30|((1|2)[0-9]))/([1-9]|1[0-2])/((1|2)[0-9]{3})");
        Matcher m = p.matcher(date);
        boolean b = m.matches();
        if (b) {
            return true;
        } else {
            System.err.println("Niepoprawny format daty: " + date);
            return false;
        }
    }
}
