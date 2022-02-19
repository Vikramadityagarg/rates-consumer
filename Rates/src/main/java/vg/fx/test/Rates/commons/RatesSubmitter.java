package vg.fx.test.Rates.commons;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import vg.fx.test.Rates.vo.RatesVO;
@Component
public class RatesSubmitter {
	@Autowired
	private RestTemplate restTemplate;
	
	public String postRates(List<RatesVO> rates) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(rates,headers);
		ResponseEntity<List<RatesVO>> rateResponse = restTemplate.exchange(
				RatesConstant.ServerURL, 
				HttpMethod.POST, 
				requestEntity, 
				new ParameterizedTypeReference<List<RatesVO>>() {});
		if(rateResponse.getStatusCode()==HttpStatus.OK) {
			return HttpStatus.OK.name();
		}
		else {
			return rateResponse.getStatusCode().name();
		}
	}
}
