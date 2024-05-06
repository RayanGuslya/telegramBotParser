/*
package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class imageInput {
    private String nameImg;
    public imageInput() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.searchapi.io/api/v1/search?engine=google_images&q=" +
                        getNameImg()
                        + "&api_key=Tzoc95J1R3TXHrwoLj94VJvo"))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        //System.out.println(response.body());
        writer(response.body());
    }
    public String getNameImg(){
        return nameImg;
    }
    public void setNameImg(String nameImg){
        this.nameImg = nameImg;
    }
    public void writer(String URI){
        try(FileWriter writer = new FileWriter("searchImage.json")){
            writer.write(URI);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
*/
