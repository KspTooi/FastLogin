package us.ktmc.Exception;

public class PasswdRulesException extends Exception{

	private static final long serialVersionUID = 1L;
	
	Boolean TooLong=false;
	Boolean TooShort=false;
	
	public PasswdRulesException(){
		super();
	}
	
	public PasswdRulesException(String Msg){
		super(Msg);
	}
	
	public PasswdRulesException(Boolean TooLong,Boolean TooShort){
		this.TooLong=TooLong;
		this.TooShort=TooShort;
	}
	
	public boolean isTooLong(){
		return TooLong;
	}
	
	public boolean isTooShort(){
		return TooShort;
	}
	
}
