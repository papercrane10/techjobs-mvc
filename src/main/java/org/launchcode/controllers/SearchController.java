package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.naming.directory.SearchResult;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {
    public ArrayList<HashMap<String, String>> items = new ArrayList<>();
    public ArrayList<HashMap<String, String>> jobs = new ArrayList<>();

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);

        return "search";
    }
    @RequestMapping(value = "results", method = RequestMethod.GET)

    public String processSearchJobs(@RequestParam String searchType, @RequestParam String searchTerm, Model model){
        model.addAttribute("columns", ListController.columnChoices);
        if (searchType.equals("all")) {
            jobs = JobData.findByValue(searchTerm);
            for (HashMap<String, String> job : jobs) {
                items.add(job);
            model.addAttribute("items", jobs);
            model.addAttribute("results", "Results: "+ jobs.size());
            return "search";
            }}

        else{
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);

            for (HashMap<String, String> job : jobs) {
                items.add(job);
            model.addAttribute("items", jobs);
            model.addAttribute("results", "Results: "+ jobs.size());

            return "search";
            }
        }

    return "search";
    }
    // TODO #1 - Create handler to process search request and display results

}
