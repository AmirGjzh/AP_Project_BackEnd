import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class Teacher implements Serializable {
    @Serial
    private static final long serialVersionUID = 0;

    private final String name;

    private final String lastname;

    private final String username;

    private String password;

    private ArrayList<Course> courses;
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

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }
//----------------------------------------------------------------------------------------------------------------------
    public int addCourse(Course course) {
        for (Course c : courses) {
            if (c.getName().equals(course.getName())) {
                return 1;
            }
        }
        if (course.setTeacher(this)) {
            courses.add(course);
            return 3;
        }
        else {
            return 2;
        }
    }

    public int removeCourse(Course course) {
        for (Course c : courses) {
            if (c.getName().equals(course.getName())) {
                for (Course c1 : courses) {
                    if (c1.getName().equals(course.getName())) {
                        courses.remove(c1);
                        break;
                    }
                }
                course.setTeacher(null);
                return 2;
            }
        }
        return 1;
    }

    public void addStudentToCourse(Student student, Course course) {
        if (courses.contains(course)) {
            course.addStudent(student);
        }
        else {
            System.out.println("You don't have access to this course!");
        }
    }

    public void removeStudentFromCourse(Student student, Course course) {
        if (courses.contains(course)) {
            course.removeStudent(student);
        }
        else {
            System.out.println("You don't have access to this course!");
        }
    }

    public void addExerciseToCourse(Assignment exercise) {
        if (courses.contains(exercise.getCourse())) {
            exercise.getCourse().addExercise(exercise);
        }
        else {
            System.out.println("You don't have access to this course!");
        }
    }

    public void removeExerciseFromCourse(Assignment exercise) {
        if (courses.contains(exercise.getCourse())) {
            exercise.getCourse().removeExercise(exercise);
        }
        else {
            System.out.println("You don't have access to this course!");
        }
    }

    public void addProjectToCourse(Assignment project, Course course) {
        if (courses.contains(course)) {
            course.setProject(project);
        }
        else {
            System.out.println("You don't have access to this course!");
        }
    }

    public void removeProjectFromCourse(Assignment project, Course course) {
        if (courses.contains(course)) {
            if (course.getProject() == project) {
                course.setProject(null);
            }
            else {
                System.out.println("There is no such project in this course!");
            }
        }
        else {
            System.out.println("You don't have access to this course!");
        }
    }

    public void changeDeadLine(Assignment assignment, int newDeadLine) {
        if (courses.contains(assignment.getCourse())) {
            assignment.setDeadLine(newDeadLine);
        }
        else {
            System.out.println("You don't have access to this course!");
        }
    }

    public void addScoreToStudent(Student student, Course course, Double score) {
        if (courses.contains(course)) {
            if (course.getStudents().containsKey(student)) {
                course.addScore(student, score);
            }
            else {
                System.out.println("This student isn't in this course!");
            }
        }
        else {
            System.out.println("You don't have access to this course!");
        }
    }
}
