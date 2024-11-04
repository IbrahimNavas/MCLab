package Practice;

public class findGCD {
    public static int theGCD(int a, int b){
        if(b == 0){
            return a;
        }
        return theGCD(b,a%b);
    }
    public static void main(String[] args){
        int a = 103;
        int b = 5;
        int gcd = theGCD(a,b);
        System.out.println(gcd);
    }
}
