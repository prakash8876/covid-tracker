package io.javabrains.main.services;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import io.javabrains.main.model.LocationStats;

@Service
public class CoronaVirusData {
	
	private final static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
//	private final static Logger logger = LoggerFactory.getLogger(CoronaVirusData.class);
	private List<LocationStats> allStats = new ArrayList<>();
	
	public List<LocationStats> getAllStats() {
		return allStats;
	}

	@PostConstruct
	@Scheduled(cron = "* * 1 * * *")
	public void fetchVirusData() throws IOException, InterruptedException {
		List<LocationStats> newStats = new ArrayList<>();
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create(VIRUS_DATA_URL))
					.build();
		HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
		
//		data shows on console
//		System.out.println(httpResponse.body());
//		logger.info("HTTP Response : {}", httpResponse.body());
		
//		Java CSV Library reader code
		StringReader csvBodyReader = new StringReader(httpResponse.body());
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
		for (CSVRecord record : records) {
			LocationStats stats = new LocationStats();
			stats.setState(record.get("Province/State"));
			stats.setCountry(record.get("Country/Region"));
			int latestRecord = Integer.parseInt(record.get(record.size() - 1));
			int lastRecord = Integer.parseInt(record.get(record.size() - 2));
			stats.setLatestTotalCases(latestRecord);
			stats.setDiffFromPrevDay(latestRecord - lastRecord); 
//			logger.info("Stats : ", stats);
			newStats.add(stats);
		}
		this.allStats = newStats;
//		this.allStats.forEach(System.out::println);
//		logger.info(this.allStats.toString());
	}
	
}
