package com.newkey.ai.service;

import com.newkey.ai.dao.OpenAiChatRepository;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class OpenAiChatService {

    @Autowired
    private OpenAiChatRepository openAiChatRepository;

    @Autowired
    @Qualifier("chatClientThreePointFive")
    private ChatClient chatClient3;

    @Autowired
    @Qualifier("chatClientFour")
    private ChatClient chatClient4;

    public Flux<String> getChatMessage3(String userMassage){
        Flux<String>  content = chatClient3
                .prompt()
                .advisors(new SimpleLoggerAdvisor(
                        request -> "Custom request: " + request.userText(),
                        response -> "Custom response: " + response.getResult()))
                .user(userMassage).stream().content();

        Flux<String> encodedFlux = content.map(str -> {
            String escapedStr = str.replaceAll(" ", "\\\\u0020");
            escapedStr = escapedStr.replaceAll("\n", "\\\\u000A");
            return escapedStr;
        });

        return encodedFlux;
    }


    public Flux<String> getChatMessage4(String userMassage){
        Flux<String>  content = chatClient4
                .prompt()
                .advisors(new SimpleLoggerAdvisor(
                        request -> "Custom request: " + request.userText(),
                        response -> "Custom response: " + response.getResult()))
                .user(userMassage).stream().content();
        return content;
    }
}
