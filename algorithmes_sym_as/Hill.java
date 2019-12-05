package algorithmes_sym_as;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author drioueche
 */
public class Hill implements AlgoCryptage {
    private int a,b,c,d;
    /**
     * Creates new form ChiffHill
     */
    public Hill(int a,int b,int c,int d) {
        this.a=Math.abs(a%63);
        this.b=Math.abs(b%63);
        this.c=Math.abs(c%63);
        this.d=Math.abs(d%63);
    }
    
    public void setKeys(int a,int b,int c,int d){
        this.a=Math.abs(a%63);
        this.b=Math.abs(b%63);
        this.c=Math.abs(c%63);
        this.d=Math.abs(d%63);
    }

    public static int euclide_etendu(int a, int b) {
        
        int x0,y0,x1,y1,t1,t2,q,tb;
    
        int phi = b;
        
        x0=1; x1=0; 
        y0=0; y1=1;  
        
        while (b!=0) {
        
          q = a/b;  
          
          tb = b;
          b = a % b;
          a = tb;
          
          t1 = x1;
          t2 = y1;
          
          x1 = x0 - q*x1;
          y1 = y0 - q*y1;
          
          x0=t1;
          y0=t2;
          
        }
        
        while (x0<0) {
            x0 = x0 + phi;
        }
    
        return x0;
    }

    @Override
    public String crypter(String msg) {
        int p1,p2,c1,c2;
            
        String alphab="ABCDEFGHIJKLMNOPQRSTUVWXYZ abcdefghijklmnopqrstuvwxyz0123456789";
String s1 = msg;
            
            String res = "";

            for (int i=0; i<s1.length(); i+=2) {

                    p1= alphab.indexOf(s1.charAt(i));
                    if(i+1<s1.length())
                        p2= alphab.indexOf(s1.charAt(i+1));
                    else p2=0;

                    c1 = Math.abs((a*p1 + b*p2)%63);
                    c2 = Math.abs((c*p1 + d*p2)%63);

                    char cc1 = alphab.charAt(c1);
                    char cc2 = alphab.charAt(c2);
                    res = res + cc1 + cc2;
            }
            return res;
    }

    @Override
    public String decrypter(String msg) {
        int p1,p2,c1,c2,inv;
            int aa,bb,cc,dd;
        String alphab="ABCDEFGHIJKLMNOPQRSTUVWXYZ abcdefghijklmnopqrstuvwxyz0123456789";

            
String tchiff = msg;
            inv = euclide_etendu((a*d-b*c), 63);
            
            aa = (d*inv)%63;
            bb = (-b*inv);
            
            while (bb<0) {
                bb = bb + 63;
            }
            
            cc = (-c*inv);
            
            while (cc<0) {
                cc = cc + 63;
            }        
            
            dd = (a*inv)%63;
            
String s1 = tchiff;

            String res = "";

            for (int i=0; i<s1.length(); i+=2) {
               
                    p1= alphab.indexOf(s1.charAt(i));
                    if(i+1<s1.length())
                        p2= alphab.indexOf(s1.charAt(i+1));
                    else p2=0;

                    
                    c1 = Math.abs((aa*p1 + bb*p2)%63);
                    c2 = Math.abs((cc*p1 + dd*p2)%63);

                    char cc1 = alphab.charAt(c1);
                    char cc2 = alphab.charAt(c2);
                    res = res + cc1 + cc2;
            }
            return res;
        
    }

}
