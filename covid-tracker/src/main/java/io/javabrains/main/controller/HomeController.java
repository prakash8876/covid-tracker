package io.javabrains.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.javabrains.main.model.LocationStats;
import io.javabrains.main.services.CoronaVirusData;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class HomeController {
	
	@Autowired
	private CoronaVirusData allData;
	
//	for home.html
	@GetMapping("/")
	public String home(Model model) {
		List<LocationStats> allStats = allData.getAllStats();
		int totalReports = allStats.stream().mapToInt(s -> s.getLatestTotalCases()).sum();
		int totalDiffr = allStats.stream().mapToInt(s -> s.getDiffFromPrevDay()).sum();
		model.addAttribute("allData", allStats);
		model.addAttribute("totalReports", totalReports);
		model.addAttribute("totalDiffr", totalDiffr);
		return "home";
	}
	
//	for angular request
	@GetMapping("/getData")
	@ResponseBody
	public List<LocationStats> getData() {
		return allData.getAllStats();
	}
	
}
