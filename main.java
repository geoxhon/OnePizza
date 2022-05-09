
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.TreeMap;

public class main {

	public static void main(String[] args) {
		ArrayList<APizza> pizzasList = new ArrayList<APizza>();
		ArrayList<AClient> clientsList = new ArrayList<AClient>();
		ArrayList<Integer> pizzaHashes = new ArrayList<Integer>();
		TreeMap<String, Integer> map = new TreeMap<>();
        File file = new File("clients.txt");
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            int clients = Integer.parseInt(in.readLine());
            System.out.println(clients);
        for(int x=0; x<clients; x++) {
            AClient client = new AClient();
            String likes = in.readLine();
            String[] line = likes.split(" ",6);
            int numberOfLikes = Integer.parseInt(line[0]);
            for(int i = 1; i<=numberOfLikes; i++) {
                client.addPref(line[i]);
                if(map.containsKey(line[i])) {
                	map.put(line[i], map.get(line[i])+1);
                }else {
                	map.put(line[i], 1);
                }
            }
            String dislikesLine = in.readLine();
            String[] lineOfDislikes = dislikesLine.split(" ",6);
            int numberOfDislikes = Integer.parseInt(lineOfDislikes[0]);
            for(int i = 1; i<=numberOfDislikes; i++) {
                client.addDisl(lineOfDislikes[i]);
                if(map.containsKey(lineOfDislikes[i])) {
                	map.put(lineOfDislikes[i], map.get(lineOfDislikes[i])-1);
                }else {
                	map.put(lineOfDislikes[i], -1);
                }
            }
            clientsList.add(client);
            
        }
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
       // System.out.println(map);
        
        Random ran = new Random();
        int i = ran.nextInt(clientsList.size());
        APizza bestPizza = new APizza();
        for(String str: clientsList.get(i).getLikes()) {
        	bestPizza.addIngredient(str, map.get(str));
        }
        
        for(int x=0; x<clientsList.size(); x++) {
        	if(x==i) {
        		continue;
        	}
        	int scoreToRemove = 0;
        	for(String str: clientsList.get(x).getDislikes()) {
        		if(bestPizza.getIngredients().contains(str)) {
        			scoreToRemove += map.get(str);
        		}
        	}
        	int scoreToAdd = 0;
        	for(String str: clientsList.get(x).getLikes()) {
        		
        		  scoreToAdd += map.get(str);
        	}
        	
        	if(scoreToAdd>scoreToRemove) {
        		for(String disliked: clientsList.get(x).getDislikes()) {
        			bestPizza.removeIngredient(disliked);
        		}
        		for(String liked: clientsList.get(x).getLikes()) {
        			bestPizza.addIngredient(liked, map.get(liked));
        		}
        	}
        	
        	
        }
        System.out.println(bestPizza.getIngredients());
        
        int counter=0;
        for(AClient cl: clientsList) {
        	
        	if(cl.doesLikePizza(bestPizza)) {
        		counter++;
        	}
        }
        System.out.println(counter);
    }

}
