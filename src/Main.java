import java.util.Scanner;

public class Main {

    static String RESET = "\u001B[0m";
    static String RED = "\u001B[31m";
    static String GREEN = "\u001B[32m";
    static String BLUE = "\u001B[34m";

    public static void main(String[] args) {
        System.out.print("\033[H\033[2J");
        System.out.flush();

        Scanner input = new Scanner(System.in);

        System.out.println(GREEN + "|------------------------- WELCOME ------------------------|" + RESET);

        out:
        while (true) {
            System.out.print(GREEN + """
                    |----------------- What is you're access? -----------------|
                    |---------- (admin = 1, teacher = 2, student = 3) ---------|
                    """ + RESET);
            int access = input.nextInt();

            switch (access) {
                case 1: {
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println(GREEN + "|---------------------- Welcome Admin ---------------------|" + RESET);

                    out1:
                    while (true) {
                        System.out.print(BLUE + """
                                Choose you're order:
                                1- Adding new teacher.
                                2- Removing a teacher
                                3- Adding new course.
                                4- Removing a course.
                                5- Adding a student to a class
                                6- Removing a student from a class
                                10- Exit.
                                """ + RESET);
                        int order = input.nextInt();

                        switch (order) {
                            case 1: {
                                break;
                            }
                            case 2: {
                                break;
                            }
                            case 3: {
                                break;
                            }
                            case 4: {
                                break;
                            }
                            case 5: {
                                break;
                            }
                            case 6: {
                                break;
                            }
                            case 10: {
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                                System.out.println(GREEN + "|------------------------- Exited -------------------------|" + RESET);
                                break out1;
                            }
                            default: {
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                                System.out.println(RED + "|---------------------- Wrong answer! ---------------------|" + RESET);
                            }
                        }
                    }
                    break out;
                }
                case 2: {
                    break out;
                }
                case 3: {
                    break out;
                }
                default: {
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println(RED + "|---------------------- Wrong answer! ---------------------|" + RESET);
                }
            }
        }
    }



    private static boolean userValidation(String username) {
        return true;
    }

    private static boolean passValidation(String pass) {
        return true;
    }

    private static boolean teacherIdValidation(String id) {
        return true;
    }

    private static void addTeacher(String username, String pass, String name, String lastname) {
    }
}
