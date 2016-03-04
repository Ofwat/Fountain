package uk.gov.ofwat.fountain.domain;

public class ItemInEquation {

	private String item;
	private Interval interval;
	
	public ItemInEquation(String item, Interval interval) {
		super();
		this.item = item;
		this.interval = interval;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public Interval getInterval() {
		return interval;
	}
	public void setInterval(Interval interval) {
		this.interval = interval;
	}
	
}
