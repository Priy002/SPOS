package FCFS;

import java.util.*;

class FCFS {
    public static void main(String[] args) {
        int id[] = new int[20];
        int etime[] = new int[20];
        int stime[] = new int[20];
        int wtime[] = new int[20];
        int tat[] = new int[20];
        int total = 0, total1 = 0;
        float avg, avg1;
        Scanner sn = new Scanner(System.in);
        System.out.print("\nEnter the number of processes :");
        int n = sn.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.println();
            System.out.print("\nEnter the process ID of process" + (i + 1) + ":");
            id[i] = sn.nextInt();
            System.out.print("Enter the execution time of process" + (i + 1) + ":");
            etime[i] = sn.nextInt();
        }
        stime[0] = 0;
        for (int i = 1; i < n; i++) {
            stime[i] = stime[i - 1] + etime[i - 1];
        }
        wtime[0] = 0;
        for (int i = 0; i < n; i++) {
            wtime[i] = stime[i] - id[i];
            total = total + wtime[i];
        }
        for (int i = 0; i < n; i++) {
            tat[i] = wtime[i] + etime[i];
            total1 = total1 + tat[i];
        }
        avg = (float) total / n;
        avg1 = (float) total1 / n;
        System.out.println("\nArrival_time\tExecution_time\tService_time\tWait_time\tturn_around time");

        for (int i = 0; i < n; i++)

        {

            System.out.println(id[i] + "\t\t" + etime[i] + "\t\t" + stime[i] + "\t\t" + wtime[i] + "\t\t" + tat[i]);
        }
        System.out.println("\nAverage turn around time:" + avg1 + "\nAverage waittime:" + avg);
    }
}