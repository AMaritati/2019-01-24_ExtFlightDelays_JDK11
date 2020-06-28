package it.polito.tdp.extflightdelays.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {
	private Graph<String, DefaultWeightedEdge> graph;
	private ExtFlightDelaysDAO dao;
	
	public Model() {
		dao = new ExtFlightDelaysDAO();
	}
	
	public void creaGrafo() {
		graph = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(this.graph, dao.loadAllStates());
		
		for(Adiacenza a: dao.loadAd()) {
			DefaultWeightedEdge e = graph.getEdge(a.getS1()	, a.getS2());
			if (e == null) {
				Graphs.addEdgeWithVertices(graph, a.getS1(), a.getS2(), a.getPeso());
			}
			
		}
	}
	
	public List<Vicino> getVicini(String source){
		List<Vicino> lista = new ArrayList<>();
		for(DefaultWeightedEdge e : this.graph.outgoingEdgesOf(source)) {
			Vicino v = new Vicino(graph.getEdgeTarget(e),(int)graph.getEdgeWeight(e));
			lista.add(v);
		}
		Collections.sort(lista);
		return lista;
		
	}
	
	public List<String> getStates(){
		List<String> lista = new ArrayList<>(this.graph.vertexSet());
		Collections.sort(lista);
		return lista;
	}
	
	public int nVertici() {
		return this.graph.vertexSet().size();
	}

	public int nArchi() {
		return this.graph.edgeSet().size();
	}
}
