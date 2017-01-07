package com.ibm.sample.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.sample.domain.RestfulMessage;
import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.Intent;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

@RestController
@RequestMapping("/sample")
public class SampleRestfulAPI {

	@RequestMapping(value = "/helloworld", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public RestfulMessage helloWorld() {
		RestfulMessage restfulMessage = new RestfulMessage();
		restfulMessage.setMessage("Hello World for Spring MVC Restful Service!");
		return restfulMessage;
	}

	@RequestMapping(value = "/conversation", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public MessageResponse conversation() {
		// workspace id
		final String workspaceId = "25c3634e-f95e-45c8-914e-fe70564796dd";
		// input text
		String input;
		Map<String, Object> context = new HashMap<String, Object>();

		// instantiate the conversation service
		ConversationService conversationService = new ConversationService("2016-09-20");
		conversationService.setUsernameAndPassword("3e442370-d3af-489c-87d2-9efcd7ceda23", "SXeUcCEEA61q");

		// call the first Conversation Service with the input "" context
		input = "";
		MessageRequest message = new MessageRequest.Builder().inputText(input).context(context).build();
		MessageResponse response = conversationService.message(workspaceId, message).execute();

		return response;
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// workspace id
		final String workspaceId = "25c3634e-f95e-45c8-914e-fe70564796dd";
		// input text
		String input;
		Map<String, Object> context = new HashMap<String, Object>();

		// instantiate the conversation service
		ConversationService conversationService = new ConversationService("2016-09-20");
		conversationService.setUsernameAndPassword("3e442370-d3af-489c-87d2-9efcd7ceda23", "SXeUcCEEA61q");

		// call the first Conversation Service with the input "" context
		input = "";
		MessageRequest message = new MessageRequest.Builder().inputText(input).context(context).build();
		MessageResponse response = conversationService.message(workspaceId, message).execute();
		Map<String, Object> output = response.getOutput();
		List<Intent> intents = response.getIntents();
		List<String> outputList;
		System.out.println("The first conversation input message: {" + response.getInputText() + "}");
		if (output != null) {
			outputList = (List<String>) output.get("text");
			for (String outputMessage : outputList) {
				if (outputMessage != null && !outputMessage.equals("")) {
					System.out.println("The first conversation output message: {" + outputMessage + "}");
				}
			}

			for (Intent intent : intents) {
				System.out.println("The first conversation intent value: {" + intent.getIntent() + "}");
				System.out.println("The first conversation intent confidence rate: {" + intent.getConfidence() + "}");
			}
		}

		context = response.getContext();// set the context object for the second
										// call

		InputStreamReader isr = new InputStreamReader(System.in);
		// get the input for Conversation Service from the system console panel
		BufferedReader br = new BufferedReader(isr);
		try {
			while (true) {
				input = br.readLine();
				if (input != null && input.equalsIgnoreCase("exit")) {
					break;
				}

				message = new MessageRequest.Builder().inputText(input).context(context).build();
				response = conversationService.message(workspaceId, message).execute();
				context = response.getContext();// store the new context object
												// for the next round
												// conversation
				output = response.getOutput();
				intents = response.getIntents();
				System.out.println("The conversation input message: {" + response.getInputText() + "}");
				if (output != null) {
					outputList = (List<String>) output.get("text");
					for (String outputMessage : outputList) {
						if (outputMessage != null && !outputMessage.equals("")) {
							System.out.println("The conversation output message: {" + outputMessage + "}");
						}
					}

					for (Intent intent : intents) {
						System.out.println("The conversation intent value: {" + intent.getIntent() + "}");
						System.out.println("The conversation intent confidence rate: {" + intent.getConfidence() + "}");
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
