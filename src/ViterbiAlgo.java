
public class ViterbiAlgo {
	static String[] states={"F","L"};
	static int[] observations;
	static double[] initial_prob; 
	static double[][] transition_prob;
	static double[][] emission_prob;
	public ViterbiAlgo(){
//		observations= new int[]{6,5,1,1,6,6,4,5,3,1,3,2,6,5,1,2,4,5,6,3,6,6,6,4,6,3,1,6,3,6,6,6,3,1,6,2,3,2,6,4,5,5,2,3,5,2,6,6,6,6,6,6,2,5,1,5,1,6,3,1};
//		observations= new int[]{2,2,2,5,5,5,4,4,1,6,6,6,5,6,6,5,6,3,5,6,4,3,2,4,3,6,4,1,3,1,5,1,3,4,6,5,1,4,6,3,5,3,4,1,1,1,2,6,4,1,4,6,2,6,2,5,3,3,5,6};
//		observations= new int[]{3,6,6,1,6,3,6,6,6,4,6,6,2,3,2,5,3,4,4,1,3,6,6,1,6,6,1,1,6,3,2,5,2,5,6,2,4,6,2,2,5,5,2,6,5,2,5,2,2,6,6,4,3,5,3,5,3,3,3,6};
		observations= new int[]{2,3,3,1,2,1,6,2,5,3,6,4,4,1,4,4,3,2,3,3,5,1,6,3,2,4,3,6,3,3,6,6,5,5,6,2,4,6,6,6,6,2,6,3,2,6,6,6,6,1,2,3,5,5,2,4,5,2,4,2};

		initial_prob= new double[]{0.5,0.5};
		transition_prob=new double[][]{
			{0.95,0.05},
			{0.10,0.90}
		};
		double t=1.0/6.0;
		emission_prob=new double[][]{
			{t,t,t,t,t,t},
			{0.1,0.1,0.1,0.1,0.1,0.5}
		};
	}
	public int[] compute(int[] observations, double[] initial_prob, double[][] transition_prob, double[][] emission_prob){
		double[][] path =new double[observations.length][2];
		int[][] max=new int[observations.length][2];
		int[] sequence= new int[observations.length];
		//initialization
		for(int i=0;i<2;i++){
			path[0][i]=initial_prob[i]*emission_prob[i][observations[0]-1];
		}
		max[0][0]=0;
		max[0][1]=0;
		//recursion
		for(int i=1;i<observations.length;i++){
			for(int j=0;j<2;j++){
				double tmp1=path[i-1][0]*transition_prob[0][j];
				double tmp2=path[i-1][1]*transition_prob[1][j];
				double big;
				if(tmp1>tmp2){
					big=tmp1;
					max[i][j]=0;
				}else{
					big =tmp2;
					max[i][j]=1;
				}
				path[i][j]=big*emission_prob[j][observations[i]-1];
			}
		}
//		double max_prob=Math.max(path[observations.length-1][0], path[observations.length-1][1]);
		if(path[observations.length-1][0]>path[observations.length-1][1]){
			sequence[observations.length-1]=0;
		}else{
			sequence[observations.length-1]=1;
		}
		for(int i=observations.length-2;i>=0;i--){
			sequence[i]=max[i+1][sequence[i+1]];
		}
		return sequence;
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ViterbiAlgo va= new ViterbiAlgo();
		System.out.print("Rolls    ");
		for(int i=0;i<observations.length;i++){
			System.out.print(String.valueOf(observations[i]));
		}
		System.out.println("");
		System.out.print("Die      ");
//		System.out.print("LLLLLLFFFFFFFFFFFFLLLLLLLLLLLLLLLLFFFLLLLLLLLLLLLLLFFFFFFFFF");
//		System.out.print("FFFFFFFFLLLLLLLLLLLLLFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFL");
//		System.out.print("LLLLLLLLFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF");

		System.out.print("FFFFFFFFFFFFFFFFFFFFFFFFFFFLLLLLLLLLLLLLLLLLLLLLLFFFFFFFFFFF");

		int[] result=va.compute(observations, initial_prob, transition_prob, emission_prob);
		System.out.println("");
		System.out.print("Viterbi  ");
		for(int i:result){
			System.out.print(states[i]);
		}

	}

}
