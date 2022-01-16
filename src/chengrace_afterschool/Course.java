package chengrace_afterschool;

/**
 * Represents a course
 *
 * @author Grace Chen
 */
public class Course {

    private String courseCode;
    private String courseName;
    private String subject;
    private double hourlyProfitPerStudent;
    private double hourlyCostPerTeacher;
    private double hoursPerDay;

    /**
     * Constructs a blank course
     */
    public Course() {
        this.courseCode = "";
        this.courseName = "";
        this.subject = "";
        this.hourlyProfitPerStudent = 0;
        this.hourlyCostPerTeacher = 0;
        this.hoursPerDay = 0;
    }

    /**
     * Constructs a course by reading from a file
     *
     * @param courseCode The course code
     * @param courseName The course name
     * @param subject The subject of this course
     * @param hourlyProfitPerStudent The hourly profit per student
     * @param hourlyCostPerTeacher The hourly cost per teacher
     * @param hoursPerDay The hours per day
     */
    public Course(String courseCode, String courseName, String subject, double hourlyProfitPerStudent, double hourlyCostPerTeacher, double hoursPerDay) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.subject = subject;
        this.hourlyProfitPerStudent = hourlyProfitPerStudent;
        this.hourlyCostPerTeacher = hourlyCostPerTeacher;
        this.hoursPerDay = hoursPerDay;
    }

    /**
     * Gets this course's courseCode
     *
     * @return this course's courseCode
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * Gets this course's courseName
     *
     * @return this course's courseName
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Gets this course's subject
     *
     * @return this course's subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Gets this course's hourly profit per student
     *
     * @return this course's hourly profit per student
     */
    public double getHourlyProfitPerStudent() {
        return hourlyProfitPerStudent;
    }

    /**
     * Gets this course's hourly cost per teacher
     *
     * @return this course's hourly cost per teacher
     */
    public double getHourlyCostPerTeacher() {
        return hourlyCostPerTeacher;
    }

    /**
     * Gets this course's hours per day
     *
     * @return this course's hours per day
     */
    public double getHoursPerDay() {
        return hoursPerDay;
    }

    /**
     * Sets this course's courseCode
     *
     * @param courseCode The courseCode
     */
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    /**
     * Sets this course's courseName
     *
     * @param courseName The courseName
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * Sets this course's subject
     *
     * @param subject The subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Sets this course's hourly profit per student
     *
     * @param hourlyProfitPerStudent The hourly profit per student
     */
    public void setHourlyProfitPerStudent(double hourlyProfitPerStudent) {
        this.hourlyProfitPerStudent = hourlyProfitPerStudent;
    }

    /**
     * Sets this course's hourly cost per teacher
     *
     * @param hourlyCostPerTeacher The hourly cost per teacher
     */
    public void setHourlyCostPerTeacher(double hourlyCostPerTeacher) {
        this.hourlyCostPerTeacher = hourlyCostPerTeacher;
    }

    /**
     * Sets this course's hours per day
     *
     * @param hoursPerDay The hours per day
     */
    public void setHoursPerDay(double hoursPerDay) {
        this.hoursPerDay = hoursPerDay;
    }

    /**
     * Compares two courses by their names
     *
     * @param c The other course
     * @return The comparative value of the two names
     */
    public int compareName(Course c) {
        return this.courseName.compareTo(c.getCourseName());
    }

    /**
     * Compares two courses by their IDs
     *
     * @param c The other course
     * @return The comparative value of the two IDs
     */
    public int compareID(Course c) {
        return this.courseCode.compareTo(c.getCourseCode());
    }

    /**
     * Prints basic information about the course
     *
     * @return basic information about the course
     */
    public String toString() {
        return "Course code: " + this.courseCode
                + "\nCourse name: " + this.courseName
                + "\nSubject: " + this.subject
                + "\nHourly profit per student: " + this.hourlyProfitPerStudent
                + "\nHourly cost per teacher: " + this.hourlyCostPerTeacher
                + "\nHours per day: " + this.hoursPerDay + "\n";
    }
}
