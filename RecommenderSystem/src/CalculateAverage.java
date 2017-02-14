
/*
 * CalculateAverage.java : This class is used to calculate the average rating of all the users.
 */
public class CalculateAverage {

	int m;
	int n;
	int[][] rating;

	public CalculateAverage(int[][] ratingIn, int mIn, int nIn)
	{
		m = mIn;
		n = nIn;
		rating = ratingIn;
	}
	// returns the array which contains avg rating given each user.
	public double[] getUserAvg()
	{
		
		int i = 0, j = 0, k = 0;
		double temp = 0;
		double temp1 = 0;
		
		double[] average = new double[m];
		System.out.println("Generating Average of User Ratings.");
		for (i = 0; i < m; i++) {
			temp = 0;
			temp1 = 0;
			k = 0;
			for (j = 0; j < n; j++) {
				if (0 == rating[i][j]) {
					continue;
				} else {
					temp += rating[i][j];
					k++;
				}
			}

			temp1 = (double) temp / k;
			average[i] = Math.round(temp1 * 100.0) / 100.0;
		}
		return (average);
	}

}
