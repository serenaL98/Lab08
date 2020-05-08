package it.polito.tdp.extflightdelays.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {
	
	//dao
	ExtFlightDelaysDAO dao = new ExtFlightDelaysDAO();
	
	//inizializzo il grafo SEMPLICE, NON ORIENTATO e PESATO (pesato dalla distanza minima inserita)
	private Graph<Airport, DefaultWeightedEdge> grafo;
	
	//private List<Airline> aerei;
	private List<Airport> aeroporti;
	private List<Flight> voli;
	
	//creo una mappa in modo da non avere dei duplicati
	private Map<Integer, Airport> idMap;
	//idmap dei voli
	private Map<Integer, Flight> idMapVoli;
	//lista collegamenti
	private List<CollegamentoAeroporti> collegamenti;
	
	public Model(){
		
		//riempio le idMap
		this.idMap = new HashMap<Integer, Airport>();
		
		this.idMapVoli = new HashMap<>();

	}
	
	public void creaGrafo(int distanzaMin) {

		//chiamo il dao per avere le informazioni sugli aeroporti, aerei, voli
		//aerei = dao.loadAllAirlines();
		aeroporti = dao.loadAllAirports();
		voli = dao.loadAllFlights();
		collegamenti = dao.collegamenti(distanzaMin);
		
		for(Airport a: this.aeroporti) {
			idMap.put(a.getId(), a);
		}
		
		for(Flight f: this.voli) {
			idMapVoli.put(f.getAirlineId(), f);
		}
		
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		//aggiungi i VERTICI
		for(CollegamentoAeroporti c: collegamenti) {
			if(!this.grafo.containsVertex(idMap.get(c.aeropOrig))) {
				grafo.addVertex(idMap.get(c.getAeropOrig()));
			}
				//Graphs.addAllVertices(this.grafo, this.aeroporti);
			if(!this.grafo.containsVertex(idMap.get(c.aeropDest))) {
				grafo.addVertex(idMap.get(c.getAeropDest()));
			}
			//aggiungi ARCHI
			Graphs.addEdge(this.grafo, this.idMap.get(c.getAeropOrig()),this.idMap.get(c.getAeropDest()) , c.getDistanza());
		}
	}
	
	public int numeroVertici() {
		return this.grafo.vertexSet().size();
	}
	
	public int numeroArchi() {
		return this.grafo.edgeSet().size();
	}
	
	public String elenco() {
		String el = "";
		for(CollegamentoAeroporti c: collegamenti) {
			el += this.idMap.get(c.aeropOrig).getAirportName()+", "+this.idMap.get(c.getAeropDest()).getAirportName()+", "+c.getDistanza()+"\n";
		}
		return el;
	}
}
