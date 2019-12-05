package algorithmes_sym_as;
public class Cesar implements AlgoCryptage {
	private int key=3;
	public Cesar(){	}
	public Cesar(int key){
		this.key=Math.abs(key%26);
	}
	public void setKey(int key){
		this.key=Math.abs(key%26);
	}
	public String crypter(String msg){
		String t="";
		for(int i=0,n=msg.length();i<n;i++){
			int c=(Character.isUpperCase(msg.charAt(i)))?(int)msg.charAt(i)-65:(int)msg.charAt(i)-97;
			int e=(c+key)%26;
			if(e<0) 
				e=26+e;
			if((msg.charAt(i)>=65 && msg.charAt(i)<=91)||(msg.charAt(i)>=97 && msg.charAt(i)<=123))
				t+=(Character.isUpperCase(msg.charAt(i)))?(char)(e+65):(char)(e+97);
			else t+=msg.charAt(i);
		}
		return t;
	}
	public String decrypter(String msg){
		String t="";
		key=-1*key;
	    t=crypter(msg);
	    key=-1*key;
	    return t;
	}
}
