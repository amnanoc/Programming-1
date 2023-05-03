package com.company;

import java.util.*; // Scanner

import static java.lang.System.out;

class OperationsWithNaturalNumbersGivenAsStrings {
    public static String removeLeadingZeroes(String str) {
        String strPattern = "^0+(?!$)";
        str = str.replaceAll(strPattern, "");
        return str;
    }
    public static void main(String[] args) {
        out.println("OPERATIONS ON NATURAL NUMBERS " +
                "IN CHARACTER STRINGS");
// enter two natural numbers
        Scanner in = new Scanner(System.in);
        out.println("two natural numbers:");
        String tal1 = in.next();
        String tal2 = in.next();
        out.println();
// add the numbers and show the result
        String sum = add(tal1, tal2);
        show(tal1, tal2, sum, '+');
// subtract the numbers and show the result
// *** WRITE YOUR CODE HERE ***
        sum = subtract(tal1, tal2);
        show(tal1, tal2, sum, '-');
    }

    // The add method accepts two natural numbers represented
// as character strings and returns their sum as a
// character string.
    public static String add(String num1, String num2) {
// *** WRITE YOUR CODE HERE ***
        StringBuilder sum = new StringBuilder();
        int indexOfNum1 = num1.length() - 1;
        int indexOfNum2 = num2.length() - 1;
        int numberOfPotentialAdditions = Math.min(indexOfNum1 + 1, indexOfNum2 + 1);
        int digit1 = 0, digit2 = 0, digitSum = 0, carry = 0;

        for (int i = 0; i < numberOfPotentialAdditions; i++) {

            digit1 = Integer.parseInt(String.valueOf(num1.charAt(indexOfNum1)));
            digit2 = Integer.parseInt(String.valueOf(num2.charAt(indexOfNum2)));
            digitSum = digit1 + digit2;

            if (digitSum > 9) {
                carry = 1;
                sum.append(digitSum % 10);
            } else {
                if (1 == carry) {
                    digitSum++;
                    carry = 0;
                }

                sum.append(digitSum);
            }
            indexOfNum1--;
            indexOfNum2--;
        }

        //fixing the carry after all the additions
        if (indexOfNum1 != -1 && indexOfNum2 == -1) { //end of nr 2 but not end of nr1
            while (indexOfNum1 != -1 ) {
                digit1 = Integer.parseInt(String.valueOf(num1.charAt(indexOfNum1)));
                ;
                if (digit1 + 1 > 9) {
                    sum.append((digit1 + 1) % 10);
                } else if(carry==1) {
                    sum.append(digit1+1);
                    carry = 0;
                } else {
                    sum.append(digit1);
                }

                if (indexOfNum1 == 0 && carry == 1) {
                    sum.append(1);
                    carry = 0;
                    break;
                }

                indexOfNum1--;
            }
        } else if (indexOfNum2 != -1 && indexOfNum1 == -1) {
            while (indexOfNum2 != -1 ) {
                digit2 = Integer.parseInt(String.valueOf(num2.charAt(indexOfNum2)));
                ;
                if (digit2 + 1 > 9) {
                    sum.append((digit2 + 1) % 10);
                } else if(carry==1) {
                    sum.append(digit2+1);
                    carry = 0;
                } else {
                    sum.append(digit2);
                }

                if (indexOfNum2 == 0 && carry == 1) {
                    sum.append(1);
                    carry = 0;
                    break;
                }

                indexOfNum2--;
            }
        } else if (indexOfNum1 == -1 && indexOfNum2 == -1 && carry == 1)
            sum.append(1);

        sum.reverse();
        return sum.toString();
    }

    // The subtract method accepts two natural numbers
// represented as character strings and returns their
// difference as a character string.
// The first number is not smaller than the second
    public static String subtract(String num1, String num2) {
// *** WRITE YOUR CODE HERE ***
        if(Integer.parseInt(String.valueOf(num1))<Integer.parseInt(String.valueOf(num2)))
            return "Error!";
        StringBuilder sum = new StringBuilder();
        int indexOfNum1 = num1.length() - 1;
        int indexOfNum2 = num2.length() - 1;
        int numberOfPotentialSubtractions = Math.min(indexOfNum1 + 1, indexOfNum2 + 1);
        int digit1 = 0, digit2 = 0, digitSum = 0, take = 0;

        for (int i = 0; i < numberOfPotentialSubtractions; i++) {

            digit1 = Integer.parseInt(String.valueOf(num1.charAt(indexOfNum1)));
            digit2 = Integer.parseInt(String.valueOf(num2.charAt(indexOfNum2)));
            digitSum = digit1 - digit2;

            if (digitSum < 0) {
                take=1;
                sum.append(10+digitSum);
            } else {
                if (1 == take) {
                    digitSum--;
                    take = 0;
                }

                sum.append(digitSum);
            }
            indexOfNum1--;
            indexOfNum2--;
        }

        //fixing the take after all the subtractions
        if (indexOfNum1 != -1 && indexOfNum2 == -1) { //end of nr 2 but not end of nr1
            while (indexOfNum1 != -1 ) {
                digit1 = Integer.parseInt(String.valueOf(num1.charAt(indexOfNum1)));

                if (digit1-1 < 0) {
                    sum.append((digit1 + 1) % 10);
                } else if(take==1) {
                    sum.append(--digit1);
                    take = 0;
                } else {
                    sum.append(digit1);
                }

                indexOfNum1--;
            }
        }

        sum.reverse();
        sum = new StringBuilder(removeLeadingZeroes(sum.toString()));
        return sum.toString();
    }

    // The show method presents two natural numbers , an
// operator and the result string.
    public static void show(String num1, String num2, String result, char operator) {
// set an appropriate length on numbers and result
        int len1 = num1.length();
        int len2 = num2.length();
        int len = result.length();
        int maxLen = Math.max(Math.max(len1, len2), len);
        num1 = setLen(num1, maxLen - len1);
        num2 = setLen(num2, maxLen - len2);
        result = setLen(result, maxLen - len);
// show the expression
        out.println("  " + num1);
        out.println("" + operator + " " + num2);
        for (int i = 0; i < maxLen + 2; i++)
            out.print("-");
        out.println();
        out.println("  " + result + "\n");
    }

    // The setLen method prepends the supplied number of
// spaces ato the beginning of a string
    public static String setLen(String s, int nofSpaces) {
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < nofSpaces; i++)
            sb.insert(0, " ");
        return sb.toString();
    }


}
