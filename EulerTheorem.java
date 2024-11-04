package Practice;

public class EulerTheorem{
    public static int gcd(int a, int b){
        if(b == 0){
            return a;
        }
        return gcd(b,a%b);
    }
    public static int eulerTotient(int n){
        int result = 1;
        for(int i = 2 ; i < n ; i++){
            if(gcd(i,n) == 1){
                result++;
            }
        }
        return result;
    }
    public static int eulerTheorem(int a, int totient, int n){
        int result = 1;
        a = a%n;
        while(totient > 0){
            if(totient%2 != 0){
                result = (result*a)%n;
            }
            totient = totient >> 1;
            a = (a*a)%n;
        }
        return result;
    }    
    public static void main(String[] args){
        int a = 3;
        int n = 10;
        int totient = eulerTotient(n);
        int euler = eulerTheorem(a,totient,n);
        System.out.println(euler);
    }
}
