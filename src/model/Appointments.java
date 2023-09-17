package model;

import java.time.LocalDateTime;

/**
 * This class creates Appointments.
 */

public class Appointments {

    private int appID;
    private String title;
    private String desc;
    private String loc;
    private String type;
    private LocalDateTime start;
    private LocalDateTime end;
    private int cusID;
    private int useID;
    private int conID;
    private String conName;

    /**
     * This method creates an Appointments object.
     * @param appID the appID of the appointment
     * @param title the title of the appointment
     * @param desc the desc of the appointment
     * @param loc the loc of the appointment
     * @param type the type of the appointment
     * @param start the start of the appointment
     * @param end the end of the appointment
     * @param cusID the cusID of the appointment
     * @param useID the useID of the appointment
     * @param conID the conID of the appointment
     * @param conName the conName of the appointment
     */

    public Appointments(int appID, String title, String desc, String loc, String type, LocalDateTime start, LocalDateTime end, int cusID, int useID, int conID, String conName) {
        this.appID = appID;
        this.title = title;
        this.desc = desc;
        this.loc = loc;
        this.type = type;
        this.start = start;
        this.end = end;
        this.cusID = cusID;
        this.useID = useID;
        this.conID = conID;
        this.conName = conName;
    }

    public Appointments(int appID, LocalDateTime start, LocalDateTime end) {
        this.appID = appID;
        this.start = start;
        this.end = end;
    }

    /**
     *
     * @return the companyName
     */

    public int getAppID() {
        return appID;
    }

    /**
     *
     * @param appID the appID to set
     */

    public void setAppID(int appID) {
        this.appID = appID;
    }

    /**
     *
     * @return the title
     */

    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title the title to set
     */

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return the desc
     */

    public String getDesc() {
        return desc;
    }

    /**
     *
     * @param desc the desc to set
     */

    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     *
     * @return the loc
     */

    public String getLoc() {
        return loc;
    }

    /**
     *
     * @param loc the loc to set
     */

    public void setLoc(String loc) {
        this.loc = loc;
    }

    /**
     *
     * @return the type
     */

    public String getType() {
        return type;
    }

    /**
     *
     * @param type the type to set
     */

    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return the start
     */

    public LocalDateTime getStart() {
        return start;
    }

    /**
     *
     * @param start the start to set
     */

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    /**
     *
     * @return the end
     */

    public LocalDateTime getEnd() {
        return end;
    }

    /**
     *
     * @param end the end to set
     */

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    /**
     *
     * @return the cusID
     */

    public int getCusID() {
        return cusID;
    }

    /**
     *
     * @param cusID the cusID to set
     */

    public void setCusID(int cusID) {
        this.cusID = cusID;
    }

    /**
     *
     * @return the useID
     */

    public int getUseID() {
        return useID;
    }

    /**
     *
     * @param useID the useID to set
     */

    public void setUseID(int useID) {
        this.useID = useID;
    }

    /**
     *
     * @return the conID
     */

    public int getConID() {
        return conID;
    }

    /**
     *
     * @param conID the conID to set
     */

    public void setConID(int conID) {
        this.conID = conID;
    }

    /**
     *
     * @return the conName
     */

    public String getConName() { return conName; }

    /**
     *
     * @param conName the conName to set
     */

    public void setConName(String conName) {  this.conName = conName; }

}
