// import java.util.Scanner;

// public class aboveaverage {
// public static void main(String[] args) {
// Scanner in = new Scanner(System.in);
// int numOfCases = in.nextInt();
// double[] perentages = new double[numOfCases];

// for (int i = 0; i < numOfCases; i++) {
// int numOfStudents = in.nextInt();

// // Save grades
// int[] grades = new int[numOfStudents];
// for (int j = 0; j < numOfStudents; j++) {
// grades[j] = in.nextInt();
// }

// double avgGrade = getAverageGrade(grades);

// // Count how many are above average
// double count = 0;
// for (int j =0; j < numOfStudents; j++) {
// if (grades[j] > avgGrade) {
// count++;
// }
// }

// // Perentage of students above average
// double percent = count / numOfStudents * 100;
// perentages[i] = percent;
// }

// // Output
// for (var percent : perentages) {
// System.out.printf("%.3f%%", percent);
// System.out.println();
// }
// in.close();
// }

// private static double getAverageGrade(int...grades) {
// double sum = 0;
// int count = 0;
// for (int grade : grades) {
// sum += grade;
// count++;
// }
// return sum / count;
// }

// }
