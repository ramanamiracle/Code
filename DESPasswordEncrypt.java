

import sun.misc.*;

import java.net.URLEncoder;

import java.security.*;

import javax.crypto.*;
import javax.crypto.spec.*;


public class DESPasswordEncrypt {
    // The DES Key - any 8 bytes will do though beware of weak keys.
    // This could be read from a file.
    final byte[] DESKeyBytes = { 0x01, 0x02, 0x04, 0x08, 0x08, 0x04, 0x02, 0x01 };

    // IV For CBC mode
    // Again, could be read from a file.
    final byte[] ivBytes = { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07 };
    private String characterEncoding;
    private Cipher encryptCipher;
    private Cipher decryptCipher;
    private BASE64Encoder base64Encoder = new BASE64Encoder();
    private BASE64Decoder base64Decoder = new BASE64Decoder();

    public DESPasswordEncrypt(String characterEncoding)
        throws Exception {
        SecretKey key = new SecretKeySpec(DESKeyBytes, "DES");
        IvParameterSpec iv = new IvParameterSpec(ivBytes);
        this.characterEncoding = characterEncoding;
        this.encryptCipher = Cipher.getInstance("DES/CBC/PKCS5Padding", "SunJCE");
        this.encryptCipher.init(javax.crypto.Cipher.ENCRYPT_MODE, key, iv);
        this.decryptCipher = Cipher.getInstance("DES/CBC/PKCS5Padding", "SunJCE");
        this.decryptCipher.init(javax.crypto.Cipher.DECRYPT_MODE, key, iv);
    }

    synchronized public String encrypt(String password)
        throws Exception {
        byte[] passwordBytes = password.getBytes(characterEncoding);
        byte[] encryptedPasswordBytes = this.encryptCipher.doFinal(passwordBytes);
        String encodedEncryptedPassword = this.base64Encoder.encode(encryptedPasswordBytes);

        return encodedEncryptedPassword;
    }

    synchronized public String decrypt(String encodedEncryptedPassword)
        throws Exception {
        byte[] encryptedPasswordBytes = this.base64Decoder.decodeBuffer(encodedEncryptedPassword);
        byte[] passwordBytes = this.decryptCipher.doFinal(encryptedPasswordBytes);
        String recoveredPassword = new String(passwordBytes, characterEncoding);
        System.out.println(recoveredPassword);

        return recoveredPassword;
    }

    public static void main(String[] args) throws Exception {
//        Security.addProvider(new com.sun.crypto.provider.SunJCE());

        DESPasswordEncrypt passwordEncryptAgent = new DESPasswordEncrypt(
                "ASCII");

        String str = "something";
       // String strEncrypt ="0515470df47458b21be3164ce543b5f3bf14d219ae5f8a0da5";
        String strEncrypt = passwordEncryptAgent.encrypt(str);
        System.out.println("str " + str);

        String encryptedMDN = URLEncoder.encode(strEncrypt);
        System.out.println("strDecrypt " +
            passwordEncryptAgent.decrypt(strEncrypt));

        StringBuffer temp = new StringBuffer(str.substring(5));
        str += temp.reverse().toString();
        str += str.substring(0, 5);

        String param1 = AeSimpleSHA1.SHA1(str);
        System.out.println( encryptedMDN + "" + param1);
    
    }
}
