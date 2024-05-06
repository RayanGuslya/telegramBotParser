package org.example.modelSearch;

import java.util.List;

public class Images {
        public int position;
        public String title;
        public Source source;
        public Original original;
        public String thumbnail;

    public int getPosition() {
        return position;
    }

    @Override
    public String toString(){
        return "images{" +
                "position='" + position + '\'' +
                ", title=" + title + '\'' +
                ", source=" + source + '\'' +
                ", original=" + original + '\'' +
                ", thumbnail=" + thumbnail +
                '}';
    }


}
