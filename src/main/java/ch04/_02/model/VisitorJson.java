package ch04._02.model;

public class VisitorJson {
	String visitor;
	String address;
	
	public VisitorJson() {
	}

	public VisitorJson(String visitor, String address) {
		super();
		this.visitor = visitor;
		this.address = address;
	}

	public String getVisitor() {
		return visitor;
	}

	public void setVisitor(String visitor) {
		this.visitor = visitor;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
}
