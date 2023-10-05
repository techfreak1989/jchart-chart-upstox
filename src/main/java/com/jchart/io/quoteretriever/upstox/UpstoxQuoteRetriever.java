package com.jchart.io.quoteretriever.upstox;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Stream;

import org.springframework.web.client.RestTemplate;

import com.jchart.io.quoteretriever.IQuoteRetriever;
import com.jchart.model.ApiResponse;
import com.jchart.model.Candle;
import com.jchart.model.JchartModelFacade;
import com.jchart.model.Quote;

/**
 * @author <a href="mailto:paul.russo@jchart.com>Paul Russo</a>
 * @since Feb 10, 2018
 */
public class UpstoxQuoteRetriever implements IQuoteRetriever {

	private DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss z");

	private UpstoxHttpService upstoxHttpService = new UpstoxHttpService(new HttpRestTemplate(new RestTemplate()));

//	public static void main(String[] args) throws Exception {
//		UpstoxQuoteRetriever upstoxQuoteRetriever = new UpstoxQuoteRetriever();
//		upstoxQuoteRetriever.getRemoteQuotes("", 0);
//	}

	@Override
	public List<Quote> getRemoteQuotes(String ticker, int maxQuotes) throws Exception {
		ticker = MongoDBConnector.getTradeBySymbol(ticker).getInstrument_key();
		ApiResponse<LinkedHashMap<String, List<List<String>>>> candle = upstoxHttpService
				.getHistoryDays(MongoDBConnector.getToken(), ticker);
		System.out.println(candle.getData().get("candles").get(0));
		List<Quote> retval = lastN(
				candle.getData().get("candles").stream().map(arr -> arr.toString()).map(str -> parseQuoteIn(str)),
				1300);
		Collections.reverse(retval);
		return retval;
	}

	public static <T> List<T> lastN(Stream<T> stream, int n) {
		Deque<T> result = new ArrayDeque<>(n);
		stream.forEachOrdered(x -> {
			if (result.size() == n) {
				result.pop();
			}
			result.add(x);
		});
		return new ArrayList<>(result);
	}

	private Quote parseQuoteIn(String s) {
		Quote retval = new Quote();
		s = s.replace("[", "").replace("]", "").replace(" ", "");
		// Date,Open,High,Low,Close,Volume
		StringTokenizer st = new StringTokenizer(s, ",");
		Date date;
		try {
			DateTimeFormatter Parser = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
			LocalDateTime ldt = LocalDateTime.parse(st.nextToken(), Parser);
			date = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
			retval.setDate(date);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		float open = new Float(st.nextToken()).floatValue();
		float hi = new Float(st.nextToken()).floatValue();
		float low = new Float(st.nextToken()).floatValue();
		float close = new Float(st.nextToken()).floatValue();
		long volume = new Float(st.nextToken()).longValue();
//      retval.setDate(date);
		retval.setOpen(open);
		retval.setHi(hi);
		retval.setLow(low);
		retval.setClose(close);
		retval.setVolume(volume);
		return retval;
	}

	@Override
	public List<Quote> getLocalQuotes(BufferedReader br, int maxQuotes) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveRemoteQuotes(String ticker, int maxQuotes, String savePath) throws Exception {
		// TODO Auto-generated method stub

	}

}