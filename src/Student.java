import java.io.*;
import java.util.ArrayList;

public class Student implements Serializable {
    @Serial
    private static final long serialVersionUID = 0;

    private final String name;

    private final String lastname;

    private final String id;

    private String password;

    private int units;

    private final ArrayList<Course> courses;

    static String RESET = "\u001B[0m";
    static String RED = "\u001B[31m";
    static String GREEN = "\u001B[32m";
    private static final String dataBaseUrl = "C:\\Users\\Sumsung\\OneDrive\\Documents\\Java\\AP_Project_BackEnd\\Data Base Files";

//----------------------------------------------------------------------------------------------------------------------

    public Student(String name, String lastname, String id, String password) {
        this.name = name;
        this.lastname = lastname;
        this.id = id;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
    } //--- Completed ---//

    public void removeCourse(Course course) {
        units -= course.getUnits();
        for (Course c : courses) {
            if (c.getName().equals(course.getName())) {
                courses.remove(c);
                return;
            }
        }
    } //--- Completed ---//

    public void printCourses() {
        if (courses.isEmpty()) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println(RED + "You don't have any courses\n" + RESET);
        } else {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            for (Course course : courses) {
                System.out.println(GREEN + "Course: " + course.getName() + ", Units: " + course.getUnits() + RESET);
            }
            System.out.println();
        }
    } //--- Completed ---//

    public void printTotalAverage() {
        double total = 0;
        for (Course course : courses) {
            total += getCourseFromDataBase(course.getName()).getScore(getStudentFromDataBase(id)) * course.getUnits();
        }
        System.out.printf(GREEN + "Total average is %.2f\n\n" + RESET, total / units);
    } //--- Completed ---//

    private static Course getCourseFromDataBase(String name) {
        try {
            File dir = new File(dataBaseUrl + "\\Courses");
            File[] files = dir.listFiles();

            for (File file : files) {
                if (file.getName().equals(name + ".txt")) {
                    ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(dataBaseUrl
                            + "\\Courses\\" + name + ".txt"));
                    Course course = (Course) objectInputStream.readObject();
                    objectInputStream.close();
                    return course;
                }
            }
        } catch (Exception e) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println(RED + "Couldn't get course! (Exception)" + RESET);
            System.exit(1384);
        }
        return null;
    } //--- Completed ---//

    private static Student getStudentFromDataBase(String id) {
        try {
            File dir = new File(dataBaseUrl + "\\Students");
            File[] files = dir.listFiles();

            for (File file : files) {
                if (file.getName().equals(id + ".txt")) {
                    ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(dataBaseUrl
                            + "\\Students\\" + id + ".txt"));
                    Student student = (Student) objectInputStream.readObject();
                    objectInputStream.close();
                    return student;
                }
            }
        } catch (Exception e) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println(RED + "Couldn't get student! (Exception)" + RESET);
            System.exit(1384);
        }
        return null;
    } //--- Completed ---//
}