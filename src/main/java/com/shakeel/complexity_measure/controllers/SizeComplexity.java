package sizeComplexity;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class SizeComplexity  {

	
	private int Cs=0;
	private String[] ch= {"+","rishan"};
	private String[] ch2= {"Tikiri",">>"};
	
	
	public void measureSize() throws Exception{
			
		FileReader file=new FileReader("code.java");
		BufferedReader reader=new BufferedReader(file);
		
		//this.classElements(this.getClass(),1);
		
		String CurrentLine="",line;
		int numericals=0;
		
		while((line = reader.readLine())!= null) {
			
			CurrentLine=line;
				

			numericals=line.replaceAll("[^0-9]","").length();
			
				
			detectChar(CurrentLine,ch,1);
			//detectChar(CurrentLine,ch2,2);
			
		}
		
		System.out.println("Result :"+this.Cs);	
	}
	
	public void detectChar(String CurrentLine,String ch[],int val) throws Exception {
		
		for(String k:ch) {
			if(k.length()<=1) {		
				singleChar(CurrentLine,k,val);		
			}else {		
				patternM(CurrentLine,k,val);		 
			}
		}	
	}
	
	public void singleChar(String str,String ch,int val) throws Exception{
			
			str = str.replace(" ","");
				
			while(str.indexOf(ch) != -1) {
				
				if(ch=="\"") {		
					while(str.indexOf(ch)!=-1) {
						str=str.substring(str.indexOf(ch)+1);
						
						while(str.indexOf(ch)!=-1) {
							
							if(str.charAt(0)==ch.charAt(0)) {
								str=str.substring(str.indexOf(ch)+1);
								break;
							}
							
							str=str.substring(str.indexOf(ch)+1);
							this.Cs+=val;
							break;		
						}
					}
					break;
				}
							
				str=str.substring((str.indexOf(ch)));
					System.out.println(str);	
				if(str.length()==1) {
					this.Cs+=val;
					break;
				}else if((str.charAt(str.indexOf(ch)+1)==ch.charAt(0)) || str.charAt(str.indexOf(ch)+1)=='='){
					
					str=str.substring((str.indexOf(ch)+2));
					
					if(str.length()==0) {
						break;
					}else if((ch==">" || ch=="<") && (str.charAt(str.indexOf(ch))==ch.charAt(0))) {
					
						str=str.substring((str.indexOf(ch)+1));
					}if(str.length()==0) {
						break;
					}
					
				}else {
					this.Cs+=val;
					str=str.substring((str.indexOf(ch)+1));
				}		
			}		
	}
	
	public void patternM(String str,String ptn,int val) {

		char[] s=str.toCharArray();
		char[] p=ptn.toCharArray();
	     
		int LS=s.length;
		int LP=p.length;
		int max=(LS-LP+1);
		boolean flag;
		 
		 for(int i=0;i<max;i++) {
			 flag=true;
			 
			 for(int j=1;j<=LP && flag==true;j++) {
				 
				 if(p[j-1]!=s[j+i-1]) {
					 flag=false;
				 }		 
			 }
			 
			 if(flag==true) {
				  			 	 
				 if((i!=0 && str.toString().substring(i,i+LP).equals(">=")) || (i!=0 && str.toString().substring(i,i+LP).equals("<="))){
					 
					 if(str.toString().charAt(i-1)=='>' || str.toString().charAt(i-1)=='<') {
						 continue;
					 }
				 }
				 			 
				 if(str.toString().substring(i,i+LP).equals(">>") || str.toString().substring(i,i+LP).equals("<<")){
					 
					 if(str.toString().charAt(i+LP)=='=' || str.toString().charAt(i+LP)=='>' || str.toString().charAt(i+LP)=='<') {
						 
						 flag=false;
						 i++;
					 }else {				 
						 this.Cs+=val;
					 }
					 	 
				 }else if(str.toString().substring(i,i+LP).equals(">>>") && str.toString().charAt(i+LP)=='='){
					 
					 flag=false;
					 
				 }else {			 
					 this.Cs+=val;
				 }
			 }
		 }    	
	 }		

	public void classElements(Class c,int val) {
		    
		Field[] field = c.getDeclaredFields();
		Method[] method = c.getDeclaredMethods();
			    
		for(Field f:field) {
			System.out.println("method = " + f.toString());
		}
		
		for(Method m:method) {
			System.out.println("method = " + m.toString());
			patternM(m.toString(), "throws",val);
		}	
	}
}
