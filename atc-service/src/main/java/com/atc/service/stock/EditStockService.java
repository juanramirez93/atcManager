package com.atc.service.stock;

import com.atc.connection.StockEM;
import com.atc.model.Stock;

public class EditStockService {

	private StockEM stockEM;

	public EditStockService() {
		stockEM = new StockEM();
	}
	
	public void addStock(Stock stock) {
		stockEM.save(stock);
	}
	

}
