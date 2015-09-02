import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;


public class GraphGenerator {

	
	public void generate(int n , int s , int t ) throws IOException
	{
		int[][] g = new int[n][n];
		int e = 0;
		Random r = new Random();
		
		for (int i = 0; i < g.length; i++) {
			for (int j = 0; j < g.length; j++) {
				
				g[i][j] = r.nextInt(1000000)*r.nextInt(2);
				if(g[i][j]>0)
					e++;
				
			}
		}
		
		
		if(g[s][t]!=0){
			g[s][t]=0;
			e--;
		}
		if(g[t][s]!=0){
			g[t][s]=0;
			e--;
		}
		
		FileWriter f = new FileWriter(new File("input.txt"));
		f.write(n+" "+e+"\n");
		System.out.println(e);
		for (int i = 0; i < g.length; i++) {
			for (int j = 0; j < g.length; j++) {
				
				if(g[i][j]>0)
					f.write(i+" "+j+" "+g[i][j]+"\n");
			}
		}
		
		f.flush();
		f.close();
		
	}
	
	
	public static void main(String[] args) throws IOException
	{
		GraphGenerator g = new GraphGenerator();
		g.generate(3000,0,2999);
		
	}
}
