package me.blog.netrance.android.key_navi_between_2_listviews_in_default;

import java.util.LinkedList;
import java.util.List;

/**
 * This class has the methods that returns data list views need..
 */
public class DataReader {

    public static List<String> getParts() {
        List<String> partList = new LinkedList<String>();

        partList.add("Part 1");
        partList.add("Part 2");
        partList.add("Part 3");

        return partList;
    }

    public static List<String> getChaptersOfPart1() {
        List<String> chapterList = new LinkedList<String>();

        chapterList.add("Chapter 1");
        chapterList.add("Chapter 2");
        chapterList.add("Chapter 3");
        chapterList.add("Chapter 4");
        chapterList.add("Chapter 5");

        return chapterList;
    }

}
