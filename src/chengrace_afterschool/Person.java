package chengrace_afterschool;

import java.util.*;

/**
 * Represents an abstract person
 *
 * @author Grace Chen
 */
abstract public class Person {

    private String ID = "";
    private String firstName = "";
    private String lastName = "";
    private String phoneNum = "";
    private String[] classIDs = {"", "", "", "", ""}; //classIDs start off blank
    Course emptyCourse = new Course(); //filler course
    private ArrayList<Course> classes = new ArrayList<>(Arrays.asList(emptyCourse, emptyCourse, emptyCourse, emptyCourse, emptyCourse)); //classes start off all filler; change by registration

    /**
     * Constructs an empty person
     */
    public Person() {
    }

    /**
     * Constructs a person by reading from a file
     *
     * @param ID The ID
     * @param firstName The first name
     * @param lastName The last name
     * @param phoneNum The phone number
     * @param classIDs The list of classes that this person currently takes
     */
    public Person(String ID, String firstName, String lastName, String phoneNum, String classIDs) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNum = phoneNum;
        this.classIDs = classIDs.split("@", -1); //a negative limit means all trailing empty Strings are included in the array
    }

    /**
     * Returns this person's ID
     *
     * @return this person's ID
     */
    public String getID() {
        return ID;
    }

    /**
     * Returns this person's first name
     *
     * @return this person's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Returns this person's last name
     *
     * @return this person's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Returns this person's full name
     *
     * @return this person's first and last names
     */
    public String getName() {
        return firstName + " " + lastName;
    }

    /**
     * Returns this person's phone number
     *
     * @return this person's phone number
     */
    public String getPhoneNum() {
        return phoneNum;
    }

    /**
     * Returns the list of classIDs that this person takes
     *
     * @return The list of classIDs that this person takes
     */
    public String[] getClassIDs() {
        return classIDs;
    }

    /**
     * Returns the list of classes that this person takes
     *
     * @return The list of classes that this person takes
     */
    public ArrayList<Course> getClasses() {
        return classes;
    }

    /**
     * Sets this person's ID
     *
     * @param ID This person's new ID
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * Sets this person's first name
     *
     * @param firstName This person's new first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Sets this person's last name
     *
     * @param lastName This person's new last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Sets this person's phone number
     *
     * @param phoneNum This person's new phone number
     */
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    /**
     * Compares two people by their names
     *
     * @param p The other person
     * @return The relative value of the two names
     */
    public int compareName(Person p) {
        return this.getName().compareTo(p.getName());
    }

    /**
     * Compares two people by their IDs
     *
     * @param p The other person
     * @return The relative value of the two IDs
     */
    public int compareID(Person p) {
        return this.getID().compareTo(p.getID());
    }

    /**
     * Calculates how much this person earns/costs the school per week
     *
     * @return The amount this person earns/costs the school per week
     */
    abstract double profit(); //positive for students, negative for teachers

    /**
     * Prints this person's information and schedule
     *
     * @return This person's information and schedule
     */
    public String toString() {
        String out = "ID: " + this.ID
                + "\nName: " + this.getName()
                + "\nPhone: " + this.phoneNum + "\n";
        for (int i = 0; i < 5; i++) { //for each day of the week
            Course c = this.classes.get(i);
            out += "Day " + i + ": ";
            if (c.getCourseCode().equals("")) {
                out += "None\n";
            } else {
                out += "Code: " + c.getCourseCode() + ", Name: " + c.getCourseName() + "\n";
            }
        }
        return out;
    }
}
