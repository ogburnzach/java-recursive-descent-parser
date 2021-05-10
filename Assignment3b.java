// Zach Ogburn
// COSC 5366.001
// 3/24/2021
// Dr. Brown
//Assignment 3 B
// This program acts as a recursive descent compiler and will determine whether or not a string is accepted in a defined language


import java.util.*;

public class Assignment3b {

    String lookAhead;
    ArrayList<String> inputList;

    public Assignment3b(ArrayList<String> stringList) {

        inputList = stringList;
        lookAhead = inputList.remove(inputList.size()-1);
    }

    public void parseG(){
        //check which of the production involving nonterminal G to perform
        if(lookAhead.equals("z") || lookAhead.equals("x") || lookAhead.equals("e")) {
            parseS();
            match("$");
        } else if (lookAhead.equals("$")) {
            return;
        } else {
            throw new java.lang.Error("Invalid Character, String Not Accepted.");
        }
    }

    public void parseS(){
        //check which of the production involving nonterminal S to perform

        if(lookAhead.equals("x") || lookAhead.equals("e") || lookAhead.equals("$")) {
            parseU();
            match("e");
        } else if (lookAhead.equals("z")) {
            match("z");
            match("a");
            parseT();
        } else {
            throw new java.lang.Error("Invalid Character, String Not Accepted.");

        }


    }

    public void parseT(){
        //check which of the production involving nonterminal T to perform

        if (lookAhead.equals("a")){
            match("a");
            parseS();
        } else if (lookAhead.equals("y")) {
            match("y");
            match("a");
            parseT();
        } else if (lookAhead.equals("x") || lookAhead.equals("e") || lookAhead.equals("$")){
            return;
        } else {
            System.out.println("bad3");
            throw new java.lang.Error("Invalid Character, String Not Accepted.");

        }
    }

    public void parseU(){
        //check which of the production involving nonterminal U to perform

        if (lookAhead.equals("x")){
            match("x");
            match("a");
            parseT();
            parseU();
        } else if (lookAhead.equals("$") || lookAhead.equals("e")) {
            return;
        } else {
            System.out.println("bad2");
            throw new java.lang.Error("Invalid Character, String Not Accepted.");

        }
    }

    public void match(String inputVar){
        //this function takes a String as input and verifies that it is equal to the lookAhead attribute
        if (lookAhead.equals(inputVar) && !lookAhead.equals("$")) {
            lookAhead = inputList.remove(inputList.size()-1);
        } else if (lookAhead.equals("$")){
            return;
        } else {
            System.out.println("bad1");
            throw new java.lang.Error("Invalid Character, String Not Accepted.");

        }
    }

    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Enter a string to test: ");
        //get input string to test
        String inputStr = myScanner.nextLine();

        //splint input string into a list of chars
        ArrayList<String> stringList = new ArrayList<String>(Arrays.asList(inputStr.split("")));
        stringList.add("$");
        Collections.reverse(stringList);
        //create parser object
        Assignment3b myParser = new Assignment3b(stringList);
        //call starting parse function
        myParser.parseG();
        //check if string was accepted
        if (myParser.lookAhead.equals("$")){
            System.out.println("String Accepted!");

        } else {
            System.out.println("String not accepted");
        }
    }
}
