package com.company;
import java.util.Arrays;
import java.util.Scanner;

public class DetermineTheShortestPath {
    private double[][] customDistancesAndNumberOfStations () {
        System.out.println("How many stations does Zone 2 have: ");
        Scanner input  = new Scanner(System.in);
        int n1 = input.nextInt();
        System.out.println("How many stations does Zone 3 have: ");
        int n2 = input.nextInt();
        final int numberOfAllStations = 2+n1+n2;
        double[][] distances = new double[numberOfAllStations][numberOfAllStations];

        System.out.println("Please enter the distances for all the stations: ");

        for(int i=0; i<numberOfAllStations; i++) {
            for(int j=0; j<numberOfAllStations; j++) {
                if(i<j && ((i==0 && j>0 && j<=n1) || (i==numberOfAllStations-1 && j>n1 && j<numberOfAllStations-1 ) ||
                        (i>0 && i<=n1 && (j==0 || j>n1 && j<numberOfAllStations-1)) ||
                        (i>n1 && i!=numberOfAllStations-1 && (j==numberOfAllStations-1 || j>0 && j<=n1)) ||
                        (numberOfAllStations==2 && ((i==0 && j==1) || (i==1 && j==0))))) { //handles when its allowed to enter the distance

                    String station1 = "";
                    String station2 = "";
                    if(i==0)
                        station1 = "X";
                    else if (i==numberOfAllStations-1)
                        station1 = "Y";
                    else if (i>0 && i<=n1) {
                        StringBuilder sb = new StringBuilder("U").append(String.valueOf(i));
                        station1 = sb.toString();
                    }
                    else if (i>n1 && i<=numberOfAllStations-1) {
                        StringBuilder sb = new StringBuilder("V").append(String.valueOf(i-n1));
                        station1 = sb.toString();
                    }

                    if(j==0)
                        station2 = "X";
                    else if (j==numberOfAllStations-1)
                        station2 = "Y";
                    else if (j>0 && j<=n1) {
                        StringBuilder sb = new StringBuilder("U").append(String.valueOf(j));
                        station2 = sb.toString();
                    }
                    else if (j>n1 && j<=numberOfAllStations-1) {
                        StringBuilder sb = new StringBuilder("V").append(String.valueOf(j-n1));
                        station2 = sb.toString();
                    }

                    System.out.print("Distance between station " + station1 + " and station " + station2 + ":" );
                    distances[i][j] = input.nextInt();
                    distances[j][i] = distances[i][j]; // filling it in symmetrically
                }

            }
        }

        return distances;
    }

    private static String printOutStations(int[] stations) {
        String stationsString = "";
        for(int i = 0 ; i<stations.length; i++) {
            if(stations[i]!=-1 && i!=0 && i!= stations.length-1) {
                switch(stations[i]) {
                    case 0:
                        stationsString+=" X ";
                        break;
                    case 1:
                        stationsString+=" U1 ";
                        break;
                    case 2:
                        stationsString+=" U2 ";
                        break;
                    case 3:
                        stationsString+=" U3 ";
                        break;
                    case 4:
                        stationsString+=" V1 ";
                        break;
                    case 5:
                        stationsString+=" V2 ";
                        break;
                    case 6:
                        stationsString+=" V3 ";
                        break;
                    case 7:
                        stationsString+=" V4 ";
                        break;
                    case 8:
                        stationsString+=" Y ";
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + stations[i]);
                }
            }

        }

        return stationsString;
    }

    public static void main(String[] args) {
	// write your code here
        double [][] distances = { // if u enter them like this, make sure they are symmetrical
                {0,1,2,3,0,0,0,0,0}, //X
                {1,0,0,0,1,2,3,4,0}, //U1
                {2,0,0,0,2,3,4,1,0}, //u2
                {3,0,0,0,3,4,1,2,0}, //U3
                {0,1,2,3,0,0,0,0,1}, //V1
                {0,2,3,4,0,0,0,0,2}, //V2
                {0,3,4,1,0,0,0,0,3}, //V3
                {0,4,1,2,0,0,0,0,4}, //V4
                {0,0,0,0,1,2,3,4,0}, //Y
        };


        Scanner input = new Scanner(System.in);
        System.out.println("From which station do you want to travel from: ");
        String current = input.nextLine();
        double [] currentRow;
        System.out.println("To which station do you want to travel to: ");
        String destination = input.nextLine();
        double [] destinationRow;

        switch(current) {
            case "X":
            case "x":
                currentRow = distances[0];
                break;
            case "U1":
            case "u1":
                currentRow = distances[1];
                break;
            case "U2":
            case "u2":
                currentRow = distances[2];
                break;
            case "U3":
            case "u3":
                currentRow = distances[3];
                break;
            case "V1":
            case "v1":
                currentRow = distances[4];
                break;
            case "V2":
            case "v2":
                currentRow = distances[5];
                break;
            case "V3":
            case "v3":
                currentRow = distances[6];
                break;
            case "V4":
            case "v4":
                currentRow = distances[7];
                break;
            case "Y":
            case "y":
                currentRow = distances[8];
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + current);
        }
        switch(destination) {
            case "X":
            case "x":
                destinationRow = distances[0];
                break;
            case "U1":
            case "u1":
                destinationRow = distances[1];
                break;
            case "U2":
            case "u2":
                destinationRow = distances[2];
                break;
            case "U3":
            case "u3":
                destinationRow = distances[3];
                break;
            case "V1":
            case "v1":
                destinationRow = distances[4];
                break;
            case "V2":
            case "v2":
                destinationRow = distances[5];
                break;
            case "V3":
            case "v3":
                destinationRow = distances[6];
                break;
            case "V4":
            case "v4":
                destinationRow = distances[7];
                break;
            case "Y":
            case "y":
                destinationRow = distances[8];
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + destination);
        }

        try{
            System.out.println("Stations along the shortest path between " +current + " and " + destination + " is through stations: " + printOutStations(TheShortestPath.intermediateStations(currentRow,distances,destinationRow)));
            System.out.println("The lenght of the shortest path is: " + TheShortestPath.length(currentRow,distances,destinationRow) + " units long.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
