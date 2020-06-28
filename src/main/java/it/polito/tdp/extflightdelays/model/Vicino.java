package it.polito.tdp.extflightdelays.model;

public class Vicino implements Comparable<Vicino>{
	private String s;
	private Integer peso;
	
	public Vicino(String s, int peso) {
		this.s = s;
		this.peso = peso;
	}
	
	public String getS() {
		return s;
	}
	public void setS(String s) {
		this.s = s;
	}
	public Integer getPeso() {
		return peso;
	}
	public void setPeso(Integer peso) {
		this.peso = peso;
	}
	@Override
	public int compareTo(Vicino o) {
		// TODO Auto-generated method stub
		return -(this.peso.compareTo(o.getPeso()));
	}

	@Override
	public String toString() {
		return s + ", voli=" + peso;
	}
	
	

}
