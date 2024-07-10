import java.io.Serial;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Assignment implements Serializable {
    @Serial
    private static final long serialVersionUID = 0;

    private final String title;

    private final Course course;

    private LocalDateTime deadLine;

    private LocalDateTime lastDeadline;

    private final Map<Student, Boolean> isDone = new HashMap<>();

//----------------------------------------------------------------------------------------------------------------------

    public Assignment(String title, Course course, LocalDateTime deadLine) {
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

    public long getDeadLine() {
        return Duration.between(LocalDateTime.now(), deadLine).toMinutes();
    }

    public long getLastDeadLine() {
        return Duration.between(LocalDateTime.now(), lastDeadline).toMinutes();
    }

    public int setDeadLine(LocalDateTime deadLine) {
        lastDeadline = this.deadLine;
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
        for (Student s: isDone.keySet()) {
            if (s.getId().equals(id)) {
                isDone.put(s, true);
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

    public boolean hasChanged() {
        return lastDeadline != null;
    }
}