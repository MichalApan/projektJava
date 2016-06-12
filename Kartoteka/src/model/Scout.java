package model;

/**
 *
 * @author Michał
 */
public class Scout {

    private int pesel;
    private String name;
    private String surname;
    private int scoutRankId;
    private final String[] columnNames = {"pesel", "name", "surname", "scoutRankId"};

    public Scout(int pesel, String name, String surname, int scoutRankId) {
        this.pesel = pesel;
        this.name = name;
        this.surname = surname;
        this.scoutRankId = scoutRankId;
    }

    public Scout() {
    }
    
    public String getColumnAt(int column){
        return columnNames[column];
    }
    
    public int getPesel() {
        return pesel;
    }

    public void setPesel(int pesel) {
        this.pesel = pesel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getScoutRankId() {
        return scoutRankId;
    }

    public void setScoutRankId(int scoutRankId) {
        this.scoutRankId = scoutRankId;
    }

    @Override
    public String toString() {
        return "" + pesel + " " + name + " " + surname + " - " + scoutRankId;
    }
}
