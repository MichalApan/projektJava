/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import javax.swing.JTable;

/**
 *
 * @author Michał
 */
public class Select extends Badge implements Callable<List<Badge>> {
private Connection c;
private Statement s;
    Select(Connection c, Statement s){
        this.c = c;
        this.s = s;
    }
    @Override
    public List<Badge> call() throws Exception {
        List<Badge> l = new LinkedList<>();
        try {
            ResultSet result = s.executeQuery("SELECT * FROM Badges ORDER BY badgeId");
            int id;
            String title, tasks;
            while (result.next()) {
                id = result.getInt("badgeId");
                title = result.getString("title");
                tasks = result.getString("tasks");

                l.add(new Badge(id, title, tasks));
            }
        } catch (SQLException e) {
            return null;
        }
        return l;
    }
}
