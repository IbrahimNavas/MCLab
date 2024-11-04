package Practice;

public class Primality {
    public static boolean isPrime(int n){
        boolean result = true;
        for(int i = 2 ; i < Math.sqrt(n) ; i++){
            if(n%i == 0){
                result = false;
                break;
            }
        }
        return result;
    }
    public static void main(String[] args){
        int n = 1009;
        boolean prime = isPrime(n);
        if(prime){
            System.out.println("Prime number");
        }else{
            System.out.println("Not a prime number");
        }
    }
}
