package org.umlatj.internal.behavior.petri;

public class RtArc {
	
	private RtPlace place;
	
	private short weight = 1;
	
	public RtArc() {
		
	}
	public RtArc(RtPlace place) {
	    this.place = place;
    }
	
	public RtArc(RtPlace place, short weight) {
	    this.place = place;
	    this.weight = weight;
    }
	

	/**
     * @return the place
     */
    public RtPlace getPlace() {
    	return place;
    }

	/**
     * @param place the place to set
     */
    public void setPlace(RtPlace place) {
    	this.place = place;
    }

	/**
     * @return the weight
     */
    public short getWeight() {
    	return weight;
    }

	/**
     * @param weight the weight to set
     */
    public void setWeight(short weight) {
    	this.weight = weight;
    }
	

}
