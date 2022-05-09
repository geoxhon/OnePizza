import java.util.ArrayList;

public class AClient {
    private ArrayList<String> preferences;
    private ArrayList<String> dislikes;
    private int preferenceHash;
    
    public int getPreferenceHash() {
		return preferenceHash;
	}
	
    
    public AClient() {
        preferences = new ArrayList<>();
        dislikes = new ArrayList<>();
        preferenceHash = 0;
    }
    
    public void addPref(String ingr) {
        preferences.add(ingr);
        for(int i = 0; i<ingr.length();i++) {
        	preferenceHash = preferenceHash+ingr.charAt(i);
        }
    }
    
    public void addDisl(String ingr) {
        dislikes.add(ingr);
    }
    public boolean doesLikePizza(APizza Pizza) {
    	ArrayList<String> pizzaingredients = Pizza.getIngredients();
    	for(int i = 0; i<preferences.size(); i++) {
    		if(!pizzaingredients.contains(preferences.get(i))) {
    			return false;
    		}
    	}
    	for(int i = 0; i<dislikes.size(); i++) {
    		if(pizzaingredients.contains(dislikes.get(i))) {
    			return false;
    		}
    	}
    	return true;
    }
    public ArrayList<String> getLikes(){
    	return preferences;
    }


	public ArrayList<String> getDislikes() {
		return dislikes;
	}
    
    

}