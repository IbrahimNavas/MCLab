import java.util.*;
public class AffineCipher{
    public static String AEncrypt(String pText , int a , int b){
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < pText.length() ; i++){
            char ch = pText.charAt(i);
            if(Character.isUpperCase(ch)){
                char ch1 = (char)(((a*(ch-'A')+b)%26)+'A');
                sb.append(ch1);
            }
            else if(Character.isLowerCase(ch)){
                char ch2 = (char)(((a*(ch-'a')+b)%26)+'a');
                sb.append(ch2);
            }
        }
        return sb.toString();
    }
    public static String ADecrypt(String ciphered , int a , int b){
        StringBuilder sb = new StringBuilder();
        int a_inv = 0;
        int flag = 0;
        for(int i = 0 ; i < 26 ; i++){
            flag = (a*i)%26;
            if(flag == 1){
                a_inv = i;
            }
        }
        System.out.println(a_inv);
        for(int i = 0 ; i < ciphered.length() ; i++){
            char ch = ciphered.charAt(i);
            if(Character.isUpperCase(ch)){
                char ch1 = (char)(((a_inv*((ch+'A')-b+26))%26)+'A');
                sb.append(ch1);
            }
            if(Character.isLowerCase(ch)){
                char ch2 = (char)(((a_inv*((ch+'a')-b+26))%26)+'a');
                sb.append(ch2);
            }
        }
        return sb.toString();
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the text : ");
        String ptext = sc.nextLine();
        System.out.print("Enter the key1 : ");
        int a = sc.nextInt();
        System.out.print("Enter the key2 : ");
        int b = sc.nextInt();
        System.out.println("Plain text : "+ptext);
        String ciphered = AEncrypt(ptext,a,b);
        System.out.println("Ciphered text : "+ciphered);
        String deciphered = ADecrypt(ciphered,a,b);
        System.out.println("Deciphered text : "+deciphered);
    }
}
