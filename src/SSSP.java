import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;


public class SSSP {
	
	private boolean marked[] ;
	private int parent[] ;
	private double dist[] ;
	
	
	public void dijkstra(ArrayList<ArrayList<Edge>> g ,int s )
	{
		init(g.size(),s);
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		pq.add(new Node(s,0));
		
		
		Node temp ;
		while(!pq.isEmpty())
		{
			temp = pq.poll();

			if(!marked[temp.i])
			{
				marked[temp.i] = true ;
				for (Edge e : g.get(temp.i)) {
					
					if(dist[e.to]>dist[temp.i]+e.w)
					{
						dist[e.to]=dist[temp.i]+e.w ;
						parent[e.to] = temp.i ;
						pq.add(new Node(e.to,dist[e.to]));
					}
				}
			}
			
		}
		
	}
	
	public boolean BFSSSP( ArrayList<ArrayList<Edge>> g ,int s )
	{
		init(g.size(),s);
		boolean relaxation = true ;
	
		for (int i = 0;  i < g.size()-1 && relaxation ; i++) {
			
			relaxation = false ;
			for (int j = 0; j < g.size(); j++) {
				for (Edge e : g.get(j)) {
					
					if(dist[e.to] > dist[e.from] + e.w)
					{
						dist[e.to] = dist[e.from] + e.w;
						parent[e.to] = e.from;
						relaxation = true ;
					}
				}
				
			}
		}
		
		for (int j = 0; j < g.size(); j++) {
			for (Edge e : g.get(j)) {
				
				if(dist[e.to] > dist[e.from] + e.w)
				{
					return  false ;
				}
			}
			
		}
		return true;
	}
	
	
	
	public void BFMSSSP( ArrayList<ArrayList<Edge>> g ,int s )
	{
		init(g.size(),s);
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(s);
		
		while(!queue.isEmpty())
		{
			int node = queue.poll();
			marked[node] = false ;
			for (Edge e : g.get(node)) 
			{
				if(dist[e.to] > dist[e.from]+e.w)
				{
					dist[e.to] = dist[e.from]+e.w ;
					parent[e.to] = e.from;
					if(!marked[e.to])
					{						
						queue.add(e.to);
						marked[e.to] = true ;
					}
				}
				
			}
		}
	}
	
	
	private void init(int n , int s )
	{
		marked = new boolean[n];
		parent = new int[n];
		dist = new double[n];
		
		Arrays.fill(dist, Double.MAX_VALUE-1000000);
		parent[s]=s;
		dist[s] = 0 ;
	}
	
	public int[] getPaths(){
		return parent ;
	}
	
	public double[] getPathsValues()
	{
		return dist ;
	}
	

	
	/*******************************************************************************************************************************/
	private class Node implements Comparable{
		
		int i ;
		double d ;
		
		Node(int node, double distance)
		{
			i = node ;
			d = distance ;
		}

		@Override
		public int compareTo(Object arg0) {
			// TODO Auto-generated method stub
			return (int )(this.d-((Node)arg0).d);
		}
			
		
	}
	
}
