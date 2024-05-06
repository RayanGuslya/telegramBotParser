package org.example;

import org.example.modelSearch.GsonParser;
import org.example.modelSearch.Root;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.IOException;
@SpringBootApplication
public class Main {
    public static void main(String[] args) throws TelegramApiException {
        SpringApplication.run(Main.class, args);
        CommandBot bot = new CommandBot();
        TelegramBotsApi telegramBot = new TelegramBotsApi(DefaultBotSession.class);
        telegramBot.registerBot(bot);

        try {
            GsonParser parser = new GsonParser();
            Root root = parser.parse();
            System.out.println("Root " + root.images.get(0).original.getLink());
        }
        catch (Exception e){
            System.out.println("parsing null " + e.getMessage());
        }
    }
}