package com.hiromia.banglish;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static org.testng.Assert.*;

public class ThreadSafetyTest {

    @Test(description = "Banglish generator is thread-safe under concurrent multi-threaded load")
    public void testConcurrentExecution() throws Exception {
        Banglish banglish = BanglishGenerator.getInstance();
        int threadCount = 10;
        int iterationsPerThread = 200;

        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        List<Future<Void>> futures = new ArrayList<>();

        for (int t = 0; t < threadCount; t++) {
            futures.add(executor.submit(() -> {
                for (int i = 0; i < iterationsPerThread; i++) {
                    String word = banglish.getWord();
                    assertNotNull(word);

                    String sentence = banglish.getSentence();
                    assertNotNull(sentence);

                    String name = banglish.getName();
                    assertNotNull(name);

                    String phone = banglish.getMobileNumber();
                    assertEquals(phone.length(), 11);

                    String zip = banglish.getZipCode();
                    assertEquals(zip.length(), 4);

                    String city = banglish.getCity();
                    assertNotNull(city);

                    String district = banglish.getDistrict();
                    assertNotNull(district);

                    String nid = banglish.getNationalIdNumber();
                    assertEquals(nid.length(), 17);

                    String brn = banglish.getBirthRegistrationNumber();
                    assertEquals(brn.length(), 17);

                    String smartCard = banglish.getSmartCardNumber();
                    assertEquals(smartCard.length(), 10);

                    String passport = banglish.getPassportNumber();
                    assertEquals(passport.length(), 9);

                    String dl = banglish.getDrivingLicenseNumber();
                    assertEquals(dl.length(), 9);

                    assertNotNull(banglish.getCitizenIdentification());
                }
                return null;
            }));
        }

        executor.shutdown();
        boolean completed = executor.awaitTermination(30, TimeUnit.SECONDS);
        assertTrue(completed, "All threads should complete within timeout");

        for (Future<Void> future : futures) {
            future.get();
        }
    }
}
