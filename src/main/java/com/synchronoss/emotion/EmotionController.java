package com.synchronoss.emotion;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.ToneAnalyzer;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneAnalysis;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/")
public class EmotionController {

	@Value("#{T(org.apache.commons.io.FileUtils).readFileToString(" +
			"T(org.springframework.util.ResourceUtils).getFile('classpath:sample-tone-response.json')" +
			")}")
	String stubJson;


	@RequestMapping(value = "/week/{week}", method = RequestMethod.GET,  produces = "application/json")
	@ResponseBody
	public String getEmotion(@PathVariable("week") int week) {
		System.out.println("week" + week);
		System.out.println("json:" + stubJson);


		switch(week) {
			case 0:
				return parseToneResponse(stubJson);
		}


		/*model.addAttribute("message", "Hello world!");

		final String VERSION_DATE = "2016-05-19";
		ToneAnalyzer service = new ToneAnalyzer(VERSION_DATE);
		service.setUsernameAndPassword("776da0f2-937e-4cad-a3b0-4257e26a248f", "UGERgzkDCy7c");

		String text =
				"I know the times are difficult! Our sales have been "
						+ "disappointing for the past three quarters for our data analytics "
						+ "product suite. We have a competitive data analytics product "
						+ "suite in the industry. But we need to do our job selling it! "
						+ "We need to acknowledge and fix our sales challenges. "
						+ "We can’t blame the economy for our lack of execution! "
						+ "We are missing critical sales opportunities. "
						+ "Our product is in no way inferior to the competitor products. "
						+ "Our clients are hungry for analytical tools to improve their "
						+ "business outcomes. Economy has nothing to do with it.";

// Call the service and get the tone
		ToneOptions toneOptions = new ToneOptions.Builder().text(text).build();
		ToneAnalysis tone = service.tone(toneOptions).execute();
		System.out.println(tone); */

		return "hello";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String defaultRoute(ModelMap model) {
		System.out.println("default");
		model.addAttribute("message", "Hello world!");
		return "hello";

	}

	private String parseToneResponse(String response) {
		JsonObject json = new JsonParser().parse(response).getAsJsonObject();
		System.out.println("json:" + json);
		return json.getAsJsonObject("document_tone").toString();
	}

}