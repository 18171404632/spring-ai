package com.newkey.ai.config.commonbean;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Desc: openai bean
 * <p>
 * Function:
 */
@Configuration
public class ChatClientBean {

    @Value("${svr.chnl.third.3version}")
    private String apiKey3version;

    @Value("${svr.chnl.third.4version}")
    private String apiKey4version;

    @Value("${svr.chnl.third.baseUrl}")
    private String baseUrl;

    @Bean
    public ChatClient chatClientThreePointFive(){
        OpenAiApi openAiApi = new OpenAiApi(baseUrl, apiKey3version);
        OpenAiChatModel chatModel = new OpenAiChatModel(openAiApi, OpenAiChatOptions.builder().withModel(OpenAiApi.ChatModel.GPT_3_5_TURBO_1106).build());
        return ChatClient.builder(chatModel).build();
    }

    @Bean
    public ChatClient chatClientFour(){
        OpenAiApi openAiApi = new OpenAiApi(baseUrl, apiKey4version);
        OpenAiChatModel chatModel = new OpenAiChatModel(openAiApi, OpenAiChatOptions.builder().withModel(OpenAiApi.ChatModel.GPT_4_O).build());
        return ChatClient.builder(chatModel).build();
    }
}
