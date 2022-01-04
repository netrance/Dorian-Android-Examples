package cf.domone.android.recyclerview_example_1;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by dorian on 16. 3. 22..
 */
public class SampleItem {
    public int number;

    public String title;

    public String address;

    public SampleItem(int number, String title, String address) {
        this.number = number;
        this.title = title;
        this.address = address;
    }

    public static List<SampleItem> getSampleItemList() {
        List<SampleItem> sampleItemList = new LinkedList<>();

        sampleItemList.add(new SampleItem(1, "Google", "http://www.google.com"));
        sampleItemList.add(new SampleItem(2, "Naver", "http://www.naver.com"));
        sampleItemList.add(new SampleItem(3, "Baidu", "http://www.baidu.com"));
        sampleItemList.add(new SampleItem(4, "Yahoo", "http://www.yahoo.com"));
        sampleItemList.add(new SampleItem(5, "Daum", "http://www.daum.net"));
        sampleItemList.add(new SampleItem(6, "Goo", "http://www.goo.ne.jp"));
        sampleItemList.add(new SampleItem(7, "Facebook", "http://www.facebook.com"));
        sampleItemList.add(new SampleItem(8, "Twitter", "http://www.twitter.com"));
        sampleItemList.add(new SampleItem(9, "Google+", "http://plus.google.com"));
        sampleItemList.add(new SampleItem(10, "Cyworld", "http://www.cyworld.com"));
        sampleItemList.add(new SampleItem(11, "Kakao Story", "http://story.kakao.com"));
        sampleItemList.add(new SampleItem(12, "Instagram", "http://www.instagram.com"));
        sampleItemList.add(new SampleItem(13, "Youtube", "http://www.youtube.com"));
        sampleItemList.add(new SampleItem(14, "Vimeo", "http://www.vimeo.com"));
        sampleItemList.add(new SampleItem(15, "Vevo", "http://www.vevo.com"));
        sampleItemList.add(new SampleItem(16, "Dailymotion", "http://www.dailymotion.com"));

        return sampleItemList;
    }
}
