public class HillCipher{
    public static void Encrypt(int cipherMat[][] , int msgMat[][] , int keyMat[][]){
        for(int i = 0 ; i < 3 ; i++){
            for(int j = 0 ; j < 1 ; j++){
                cipherMat[i][j] = 0;
                for(int k = 0 ; k < 3 ; k++){
                    cipherMat[i][j] += keyMat[i][k]*msgMat[k][j];
                }
                cipherMat[i][j] = cipherMat[i][j]%26;
            }
        }
    }
    public static void getKeyMat(String key , int keyMat[][]){
        int k = 0;
        for(int i = 0 ; i < 3 ; i++){
            for(int j = 0 ; j < 3 ; j++){
                keyMat[i][j] = (key.charAt(k))%65;
                k++;
            }
        }
    }
    public static void getMsgMat(String msg , int msgMat[][]){
        for(int i = 0 ; i < 3 ; i++){
            msgMat[i][0] = (msg.charAt(i))%65;
        }
    }
    public static String HCEncrypt(String msg , String key){
        int keyMat[][] = new int[3][3];
        getKeyMat(key,keyMat);

        int msgMat[][] = new int[3][1];
        getMsgMat(msg,msgMat);

        int cipherMat[][] = new int[3][1];
        Encrypt(cipherMat, msgMat, keyMat);

        String ciphered = "";
        for(int i = 0 ; i < 3 ; i++){
            ciphered += (char)(cipherMat[i][0]+65);
        }
        return ciphered;

    }
    public static void main(String[] args){
        String key = "GYBNQKURP";
        String msg = "ACT";
        String ciphered = HCEncrypt(msg,key);
        System.out.println(ciphered);
    }
}
