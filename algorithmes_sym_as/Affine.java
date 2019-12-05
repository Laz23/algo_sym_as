package algorithmes_sym_as;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DELL
 */
public class Affine implements AlgoCryptage {
    private int a,b;
    public Affine(int a,int b){
        this.a=Math.abs(a%63);
        this.b=Math.abs(b%63);
    }
    
    public void setKeys(int a,int b){
        this.a=Math.abs(a%63);
        this.b=Math.abs(b%63);
    }

    @Override
    public String crypter(String str2crypt) {
        //str2crypt=str2crypt.replace(" ","");
        String alphab="ABCDEFGHIJKLMNOPQRSTUVWXYZ abcdefghijklmnopqrstuvwxyz0123456789";
	String affstr="";		
	try{	
            for(int i=0;i<str2crypt.length();i++){
                
                        affstr=affstr+alphab.substring((((alphab.indexOf(str2crypt.charAt(i))*a)+b)%63),(((alphab.indexOf(str2crypt.charAt(i))*a)+b)%63)+1);          
                
            }
	}catch (Exception e){
	    e.printStackTrace();
	}	
	return affstr;
    }

    @Override
    public String decrypter(String str2decrypt) {
        String affstr1="";
        String alphab="ABCDEFGHIJKLMNOPQRSTUVWXYZ abcdefghijklmnopqrstuvwxyz0123456789";
	//str2decrypt=str2decrypt.replace(" ","");
	
	int invA=0;
	for(int j=1;j<64;j++){
	    if((a*j)%63==1){
		invA=j;
		break;
            }
	}
	try{
	    for(int m=0;m<str2decrypt.length();m++){
                
                    if(((alphab.indexOf(str2decrypt.charAt(m))-b)*invA)>=0){
                        affstr1=affstr1+alphab.substring(((invA*((alphab.indexOf(str2decrypt.charAt(m)))-b))%63),((invA*((alphab.indexOf(str2decrypt.charAt(m)))-b))%63)+1);
                    }else{
                        affstr1=affstr1+alphab.substring(((invA*((alphab.indexOf(str2decrypt.charAt(m)))-b))%63)+63,((invA*((alphab.indexOf(str2decrypt.charAt(m)))-b))%63)+64);
                    }
                
            }
	}catch(Exception e1){
	    e1.printStackTrace();
	}
	
	return affstr1;
    }
    
}
