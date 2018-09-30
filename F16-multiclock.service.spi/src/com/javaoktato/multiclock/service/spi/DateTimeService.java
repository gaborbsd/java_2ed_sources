/*
 * Copyright © 2018. Kövesdán Gábor
 * 
 * Az alábbi forráskód a "Szoftverfejlesztés Java SE platformon"
 * c. könyv második kiadásának (ISBN 978-615-00-2933-7) mellékletét
 * képezi.  A forráskódot vagy annak részeit a kiadó engedélye nélkül
 * tilos reprodukálni, adatrögzítő rendszerben tárolni, bármilyen
 * formában vagy eszközzel elektronikus úton vagy más módon közölni.
 */

package com.javaoktato.multiclock.service.spi;

import java.util.List;

public interface DateTimeService {

    String getCurrentTimeByTimeZone(String zoneId);

    String getZeroTime();

    void startStopper();

    void stopStopper();

    void resetStopper();

    void lapStopper();

    String getCurrentStopperDuration();

    List<String> getStopperLaps();

    void startTimer(String d);

    void stopTimer();

    void resumeTimer();

    String getCurrentTimerDuration();

    boolean isTimerOff();

    String getDuration(int y1, int m1, int d1, int y2, int m2, int d2);
}
