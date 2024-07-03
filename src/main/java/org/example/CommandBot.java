package org.example;

import org.example.modelSearch.GsonParser;
import org.example.modelSearch.Images;
import org.example.modelSearch.Original;
import org.example.modelSearch.Root;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Random;

public class CommandBot extends TelegramLongPollingBot {
    //имя SearchBot
    private String apiKey = "RnNhqRCNnS7GhvhuFkeFQM7e";// запасной ключ - RnNhqRCNnS7GhvhuFkeFQM7e
    private String nameImg;
    private final String userName = "тут имя бота";
    private final String botToken = "тут должен быть ваш токен бота";
    @Override
    public String getBotUsername() {
        return userName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
    SendMessage message = new SendMessage();
    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()){
            String messageText = update.getMessage().getText();
            String convertMessage = "";
            long chatId = update.getMessage().getChatId();
            for(char c : messageText.toCharArray()){
                if(c == 32){
                    c = 95;
                }
                convertMessage += Character.toString(c);
            }
            messageText = convertMessage;
            nameImg = messageText;
            try {
                image(chatId);
                message.setChatId(chatId);
                GsonParser parser = new GsonParser();
                Root root = parser.parse();

                System.out.println(messageText);
                int random = rndImg(root.images.get(0).getPosition(),root.images.get(68).getPosition());
                sendPhoto(chatId, root.images.get(random).original.getLink());

            } catch (IOException | InterruptedException e) {
              SendText(chatId, "Unknown error...");
            }


        }
    }
    public void image(long chatID) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.searchapi.io/api/v1/search?engine=google_images&q=" +
                        nameImg
                        + "&api_key=" + apiKey))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        //System.out.println(response.body());
        writer(response.body(), chatID);
    }
    public void writer(String URI,long chatId){
        try(FileWriter writer = new FileWriter("searchImage.json")){
            writer.write(URI);
        }catch (IOException e){
            System.out.println(e.getMessage());
            SendText(chatId, "ненаход");
        }
    }
    public int rndImg(int min, int max){
        max -= min;
        return (int)(Math.random() * ++max) + min;
    }
    public void sendPhoto(long chatId,String imagePath) {
        SendPhoto sendPhoto = new SendPhoto();
        //sendPhoto.setCaption(caption);
        message.setChatId(chatId);
        sendPhoto.setChatId(message.getChatId());

        System.out.println("caption " + imagePath);

        InputFile inputFile = new InputFile(imagePath);
        sendPhoto.setPhoto(inputFile);

        try {
            execute(sendPhoto);
        } catch (TelegramApiException e) {
            e.printStackTrace();
            System.out.println("error send Photo");
            SendText(chatId,"хуйня какая то, напиши еще раз");
        }
    }
    public void SendText(long chatId, String text){
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(text);

        try {
            execute(message);
        }catch (TelegramApiException e){
            e.printStackTrace();
        }
    }
}
