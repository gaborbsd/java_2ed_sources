/*
 * Copyright © 2018. Kövesdán Gábor
 * 
 * Az alábbi forráskód a "Szoftverfejlesztés Java SE platformon"
 * c. könyv második kiadásának (ISBN 978-615-00-2933-7) mellékletét
 * képezi.  A forráskódot vagy annak részeit a kiadó engedélye nélkül
 * tilos reprodukálni, adatrögzítő rendszerben tárolni, bármilyen
 * formában vagy eszközzel elektronikus úton vagy más módon közölni.
 */

module com.javaoktato.multiclock.service.impl {
    exports com.javaoktato.multiclock.service.impl;

    requires com.javaoktato.multiclock.service.spi;

    provides com.javaoktato.multiclock.service.spi.DateTimeService with com.javaoktato.multiclock.service.impl.DateTimeUtil;
}