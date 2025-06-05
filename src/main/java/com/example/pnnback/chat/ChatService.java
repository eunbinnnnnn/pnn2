package com.example.pnnback.chat;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final ChatModel chatModel;

    public ChatService(OpenAiChatModel openAiChatModel) {
        this.chatModel = openAiChatModel;
    }

    public ChatResponse askGPT(AIRequest request) {
        ChatResponse response = chatModel.call(
                new Prompt(
                        ""+request,
                        OpenAiChatOptions.builder()
                                .model("gpt-4o")
                                .temperature(0.4)
                                .build()
                ));

        return response;
    }
}