package com.netease.cloud.nsf.demo.stock.advisor.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netease.cloud.nsf.demo.stock.advisor.web.entity.Stock;
import com.netease.cloud.nsf.demo.stock.advisor.web.service.IAdvisorService;
import com.netease.cloud.nsf.demo.stock.advisor.web.util.CastKit;
import com.netease.cloud.nsf.demo.stock.advisor.web.util.StringKit;

import javax.servlet.http.HttpServletRequest;

@Service
public class AdvisorServiceImpl implements IAdvisorService{

	private static Logger log = LoggerFactory.getLogger(AdvisorServiceImpl.class);

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Autowired
	RestTemplate restTemplate;

	@Value("${hot_stock_ids}")
	String hotStockIds;

	@Value("${stock_provider_url}")
	String stockProviderUrl;
	
	@Value("${stock_viewer_url}")
	String stockViewerrUrl;

	@Value("${nsf.application.version:0.0.1}")
	String version;

	private int retryCount = 0;

	@Override
	public List<Stock> getHotStocks() throws Exception {

		log.info("getHotStocks is invoked with retry count = " + retryCount );

		Thread.sleep(1000);
		if(retryCount ++ % 5 != 0){
			throw new Exception();
		}

		List<Stock> stocks = null;
		Object[] params = {};
		if(StringKit.isEmpty(hotStockIds)) return stocks;

		String hotIds = getRecommendStockIds();
		try {
			String finalUrl = stockProviderUrl + "/stocks/" + hotIds;
			String stocksStr = restTemplate.getForObject(finalUrl, String.class, params);
			stocks = CastKit.str2StockList(stocksStr);
			Stock debugInfo = new Stock();
			debugInfo.setId("");
			debugInfo.setName("(第" + retryCount + "次请求)");
			stocks.add(debugInfo);
			log.info("get hot stocks from {} successful : {}", finalUrl, stocks);
		} catch (Exception e) {
			log.warn("get hot stocks failed", e);
		}

		return stocks;
	}

	/**
	 * @return  ids separated by comma 
	 *  e.g. xx,yy,zz
	 */
	private String getRecommendStockIds() {
		return hotStockIds;
	}

	@Override
	public List<String> batchHi() {
		
		List<String> results = new ArrayList<>();
		
		for(int i = 0; i < 20; i++) {
			String result = restTemplate.getForObject(stockProviderUrl + "/hi?p=" + i, String.class);
			results.add(result);
		}
		return results;
	}

	@Override
	public String deepInvoke(int times) {
		
		if(times --> 0) {
			return restTemplate.getForObject(stockViewerrUrl + "/deepInvoke?times=" + times , String.class);
		} 
		return "finish";
	}

	@Override
	public String echoProvider(HttpServletRequest request) {

		StringBuilder sBuilder = new StringBuilder();
		String url = stockProviderUrl + "/echo";
		sBuilder.append(restTemplate.getForObject(url, String.class));

		String host = request.getServerName();
		int port = request.getServerPort();

		return "echo from advisor " + version +"[" + host + ":" + port + "]" + "+" + sBuilder.toString();
	}
}
