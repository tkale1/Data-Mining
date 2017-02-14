import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 * Similarities_Generation.java : This class is used to Generate Similarities based on user rating.
 *	The similarity() implements the adjacent cosine method.
 */
public class Similarities_Generation {
	
	int[][] rating;
	int m;
	int n;
	double average[];
	
	HashMap<Integer, List<Double>> similarities;// = new HashMap<Integer, List<Double>>();
	HashMap<Integer, List<Integer>> similarities_Index;// = new HashMap<Integer, List<Integer>>();
	public Similarities_Generation(int [][]ratingIn, int mIn, int nIn, double[] averageIn)// HashMap<Integer, List<Double>> similaritiesIn, HashMap<Integer, List<Integer>> similarities_IndexIn)
	{
		m = mIn;
		n = nIn;
		rating = ratingIn;
		average = averageIn;
		similarities = new HashMap<Integer, List<Double>>();
		similarities_Index = new HashMap<Integer, List<Integer>>();
	}
	
	//This similarity() contains the implementation of the adjacent consine method to generate similarities.
	void similarity()
	{
		System.out.println("Generating Item Based Similarities.");
		double numerator = 0;
		double denominator = 0;
		double tempNumer = 0;
		double tempDemon1 = 0;
		double tempDemon2 = 0;
		for (int i = 0; i < n; i++)
		{
			if (!similarities.containsKey(i) && !similarities_Index.containsKey(i))
			{
				similarities.put(i, new ArrayList<Double>());
				similarities_Index.put(i, new ArrayList<Integer>());
			}
			
			for (int j = i + 1; j < n; j++)
			{
			
				if (!similarities.containsKey(j) && !similarities_Index.containsKey(j))
				{
					similarities.put(j, new ArrayList<Double>());
					similarities_Index.put(j, new ArrayList<Integer>());
				}
				tempNumer = tempDemon1 = tempDemon2 = 0;
				for (int k = 0; k < m; k++)
				{
					if (rating[k][i] != 0 && rating[k][j] != 0)
					{
						tempNumer += (rating[k][i] - average[k]) * (rating[k][j] - average[k]);
						tempDemon1 += Math.pow((rating[k][i] - average[k]), 2);
						tempDemon2 += Math.pow((rating[k][j] - average[k]), 2);
						
					}
				}
				if (tempNumer > 0.0) // can keep tempNumer >0 and skip the negative values.
				{

					numerator = tempNumer;
					denominator = Math.sqrt(tempDemon1) * Math.sqrt(tempDemon2);
					double tempAns = Math.round(numerator / denominator * 100.0) / 100.0;
					//System.out.println(tempAns);
					if(tempAns > 0)
					{
						similarities.get(i).add(tempAns);
						similarities.get(j).add(tempAns);
						similarities_Index.get(i).add(j);
						similarities_Index.get(j).add(i);
					}

				}				
			}

		}	
	}// End of Similarities_generation function

	// Getter method which returns similatiries.
	public HashMap<Integer, List<Double>> getsimilarities()
	{
		return similarities;
	}
	// Getter method which returns item indexes of the generated similarities.
	public HashMap<Integer, List<Integer>> getsimilaritiesIndex()
	{
		return similarities_Index;
	}
}
