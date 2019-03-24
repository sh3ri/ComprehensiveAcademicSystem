package Model;

import java.time.DayOfWeek;
import java.util.ArrayList;

public class Class {
    public static final int[] startingTime = new int[]{8, 10, 14}, finishingTime = new int[]{10, 12, 16};
    private final Teacher owner;
    private final DayOfWeek dayOfWeek;
    private final int startTime, finishTime, unitCount;
    private ArrayList<Student> members;

    public Class(Teacher owner, DayOfWeek dayOfWeek, int startTime, int finishTime, int unitCount) {
        this.owner = owner;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.unitCount = unitCount;
    }

    public static int[] getStartingTime() {
        return startingTime;
    }

    public static int[] getFinishingTime() {
        return finishingTime;
    }

    public boolean addMember(Student student) {
        //TODO: check collisions with other classes he has taken in Student class :: takeClass method
        //TODO: VEEEEEEERY IMPORTANT INCREASE UNITS TAKEN BY THE UNIT COUNT OF THIS CLASS IN STUDENT CLASS
        if ((student.getAverage() < 17.0 && student.getUnitsTaken() + unitCount > 20) ||
                (student.getAverage() >= 17.0 && student.getUnitsTaken() + unitCount > 24))
            return false;
        return members.add(student);
    }

    {
    }

    public ArrayList<Student> getMembers() {
        return members;
    }

    public Teacher getOwner() {
        return owner;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getFinishTime() {
        return finishTime;
    }
}
