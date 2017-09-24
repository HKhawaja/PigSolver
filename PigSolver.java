public class PigSolver {

    public static void main (String args[]) {
	int T=-1;
	int x=-1;
	int y=-1;
	
	try {
	    T = Integer.parseInt(args[0]);
	    x = Integer.parseInt(args[1]);
	    y = Integer.parseInt(args[2]);
	}
	catch (ArrayIndexOutOfBoundsException e) {
	    System.out.println ("Give total, player 1 score and player 2 score");
	    System.exit(2);
	}
	
	double [][] firstWin = new double [T-y+1][T-x+1];
	double [][] secondWin = new double [T-y+1][T-x+1];
	double [][] thirdWin = new double [T-y+1][T-x+1];
	
	int n = 0;
	int turnTotal = -1;
	PigProbabilities prob = new PigProbabilities (T);

	//initialize base cases
	for (int i = 0; i<firstWin.length; i++) {
	    for (int j = 0; j<firstWin[0].length;j++) {
		if (j==firstWin[0].length-1 && i==firstWin.length-1) {
		    firstWin[i][j] = 1.0;
		}
		else if (j==firstWin[0].length-1) {
		    firstWin[i][j] = 1.0;
		}
		else if (i==firstWin.length-1) {
		    firstWin[i][j] = 0.0;
		}
		else {
		    firstWin[i][j] = 0.5;
		}
	    }
	}
			    
	while (true) {
	    if (n>=2 && n%2==0 && equals(firstWin,thirdWin)) {
		break;
	    }
	    
	    else {
		n++;
		if (n==1) { //create the 2nd table
		    double [][] second =  new double [T-y+1][T-x+1];
		    initializeCases (second);
		    
		    for (int i = 0; i<second.length; i++) {
			for (int j = 0; j<second[0].length; j++) {
			    
			    if (second[i][j] == -1) {
			   
				double minProbability = 1;
				
				//value of x computing = we compute [j+x] at index j
				//value of y computing = we compute [i+y] at index i
				int max = Math.max(2,T-i);

				
				for (int S =2; S<=max;S++) {
				    double probability = firstWin[i][j]*prob.pEndAt(S,0);
				    double sum=0;
				    for (int s=S; s<=S+5; s++) {
					if (s+i>=firstWin.length) {   
					    sum+= prob.pEndAt(S,s)*0.0;
					    continue;
					}
					else {
					    sum+=firstWin[s+i][j]*prob.pEndAt(S,s);
					}
				    }
				    probability+=sum;
				    if (probability<=minProbability) {
					minProbability=probability;
				    }
				}
				second[i][j] = minProbability;
			    }
			}
		    }
		    
		    secondWin = second;
		    
		}
		else if (n == 2) { //create the third array
		    double[][] third = new double [T-y+1][T-x+1];
		    initializeCases (third);
		    
		    for (int i = 0; i<third.length; i++) {
			for (int j = 0; j<third[0].length; j++) {
			    
			    if (third[i][j] == -1) {
			   
				double maxProbability = 0;
				
				//value of x computing = we compute [j+x] at index j
				//value of y computing = we compute [i+y] at index i
				int max = Math.max(2,T-j);
				
				for (int S =2; S<=max;S++) {
				    double probability = secondWin[i][j]*prob.pEndAt(S,0);
				    double sum=0;
				    for (int s=S; s<=S+5; s++) {
					if (s+j>=secondWin[0].length) {   
					    sum+= prob.pEndAt(S,s)*1.0;
					    continue;
					}
					else {
					    sum+=secondWin[i][s+j]*prob.pEndAt(S,s);
					}
				    }
				    probability+=sum;
				    if (probability>=maxProbability) {
					maxProbability=probability;
					if (i==0 && j==0) {
					    turnTotal = S;
					}
				    }
				}
				third[i][j] = maxProbability;
			    }
			}
		    }

		    thirdWin = third;
		}
		else {
		    firstWin = secondWin;
		    secondWin = thirdWin;

		    double[][] third = new double [T-y+1][T-x+1];
		    initializeCases (third);
		    
		    if (n%2==0) {
			for (int i = 0; i<third.length; i++) {
			    for (int j = 0; j<third[0].length; j++) {
				
				if (third[i][j] == -1) {
			   
				    double maxProbability = 0;
				    
				    //value of x computing = we compute [j+x] at index j
				    //value of y computing = we compute [i+y] at index i
				
				    int max = Math.max(2,T-j);
				    for (int S =2; S<=max;S++) {
					double probability = secondWin[i][j]*prob.pEndAt(S,0);
					double sum=0;
					for (int s=S; s<=S+5; s++) {
					    if (s+j>=secondWin[0].length) {  
						sum+= prob.pEndAt(S,s)*1.0;
						continue;
					    }
					    else {
						sum+=secondWin[i][s+j]*prob.pEndAt(S,s);
					    }
					}
					probability+=sum;
					if (probability>=maxProbability) {
					    maxProbability=probability;
					    if (i==0&&j==0) {
						turnTotal = S;
					    }
					}
				    }
				    third[i][j] = maxProbability;
				}
			    }
			    
			}
		    }
		    else {
			for (int i = 0; i<third.length; i++) {
			    for (int j = 0; j<third[0].length; j++) {
			    
				if (third[i][j] == -1) {
			   
				    double minProbability = 1;
				
				    //value of x computing = we compute [j+x] at index j
				    //value of y computing = we compute [i+y] at index i
				    int max = Math.max(2,T-i);
				    
				    for (int S =2; S<=max;S++) {
					double probability = secondWin[i][j]*prob.pEndAt(S,0);
					double sum=0;
					for (int s=S; s<=S+5; s++) {
					    if (s+i>=secondWin.length) {   
						sum+= prob.pEndAt(S,s)*0.0;
						continue;
					    }
					    else {
						sum+=secondWin[s+i][j]*prob.pEndAt(S,s);
					    }
					}
					probability+=sum;
					if (probability<=minProbability) {
					    minProbability=probability;
					}
				    }
				    third[i][j] = minProbability;
				}
			    }
			}
		    
		    }
		    thirdWin = third;
		}
		
	    }
	}

	System.out.println (n + " iterations");
	System.out.print (thirdWin[0][0] + " ");
	System.out.println (turnTotal);
    }
    
    public static void initializeCases(double[][]array) {
	for (int i = 0; i<array.length; i++) {
	    for (int j =0; j<array[0].length; j++) {
		if (j==array[0].length-1 && i==array.length-1) {
		    array[i][j] = 1.0;
		}
		else if (j==array[0].length-1) {
		    array[i][j] = 1.0;
		}
		else if (i==array.length-1) {
		    array[i][j] = 0.0;
		}
		else {
		    array[i][j] = -1;
		}
	    }
	}
	
    }

    public static boolean equals (double[][]a, double[][]b) {
	for (int i = 0; i<a.length; i++) {
	    for (int j = 0; j<a[0].length; j++) {
		
		if (a[i][j] != b[i][j])
		    return false;

	    }
	}

	return true;
    }
    
}

    