package com.company;

import java.util.Arrays;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

class TheShortestPath {
// The method intermediateStations returns a vector of the
// intermediate stations that are on the shortest path.
// The ordinal number of the first station is located in
// index 1 of the vector , and the second station on index 2.
public static int [] intermediateStations (double [] a, double [][] b, double [] c) throws IllegalStateException {
    int [] stations = new int[4];
    int currentStation = 0, destination = 0, stationsPassed = 0;
    for(int i=0; i<b.length; i++) {
        if(b[i]==a)
            currentStation = i;
        if(b[i]==c)
            destination = i;
    }

    if(currentStation==destination) //going to the same station
        throw new IllegalStateException("Error, no such path.");

    int currentZone, destinationZone;
    switch(currentStation) {
        case 0:
            currentZone = 1;
            break;
        case 1:
            currentZone = 2;
            break;
        case 2:
            currentZone = 2;
            break;
        case 3:
            currentZone = 2;
            break;
        case 4:
            currentZone = 3;
            break;
        case 5:
            currentZone = 3;
            break;
        case 6:
            currentZone = 3;
            break;
        case 7:
            currentZone = 3;
            break;
        case 8:
            currentZone = 4;
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + currentStation);
    }
    switch(destination) {
        case 0:
            destinationZone = 1;
            break;
        case 1:
            destinationZone = 2;
            break;
        case 2:
            destinationZone = 2;
            break;
        case 3:
            destinationZone = 2;
            break;
        case 4:
            destinationZone = 3;
            break;
        case 5:
            destinationZone = 3;
            break;
        case 6:
            destinationZone = 3;
            break;
        case 7:
            destinationZone = 3;
            break;
        case 8:
            destinationZone = 4;
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + destination);
    }
    if(currentZone==destinationZone) //going to the same zone
        throw new IllegalStateException("Error, no such path.");

    boolean swappedValues = false;
    if(currentZone>destinationZone) {
        currentZone = currentZone + destinationZone - (destinationZone = currentZone); //swapping the values so i dont have to keep track of asc of desc
        swappedValues = true;
    }

    int zonesToPass = destinationZone-currentZone;


    int tempIndex = 2;
    if(!swappedValues) {
        stations[0] = currentStation; //and the indexing starts from 0
        tempIndex = 1;
    } else {
        stations[3] = currentStation;
    }



    for(int i=currentZone; i<destinationZone; i++){
        int stationIndex = currentStation;
        double[] searchArray = b[stationIndex];
        double min = 0;

        for(int k=0; k<searchArray.length; k++) { // we find the first non zero value in the row
            if(searchArray[k]!=0) {
                min=searchArray[k];
                currentStation = k;
                break;
            }
        }
        
        for(int j=0; j<searchArray.length; j++) {
            if(currentZone==1 && j>=1 && j<=3 && ((j<currentStation && swappedValues) || (j>currentStation && !swappedValues) ) && searchArray[j]<=min && searchArray[j]!=0 && zonesToPass!=1) {
                min=searchArray[j];
                currentStation = j;
            }

            else if(currentZone==2 && j>=4 && j<=7 && ((j<currentStation && swappedValues) || (j>currentStation && !swappedValues) ) && searchArray[j]<=min && searchArray[j]!=0 && zonesToPass!=1) {
                min=searchArray[j];
                currentStation = j;

            }

            else if(currentZone==3 && j>=1 && j<=3 || j==8 && ((j<currentStation && swappedValues) ||(j>currentStation && !swappedValues) ) && searchArray[j]<=min && searchArray[j]!=0 && zonesToPass!=1) {
                min=searchArray[j];
                currentStation = j;

            }

            else if(currentZone==4 && j>=4 && j<=7 && ((j<currentStation && swappedValues) || (j>currentStation && !swappedValues)) && searchArray[j]<=min && searchArray[j]!=0 && zonesToPass!=1) {
                min=searchArray[j];
                currentStation = j;
            }

            else if (zonesToPass == 1) {
                min = searchArray[destination];
                currentStation = destination;
                j=searchArray.length-1;
            }


            if(j==searchArray.length-1) {
                stationIndex = currentStation;
                stationsPassed++;
                zonesToPass--;
                stations[tempIndex] = currentStation;
                if(!swappedValues) {
                    tempIndex++;
                    currentZone++;
                }

                else {
                    tempIndex--;
                    currentZone--;
                }
            }
        }

    }

    if(swappedValues) {
        for (int i = 0; i < stations.length / 2; i++) {
            Object temp = stations[i];
            stations[i] = stations[stations.length - 1 - i];
            stations[stations.length - 1 - i] = (int) temp;
        }
    }

    if(stationsPassed!=3)
    stations[stationsPassed+1] = -1; //so i know where it ends
    return stations;
}
// The method length returns the length of the shortest path.
public static double length (double [] a, double [][] b, double [] c) {
    int [] stations = intermediateStations(a,b,c);
    double length = 0;
    for(int i=0; i< stations.length-1; i++) {
        if(stations[i+1]!=-1)
        length+=b[stations[i]][stations[i+1]];
        else
            break;
    }
    return length;
}




}