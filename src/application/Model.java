package application;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;
import org.jgrapht.alg.BellmanFordShortestPath;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.alg.FloydWarshallShortestPaths;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.DepthFirstIterator;

import bean.Movimento;
import db.Dao;

public class Model {
	
	private Dao dao = new Dao();
	private DefaultDirectedWeightedGraph<Integer, DefaultWeightedEdge>grafo;
	
	public List<Integer> getCircoscrizioni(){                 //x la combo   //ok
		List<Integer> circo= dao.getCircoscrizioni();
		System.out.println(circo);
		return circo;
	}
	
	public List<String> getNumCambiEDest(int provenienza){                           //ok
		List<String> all= dao.getNumCambiEDestinazione(provenienza);
		return all;
	}
	

	public int getPesoArco(int c1, int c2){
		int peso = dao.CalcolaPesoCircoscrizioni(c1, c2);
		return peso;
	}
	
	
	public DefaultDirectedWeightedGraph<Integer, DefaultWeightedEdge> buildGraph(){
		grafo = new DefaultDirectedWeightedGraph<Integer, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		Graphs.addAllVertices(grafo, getCircoscrizioni());
		for(Integer i1 : grafo.vertexSet()){
			for(Integer i2 : grafo.vertexSet()){
				if(i1!=i2){
				int peso = getPesoArco(i1, i2);
				if(!grafo.containsEdge(i1, i2)){
					Graphs.addEdge(grafo, i1,  i2,  peso);
				}
				}
			}
		}
		System.out.println(grafo.toString());
		return grafo;
	}

	
	//determino l'arco piu pesante
	
	public double getArcoPiuPesante(DefaultDirectedWeightedGraph<Integer, DefaultWeightedEdge> grafo){   //si funziona
		double max =-1;
		for(DefaultWeightedEdge e : grafo.edgeSet()){
			if(grafo.getEdgeWeight(e)>max){
				max = grafo.getEdgeWeight(e);
			}
		}
		System.out.println(max);
		return max;
		
	}

	
	public List<Integer> getCamminiMinimiFloyd(Integer i1, Integer i2){            //ok
		FloydWarshallShortestPaths<Integer, DefaultWeightedEdge> floy = new FloydWarshallShortestPaths<Integer, DefaultWeightedEdge>(grafo );
		System.out.println(Graphs.getPathVertexList(floy.getShortestPath(i1, i2)));
		return Graphs.getPathVertexList(floy.getShortestPath(i1, i2));
		
	}
	
	public List<Integer> getCamminiDijkstra(Integer i1, Integer i2){                //ok
		DijkstraShortestPath<Integer, DefaultWeightedEdge> di = new DijkstraShortestPath<Integer, DefaultWeightedEdge>(grafo, i1, i2);
		GraphPath<Integer, DefaultWeightedEdge> path= di.getPath();
		if(path==null){
			System.out.println("null");
			return null;
		}
		System.out.println(Graphs.getPathVertexList(path));
		return Graphs.getPathVertexList(path);
	}
	
	
	public Map<Integer, Double> getBellmanFord(Integer partenza){   //ok
		BellmanFordShortestPath<Integer, DefaultWeightedEdge> bell = new BellmanFordShortestPath<Integer, DefaultWeightedEdge>(grafo, partenza);
		Map<Integer, Double> distanze = new HashMap<>();
		for(Integer destinazione : grafo.vertexSet()){
			if(!destinazione.equals(partenza)){
				double peso = bell.getCost(destinazione);
				distanze.put(destinazione, peso);
				
			}
		}
		System.out.println(distanze);
		return distanze;
	}
	
