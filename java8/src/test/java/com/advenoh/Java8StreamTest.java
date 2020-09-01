package com.advenoh;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Java8StreamTest {
    /**
     * todo : 작업하기
     * https://howtodoinjava.com/java8/stream-flatmap-example/
     */
//    @Test
//    public void test_flatMap() {
//        List<MediaVod> mediaVods = new ArrayList<>();
//        MediaVod mediaVod;
//
//        String dealStr;
//        int prefixNum = 234;
//        //		List<Long> testDealNos = Arrays.asList(prefixNum);
//
//        for (int i = 0; i < 5; i++) {
//            mediaVod = new MediaVod();
//            mediaVod.setVodNo((long) i);
//            dealStr = "1111" + i + ",2222" + i + ",3333" + i;
//            mediaVod.setDealStr(dealStr);
//            mediaVods.add(mediaVod);
//            prefixNum++;
//        }
//
//        List<Long> dealNos = mediaVods.stream().flatMap(it -> DealUtil.convertDealStrToListOfLong(it.getDealStr()).stream()).collect(Collectors.toList());
//        log.warn("[voddebug] mediaVods : {}", mediaVods);
//        log.warn("[voddebug] dealNos : {}", dealNos);
//    }
}
