
package algorithmes_sym_as;

import java.math.BigInteger;
import java.util.Random;

/**
 *
 * @author drioueche
 */
public class RSABIGInteger implements AlgoCryptage {

        private BigInteger p,q,n,e,d,phi;

    
        public static BigInteger pgcd(BigInteger a, BigInteger b) {
    
        BigInteger zero = new BigInteger("0");
        
        if(a.mod(b).compareTo(zero)==0) 
            return b;
        else return pgcd(b,a.mod(b));
        
    }



    // Calcul de n (paramètre de BBS)
    public static BigInteger paramBBS(int k) {

        BigInteger n,p,q,alpha,three,four;
        three = new BigInteger("3");
        four  = new BigInteger("4");

        do {
            alpha = BigInteger.probablePrime(k, new Random());
            p = alpha.multiply(three).add(four);
        }while (!p.isProbablePrime(10));

        do {
            alpha = BigInteger.probablePrime(k, new Random());
            q = alpha.multiply(three).add(four);
        }while (!q.isProbablePrime(10));

        n = p.multiply(q);
        //System.out.println("P= " + p );
        //System.out.println("q= " + q );
        return n;
    }



    // calcul de S (2ème Paramètre de BBS)
    public static BigInteger paramBBS2(BigInteger n) {

        BigInteger S;
        BigInteger one = new BigInteger("1");

        do {
            S = BigInteger.probablePrime(64, new Random());
        }while (pgcd(S, n).compareTo(one)!=0);

        return S;
    }


    //Blum blum Shub
    public static String BBS(BigInteger n, BigInteger s, int l) {

        String bits = new String();
        BigInteger x,z,two;
        two = new BigInteger("2");

        x = s.pow(2).mod(n);

        for (int i=1; i<=l; i++) {
            x = x.pow(2).mod(n);
            z = x.mod(two);
            bits = z + bits;
        }

        return bits;
    }


    // Nombre premier p
    public static BigInteger P() {

        BigInteger p;

        do {
            p = BigInteger.probablePrime(60, new Random());
        }while(!p.isProbablePrime(10));

       return p;
    }


    // Nombre Premier q
    public static BigInteger Q() {

        BigInteger q;

        do {
            q = BigInteger.probablePrime(60, new Random());
        }while(!q.isProbablePrime(10));

       return q;
    }

    // Cle de chiffrement e
    public static BigInteger E(BigInteger phi) {

        BigInteger e,one;
        one = new BigInteger("1");

        do {
            e = BigInteger.probablePrime(50, new Random());
        }while(pgcd(e, phi).compareTo(one) != 0);

       return e;
    }


    //Euclide Etendu (Inverse Modulaire) clé de déchiffrement d
    public static BigInteger D(BigInteger a, BigInteger b) {

        BigInteger x0,y0,x1,y1,t1,t2,q,tb;

        BigInteger phi = b;

        BigInteger zero = new BigInteger("0");

        x0=new BigInteger("1");
        x1=new BigInteger("0");
        y0=new BigInteger("0");
        y1=new BigInteger("1");

        while (b.compareTo(zero)!= 0) {          //b!=0

          q = a.divide(b);

          tb = b;
          b = a.mod(b);
          a = tb;

          t1 = x1;
          t2 = y1;

          x1 = x0.subtract(q.multiply(x1));           //x0 - q*x1;
          y1 = y0.subtract(q.multiply(y1));                                      //y0 - q*y1;

          x0=t1;
          y0=t2;

        }

        // Tantque l'inverse négatif (<0)
        while (x0.signum()!=1) {
            x0 = x0.add(phi);
        }

        return x0;
    }




    public RSABIGInteger() {
        BigInteger one = new BigInteger("1");
        p = P();
        q = Q();
        n = p.multiply(q);
        phi = p.subtract(one).multiply(q.subtract(one));
        e = E(phi);
        d = D(e, phi);
    }

    private String toHex(byte[] bytes)
    {
        BigInteger bi = new BigInteger(1, bytes);
        return String.format("%0" + (bytes.length << 1) + "X", bi);
    }

    public  String crypter(String msg) {

                //String chcrypter= new String();
		/*int intCarac,intResult;

                BigInteger res,bigIntCarac;


                for (int i=0; i<msg.length(); i++) {

		 	intCarac = (int) msg.charAt(i);
                        bigIntCarac = new BigInteger(Integer.toString(intCarac));
			res = bigIntCarac.modPow(e, n);

                        intResult = Integer.parseInt(res.toString());

                        chcrypter += (char) intResult;

                }
		*/

                BigInteger message = new BigInteger(msg.getBytes());
                byte[] encrypted = message.modPow(e, n).toByteArray();
                return toHex(encrypted);

		//return chcrypter;

    }

    public String decrypter(String msg) {

                /*String chDecrypter= new String();
		int intCarac,intResult;

                BigInteger res,bigIntCarac;

                for (int i=0; i<msg.length(); i++) {

		 	intCarac = (int) msg.charAt(i);
                        bigIntCarac = new BigInteger(Integer.toString(intCarac));
			res = bigIntCarac.modPow(d, n);

                        intResult = Integer.parseInt(res.toString());

                        chDecrypter += (char) intResult;

                }

		return chDecrypter;
                */
            BigInteger encrypted = new BigInteger(msg, 16);
            return new String(encrypted.modPow(d, n).toByteArray());

    }


    public static void main(String[] args) {
        // TODO code application logic here

           BigInteger n,s;

           RSABIGInteger test = new RSABIGInteger();
            //pour convertir de string binaire vers entier décimal grand (BigInteger)

            BigInteger bi = new BigInteger("100101000111111110000", 2);

            n = paramBBS(64);
            s = paramBBS2(n);

            String BBS = BBS(n, s, 15);

            BigInteger BBSDecimal = new BigInteger(BBS,2);

            System.out.println(BBS + ": " + BBSDecimal);


            String msgcrypt = test.crypter("Hello world!");

            System.out.println(msgcrypt);
            System.out.println(test.decrypter(msgcrypt));

            //System.out.println(Integer.parseInt(""));

    }



}
