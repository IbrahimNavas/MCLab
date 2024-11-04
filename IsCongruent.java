package Practice;

public class IsCongruent {
    public static boolean isCongruent(int a, int b, int c){
        boolean result = true;
        int res = (a-b)%c;
        if(res == 0){
            result = true;
        }else{
            result = false;
        }
        return result;
    }
    public static void main(String[] args){
        int a = 10;
        int b = -2;
        int c = 12;
        boolean isCong = isCongruent(a,b,c);
        if(isCong){
            System.out.println("A and B are congruent");
        }else{
            System.out.println("A nd B are not congruent");
        }
    }
    
}
