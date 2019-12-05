package algorithmes_sym_as;
public class Vigenere implements AlgoCryptage {
	private String key;
	public Vigenere(String key){
		this.key=key;
	}
	@Override
	public String crypter(String msg) {
		String t="";
		int j=0,m=key.length();
		Cesar cesar=new Cesar();
		for(int i=0,n=msg.length();i<n;i++){
			int nkey=(Character.isUpperCase(key.charAt(j)))?(int)key.charAt(j)-65:(int)key.charAt(j)-97;
			cesar.setKey(nkey);
			t+=cesar.crypter(msg.charAt(i)+"");
			j=(j+1)%m;
		}
		return t;
	}

	@Override
	public String decrypter(String msg) {
		String t="";
		int j=0,m=key.length();
		Cesar cesar=new Cesar();
		for(int i=0,n=msg.length();i<n;i++){
			int nkey=(Character.isUpperCase(key.charAt(j)))?(int)key.charAt(j)-65:(int)key.charAt(j)-97;
			cesar.setKey(nkey);
			t+=cesar.decrypter(msg.charAt(i)+"");
			j=(j+1)%m;
		}
		return t;
	}
	
}
