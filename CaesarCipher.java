import java.util.*;
public class CaesarCipher{
    public static String CCEncrypt(String pText , int key){
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < pText.length() ; i++){
            char ch = pText.charAt(i);
            if(Character.isUpperCase(ch)){
                char ch1 = (char)(((ch-'A'+key+26)%26)+'A');
                sb.append(ch1);
            }
            if(Character.isLowerCase(ch)){
                char ch2 = (char)(((ch-'a'+key+26)%26)+'a');
                sb.append(ch2);
            }
        }
        return sb.toString();
    }
    public static String CCDecrypt(String ciphered , int key){
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < ciphered.length() ; i++){
            char ch = ciphered.charAt(i);
            if(Character.isUpperCase(ch)){
                char ch1 = (char)(((ch-'A'-key+26)%26)+'A');
                sb.append(ch1);
            }
            if(Character.isLowerCase(ch)){
                char ch2 = (char)(((ch-'a'-key+26)%26)+'a');
                sb.append(ch2);
            }
        }
        return sb.toString();
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the text : ");
        String ptext = sc.nextLine();
        System.out.print("Enter the key : ");
        int key = sc.nextInt();
        key = ((key%26)+26)%26;
        System.out.println("Plain text : "+ptext);
        String ciphered = CCEncrypt(ptext,key);
        System.out.println("Ciphered text : "+ciphered);
        String deciphered = CCDecrypt(ciphered,key);
        System.out.println("Deciphered text : "+deciphered);
    }
}