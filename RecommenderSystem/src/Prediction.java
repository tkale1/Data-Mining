import java.util.HashMap;
import java.util.List;

/*
 * Prediction.java : This class is used to Generate prediction based on user rating and the item similarities.
 	The predict() is implemented using the weighted sum method.
 */
public class Prediction {
	int[][] rating;
	int m;
	int n;
	double average[];
	HashMap<Integer, List<Double>> similarities;
	HashMap<Integer, List<Integer>> similarities_Index;
	
	public Prediction(int [][]ratingIn, int mIn, int nIn, double[] averageIn, HashMap<Integer, List<Double>> similaritiesIn, HashMap<Integer, List<Integer>> similarities_IndexIn)
	{
		m = mIn;
		n = nIn;
		rating = ratingIn;
		average = averageIn;
		similarities = similaritiesIn;
		similarities_Index = similarities_IndexIn;
	}

	//This predict() contains the implementation of the weighted sum method.
	public int[][] predict()
	{
		System.out.println("Making prediction based on Similarities.");
		int tempIndex = 0;
		double numerator=0;
		double denominator=0;
		for(int i =0; i< m; i++) 
		{

			for(int j=0;j < n; j++)
			{
				numerator = 0;
				denominator = 0;
				for(int k=0; k < similarities_Index.get(j).size()-100 ; k++)
				{
					tempIndex = similarities_Index.get(j).get(k);
					if(similarities.get(j).get(k) >= 0.0  && rating[i][tempIndex] > 0.0)
					{	
						numerator += similarities.get(j).get(k) * rating[i][tempIndex];
						denominator += Math.abs(similarities.get(j).get(k));
					}
				}
				if(rating[i][j] == 0)
				{
					double tempRate = (numerator/denominator);
					int tempRating = (int)Math.round(tempRate);
					if(tempRating >= 5)
					{
						rating[i][j] = 5; 
					}
					else if(tempRating <= 1)
					{
						rating[i][j] = 1;
					}
					else
					{
						rating[i][j] = tempRating;
					}
					
				}
			}
		}
		return rating;
	}//End of predict()
}
