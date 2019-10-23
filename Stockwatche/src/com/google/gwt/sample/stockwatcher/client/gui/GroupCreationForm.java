package com.google.gwt.sample.stockwatcher.client.gui;

import com.google.gwt.sample.stockwatcher.client.ClientsideSettings;
import com.google.gwt.sample.stockwatcher.client.StockPriceServiceAsync;
import com.google.gwt.user.client.ui.FlowPanel;

public class GroupCreationForm extends FlowPanel{

	StockPriceServiceAsync stockPriceSvc = ClientsideSettings.getStockprice();
}
