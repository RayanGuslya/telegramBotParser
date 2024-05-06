package org.example.modelSearch;

public class Original {
    public String link;
    public int width;
    public int height;
    public String getLink(){
        return link;
    }
    @Override
    public String toString(){
        return "original{" +
                "link='" + link + '\'' +
                ", width=" + width + '\'' +
                ", height=" + height +
                '}';
    }
}
