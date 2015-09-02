import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;


public class Main {
	
	
	public static void main(String[] args) throws IOException
	{
		
		SSSP sssp = new SSSP();
		ArrayList<ArrayList<Edge>> g =null;
		int s =0, ch=0;
		long start  , time =0;
		boolean negCycleFree  = true ;
		while(ch != 7)
		{
			System.out.println("___________________________________\n(0)Load the graph\n(1)Bellman-Ford\n(2)bellman-Ford Moore\n(3)Dijkstra\n(4)Show Shortest paths values\n(5)Show Parent Array\n(6)Show the Path\n(7)Exit\n");
			BufferedReader re = new BufferedReader(new InputStreamReader(System.in));
			ch = Integer.parseInt(re.readLine());
			
			if(ch==0)
				g= readGraph();
			else if(ch==4)
			{
				double[] spv = sssp.getPathsValues();
				for (int i = 0; i < spv.length; i++) {	
					if(i!=s)
					{
						if(spv[i]==Double.MAX_VALUE-1000000)
							System.out.println("To Node "+i+" =INF");
						else
							System.out.println("To Node "+i+" ="+spv[i]);	
					}
						
				}
			}
			else if(ch==5)
			{	
				int[] parent = sssp.getPaths();
				for (int i = 0; i < parent.length; i++) {
					if(i!=s)
						System.out.println("Parent["+i+"]="+parent[i]);
				}
				
			}
			else if(ch==6)
			{
				System.out.println("Enter sink node");
				int p = Integer.parseInt(re.readLine());
				int[] parent = sssp.getPaths();
				Stack<Integer> stack = new Stack<Integer>();
				while(p!=s)
				{
					stack.push(p);
					p = parent[p];
				}
				stack.push(s);
				while(!stack.isEmpty())
					System.out.print(stack.pop()+" ");
				System.out.println();
				
				
			}
			else if(ch!=7)
			{
				System.out.println("Enter source node :");
				s = Integer.parseInt(re.readLine());
				switch(ch)
				{
					case 1 :
						start = System.currentTimeMillis();
						negCycleFree = sssp.BFSSSP(g, s);
						time = System.currentTimeMillis()-start;
						if(!negCycleFree)
							System.out.println("Negative Cycle !");
							
						break ;
						
					case 2 :
						start = System.currentTimeMillis();
					    sssp.BFMSSSP(g, s);
						time = System.currentTimeMillis()-start;
						break ;
						
					case 3 :
						start = System.currentTimeMillis();
						sssp.dijkstra(g, s);
						time = System.currentTimeMillis()-start;
						break ;					
				}
				
				if(negCycleFree)
					System.out.println("Time = "+time+" ms");
			}	
		}
	}
	
	
	public static ArrayList<ArrayList<Edge>> readGraph() throws FileNotFoundException
	{
		ArrayList<ArrayList<Edge>> g = new ArrayList<ArrayList<Edge>>();
		Scanner sc = new Scanner(new FileInputStream(new File("input.txt")));
		int v  = sc.nextInt() ; 
		int e = sc.nextInt() ;
		System.out.println(v+" "+e);
		for (int i = 0; i < v; i++) {
			g.add(new ArrayList<Edge>());
		}

		int f , to  ;
		double w ;
		for (int i = 0; i < e; i++) {
			
			f = sc.nextInt() ; to = sc.nextInt() ; w = sc.nextDouble();
			g.get(f).add(new Edge(f,to,w));
		}
		
		sc.close();
		return g ;
		
	}

}
