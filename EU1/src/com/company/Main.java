package com.company;

public class Main {

    public static int min (int[] elements) throws IllegalArgumentException {
        if (elements.length == 0)
            throw new IllegalArgumentException ("empty collection");

        int[] sequence = elements;
        int nofPairs = sequence.length / 2;
        int nofUnpairedElements = sequence.length % 2;
        int nofPossibleElements = nofPairs + nofUnpairedElements;
        int[] partialSeq = new int[nofPossibleElements ];
        int i = 0;
        int j = 0;
        int counter = sequence.length;
        while (counter >0) { //it was an infinite loop here so i introduced a local variable and doubled it down on each passing through the loop
        //i also changed it from >1 to >0 so it includes the last transition through an array so we can get the correct last min value
            i = 0;
            j = 0;
            while (j < nofPairs)
            {
                partialSeq[j++] = Math.min(sequence[i], sequence[i + 1]);
                i += 2;
            }
            if (nofUnpairedElements == 1)
                partialSeq[j] = sequence[sequence.length - 1];

            sequence = partialSeq;
            nofPairs = nofPossibleElements / 2;
            nofUnpairedElements = nofPossibleElements % 2;
            nofPossibleElements = nofPairs + nofUnpairedElements;

            //infinite loop happened because there was no this part below
            counter/=2; //adjusting the size of the new array
            partialSeq = new int[nofPossibleElements];

        }
        // sequence [0] is the only remaining possible element

        return sequence [0];
    }

    public static int betterMin (int[] elements) throws IllegalArgumentException {
        if (elements.length == 0)
            throw new IllegalArgumentException("empty collection");
        int min = elements[0];
        for(int i=1; i<elements.length; i++)
            if(elements[i]<min)
                min = elements[i];

        return min;
    }

    public static void main(String[] args) {
	// write your code here
        int []test = {1,2,34,5,6,7,8,55,1,-1,-23,54,8,12,34,-100,6,-176,56};
        System.out.println(test.length);
        System.out.println(min(test));
        System.out.println(betterMin(test));
    }
}
