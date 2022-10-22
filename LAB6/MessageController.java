package com.ncu.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class MessageController {

	@RequestMapping(value = "/greetings", method = RequestMethod.GET,headers="Accept=application/json")
    public Map<String,List<String>> getGreetings()
    {
        List<String> messages = getMessages();
        Map<String,List<String>> result = new HashMap<>();
        result.put("Greetings", messages);
        return result;
    }
	
	// utility method for message greeting
	public List<String> getMessages(){
		List<String> messages = new ArrayList<String>();
		messages.add("Namaste!");
		messages.add("Hi");
		messages.add("Hello");
		return messages;
	}
}
