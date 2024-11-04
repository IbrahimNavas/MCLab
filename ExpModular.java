package Practice;

public class ExpModular{
    public static int modExp(int base, int exp, int mod){
        int result = 1;
        base = base%mod;
        while(exp > 0){
            if(exp%2 != 0){
                result = (result*base)%mod;
            }
            exp = exp >> 1;
            base = (base*base)%mod;
        }
        return result;
    }
    public static void main(String[] args){
        int base = 2;
        int exp = 11;
        int mod = 1024;
        int result = modExp(base,exp,mod);
        System.out.println(result);
    }
}
