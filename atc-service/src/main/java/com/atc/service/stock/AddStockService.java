package com.atc.service.stock;

import com.atc.connection.StockEM;
import com.atc.model.Stock;

public class AddStockService {

	private StockEM stockEM;

	public AddStockService() {
		stockEM = new StockEM();
	}
	
	public void addStock(Stock stock) {
		stockEM.save(stock);
	}

	public void updateStock(Stock stock) {
		stockEM.update(stock);		
	}
	

}
