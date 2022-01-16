package chengrace_afterschool;

/**
 * Represents a teacher, an extension of a person
 * @author Grace Chen
 */
public class Teacher extends Person {
    private String subjects; //the list of subjects that the teacher can teach

    /**
     * Constructs an empty teacher
     */
    public Teacher() {
    }

    /**
     * Constructs a teacher using information read from a file
     * @param ID The ID
     * @param firstName The first name
     * @param lastName The last name
     * @param phoneNum The phone number
     * @param subjects The list of subjects that the teacher is able to teach
     * @param classes The list of classes that the teacher currently teaches
     */
    public Teacher(String ID, String firstName, String lastName, String phoneNum, String subjects, String classes) {
        super(ID, firstName, lastName, phoneNum, classes);
        this.subjects = subjects;
    }

    /**
     * Gets list of teachable subjects of this teacher
     * @return a String containing all teachable subjects of this teacher
     */
    public String getSubjects() {
        return subjects;
    }

    /**
     * Sets the list of teachable subjects of this teacher
     * @param subjects The list of teachable subjects of this teacher
     */
    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }

    /**
     * Calculates how much this teacher costs the school per week
     * @return how much this teacher costs the school per week
     */
    public double profit() { //how much the teacher costs the school per week
        double sum = 0;
        for (Course c : this.getClasses()) { //for each course that this teacher teaches in
            sum += c.getHourlyCostPerTeacher() * c.getHoursPerDay() * -1;
        }
        return sum;
    }

    /**
     * Prints status, super.toString(), list of subjects, and cost 
     * @return The formatted information about this teacher
     */
    @Override
    public String toString() { //prints super toString and subjects and paystub
        String sup = super.toString();
        int index = sup.indexOf("Day");
        return "Status: Teacher\n" + sup.substring(0, index) + "Subject(s): " + this.subjects + "\n" + sup.substring(index) + "Cost: $" + profit();
    }
}
