import java.io.Serial;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class Course implements Serializable {
    @Serial
    private static final long serialVersionUID = 0;

    private final String name;

    private Teacher teacher;

    private final int units;

    private final Map<Student, Double> students;

    private boolean isActive;

    private final ArrayList<Assignment> exercises;

    private Assignment project;

    private LocalDateTime examDate;

    static String RESET = "\u001B[0m";
    static String RED = "\u001B[31m";
    static String GREEN = "\u001B[32m";

//----------------------------------------------------------------------------------------------------------------------

    public Course(String name, int units, LocalDateTime examDate) {
        this.name = name;
        this.units = units;
        this.examDate = examDate;
        teacher = null;
        students = new HashMap<>();
        exercises = new ArrayList<>();
        isActive = true;
    }

    public String getName() {
        return name;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public int getUnits() {
        return units;
    }

    public Map<Student, Double> getStudents() {
        return students;
    }

    public boolean isActive() {
        return isActive;
    }

    public ArrayList<Assignment> getExercises() {
        return exercises;
    }

    public Assignment getProject() {
        return project;
    }

    public long getExamDate() {
        return Duration.between(LocalDateTime.now(), examDate).toDays();
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean setTeacher(Teacher teacher) {
        if (teacher == null) {
            this.teacher = null;
            return true;
        }
        if (this.teacher == null) {
            this.teacher = teacher;
            return true;
        } else {
            return false;
        }
    } //--- Completed ---//

    public int setProject(Assignment project) {
        if (project != null) {
            this.project = project;
            return 3;
        } else {
            this.project = null;
            return 2;
        }
    } //--- Completed ---//

//----------------------------------------------------------------------------------------------------------------------

    public int addStudent(Student student) {
        for (Student s : students.keySet()) {
            if (s.getId().equals(student.getId())) {
                return 1;
            }
        }
        students.put(student, 0.0);
        ArrayList<Student> st = new ArrayList<>();
        st.add(student);
        for (Assignment e : exercises) {
            e.addStudents(st);
        }
        if (project != null) {
            project.addStudents(st);
        }
        student.addCourse(this);
        return 2;
    } //--- Completed ---//

    public int removeStudent(Student student) {
        for (Student s : students.keySet()) {
            if (s.getId().equals(student.getId())) {
                students.remove(s);
                ArrayList<Student> st = new ArrayList<>();
                st.add(student);
                for (Assignment e : exercises) {
                    e.removeStudents(st);
                }
                if (project != null) {
                    project.removeStudents(st);
                }
                student.removeCourse(this);
                return 2;
            }
        }
        return 1;
    } //--- Completed ---//

    public int addExercise(Assignment exercise) {
        exercises.add(exercise);
        System.out.println(students.size());
        exercise.addStudents(students.keySet().stream().toList());
        return 3;
    } //--- Completed ---//

    public int removeExercise(Assignment exercise) {
        for (Assignment assignment : exercises) {
            if (assignment.getTitle().equals(exercise.getTitle())) {
                exercises.remove(assignment);
                return 3;
            }
        }
        return 2;
    } //--- Completed ---//

    public void printStudents() {
        if (students.isEmpty()) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println(RED + "There is no students in the course!\n" + RESET);
        } else {
            ArrayList<Student> students1 = new ArrayList<>(students.keySet());
            students1.sort((a, b) -> -students.get(a).compareTo(students.get(b)));

            System.out.print("\033[H\033[2J");
            System.out.flush();
            for (Student student : students1) {
                System.out.println(GREEN + student.getName() + " " + student.getLastname() + ", Score: "
                        + students.get(student) + RED);
            }
            System.out.println();
        }
    } //--- Completed ---//

    public int addScore(Student student, Double score) {
        for (Student s : students.keySet()) {
            if (s.getId().equals(student.getId())) {
                students.put(s, students.get(s) + score);
                return 3;
            }
        }
        return 0;
    } //--- Completed ---//

    public double getScore(Student student) {
        for (Student s : students.keySet()) {
            if (s.getId().equals(student.getId())) {
                return students.get(s);
            }
        }
        return -1;
    } //--- Completed ---//

    public String getTop() {
        Double d = students.values().stream().sorted().toList().getLast();
        for (Student s : students.keySet()) {
            if (students.get(s).equals(d)) {
                Student s1 = Main.getStudentFromDataBase(s.getId());
                return s1.getName() + "&" + s1.getLastname();
            }
        }
        return "";
    }
}