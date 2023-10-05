package com.jchart.io.quoteretriever.upstox;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.jchart.model.ApiResponse;
import com.jchart.model.Candle;
import com.jchart.model.mongodb.TokenResponse;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UpstoxHttpService {
	// webclient is causing memory leak need to test with stable versions.
//	private final HttpWebClient httpClient;
	private final HttpRestTemplate httpRestTemplate;
	private final String baseUrl = "https://api-v2.upstox.com";

	private DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	public ApiResponse<LinkedHashMap<String, List<List<String>>>> getHistoryDays(String token, String termType) {

		String term = null;
		if (StringUtils.hasText(termType)) {
			Date now = new Date();
		    String endDate = DATE_FORMAT.format(now);
		    now.setYear(now.getYear()-1);
		    String startDate = DATE_FORMAT.format(now);
			term = "/historical-candle/" + termType + "/day/"+ endDate +"/"+startDate;
		} else
			term = "/historical-candle/NSE_EQ|INE848E01016/day/2023-10-01/2023-08-01";// "/historical%2Dcandle/NSE_EQ%7CINE848E01016/day/2023-10-04/2023-09-01";
		UriComponents uri = UriComponentsBuilder.fromHttpUrl(baseUrl).path(term).build();
		HttpHeaders header = new HttpHeaders();
		header.add("Api-Version", "2.0");
		header.add("Authorization", "Bearer " + token);
		Optional<ApiResponse> profileResponse = httpRestTemplate.sendGetRequest(uri.toUri(), header,
				UUID.randomUUID().toString(), ApiResponse.class);
		return profileResponse.orElse(new ApiResponse<LinkedHashMap<String, List<List<String>>>>());
	}

}
