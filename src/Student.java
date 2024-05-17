import java.util.ArrayList;

public class Student {

    private final String name;

    private final String lastname;

    private final String id;

    private int units;

    private final ArrayList<Course> courses;
//----------------------------------------------------------------------------------------------------------------------
    public Student(String name, String lastname, String id) {
        this.name = name;
        this.lastname = lastname;
        this.id = id;
        units = 0;
        courses = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getId() {
        return id;
    }

    public int getUnits() {
        return units;
    }

    public int getNumberOfCourses() {
        return courses.size();
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }
//----------------------------------------------------------------------------------------------------------------------
    public void addCourse(Course course) {
        units += course.getUnits();
        courses.add(course);
        System.out.println("The student added successfully.");
    }

    public void removeCourse(Course course) {
        units -= course.getUnits();
        courses.remove(course);
        System.out.println("The student removed successfully.");
    }

    public void printCourses() {
        if (courses.isEmpty()) {
            System.out.println("You don't have any courses!");
        }
        else {
            for (Course course : courses) {
                System.out.println("Course: " + course.getName() + ", Units: " + course.getUnits());
            }
        }
    }

    public void printTotalAverage() {
        double total = 0;
        for (Course course : courses) {
            total += course.getScore(this) * course.getUnits();
        }
        System.out.printf("Total average is %.2f.\n", total / units);
    }
}
