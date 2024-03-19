package Encryption.Algorithms;

public class Caesarcipher {
    String message;
    char ch;
    char NumbTest[] = {'0','1', '2', '3', '4', '5', '6', '7', '8', '9'};
    public String caesarcipherEnc (String message,int key){
        String encryptedMessage = "";
        int n = 1;
        for (int i = 0; i < message.length(); i++) {
            n = 1;
            ch = message.charAt(i);
            for (int j = 0; j < NumbTest.length; j++)
            {
                if (ch == NumbTest[j])
                {

                    if((char)key+ch>'9')
                        break;
                    ch = (char) (ch + key);
                    encryptedMessage += ch;
                    n = 0;
                    break;
                }
            }
            if (n == 0)
            {
                continue;
            } else
                if (ch >= 'a' && ch <= 'z')
                {
                ch = (char) (ch + key);

                if (ch > 'z') {
                    ch = (char) (ch - 'z' + 'a' - 1);
                }

                encryptedMessage += ch;
            } else if (ch >= 'A' && ch <= 'Z') {
                ch = (char) (ch + key);

                if (ch > 'Z') {
                    ch = (char) (ch - 'Z' + 'A' - 1);
                }

                encryptedMessage += ch;
            } else
                encryptedMessage += ch;
        }

        return encryptedMessage;
    }
    public String caesarcipherDec (String message,int key)
    {
        String decryptedMessage = "";
        int n = 1;
        for (int i = 0; i < message.length(); i++) {
            n = 1;
            ch = message.charAt(i);
            for (int j = 0; j < NumbTest.length; j++) {
                if (ch == NumbTest[j]) {

                    if((char)key+ch>'9')
                        break;
                    ch = (char) (ch - key);
                    decryptedMessage += ch;
                    n = 0;
                    break;
                }
            }
            if (n == 0)
            {
                continue;
            } else if (ch >= 'a' && ch <= 'z') {
                ch = (char) (ch - key);

                if (ch < 'a') {
                    ch = (char) (ch + 'z' - 'a' + 1);
                }

                decryptedMessage += ch;
            } else if (ch >= 'A' && ch <= 'Z') {
                ch = (char) (ch - key);

                if (ch < 'A') {
                    ch = (char) (ch + 'Z' - 'A' + 1);
                }

                decryptedMessage += ch;

            } else
                decryptedMessage += ch;
        }
        return decryptedMessage;
    }
}
