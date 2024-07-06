import java.io.*;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Formatter;
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
                            dos.writeUTF("1-" + Main.getStudentFromDataBase(orderParts[1]).getName() +
                             "-" + Main.getStudentFromDataBase(orderParts[1]).getLastname());;
                        } else {
                            dos.writeBytes("2");
                        }
                    } else {
                        dos.writeBytes("3");
                    }
                    break;
                }
                case "newId": {
                    if (Main.findStudentId(orderParts[1])) {
                        dos.writeBytes("invalid");
                    } else {
                        dos.writeBytes("valid");
                    }
                    break;
                }
                case "signup": {
                    Main.addStudent(orderParts[1], orderParts[2], orderParts[3], orderParts[4]);
                    dos.writeBytes(Main.getStudentFromDataBase(orderParts[1]).getName() +
                            "-" + Main.getStudentFromDataBase(orderParts[1]).getLastname());
                    break;
                }
                case "getUnitsUser": {
                    dos.writeBytes(String.valueOf(Main.getStudentFromDataBase(orderParts[1]).getUnits()));
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
                    dos.writeBytes(average);
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
                    String bestScore = String.valueOf(student.getCourses().stream().
                            map(c -> Main.getCourseFromDataBase(c.getName())).map(c -> c.getScore(student)).
                            reduce((a, b) -> (a > b) ? a : b).orElse(0.0));
                    String worstScore = String.valueOf(student.getCourses().stream().
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
                    System.out.println(bestScore + "-" + worstScore);
                    dos.writeBytes(bestScore + "-" + worstScore);
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
                        out.append(e.getTitle()).append("*").append(e.getCourse().getName()).append("*").
                                append(e.getDeadLine()).append("*").append(e.isDone(orderParts[1])).append(" ");
                    }
                    if (!out.isEmpty()) {
                        out = new StringBuilder(out.substring(0, out.length() - 1));
                        dos.writeBytes(out.toString());
                    }
                    else {
                        dos.writeBytes("Empty");                    }
                    break;
                }
                case "exeDone": {
                    Assignment exercise = Main.getExerciseFromDataBase(orderParts[3], orderParts[2]);
                    exercise.exeDone(orderParts[1]);
                    Main.updateExercise(exercise);
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
                    System.out.println(out);

                    if (!out.isEmpty()) {
                        out = new StringBuilder(out.substring(0, out.length() - 1));
                        dos.writeBytes(out.toString());
                    }
                    else {
                        dos.writeBytes("Empty");                    }
                    break;
                }
                case "addCourse": {
                    if (!Main.findCourseName(orderParts[2])) {
                        dos.writeBytes("Empty");
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
                    dos.writeBytes(out.toString());
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
                        dos.writeBytes(out.substring(0, out.length() - 2));
                    else {
                        dos.writeBytes("Empty");
                    }
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

