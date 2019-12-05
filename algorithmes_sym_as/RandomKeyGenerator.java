package algorithmes_sym_as;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DELL
 */
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.SecureRandom;

public class RandomKeyGenerator  {
    public static int getCesarKey(){
        return random.nextInt(26)%26;
    }
    
    public static String getRandomString(int nbrBits){
        byte bytes[] = new byte[nbrBits];
        random.nextBytes(bytes);
        String str="";
        try{
            str = new String(bytes, "UTF-8");
        }catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return str;
    }
    
    public static String getDESKey(){
        byte bytes[] = new byte[64];
        random.nextBytes(bytes);
        String str="";
        try{
            str = new String(bytes, "UTF-8");
        }catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return str;
    }
    
    private final static BigInteger one      = new BigInteger("1");
    private final static SecureRandom random = new SecureRandom();



    public static BigInteger[] get2KeysPairs(int N) {
        BigInteger p = BigInteger.probablePrime(N/2, random);
        BigInteger q = BigInteger.probablePrime(N/2, random);
        BigInteger phi = (p.subtract(one)).multiply(q.subtract(one));
        BigInteger privateKey;
        BigInteger publicKey;
        BigInteger modulus,res[]=new BigInteger[3];
        modulus    = p.multiply(q);                                  
        publicKey  = new BigInteger("65537");     // common value in practice = 2^16 + 1
        privateKey = publicKey.modInverse(phi);
        res[0]=publicKey;
        res[1]=modulus;
        res[2]=privateKey;
        return res;
    }
}
