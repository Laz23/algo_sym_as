
package algorithmes_sym_as;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;

public class DES implements AlgoCryptage {
	
    public DES(String key){
	    int i;
	    int keyBits[] = new int[64];
		String s2="";
		for(i=0;i<key.length();i++){
		    String s1 = Integer.toBinaryString((int)key.charAt(i));
		    while(s1.length() < 8) {
		         s1 = "0" + s1;
		    }
		    s2+=s1;
		}
		for(i=0;i<s2.length();i++){
		     keyBits[i]= Integer.parseInt(s2.charAt(i) + "");
		}
	    for(i=0;i<28;i++){
	         C[i]=keyBits[PC1[i]-1];
		}
		for(;i<56;i++){
	         D[i-28]=keyBits[PC1[i]-1];	
		}
	    for(i=0;i<16;i++){
	         keySheudel(i,keyBits);
	    }
    }
	private static final byte IP[]={
		58, 50, 42, 34, 26, 18, 10, 2,
		60, 52, 44, 36, 28, 20, 12, 4,
		62, 54, 46, 38, 30, 22, 14, 6,
		64, 56, 48, 40, 32, 24, 16, 8,
		57, 49, 41, 33, 25, 17, 9,  1,
		59, 51, 43, 35, 27, 19, 11, 3,
		61, 53, 45, 37, 29, 21, 13, 5,
		63, 55, 47, 39, 31, 23, 15, 7
	};
	private static final byte FP[]={
		40, 8, 48, 16, 56, 24, 64, 32,
		39, 7, 47, 15, 55, 23, 63, 31,
		38, 6, 46, 14, 54, 22, 62, 30,
		37, 5, 45, 13, 53, 21, 61, 29,
		36, 4, 44, 12, 52, 20, 60, 28,
		35, 3, 43, 11, 51, 19, 59, 27,
		34, 2, 42, 10, 50, 18, 58, 26,
		33, 1, 41, 9, 49, 17, 57, 25	
	};
	
	private static final byte PC1[]={
		57, 49, 41, 33, 25, 17, 9, 1,
		58, 50, 42, 34, 26, 18, 10, 2,
		59, 51, 43, 35, 27, 19, 11, 3,
		60, 52, 44, 36, 63, 55, 47, 39,
		31, 23, 15, 7, 62, 54, 46, 38,
		30, 22, 14, 6, 61, 53, 45, 37,
		29, 21, 13, 5, 28, 20, 12, 4
	};
	
	private static final byte PC2[]={
		14, 17, 11, 24, 1, 5, 3, 28,
		15, 6, 21, 10, 23, 19, 12, 4,
		26, 8, 16, 7, 27, 20, 13, 2,
		41, 52, 31, 37, 47, 55, 30, 40,
		51, 45, 33, 48, 44, 49, 39, 56,
		34, 53, 46, 42, 50, 36, 29, 32
	};
	
	private static final byte[] E = {
		32, 1,  2,  3,  4,  5,
		4,  5,  6,  7,  8,  9,
		8,  9,  10, 11, 12, 13,
		12, 13, 14, 15, 16, 17,
		16, 17, 18, 19, 20, 21,
		20, 21, 22, 23, 24, 25,
		24, 25, 26, 27, 28, 29,
		28, 29, 30, 31, 32, 1
	};
		
