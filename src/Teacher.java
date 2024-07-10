import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Teacher implements Serializable {
    @Serial
    private static final long serialVersionUID = 0;

    private final String name;

    private final String lastname;

    private final String username;

    private String password;

    private final ArrayList<Course> courses;

    static String RESET = "\u001B[0m";
    static String RED = "\u001B[31m";
    static String GREEN = "\u001B[32m";

//----------------------------------------------------------------------------------------------------------------------

    public Teacher(String name, String lastname, String username, String password) {
        this.name = name;
        this.lastname = lastname;
        courses = new ArrayList<>();
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//----------------------------------------------------------------------------------------------------------------------

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

    public int addCourse(Course course) {
        for (Course c : courses) {
            if (c.getName().equals(course.getName())) {
                return 1;
            }
        }
        if (course.setTeacher(this)) {
            courses.add(course);
            return 3;
        } else {
            return 2;
        }
    } //--- Completed ---//

    public int removeCourse(Course course) {
        for (Course c : courses) {
            if (c.getName().equals(course.getName())) {
                courses.remove(c);
                course.setTeacher(null);
                return 2;
            }
        }
        return 1;
    } //--- Completed ---//

    public int addStudentToCourse(Student student, Course course) {
        for (Course c : courses) {
            if (c.getName().equals(course.getName())) {
                return course.addStudent(student);
            }
        }
        return 3;
    } //--- Completed ---//

    public int removeStudentFromCourse(Student student, Course course) {
        for (Course c : courses) {
            if (c.getName().equals(course.getName())) {
                return course.removeStudent(student);
            }
        }
        return 3;
    } //--- Completed ---//

    public int addExerciseToCourse(Assignment exercise, Course course) {
        for (Course c : courses) {
            if (c.getName().equals(course.getName())) {
                return course.addExercise(exercise);
            }
        }
        return 1;
    } //--- Completed ---//

    public int removeExerciseFromCourse(Assignment exercise, Course course) {
        for (Course c : courses) {
            if (c.getName().equals(course.getName())) {
                return course.removeExercise(exercise);
            }
        }
        return 1;
    } //--- Completed ---//

    public int addProjectToCourse(Assignment project, Course course) {
        for (Course c : courses) {
            if (c.getName().equals(course.getName())) {
                return course.setProject(project);
            }
        }
        return 1;
    } //--- Completed ---//

    public int removeProjectFromCourse(Assignment project, Course course) {
        for (Course c : courses) {
            if (c.getName().equals(course.getName())) {
                return course.setProject(null);
            }
        }
        return 1;
    } //--- Completed ---//

    public int changeDeadLine(Assignment assignment, LocalDateTime newDeadLine) {
        for (Course course : courses) {
            if (course.getName().equals(assignment.getCourse().getName())) {
                return assignment.setDeadLine(newDeadLine);
            }
        }
        return 1;
    } //--- Completed ---//

    public int addScoreToStudent(Student student, Course course, Double score) {
        for (Course c : courses) {
            if (c.getName().equals(course.getName())) {
                for (Student s : course.getStudents().keySet()) {
                    if (s.getId().equals(student.getId())) {
                        return course.addScore(student, score);
                    }
                }
                return 2;
            }
        }
        return 1;
    } //--- Completed ---//
}