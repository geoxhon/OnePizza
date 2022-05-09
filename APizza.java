import java.util.ArrayList;
public class APizza {
   private ArrayList<String> ingredients;
   private int score;
   public APizza() {
	   ingredients = new ArrayList<String>();
	   score = 0;
   }
   public void addIngredient(String in, int score) {
	   if(!ingredients.contains(in)) {
		   ingredients.add(in);
		   this.score += score;
	   }
	   
   }
   public ArrayList<String> getIngredients(){
	   return ingredients;
   }
   public int getScore() {
	   return score;
   }
   
   public void removeIngredient(String ingr) {
	   ingredients.remove(ingr);
   }
}