	private static final byte[][] S = { {
			14, 4,  13, 1,  2,  15, 11, 8,  3,  10, 6,  12, 5,  9,  0,  7,
			0,  15, 7,  4,  14, 2,  13, 1,  10, 6,  12, 11, 9,  5,  3,  8,
			4,  1,  14, 8,  13, 6,  2,  11, 15, 12, 9,  7,  3,  10, 5,  0,
			15, 12, 8,  2,  4,  9,  1,  7,  5,  11, 3,  14, 10, 0,  6,  13
		}, {
			15, 1,  8,  14, 6,  11, 3,  4,  9,  7,  2,  13, 12, 0,  5,  10,
			3,  13, 4,  7,  15, 2,  8,  14, 12, 0,  1,  10, 6,  9,  11, 5,
			0,  14, 7,  11, 10, 4,  13, 1,  5,  8,  12, 6,  9,  3,  2,  15,
			13, 8,  10, 1,  3,  15, 4,  2,  11, 6,  7,  12, 0,  5,  14, 9
		}, {
			10, 0,  9,  14, 6,  3,  15, 5,  1,  13, 12, 7,  11, 4,  2,  8,
			13, 7,  0,  9,  3,  4,  6,  10, 2,  8,  5,  14, 12, 11, 15, 1,
			13, 6,  4,  9,  8,  15, 3,  0,  11, 1,  2,  12, 5,  10, 14, 7,
			1,  10, 13, 0,  6,  9,  8,  7,  4,  15, 14, 3,  11, 5,  2,  12
		}, {
			7,  13, 14, 3,  0,  6,  9,  10, 1,  2,  8,  5,  11, 12, 4,  15,
			13, 8,  11, 5,  6,  15, 0,  3,  4,  7,  2,  12, 1,  10, 14, 9,
			10, 6,  9,  0,  12, 11, 7,  13, 15, 1,  3,  14, 5,  2,  8,  4,
			3,  15, 0,  6,  10, 1,  13, 8,  9,  4,  5,  11, 12, 7,  2,  14
		}, {
			2,  12, 4,  1,  7,  10, 11, 6,  8,  5,  3,  15, 13, 0,  14, 9,
			14, 11, 2,  12, 4,  7,  13, 1,  5,  0,  15, 10, 3,  9,  8,  6,
			4,  2,  1,  11, 10, 13, 7,  8,  15, 9,  12, 5,  6,  3,  0,  14,
			11, 8,  12, 7,  1,  14, 2,  13, 6,  15, 0,  9,  10, 4,  5,  3
		}, {
			12, 1,  10, 15, 9,  2,  6,  8,  0,  13, 3,  4,  14, 7,  5,  11,
			10, 15, 4,  2,  7,  12, 9,  5,  6,  1,  13, 14, 0,  11, 3,  8,
			9,  14, 15, 5,  2,  8,  12, 3,  7,  0,  4,  10, 1,  13, 11, 6,
			4,  3,  2,  12, 9,  5,  15, 10, 11, 14, 1,  7,  6,  0,  8,  13
		}, {
			4,  11, 2,  14, 15, 0,  8,  13, 3,  12, 9,  7,  5,  10, 6,  1,
			13, 0,  11, 7,  4,  9,  1,  10, 14, 3,  5,  12, 2,  15, 8,  6,
			1,  4,  11, 13, 12, 3,  7,  14, 10, 15, 6,  8,  0,  5,  9,  2,
			6,  11, 13, 8,  1,  4,  10, 7,  9,  5,  0,  15, 14, 2,  3,  12
		}, {
			13, 2,  8,  4,  6,  15, 11, 1,  10, 9,  3,  14, 5,  0,  12, 7,
			1,  15, 13, 8,  10, 3,  7,  4,  12, 5,  6,  11, 0,  14, 9,  2,
			7,  11, 4,  1,  9,  12, 14, 2,  0,  6,  10, 13, 15, 3,  5,  8,
			2,  1,  14, 7,  4,  10, 8,  13, 15, 12, 9,  0,  3,  5,  6,  11
		} 
	};
		
	private static final byte[] P = {
		16, 7,  20, 21,
		29, 12, 28, 17,
		1,  15, 23, 26,
		5,  18, 31, 10,
		2,  8,  24, 14,
		32, 27, 3,  9,
		19, 13, 30, 6,
		22, 11, 4,  25
	};
	
	private static int C[]=new int[28],D[]=new int[28];
	private static int subkey[][]=new int[16][28]; 
	
	private static int[] permute(int[] inputBits,boolean dec){
		int newBits[]= new int[inputBits.length];
		int L[]=new int[32],R[]=new int[32];
		int i;
		for(i=0;i<inputBits.length;i++){
			newBits[i]=inputBits[IP[i]-1];
		}
		System.arraycopy(newBits, 0, L, 0, 32);
		System.arraycopy(newBits, 32, R, 0, 32);
		for(i=0;i<16;i++){
			int tmp[];
			if(!dec)
				tmp=fisteil(R,subkey[i]);
			else 
				tmp=fisteil(R,subkey[15-i]);
			tmp=xor(tmp,L);
			L=R;
			R=tmp;
		}
		int output[] = new int[64];
		System.arraycopy(R, 0, output, 0, 32);
		System.arraycopy(L, 0, output, 32, 32);
		int finalOutput[] = new int[64];
		for(i=0 ; i < 64 ; i++) {
			finalOutput[i] = output[FP[i]-1];
		}
		return finalOutput;
	}
	
	private static int[] fisteil(int [] R,int [] key){
		int expantion[]=new int[48];
		for(int i=0;i<48;i++){
			expantion[i]=R[E[i]-1];
		}
		int tmp[]=xor(expantion,key);
		int out[]=sBox(tmp);
		return out;
	}
	
