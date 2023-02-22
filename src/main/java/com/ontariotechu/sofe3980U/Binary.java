package com.ontariotechu.sofe3980U;

import java.util.ArrayList;

/**
 * Unsigned integer Binary variable
 *
 */
public class Binary
{
    private String number="0";  // string containing the binary value '0' or '1'
    /**
     * A constructor that generates a binary object.
     *
     * @param number a String of the binary values. It should conatins only zeros or ones with any length and order. otherwise, the value of "0" will be stored.   Trailing zeros will be excluded and empty string will be considered as zero.
     */
    public Binary(String number) {
        for (int i = 0; i < number.length(); i++) {
            // check each character if it's not 0 or 1
            char ch=number.charAt(i);
            if(ch!='0' && ch!='1') {
                number="0"; // if not store "0" and end the function
                return;
            }
        }
        // remove any trailing zeros
        int beg;
        for (beg = 0; beg < number.length(); beg++) {
            if (number.charAt(beg)!='0')
                break;
        }
        //beg has the index of the first non zero digit in the number
        this.number=number.substring(beg); // exclude the trailing zeros if any
        // uncomment the following code

        if(this.number=="") { // replace empty strings with a single zero
            this.number="0";
        }

    }
    /**
     * Return the binary value of the variable
     *
     * @return the binary value in a string format.
     */
    public String getValue()
    {
        return this.number;
    }
    /**
     * Adding two binary variables. For more information, visit <a href="https://www.wikihow.com/Add-Binary-Numbers"> Add-Binary-Numbers </a>.
     *
     * @param num1 The first addend object
     * @param num2 The second addend object
     * @return A binary variable with a value of <i>num1+num2</i>.
     */
    public static Binary add(Binary num1,Binary num2)
    {
        // the index of the first digit of each number
        int ind1=num1.number.length()-1;
        int ind2=num2.number.length()-1;
        //initial variable
        int carry=0;
        String num3="";  // the binary value of the sum
        while(ind1>=0 ||  ind2>=0 || carry!=0) // loop until all digits are processed
        {
            int sum=carry; // previous carry
            if(ind1>=0){ // if num1 has a digit to add
                sum += (num1.number.charAt(ind1)=='1')? 1:0; // convert the digit to int and add it to sum
                ind1--; // update ind1
            }
            if(ind2>=0){ // if num2 has a digit to add
                sum += (num2.number.charAt(ind2)=='1')? 1:0; // convert the digit to int and add it to sum
                ind2--; //update ind2
            }
            carry=sum/2; // the new carry
            sum=sum%2;  // the resultant digit
            num3 =( (sum==0)? "0":"1")+num3; //convert sum to string and append it to num3
        }
        Binary result=new Binary(num3);  // create a binary object with the calculated value.
        return result;

    }
    /**
     * Bitwise "AND" of two binary variables. For more information, visit <a href="https://documentation.softwareag.com/apama/v10-11/apama10-11/apama-webhelp/index.html#page/apama-webhelp%2Fre-ApaEplRef_bitwise_intersection__and_.html%23wwconnect_header"> Logical Intersection (and) </a>.
     *
     * @param num1 The first operand object
     * @param num2 The second operand object
     * @return A binary variable with a value of <i>the bitwise AND</i>.
     */
    public static Binary AND(Binary num1, Binary num2)
    {
        StringBuilder result = new StringBuilder();
        int size;
        // check if num1 and num2 are the same length
        String num1Value = num1.getValue();
        String num2Value = num2.getValue();
        int num1length = num1Value.length();
        int num2length = num2Value.length();
        if(num1length != num2length){
            int diff = Math.abs((num2length - num1length));
            if(num1length > num2length){
                num2Value = addTailingZeroes(num2Value, diff);
            }
            else {
                num1Value = addTailingZeroes(num1Value, diff);
            }
        }
        size = num1Value.length() - 1;
        // perform bitwise AND
        for (int i = 0; i <= size; i++) {
            // compare digits
            // 1 && 1
            if (num1Value.charAt(i) == num2Value.charAt(i) && num1Value.charAt(i) == '1') {
                result.append("1");
            }
            // 1 && 0 or 0 && 1 or 0 && 0
            else {
                result.append("0");
            }
        }
        return new Binary(result.toString());
    }

    /**
     * Bitwise "OR" of two binary variables. For more information, visit <a href="https://documentation.softwareag.com/apama/v10-11/apama10-11/apama-webhelp/index.html#page/apama-webhelp%2Fre-ApaEplRef_logical_union__or_.html%23wwconnect_header"> Logical Union (or) </a>.
     *
     * @param num1 The first operand object
     * @param num2 The second operand object
     * @return A binary variable with a value of <i>the bitwise OR</i>.
     */
    public static Binary OR(Binary num1,Binary num2)
    {
        StringBuilder result = new StringBuilder();
        int size;
        // check if num1 and num2 are the same length
        String num1Value = num1.getValue();
        String num2Value = num2.getValue();
        int num1length = num1Value.length();
        int num2length = num2Value.length();
        if(num1length != num2length){
            int diff = Math.abs((num2length - num1length));
            if(num1length > num2length){
                num2Value = addTailingZeroes(num2Value, diff);
            }
            else {
                num1Value = addTailingZeroes(num1Value, diff);
            }
        }
        size = num1Value.length() - 1;
        // perform bitwise or
        for(int i = 0; i <= size; i++){
            // compare digits
            // 0 && 0
            if(num1Value.charAt(i) == num2Value.charAt(i) && num1Value.charAt(i) == '0'){
                result.append("0");
            }
            // 1 && 0 or 0 && 1 or 1 && 1
            else {
                result.append("1");
            }
        }
        return new Binary(result.toString());
    }

    /**
     * Multiplication of two binary variables. For more information, visit <a href="https://www.sciencedirect.com/topics/engineering/binary-multiplication#:~:text=The%20product%20of%20multiplying%20any,adding%20the%20shifted%20numbers%20together."> Binary Multiplication) </a>.
     *
     * @param num1 The multiplicand object
     * @param num2 The multiplier object
     * @return A binary variable with a value of <i>the product</i>.
     */
    public static Binary Multiply(Binary num1,Binary num2)
    {
        // take first bit (right most) and multiply it with all the digits of the other one, starting from the right
        // put these into an array
        // iterate to next bit
        // once done, add all elements of the array together
        ArrayList<String> subProducts = new ArrayList<>();
        Binary result = new Binary("0");
        String shift = "";
        for(int i = num2.number.length() - 1; i >= 0; i--) {
            String subProduct = shift;
            for (int j = num1.number.length() - 1; j >= 0; j--) {
                if (num1.number.charAt(j) == num2.number.charAt(i) && num1.number.charAt(j) == '1'){
                    subProduct += "1";
                }
                else {
                    subProduct += "0";
                }
            }
            shift += "0";
            String reverse = new StringBuffer(subProduct).reverse().toString();
            subProducts.add(reverse);
        }
        for(int i = 0; i < subProducts.size(); i++){
            result = Binary.add(new Binary(subProducts.get(i)), new Binary(result.number));
        }
        return result;
    }

    public static String addTailingZeroes(String binaryNumber, int noZeroes){
        StringBuilder addOn = new StringBuilder();
        addOn.append("0".repeat(Math.max(0, noZeroes)));
        return addOn + binaryNumber;
    }

}