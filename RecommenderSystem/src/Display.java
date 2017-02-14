
/*
 * Display.java : This class is used to Display the output of the recommendation system.
 */
public class Display {

	int[][] rating;
	int m;
	int n;
	public Display(int[][] ratingIn, int mIn, int nIn) {
		m = mIn;
		n = nIn;
		rating = ratingIn;
	}
	//Prints the output of the file on conlose.
	public void printData() {
		System.out.println("Printing whole data on the console");
		// printing all read data;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				System.out.println((i + 1) + " " + (j + 1) + " " + rating[i][j]);
			}
		}

	}

}
