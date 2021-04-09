package githubscrapertest.helpers;

public class utility {

	static public Boolean IsInteger(String number) {
	    try{
	        Integer.parseInt(number);
	    }catch(Exception e ){
	        return false;
	    }
	    return true;
	}

}
