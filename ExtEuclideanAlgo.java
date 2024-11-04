package Practice;

public class ExtEuclideanAlgo {
    static class Result{
        int gcd;
        int x;
        int y;
        Result(int gcd , int x, int y){
            this.gcd = gcd;
            this.x = x;
            this.y = y;
        }
    }
    public static Result extendedEuclidean(int a, int b){
        if(b == 0){
            return new Result(a,1,0);
        }
        Result result = extendedEuclidean(b,a%b);
        int x = result.y;
        int y = result.x - (a/b)*result.y;
        return new Result(result.gcd,x,y);
    }
    public static void main(String args[]){
        int a = 56;
        int b = 98;
        Result result = extendedEuclidean(a,b);
        System.out.println(a+" "+b+" "+result.x+" "+result.y);
    }
}
