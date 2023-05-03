package com.company;
import  java.util .*;


public class Temperatures {

    public static double[] getMaxWeeklyValueIn2dArray (double [][] temperatures, int nrWeeks, int nrMeasurements) {
        double [] max = new double[nrWeeks+1];

        for(int i=1 ; i<nrWeeks+1; i++) {
            double maxForTheWeek = temperatures[i][1];
                for(int j=1; j<nrMeasurements+1; j++) {
                    if (maxForTheWeek < temperatures[i][j])
                        maxForTheWeek=temperatures[i][j];
                }
                    max[i] = maxForTheWeek;
        }
        return max;
    }

    public static double [] getMinWeeklyValueIn2dArray (double [][] temperatures,  int nrWeeks, int nrMeasurements) {
        double [] min = new double[nrWeeks+1];

        for(int i=1 ; i<nrWeeks+1; i++) {
            double minForTheWeek = temperatures[i][1];
            for(int j=1; j<nrMeasurements+1; j++) {
                if (minForTheWeek > temperatures[i][j])
                    minForTheWeek=temperatures[i][j];
            }
            min[i] = minForTheWeek;
        }
        return min;
    }

    public static double[] getAvgWeeklyValueIn2dArray (double [][] temperatures,  int nrWeeks, int nrMeasurements) {
        double [] avg = new double[nrWeeks+1];

        for(int i=1 ; i<nrWeeks+1; i++) {
            double sumOfTheWeek = 0;
                for(int j=1; j<nrMeasurements+1; j++) {
                   sumOfTheWeek+=temperatures[i][j];
                }
                    avg[i] = sumOfTheWeek/nrMeasurements;
        }
        return avg;
    }

    private static double getSumOfAllTemperatures(double[] weeklySums) {
        double sum = 0;
        /*for(int i = 1; i<nofWeeks+1; i++) {
            for(int j=1; j<nofMeasurementsPerWeek+1; j++) {
                sum+=temperatures[i][j];
            }
        }*/
        for(int i=1; i<weeklySums.length; i++)
            sum+=weeklySums[i];
        return sum;
    }

    private static double[] getWeeklySumValueIn2dArray(double[][] temperatures, int nrWeeks, int nrMeasurements) {
        double [] sum = new double[nrWeeks+1];

        for(int i=1 ; i<nrWeeks+1; i++) {
            double sumForTheWeek = 0;
            for(int j=1; j<nrMeasurements+1; j++) {
                sumForTheWeek+=temperatures[i][j];
            }
            sum[i] = sumForTheWeek;
        }
        return sum;
    }

    private static void printArray(double[] temperatures, int nofMeasurementsPerWeek) {
        for(int i=1; i<nofMeasurementsPerWeek; i++)
            System.out.print(temperatures[i] + " ");
        System.out.println();
    }

    public static void main(String[] args) {
	// write your code here
        System.out.println ("TEMPERATURES\n");
// input tools
        Scanner in = new Scanner (System.in);
        in.useLocale (Locale.US);
// enter the number of weeks and measurements
        System.out.print ("number of weeks: ");
        int nofWeeks = in.nextInt ();

        System.out.print ("number of measurements per week: ");
        int nofMeasurementsPerWeek = in.nextInt ();
// storage space for temperature data
        double [][] t = new double[nofWeeks + 1][ nofMeasurementsPerWeek + 1];
// read the temperatures
        for (int week = 1; week <= nofWeeks; week ++)
        {
            System.out.println ("temperatures - week " + week + ":");
            for (int reading = 1; reading <= nofMeasurementsPerWeek; reading ++)
                t[week][ reading] = in.nextDouble ();
        }
        System.out.println ();
// show the temperatures
        System.out.println ("the temperatures:");
        for (int week = 1; week <= nofWeeks; week ++)
        {
            for (int reading = 1; reading <= nofMeasurementsPerWeek; reading ++)
                System.out.print (t[week][ reading] + " ");
            System.out.println ();
        }
        System.out.println ();
// the least , greatest and average temperature - weekly
        double [] minT = new double[nofWeeks + 1];
        double [] maxT = new double[nofWeeks + 1];
        double [] sumT = new double[nofWeeks + 1];
        double [] avgT = new double[nofWeeks + 1];
// compute and store the least , greatest and average
// temperature for each week.
// *** WRITE YOUR CODE HERE ***
        minT = getMinWeeklyValueIn2dArray (t, nofWeeks, nofMeasurementsPerWeek);
        maxT = getMaxWeeklyValueIn2dArray(t, nofWeeks, nofMeasurementsPerWeek);
        sumT = getWeeklySumValueIn2dArray(t, nofWeeks, nofMeasurementsPerWeek);
        avgT = getAvgWeeklyValueIn2dArray(t, nofWeeks, nofMeasurementsPerWeek);
// show the least , greatest and average temperature for
// each week
// *** WRITE YOUR CODE HERE ***
        System.out.println("Min values for each week:");
        printArray(minT, nofMeasurementsPerWeek);
        System.out.println("Max values for each week:");
        printArray(maxT, nofMeasurementsPerWeek);
        System.out.println("Sum of the week:");
        printArray(sumT, nofMeasurementsPerWeek);
        System.out.println("Avg values for each week:");
        printArray(avgT, nofMeasurementsPerWeek);
// the least , greatest and average temperature - whole period
// compute and store the least , greatest and average
// temperature for the whole period
// *** WRITE YOUR CODE HERE ***
        double [] minTCopy = minT; //copying just in case we still have to use the original array
        double [] maxTCopy = maxT;
        Arrays.sort(minTCopy);
        Arrays.sort(maxTCopy);
        double minTemp = minTCopy[1];
        double maxTemp = maxTCopy[nofWeeks];
        double sumAllTemp = getSumOfAllTemperatures(sumT);
        double avgTemp = sumAllTemp/(nofMeasurementsPerWeek*nofWeeks);
// show the least , greatest and average temperature for
// the whole period
// *** WRITE YOUR CODE HERE
        System.out.println("Min value overall:");
        System.out.println(minTemp);
        System.out.println("Max value overall:");
        System.out.println(maxTemp);
        System.out.println("Sum of all temperatures:");
        System.out.println(sumAllTemp);
        System.out.println("Avg value overall:");
        System.out.println(avgTemp);
    }
}
