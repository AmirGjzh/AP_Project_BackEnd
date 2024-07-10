import java.io.*;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(1384)) {
            System.out.println("Server started with IP = " + Inet4Address.getLocalHost().getHostAddress());

            while (true) {
                System.out.println("Waiting for client ...");
                new ClientHandler(serverSocket.accept()).start();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler extends Thread {
    String RESET = "\u001B[0m";
    String RED = "\u001B[31m";
    Socket socket;
    DataOutputStream dos;
    DataInputStream dis;

    public ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        dos = new DataOutputStream(socket.getOutputStream());
        dis = new DataInputStream(socket.getInputStream());
        System.out.println("Connected");
    }

    @Override
    public void run() {
        try {
            StringBuilder order = new StringBuilder();
            System.out.println("Listening ...");
            int index = dis.read();
            while (index != 0) {
                order.append((char) index);
                index = dis.read();
            }
            System.out.println("Order received => " + order);

            String[] orderParts = order.toString().split("-");

            switch (orderParts[0]) {
                case "login": {
                    if (Main.findStudentId(orderParts[1])) {
                        if (Main.getStudentFromDataBase(orderParts[1]).getPassword().equals(orderParts[2])) {
                            System.out.println(RED + "1-" + Main.getStudentFromDataBase(orderParts[1]).getName() +
                                    "-" + Main.getStudentFromDataBase(orderParts[1]).getLastname() + RESET);
                            dos.writeUTF("1-" + Main.getStudentFromDataBase(orderParts[1]).getName() +
                             "-" + Main.getStudentFromDataBase(orderParts[1]).getLastname());
                        } else {
                            System.out.println(RED + "2" + RESET);
                            dos.writeUTF("2");
                        }
                    } else {
                        System.out.println(RED + "3" + RESET);
                        dos.writeUTF("3");
                    }
                    break;
                }
                case "newId": {
                    if (Main.findStudentId(orderParts[1])) {
                        System.out.println(RED + "invalid" + RESET);
                        dos.writeUTF("invalid");
                    } else {
                        System.out.println(RED + "valid" + RESET);
                        dos.writeUTF("valid");
                    }
                    break;
                }
                case "signup": {
                    Main.addStudent(orderParts[1], orderParts[2], orderParts[3], orderParts[4], orderParts[5]);
                    System.out.println(RED + Main.getStudentFromDataBase(orderParts[1]).getName() +
                            "-" + Main.getStudentFromDataBase(orderParts[1]).getLastname() + RESET);
                    dos.writeUTF(Main.getStudentFromDataBase(orderParts[1]).getName() +
                            "-" + Main.getStudentFromDataBase(orderParts[1]).getLastname());
                    break;
                }
                case "getUnitsUser": {
                    System.out.println(RED + String.valueOf(Main.getStudentFromDataBase(orderParts[1]).getUnits()) + RESET);
                    dos.writeUTF(String.valueOf(Main.getStudentFromDataBase(orderParts[1]).getUnits()));
                    break;
                }
                case "getAverage": {
                    String average = String.valueOf(Main.getStudentFromDataBase(orderParts[1]).printTotalAverage());
                    if (average.charAt(1) == '.') {
                        average = "0" + average;
                    }
                    if (average.length() == 4) {
                        average = "" + average.charAt(0) + average.charAt(1) + average.charAt(2) + "0" + average.charAt(3);
                    }
                    System.out.println(RED + average + RESET);
                    dos.writeUTF(average);
                    break;
                }
                case "updateName": {
                    Student student = Main.getStudentFromDataBase(orderParts[1]);
                    student.setName(orderParts[2]);
                    Main.updateStudent(student);
                    break;
                }
                case "updateLastname": {
                    Student student = Main.getStudentFromDataBase(orderParts[1]);
                    student.setLastname(orderParts[2]);
                    Main.updateStudent(student);
                    break;
                }
                case "updatePassword": {
                    Student student = Main.getStudentFromDataBase(orderParts[1]);
                    student.setPassword(orderParts[2]);
                    Main.updateStudent(student);
                    break;
                }
                case "removeStudent": {
                    Main.removeStudent(orderParts[1]);
                    break;
                }
                case "getScores": {
                    Student student = Main.getStudentFromDataBase(orderParts[1]);
                    String bestScore = String.format("%4.2f", student.getCourses().stream().
                            map(c -> Main.getCourseFromDataBase(c.getName())).map(c -> c.getScore(student)).
                            reduce((a, b) -> (a > b) ? a : b).orElse(0.0));
                    String worstScore = String.format("%4.2f", student.getCourses().stream().
                            map(c -> Main.getCourseFromDataBase(c.getName())).map(c -> c.getScore(student)).
                            reduce((a, b) -> (a > b) ? b : a).orElse(0.0));

                    if (bestScore.charAt(1) == '.') {
                        bestScore = "0" + bestScore;
                    }
                    if (bestScore.length() == 4) {
                        bestScore = "" + bestScore.charAt(0) + bestScore.charAt(1) + bestScore.charAt(2) + "0" + bestScore.charAt(3);
                    }

                    if (worstScore.charAt(1) == '.') {
                        worstScore = "0" + worstScore;
                    }
                    if (worstScore.length() == 4) {
                        worstScore = "" + worstScore.charAt(0) + worstScore.charAt(1) + worstScore.charAt(2) + "0" + worstScore.charAt(3);
                    }
                    System.out.println(RED + bestScore + "-" + worstScore + RESET);
                    dos.writeUTF(bestScore + "-" + worstScore);
                    break;
                }
                case "getExams": {
                    System.out.println(RED + Main.getStudentFromDataBase(orderParts[1]).getCourses().stream().
                            map(c -> Main.getCourseFromDataBase(c.getName())).filter(c -> c.getExamDate() <= 7).map(c -> 1).
                            reduce(Integer::sum).orElse(0) + RESET);
                    dos.writeUTF(String.valueOf(Main.getStudentFromDataBase(orderParts[1]).getCourses().stream().
                            map(c -> Main.getCourseFromDataBase(c.getName())).filter(c -> c.getExamDate() <= 7).map(c -> 1).
                            reduce(Integer::sum).orElse(0)));
                    break;
                }
                case "getExercises": {
                    Student student = Main.getStudentFromDataBase(orderParts[1]);
                    StringBuilder out = new StringBuilder();
                    ArrayList<Assignment> exercises = new ArrayList<>();
                    for (Course course : student.getCourses()) {
                        Course c = Main.getCourseFromDataBase(course.getName());
                        for (Assignment e : c.getExercises()) {
                            exercises.add(Main.getExerciseFromDataBase(e.getTitle(), c.getName()));
                        }
                    }
                    for (Assignment e : exercises) {
                        String temp = "";
                        Scanner scanner = new Scanner(new FileInputStream(Main.dataBaseUrl + "\\Courses\\" +
                                e.getCourse().getName() + "\\" + e.getTitle() + "-d.txt"));
                        while (scanner.hasNext()) {
                            String temp2 = scanner.nextLine();
                            if (temp2.startsWith(orderParts[1])) {
                                temp = "*" + temp2.split("\\$\\$")[1] + "*" + temp2.split("\\$\\$")[2] + "*"
                                + temp2.split("\\$\\$")[3] ;
                            }
                        }
                        out.append(e.getTitle()).append("*").append(e.getCourse().getName()).append("*").
                                append(e.getDeadLine()).append("*").append(e.isDone(orderParts[1])).append(temp).append("  ");
                    }
                    if (!out.isEmpty()) {
                        out = new StringBuilder(out.substring(0, out.length() - 1));
                        System.out.println(RED + out + RESET);
                        dos.writeUTF(out.toString());
                    }
                    else {
                        System.out.println(RED + "Empty" + RESET);
                        dos.writeUTF("Empty");
                    }
                    break;
                }
                case "exeDone": {
                    Assignment exercise = Main.getExerciseFromDataBase(orderParts[3], orderParts[2]);
                    exercise.exeDone(orderParts[1]);
                    Main.updateExercise(exercise);
                    break;
                }
                case "exeRecord": {
                    Scanner scanner = new Scanner(new FileInputStream(Main.dataBaseUrl + "\\Courses\\" +
                            orderParts[2] + "\\" + orderParts[3] + "-d.txt"));
                    String temp = "";
                    while (scanner.hasNext()) {
                        String temp2 = scanner.nextLine();
                        if (!temp2.startsWith(orderParts[1])) {
                            temp2 += temp2 + "\n";
                        }
                    }
                    temp += orderParts[1] + "$$" + orderParts[4] + "$$" + orderParts[5] + "$$" + orderParts[6];
                    Formatter formatter = new Formatter(new FileOutputStream(Main.dataBaseUrl + "\\Courses\\" +
                            orderParts[2] + "\\" + orderParts[3] + "-d.txt"));
                    formatter.format(temp);
                    formatter.close();
                    scanner.close();
                    break;
                }
                case "getClasses": {
                    Student student = Main.getStudentFromDataBase(orderParts[1]);
                    StringBuilder out = new StringBuilder();
                    for (Course course : student.getCourses()) {
                        Course c = Main.getCourseFromDataBase(course.getName());
                        out.append(c.getName()).append("*").append(c.getTeacher().getName()).append("*").
                                append(c.getTeacher().getLastname()).append("*").append(c.getUnits()).append("*").
                                append(c.getExercises().stream().map(e -> Main.getExerciseFromDataBase(e.getTitle(), course.getName())).
                                        filter(e -> !e.isDone(orderParts[1])).count()).append("*").
                                append(c.getTop()).append(" ");
                    }

                    if (!out.isEmpty()) {
                        out = new StringBuilder(out.substring(0, out.length() - 1));
                        System.out.println(RED + out + RESET);
                        dos.writeUTF(out.toString());
                    }
                    else {
                        System.out.println(RED + "Empty" + RESET);
                        dos.writeUTF("Empty");
                    }
                    break;
                }
                case "addCourse": {
                    if (!Main.findCourseName(orderParts[2])) {
                        System.out.println(RED + "Empty" + RESET);
                        dos.writeUTF("Empty");
                        break;
                    }
                    Student student = Main.getStudentFromDataBase(orderParts[1]);
                    Course course = Main.getCourseFromDataBase(orderParts[2]);
                    Teacher teacher = Main.getTeacherFromDataBase(course.getTeacher().getUsername());

                    teacher.addStudentToCourse(student, course);

                    Main.updateStudent(student);
                    Main.updateCourse(course);
                    Main.updateTeacher(teacher);

                    StringBuilder out = new StringBuilder();
                    out.append(course.getName()).append("*").append(course.getTeacher().getName()).append("*").
                            append(course.getTeacher().getLastname()).append("*").append(course.getUnits()).append("*").
                            append(course.getExercises().stream().map(e -> Main.getExerciseFromDataBase(e.getTitle(), course.getName())).
                                    filter(e -> !e.isDone(orderParts[1])).count()).append("*").
                            append(course.getTop()).append(" ");
                    System.out.println(out);
                    out = new StringBuilder(out.substring(0, out.length() - 1));
                    System.out.println(RED + out + RESET);
                    dos.writeUTF(out.toString());
                    break;
                }
                case "addTask": {
                    Formatter formatter = new Formatter(new FileOutputStream(Main.dataBaseUrl + "\\Tasks\\" + orderParts[1] + ".txt", true));
                    formatter.format(order.substring(9 + orderParts[1].length()) + "\n");
                    formatter.close();
                    break;
                }
                case "getTasks": {
                    Scanner scanner = new Scanner(new FileInputStream(Main.dataBaseUrl + "\\Tasks\\" + orderParts[1] + ".txt"));
                    StringBuilder out = new StringBuilder();
                    while (scanner.hasNext()) {
                        out.append(scanner.nextLine()).append("$$");
                    }
                    if (!out.isEmpty())
                        dos.writeUTF(out.substring(0, out.length() - 2));
                    else {
                        dos.writeUTF("Empty");
                    }
                    scanner.close();
                    break;
                }
                case "taskDone": {
                    Scanner scanner = new Scanner(new FileInputStream(Main.dataBaseUrl + "\\Tasks\\" + orderParts[1] + ".txt"));
                    StringBuilder out = new StringBuilder();
                    while (scanner.hasNext()) {
                        String temp = scanner.nextLine();
                        if (temp.startsWith(orderParts[2])) {
                            temp = temp.substring(0, temp.length() - 5);
                            temp = temp + "true";
                        }
                        out.append(temp).append("\n");
                    }
                    Formatter formatter = new Formatter(new FileOutputStream(Main.dataBaseUrl + "\\Tasks\\" + orderParts[1] + ".txt"));
                    formatter.format(String.valueOf(out));
                    formatter.close();
                    scanner.close();
                    break;
                }
                case "getDates": {
                    int day = LocalDate.now().getDayOfMonth();
                    int month = LocalDate.now().getMonthValue();
                    Scanner scanner = new Scanner(new FileInputStream(Main.dataBaseUrl + "\\BirthDates.txt"));
                    StringBuilder out = new StringBuilder();
                    while (scanner.hasNext()) {
                        String temp = scanner.nextLine();
                        if (Objects.equals(temp.split("-")[1], String.valueOf(day)) && Objects.equals(temp.split("-")[2], String.valueOf(month))) {
                            out.append(Main.getStudentFromDataBase(temp.split("-")[0]).getName()).append("-").append(Main.getStudentFromDataBase(temp.split("-")[0]).getLastname()).append("$");
                        }
                    }
                    scanner.close();
                    if (out.isEmpty()) {
                        System.out.println(RED + "Empty" + RESET);
                        dos.writeUTF("Empty");
                    }
                    else {
                        out = new StringBuilder(out.substring(0, out.length() - 1));
                        System.out.println(RED + out + RESET);
                        dos.writeUTF(String.valueOf(out));
                    }
                    break;
                }
                case "getChanges": {
                    Student student = Main.getStudentFromDataBase(orderParts[1]);
                    StringBuilder out = new StringBuilder();
                    ArrayList<Assignment> exercises = new ArrayList<>();
                    for (Course course : student.getCourses()) {
                        Course c = Main.getCourseFromDataBase(course.getName());
                        for (Assignment e : c.getExercises()) {
                            if (Main.getExerciseFromDataBase(e.getTitle(), c.getName()).hasChanged())
                                if (!Main.getExerciseFromDataBase(e.getTitle(), c.getName()).isDone(orderParts[1]))
                                    exercises.add(Main.getExerciseFromDataBase(e.getTitle(), c.getName()));
                        }
                    }
                    for (Assignment e : exercises) {
                        out.append(e.getTitle()).append("-").append(e.getCourse().getName()).append("-").
                                append(e.getDeadLine()).append("$");
                    }
                    if (out.isEmpty()) {
                        System.out.println(RED + "Empty" + RESET);
                        dos.writeUTF("Empty");
                    }
                    else {
                        out = new StringBuilder(out.substring(0, out.length() - 1));
                        System.out.println(RED + out + RESET);
                        dos.writeUTF(String.valueOf(out));
                    }
                }
                break;
            }

            socket.close();
            dos.close();
            dis.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

