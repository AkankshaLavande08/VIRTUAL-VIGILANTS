import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Grade {
    private String courseName;
    private double score;

    public Grade(String courseName, double score) {
        this.courseName = courseName;
        this.score = score;
    }

    public String getCourseName() {
        return courseName;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}

class Student {
    private String name;
    private Map<String, List<Grade>> grades;

    public Student(String name) {
        this.name = name;
        this.grades = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void addGrade(String courseName, double score) {
        if (!grades.containsKey(courseName)) {
            grades.put(courseName, new ArrayList<>());
        }
        grades.get(courseName).add(new Grade(courseName, score));
    }

    public void editGrade(String courseName, double newScore) {
        if (grades.containsKey(courseName)) {
            for (Grade grade : grades.get(courseName)) {
                if (grade.getCourseName().equals(courseName)) {
                    grade.setScore(newScore);
                    break;
                }
            }
        }
    }

    public double calculateAverageGrade() {
        double total = 0;
        int count = 0;
        for (List<Grade> gradeList : grades.values()) {
            for (Grade grade : gradeList) {
                total += grade.getScore();
                count++;
            }
        }
        return count > 0 ? total / count : 0;
    }

    public void displayGrades() {
        System.out.println("Grades for " + name + ":");
        for (Map.Entry<String, List<Grade>> entry : grades.entrySet()) {
            System.out.print(entry.getKey() + ": ");
            for (Grade grade : entry.getValue()) {
                System.out.print(grade.getScore() + " ");
            }
            System.out.println();
        }
    }
}

public class GradeTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Student> students = new ArrayList<>();

        while (true) {
            System.out.println("\nGrade Tracker Menu:");
            System.out.println("1. Add Student");
            System.out.println("2. Add Grade");
            System.out.println("3. Edit Grade");
            System.out.println("4. Display Grades");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter student name: ");
                    String studentName = scanner.nextLine();
                    students.add(new Student(studentName));
                    break;
                case 2:
                    if (students.isEmpty()) {
                        System.out.println("No students added yet. Please add a student first.");
                        break;
                    }
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    Student student = findStudent(students, name);
                    if (student == null) {
                        System.out.println("Student not found.");
                        break;
                    }
                    System.out.print("Enter course name: ");
                    String courseName = scanner.nextLine();
                    System.out.print("Enter grade: ");
                    double grade = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    student.addGrade(courseName, grade);
                    break;
                case 3:
                    if (students.isEmpty()) {
                        System.out.println("No students added yet. Please add a student first.");
                        break;
                    }
                    System.out.print("Enter student name: ");
                    String studentNameEdit = scanner.nextLine();
                    Student studentEdit = findStudent(students, studentNameEdit);
                    if (studentEdit == null) {
                        System.out.println("Student not found.");
                        break;
                    }
                    System.out.print("Enter course name: ");
                    String courseNameEdit = scanner.nextLine();
                    System.out.print("Enter new grade: ");
                    double newGrade = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    studentEdit.editGrade(courseNameEdit, newGrade);
                    break;
                case 4:
                    if (students.isEmpty()) {
                        System.out.println("No students added yet. Please add a student first.");
                        break;
                    }
                    for (Student s : students) {
                        s.displayGrades();
                        System.out.println("Average grade: " + s.calculateAverageGrade());
                    }
                    break;
                case 5:
                    System.out.println("Thank you for using the Grade Tracker. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static Student findStudent(List<Student> students, String name) {
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(name)) {
                return student;
            }
        }
        return null;
    }
}
