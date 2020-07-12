package crop.computer.askey.bopomopractice.util;

import java.util.HashMap;

import crop.computer.askey.bopomopractice.R;

public class AudioSourceUtil {

    private static HashMap<Integer, Integer> rawRourceMap = new HashMap<>();

    static {

        rawRourceMap.put(0, R.raw._01); rawRourceMap.put(1, R.raw._02);
        rawRourceMap.put(2, R.raw._03); rawRourceMap.put(3, R.raw._04);
        rawRourceMap.put(4, R.raw._05); rawRourceMap.put(5, R.raw._06);
        rawRourceMap.put(6, R.raw._07); rawRourceMap.put(7, R.raw._08);
        rawRourceMap.put(8, R.raw._09); rawRourceMap.put(9, R.raw._10);

        rawRourceMap.put(10, R.raw._11); rawRourceMap.put(11, R.raw._12);
        rawRourceMap.put(12, R.raw._13); rawRourceMap.put(13, R.raw._14);
        rawRourceMap.put(14, R.raw._15); rawRourceMap.put(15, R.raw._16);
        rawRourceMap.put(16, R.raw._17); rawRourceMap.put(17, R.raw._18);
        rawRourceMap.put(18, R.raw._19); rawRourceMap.put(19, R.raw._20);

        rawRourceMap.put(20, R.raw._21); rawRourceMap.put(21, R.raw._22);
        rawRourceMap.put(22, R.raw._23); rawRourceMap.put(23, R.raw._24);
        rawRourceMap.put(24, R.raw._25); rawRourceMap.put(25, R.raw._26);
        rawRourceMap.put(26, R.raw._27); rawRourceMap.put(27, R.raw._28);
        rawRourceMap.put(28, R.raw._29); rawRourceMap.put(29, R.raw._30);

        rawRourceMap.put(30, R.raw._31); rawRourceMap.put(31, R.raw._32);
        rawRourceMap.put(32, R.raw._33); rawRourceMap.put(33, R.raw._34);
        rawRourceMap.put(34, R.raw._35); rawRourceMap.put(35, R.raw._36);
        rawRourceMap.put(36, R.raw._37);
    }

    public static String getHttpUrl(int boPoMoIndex) {
        String baseUrl = "https://www.mdnkids.com/BoPoMo/";
        String audioPath = "audio/";
        if(boPoMoIndex < 9) {
            audioPath += ("0" + (boPoMoIndex+1));
        }else {
            audioPath += (boPoMoIndex+1);
        }
        return baseUrl + audioPath+".mp3";
    }

    public static int getAudioResourceId(int boPoMoIndex) {

        Integer resourceId = rawRourceMap.get(boPoMoIndex);

        return resourceId != null ? resourceId : R.raw._01;
    }
}
