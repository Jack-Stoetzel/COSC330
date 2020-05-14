
public class Held_Stock {

	private Stock[] heldStocks;
	private Double[] buy_atm;
	private int[] buy_count;
	
	
	public Held_Stock(int held_limit) {
		heldStocks = new Stock[held_limit];
		buy_atm = new Double[held_limit];
		buy_count = new int[held_limit];
	}
	
	public void addStock(String ticker, int amount) {
		
		for(int count = 0; count < heldStocks.length; count++) {
			if(heldStocks[count] == null) {
				heldStocks[count] = new Stock(ticker);
				buy_count[count] = amount;
				buy_atm[count] = heldStocks[count].getCurrent() * amount;
				System.out.println("Stock added to held stock");
				break;
			} else {
				System.out.println("Stock not added to held stock");
			}
		}
	}
	
	public void addToStock(String ticker, int amount) {
		int position = findHeld(ticker);
		buy_count[position] = buy_count[position] + amount;
		buy_atm[position] = buy_atm[position] + heldStocks[position].getCurrent() * amount;
	}
	
	public void removeStock(String ticker, int amount) {
		
		for(int count = 0; count < heldStocks.length; count++) {
			if(heldStocks[count].getSymbol().contentEquals(ticker)) {
				heldStocks[count] = new Stock();
				buy_count[count] = -1;
				buy_atm[count] = 0.00;
				System.out.println("Stock removed from held stock");
				break;
			} else {
				System.out.println("Stock failed to remove from held stock");
			}
		}
	}
	
	public void removeFromtStock(String ticker, int amount) {
		int position = this.findHeld(ticker);
		buy_count[position] = buy_count[position] - amount;
		buy_atm[position] = buy_atm[position] - (heldStocks[position].getCurrent() * amount);
	}
	
	public int findHeld(String ticker) {
		for(int count = 0; count < heldStocks.length; count++) {
			if(heldStocks[count] == null) {
				// Skip over.
			} else if (heldStocks[count].getSymbol().contentEquals(ticker)){
				return count;
			}
		}
		return -1;
	}
	
	public Stock getStockAt(int num) {
		return this.heldStocks[num];
	}
	
	public boolean isNullAt(int num) {
		if(this.heldStocks[num] == null || this.heldStocks[num].getName().equals("null"))
			return true;
		else
			return false;
	}
	
	public String getStockNameAt(int num) {
		return this.heldStocks[num].getName();
	}
	
	public Double getStockValueAt(int num) {
		return this.heldStocks[num].getCurrent();
	}
	
	public Double getNewStockValueAt(int num) {
		String ticker = getStockTickerAt(num);
		this.heldStocks[num] = new Stock(ticker);
		return this.heldStocks[num].getCurrent();
	}
	
	public int getStocksBoughtAt(int num) {
		return this.buy_count[num];
	}
	
	public String getStockTickerAt(int num) {
		return this.heldStocks[num].getSymbol();
	}
	
	public Double getGainLoss(int num) {
		String ticker = getStockTickerAt(num);
		this.heldStocks[num] = new Stock(ticker);
		return ((this.heldStocks[num].getCurrent() * this.buy_count[num])- this.buy_atm[num]);
	}
	
}
