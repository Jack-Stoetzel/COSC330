
public class User_Portfolio {
	
	private String name;
	private Double total_value;
	private Double buying_power;
	private Double current_gain;
	
	
	public User_Portfolio () {
		name = "John Doe";
		total_value = 0.00;
		buying_power = 0.00;
		current_gain = 0.00;
	}
	
	public User_Portfolio(String name) {
		this.name = name;
		this.total_value = 0.00;
		this.buying_power = 0.00;
		this.current_gain = 0.00;		
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setTotalValue(Double value) {
		this.total_value = value;
	}
	
	public void setBuyingPower(Double value) {
		this.buying_power = value;
	}
	
	public void setCurrentGain(Double value) {
		this.current_gain = value;
	}
	
	public String getName() {
		return name;
	}
	
	public Double getTotalValue() {
		return total_value;
	}
	
	public Double getBuyingPower() {
		return buying_power;
	}
	
	public Double getCurrentGain() {
		return current_gain;
	}

}
