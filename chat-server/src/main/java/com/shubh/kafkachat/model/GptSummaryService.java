package com.shubh.kafkachat.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// @Service
public class GptSummaryService {

    public static String runScript(String usrinput, String msginput) {
        ProcessBuilder processBuilder = new ProcessBuilder();
        // Assuming Python is in the system's PATH, and adjust the path to your script accordingly
        processBuilder.command("python", "src/main/resources/scripts/summary.py", usrinput, msginput);

        try {
            Process process = processBuilder.start();

            // Read the output of the script
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            StringBuilder output = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            int exitCode = process.waitFor();
            if (exitCode == 0) {
                return output.toString();
            } else {
                // Handle error scenario
                return "Error executing script";
            }
        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }
}






// --------------------------------- WORKING OLD CODE --------------------------------------------------------------

//package com.shubh.kafkachat.model;
//
//import java.io.IOException;
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//import java.net.http.HttpRequest.BodyPublishers;
//import java.net.http.HttpResponse.BodyHandlers;
//
//import org.json.JSONObject;
//import org.json.JSONArray;
//
//public class GptSummaryService {
//    private final String apiKey;
//    private final HttpClient httpClient;
//
//    public GptSummaryService(String apiKey) {
//        this.apiKey = apiKey;
//        this.httpClient = HttpClient.newHttpClient();
//    }
//
//    public String generateSummary(String text) throws IOException, InterruptedException  {
//        try{
//                // Construct the prompt as a series of messages
//                JSONObject systemMessage = new JSONObject()
//                        .put("role", "system")
//                        .put("content", "You are a helpful assistant. Summarize the following text.");
//
//                JSONObject userMessage = new JSONObject()
//                        .put("role", "user")
//                        .put("content", text);
//
//                JSONArray messages = new JSONArray();
//                messages.put(systemMessage);
//                messages.put(userMessage);
//
//                JSONObject requestBody = new JSONObject()
//                        .put("model", "gpt-3.5-turbo") // Adjust according to the specific model you're using
//                        .put("messages", messages);
//
//                HttpRequest request = HttpRequest.newBuilder()
//                        .uri(URI.create("https://api.openai.com/v1/chat/completions")) // Make sure the URI is correct for the Conversations/Chat API
//                        .header("Content-Type", "application/json")
//                        .header("Authorization", "Bearer " + this.apiKey)
//                        .POST(BodyPublishers.ofString(requestBody.toString()))
//                        .build();
//
//                HttpResponse<String> response = this.httpClient.send(request, HttpResponse.BodyHandlers.ofString());
//                JSONObject jsonResponse = new JSONObject(response.body());
//                // The structure to extract the response might be different based on the Conversations API response format
//                String summary = jsonResponse.getJSONArray("choices").getJSONObject(0).getJSONArray("messages").getJSONObject(0).getString("content");
//                System.out.println(summary);
//                return summary.trim();
//
//        }catch(IOException | InterruptedException e){
//            // Handle the exceptions appropriately (e.g., log the error or throw a custom exception)
//            e.printStackTrace(); // Example of handling (printing the stack trace)
//            return ""; // Example of returning a default value
//        }
//
//    }
//}


/// --------------------Old code -----------------
//            String prompt = "Summarize the following text:\n" + text;
//            JSONObject requestBody = new JSONObject()
//                    .put("model", "gpt-3.5-turbo") // Use an appropriate model
//                    .put("prompt", prompt)
//                    .put("temperature", 0.7)
//                    .put("max_tokens", 150);
//
//            HttpRequest request = HttpRequest.newBuilder()
//                    .uri(URI.create("https://api.openai.com/v1/chat/completions"))
//                    .header("Content-Type", "application/json")
//                    .header("Authorization", "Bearer " + this.apiKey)
//                    .POST(BodyPublishers.ofString(requestBody.toString()))
//                    .build();
//
//            HttpResponse<String> response = this.httpClient.send(request, BodyHandlers.ofString());
//            JSONObject jsonResponse = new JSONObject(response.body());
//            System.out.println(jsonResponse);
//            return jsonResponse.getJSONArray("choices").getJSONObject(0).getString("text").trim();

//  ------ Llama modification -------------

//            JSONObject requestBody = new JSONObject()
//                    .put("prompt", prompt)
//                    .put("other_parameters", "value"); // Replace with actual parameters as per LLAMA API documentation
//            HttpRequest request = HttpRequest.newBuilder()
//                    .uri(URI.create("https://api.example.com/llama"))
//                    .header("Content-Type", "application/json")
//                    .header("Authorization", "Bearer " + this.apiKey) // Adjust header as per API requirements
//                    .POST(BodyPublishers.ofString(requestBody.toString()))
//                    .build();
//
//            HttpResponse<String> response = this.httpClient.send(request, HttpResponse.BodyHandlers.ofString());
//            return response.body(); // Process the response as needed