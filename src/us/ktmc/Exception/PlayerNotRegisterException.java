package us.ktmc.Exception;

public class PlayerNotRegisterException extends Exception{

	private static final long serialVersionUID = 1L;

	public PlayerNotRegisterException(){
		super();
	}

	public PlayerNotRegisterException(String Msg){
		super(Msg);	
	}
	
	
}
