package it.polito.tdp.extflightdelays.model;


public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		//model.creaGrafo(700);
		
		System.out.println("Il grafo ha "+model.numeroVertici()+" vertici e "+model.numeroArchi());
		
		//System.out.printf("Il grafo ha %d vertici e %d archi.", model.creaGrafo(700));
	}

}
