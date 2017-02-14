/*
 *	Author 	: Tanmay Kale
 *	Subject : CS535 Data Mining
 *	Aim		: To implement Recommender System.
 *
 */

import java.util.HashMap;
import java.util.List;

/*
 * Driver.java : This file is used to call all the necessary functions which are
 * 				required to execute a Recommendation System
 * 				This class take 4 agruments.
 				1. The name of the input file.
 				2. The total no. of Users
 				3. The total no. of Items.
 				4. THe name of the output file.
 */

public class Driver {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		if(4 != args.length)
		{
			System.out.println("Pleas Check the total no. of arguments.");
			System.exit(-1);
		}
		String input_file = args[0];
		int m = Integer.parseInt(args[1]);
		int n = Integer.parseInt(args[2]);
		String output_file = args[3];

		int[][] rating = new int[m][n];
		double[] userAvgRating = new double[m];
		
		FileProcessor fpObj = new FileProcessor(m, n, input_file,output_file);
		rating = fpObj.readFromFile();
		
		Display dis = new Display(rating, m, n);
		CalculateAverage ca = new CalculateAverage(rating,m,n);
		userAvgRating = ca.getUserAvg();
		
		Similarities_Generation sim = new Similarities_Generation (rating,m,n,userAvgRating);
		sim.similarity();
		HashMap<Integer, List<Double>> similarities = sim.getsimilarities();
		HashMap<Integer, List<Integer>> similarities_Index = sim.getsimilaritiesIndex();
		
		Prediction pred = new Prediction(rating,m,n,userAvgRating,similarities,similarities_Index);
		rating = pred.predict();
		//dis.printData();
		fpObj.writeToFile(rating);

	}

}