	private static int[] sBox(int[] t){
		int output[] = new int[32];
		for(int i=0;i<8;i++){
			int row[]=new int[2];
			int colum[]=new int[4];
			row[0]=t[6*i];
			row[1]=t[6*i+5];
			colum[0]=t[6*i+1];
			colum[1]=t[6*i+2];
			colum[2]=t[6*i+3];
			colum[3]=t[6*i+4];
			String sr=row[0]+""+row[1],sc=colum[0]+""+colum[1]+""+colum[2]+""+colum[3];
			int ir=Integer.parseInt(sr,2),ic=Integer.parseInt(sc,2);
			int x = S[i][(ir*16) + ic];
			String s = Integer.toBinaryString(x);
			while(s.length() < 4) {
				s = "0" + s;
			}
			for(int j=0 ; j < 4 ; j++) {
				output[(i*4) + j] = Integer.parseInt(s.charAt(j) + "");
			}
		}
		int finalOutput[] = new int[32];
		for(int i=0 ; i < 32 ; i++) {
			finalOutput[i] = output[P[i]-1];
		}
		return finalOutput;
	}
	
	private static int[] xor(int[] a,int[] b){
		int answer[] = new int[a.length];
		for(int i=0 ; i < a.length ; i++) {
			answer[i] = a[i]^b[i];
		}
		return answer;
	}
	
	private static int[] keySheudel(int round,int[] key){
		int C1[]=new int[28],D1[]=new int[28];
		int CD[]=new int[56];
		int K[]=new int[48];
		int rotation=(round==1 || round==2 || round==9 || round==16)? 1 : 2;
		C1=leftrotation(rotation,C);
		D1=leftrotation(rotation,D);
		System.arraycopy(C1, 0, CD, 0, 28);
		System.arraycopy(D1, 0, CD, 28, 28);
		for(int i=0;i<48;i++){
			K[i]=CD[PC2[i]-1];
		}
		subkey[round]=K;
		C=C1;
		D=D1;
		return K;
	}
	
	private static int[] leftrotation(int round,int[] tab){
		int x=tab[tab.length-1];
		for(int j=1;j<=round;j++){
			for(int i=tab.length-1;i>0;i--){
				tab[i]=tab[i-1];
			}
		}
		tab[0]=x;
		return tab;
	}

    @Override
    public String crypter(String input) {
		String s="";
		int inputBits[] = new int[64];
		for(int i=0;i<input.length();i++){
			String s1 = Integer.toBinaryString(input.charAt(i));
			while(s1.length() < 8) {
				s1 = "0" + s1;
			}
			s+=s1;
		}
		String hex="";
		int outputBits[] = null;
		for(int i=0,n=s.length();i<n;i+=64){
			for(int k=0;k<64;k++){
				if(i+k<n)
					inputBits[k]= Integer.parseInt(s.charAt(i+k) + "");
			}
			outputBits= permute(inputBits,false);
			for(int k=0 ; k < 16 ; k++) {
				String bin = new String();
				for(int j=0 ; j < 4 ; j++) {
					bin += outputBits[(4*k)+j];
				}
				int decimal = Integer.parseInt(bin, 2);
				hex += Integer.toHexString(decimal);
			}
		}
		return hex;
    }
    
    private String convertStringToHex(String str){
  	  char[] chars = str.toCharArray();
  	  StringBuffer hex = new StringBuffer();
  	  for(int i = 0; i < chars.length; i++){
  	    hex.append(Integer.toHexString((int)chars[i]));
  	  }
  	  return hex.toString();
    }
    
    private String convertHexToString(String hex){
  	  StringBuilder sb = new StringBuilder();
  	  StringBuilder temp = new StringBuilder();
  	  for( int i=0; i<hex.length()-1; i+=2 ){
  	      String output = hex.substring(i, (i + 2));
  	      int decimal = Integer.parseInt(output, 16);
  	      sb.append((char)decimal);
  	      temp.append(decimal);
  	  }  	  
  	  return sb.toString();
    }

    @Override
    public String decrypter(String input) {
        String hex,result="",s="";
        int inputBits[] = new int[64];
		int outputBits[] = null;
		byte[] bytes;
        //String msg=convertStringToHex(input);
        for(int i=0;i<input.length();i++){
        	int h = Integer.parseInt(input.charAt(i)+"", 16);
		    String s1 = Integer.toBinaryString(h);
			while(s1.length() < 4) {
				s1 = "0" + s1;
			}
			s+=s1;
		}
        for(int i=0,n=s.length();i<n;i+=64){
			for(int k=0;k<64;k++){
				if(i+k<n)
					inputBits[k]= Integer.parseInt(s.charAt(i+k) + "");
			}
			outputBits= permute(inputBits,true);
			hex = new String();
			for(int k=0 ; k < 16 ; k++) {
				String bin = new String();
				for(int j=0 ; j < 4 ; j++) {
					bin += outputBits[(4*k)+j];
				}
				int decimal = Integer.parseInt(bin, 2);
				hex += Integer.toHexString(decimal);
			}
			 bytes = DatatypeConverter.parseHexBinary(hex);
			try {
				result+= new String(bytes, "utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
        return result;
    }
}
