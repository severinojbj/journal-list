package com.test.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.entities.JournalEntry;
import com.test.repository.JournalEntryRepository;

@Controller
public class JournalEntryController {

	@Autowired
	JournalEntryRepository repo;

	@RequestMapping("/home")
	public String home() {
		return "home";
	}

	// **************************Basic CRUD Operations***********************


	private String convert2JSONEntries (List<JournalEntry> entriesList) {
		String entries = "{";
		if (entriesList.size() > 0) {
			for (JournalEntry entry: entriesList) {
				entries += entry.toString() + ",";
			}
			entries = entries.substring(0,entries.length() -1) + "}";
		}
		else {
			entries += "}";
		}
		return entries;
	}

	// Create and Update
	@RequestMapping("/addEntry")
	public String addJournalEntry(JournalEntry entry) {
		List<JournalEntry> entries = repo.findAll();
		int id = entries.size();
		entry.setId(id + 1);
		repo.save(entry);
		return "home";
	}

	// Retrieve All Data
	@RequestMapping("/getAllEntries")
	public ModelAndView getAllJournalEntries() {
		ModelAndView mv = new ModelAndView();
		List<JournalEntry> entriesList = repo.findAll();
		String entries = this.convert2JSONEntries(entriesList);
		mv.addObject("entries", entries);
		mv.setViewName("getAllEntries");

		return mv;
	}

	// Retrieve Specific Data
	@RequestMapping("/getEntry")
	public ModelAndView getJournalEntry(@RequestParam int id) {
		ModelAndView mv = new ModelAndView();
		JournalEntry entry = repo.findById(id).orElse(new JournalEntry());
		mv.addObject("entry", entry);
		mv.setViewName("getEntry");

		return mv;
	}

	// Delete Data
	@RequestMapping("/deleteEntry")
	public String deleteJournalEntry(@RequestParam int id) {

		repo.deleteById(id);
		return "home";
	}

	// ***************************More Complex Queries***********************

	// Find By Category
	@RequestMapping("/getEntriesByCategory")
	public ModelAndView getEntriesByCategory(@RequestParam String category) {

		ModelAndView mv = new ModelAndView();
		List<JournalEntry> entriesList = repo.findByCategory(category);
		String entries = this.convert2JSONEntries(entriesList);
		mv.addObject("entries", entries);
		mv.setViewName("getAllEntries");
		return mv;

	}

	// Find By Id Greater Than
	@RequestMapping("/getEntriesByIdGT")
	public ModelAndView getEntriesByIdGT(@RequestParam int id) {

		ModelAndView mv = new ModelAndView();
		List<JournalEntry> entriesList = repo.findByIdGreaterThan(id);
		String entries = this.convert2JSONEntries(entriesList);
		mv.addObject("entries", entries);
		mv.setViewName("getAllEntries");
		return mv;

	}

	// Find By Category but sorted by title
	@RequestMapping("/getEntriesByCategorySorted")
	public ModelAndView getEntriesByCategorySorted(@RequestParam String category) {

		ModelAndView mv = new ModelAndView();
		List<JournalEntry> entriesList = repo.findByCategorySorted(category);
		System.out.println(repo.findByCategorySorted(category));
		String entries = this.convert2JSONEntries(entriesList);
		mv.addObject("entries", entries);
		mv.setViewName("getAllEntries");
		return mv;

	}

}






