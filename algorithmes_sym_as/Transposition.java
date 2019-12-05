package algorithmes_sym_as;
public class Transposition implements AlgoCryptage {
	private int key;
	public Transposition(int key){
		
		this.key=Math.abs(key);
	}
        
        public void setKey(int key){
            this.key=Math.abs(key);
        }

	@Override
	public String crypter(String input) {
		// TODO Auto-generated method stub
		char[][] mat=new char[((int)input.length()/key)+1][key];
		int start=0;
                String s="";
                for(int i=0;i<input.length();i++)
                {
                    if(input.charAt(i)==' ')
                    {
                        s=s+input.substring(start,i)+' ';
                        start=i+1;
                    }
                }
                s=s+input.substring(start);
                System.out.println(((int)input.length()/key)+1);
		for(int i=0,n=((int)s.length()/key)+1;i<n;i++){
			for(int j=0;j<key;j++){
				if(key*i+j<s.length())
				mat[i][j]=s.charAt(key*i+j);
                                
                                System.out.print(mat[i][j]);
			}
                        System.out.println();
		}
                
		s="";
		for(int j=0;j<key;j++){
			for(int i=0,n=((int)input.length()/key)+1;i<n;i++){
				s+=mat[i][j];
			}
		}
		return s;
	}

	@Override
	public String decrypter(String input) {
		// TODO Auto-generated method stub
		char[][] mat=new char[key][((int)input.length()/key)];
		
		for(int i=0,n=((int)input.length()/key);i<key;i++){
			for(int j=0;j<n;j++){
				if(key*i+j<input.length())
				mat[i][j]=input.charAt(n*i+j);
			System.out.print(mat[i][j]);
			}
                        System.out.println();
		}
		String s="";
		for(int j=0,n=((int)input.length()/key);j<n;j++){
			for(int i=0;i<key;i++){
				s+=mat[i][j];
			}
		}
		return s;
	}

}
//karim remmide is testing his crypto app but he is not happy car it contains error that have not gone but he wish that they gone now or else he will be very mad