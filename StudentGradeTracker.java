import java.util.ArrayList;
import java.util.Scanner;

public class StudentGradeTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Double> grades = new ArrayList<>();
        
        System.out.println("=== CodeAlpha Student Grade Tracker ===");
        System.out.println("Enter student grades. Type -1 when you are finished:");
        
        // Loop to take user input
        while (true) {
            System.out.print("Enter grade: ");
            double grade = scanner.nextDouble();
            
            if (grade == -1) {
                break;
            }
            
            if (grade >= 0 && grade <= 100) {
                grades.add(grade);
            } else {
                System.out.println("Invalid entry. Please enter a grade between 0 and 100.");
            }
        }
        
        // Process data if the list isn't empty[cite: 1]
        if (!grades.isEmpty()) {
            double total = 0;
            double highest = grades.get(0);
            double lowest = grades.get(0);
            
            for (double g : grades) {
                total += g;
                if (g > highest) highest = g;
                if (g < lowest) lowest = g;
            }
            
            double average = total / grades.size();
            
            // Output summary report[cite: 1]
            System.out.println("\n--- Performance Summary Report ---");
            System.out.println("Total Students Registered: " + grades.size());
            System.out.printf("Average Score: %.2f\n", average);
            System.out.println("Highest Score: " + highest);
            System.out.println("Lowest Score: " + lowest);
        } else {
            System.out.println("No grades were entered.");
        }
        
        scanner.close();
    }
}