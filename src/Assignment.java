import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Assignment implements Serializable {
    @Serial
    private static final long serialVersionUID = 0;

    private final String title;

    private final Course course;

    private int deadLine;

    private final Map<Student, Boolean> isDone = new HashMap<>();

//----------------------------------------------------------------------------------------------------------------------

    public Assignment(String title, Course course, int deadLine) {
        this.title = title;
        this.course = course;
        this.deadLine = deadLine;
    }

    public String getTitle() {
        return title;
    }

    public Map<Student, Boolean> getIsDone() {
        return isDone;
    }

    public Course getCourse() {
        return course;
    }

    public int getDeadLine() {
        return deadLine;
    }

    public int setDeadLine(int deadLine) {
        this.deadLine = deadLine;
        return 2;
    }

    public void addStudents(List<Student> students) {
        for (Student s : students) {
            isDone.put(s, false);
        }
    }

    public void removeStudents(List<Student> students) {
        for (Student s : students) {
            for (Student s1 : isDone.keySet()) {
                if (s1.getId().equals(s.getId())) {
                    isDone.remove(s1);
                    break;
                }
            }
        }
    }

    public void exeDone(String id) {
        System.out.println(isDone.size());
        for (Student s: isDone.keySet()) {
            System.out.println(s.getId());
            if (s.getId().equals(id)) {
                isDone.put(s, true);
                System.out.println("lll");
                break;
            }
        }
    }

    public boolean isDone(String id) {
        for (Student s: isDone.keySet()) {
            if (s.getId().equals(id)) {
                return isDone.get(s);
            }
        }
        return false;
    }
}