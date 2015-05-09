package common;

import java.util.HashMap;

public class DistributedRandomNumberGenerator {
	
	private HashMap<Integer, Double> distribution;
    private double distSum;

    public DistributedRandomNumberGenerator() {
        distribution = new HashMap<>();
    }

    public void addNumber(int value, double distribution) {
        if (this.distribution.get(value) != null) {
            distSum -= this.distribution.get(value);
        }
        this.distribution.put(value, distribution);
        distSum += distribution;
    }

    public int getDistributedRandomNumber() {
        double rand = Math.random();
        double ratio = 1.0f / distSum;
        double tempDist = 0;
        for (Integer i : distribution.keySet()) {
            tempDist += distribution.get(i);
            if (rand / ratio <= tempDist) {
                return i;
            }
        }
        return 0;
    }
	
    public static int getNumber() {	
    	
    	DistributedRandomNumberGenerator rand = new DistributedRandomNumberGenerator();
		rand.addNumber(0, 0.1);
		rand.addNumber(1, 0.2);
		rand.addNumber(2, 0.6);
		rand.addNumber(3, 0.05);
		rand.addNumber(4, 0.05);
		
		return rand.getDistributedRandomNumber();    	
    }
    
    
    

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		for (int i = 0;  i < 20; i++) {
			System.out.println(DistributedRandomNumberGenerator.getNumber());
		}
	}

}