	public List<Integer> getBread(Integer start){   //lo calcola x 1
		BreadthFirstIterator<Integer, DefaultWeightedEdge> bread = new BreadthFirstIterator<Integer, DefaultWeightedEdge>(grafo, start);
		List<Integer> all= new LinkedList<>();
		while(bread.hasNext()){
			Integer i = bread.next();
			all.add(i);
		}
		System.out.println(all);
		return all;
	}
	
	
	public List<Integer> getB2(){                 //lo calcola per tutti
		 List<Integer> all= new LinkedList<>();
		for(Integer v : grafo.vertexSet()){
		     BreadthFirstIterator<Integer, DefaultWeightedEdge> bread = new BreadthFirstIterator<Integer, DefaultWeightedEdge>(grafo, v);
		     while(bread.hasNext()){
			   Integer i = bread.next();
			   all.add(i);
		       }
		  }
		System.out.println(all);
		return all;
	}

	public int  getBb(){                 //lo calcola per tutti
		// List<Integer> all= new LinkedList<>();
		 int contatore =-1;
		 for(Integer v : grafo.vertexSet()){
		     BreadthFirstIterator<Integer, DefaultWeightedEdge> bread = new BreadthFirstIterator<Integer, DefaultWeightedEdge>(grafo, v);
		      while(bread.hasNext()){
			   Integer i = bread.next();
			   contatore ++;
		       }
		  }
		System.out.println(contatore);
		return contatore;
	}
	
	
	public int  getBBBB(Integer i){                 //lo calcola per uno
		// List<Integer> all= new LinkedList<>();
		 int contatore =-1;
		     BreadthFirstIterator<Integer, DefaultWeightedEdge> bread = new BreadthFirstIterator<Integer, DefaultWeightedEdge>(grafo, i);
		      while(bread.hasNext()){
			   Integer ii = bread.next();
			   contatore ++;
		       }
		System.out.println(contatore);
		return contatore;
	}
	
		
	public List<Integer> getDept(){                  //dept x tutti
		List<Integer> visitati = new LinkedList<>();
		for(Integer v : grafo.vertexSet()){
		    DepthFirstIterator<Integer, DefaultWeightedEdge> dep = new DepthFirstIterator<Integer, DefaultWeightedEdge>(grafo, v);
		    while(dep.hasNext()){
			 Integer i = dep.next();
			 visitati.add(i);
		    }
		}
		System.out.println(visitati);
		return visitati;
	}

	public List<Integer> getDeptUno(Integer start){    //dept x uno
		List<Integer> visitati = new LinkedList<>();
		    DepthFirstIterator<Integer, DefaultWeightedEdge> dep = new DepthFirstIterator<Integer, DefaultWeightedEdge>(grafo, start);
		    while(dep.hasNext()){
			 Integer i = dep.next();
			 visitati.add(i);
		    }
		System.out.println(visitati);
		return visitati;
	}
	
	public int  getDDDD(Integer i){                 //lo calcola per uno
		// List<Integer> all= new LinkedList<>();
		 int contatore =-1;
		     DepthFirstIterator<Integer, DefaultWeightedEdge> bread = new DepthFirstIterator<Integer, DefaultWeightedEdge>(grafo, i);
		      while(bread.hasNext()){
			   Integer ii = bread.next();
			   contatore ++;
		       }
		System.out.println(contatore);
		return contatore;
	}
	
	
	public int getDepthTutti(){
	 int contatore =-1;
	 for(Integer v : grafo.vertexSet()){
	     DepthFirstIterator<Integer, DefaultWeightedEdge> bread = new DepthFirstIterator<Integer, DefaultWeightedEdge>(grafo, v);
	      while(bread.hasNext()){
		   Integer i = bread.next();
		   contatore ++;
	       }
	  }
	System.out.println(contatore);
	return contatore;
}
	
	
	public static void main(String [] args){
		Model m = new Model();
		DefaultDirectedWeightedGraph<Integer, DefaultWeightedEdge> grafo = m.buildGraph();
		//m.getArcoPiuPesante(grafo);
		//m.getCamminiMinimiFloyd(2, 6);
		//m.getCamminiDijkstra(2, 6);
		//m.getBellmanFord(4);
		//m.getBread(0);
		//m.getB2();
		//m.getBb();          //120
		//m.getBBBB(5);     
		//m.getDept();
		//m.getDeptUno(4);
		//m.getDDDD(5);
		//m.getDepthTutti();

	}

}
