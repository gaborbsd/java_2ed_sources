/*
 * Copyright © 2018. Kövesdán Gábor
 * 
 * Az alábbi forráskód a "Szoftverfejlesztés Java SE platformon"
 * c. könyv második kiadásának (ISBN 978-615-00-2933-7) mellékletét
 * képezi.  A forráskódot vagy annak részeit a kiadó engedélye nélkül
 * tilos reprodukálni, adatrögzítő rendszerben tárolni, bármilyen
 * formában vagy eszközzel elektronikus úton vagy más módon közölni.
 */

module com.javaoktato.multiclock.app {
    exports com.javaoktato.multiclock;

    opens com.javaoktato.multiclock to javafx.fxml;

    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;

    requires com.javaoktato.multiclock.service.spi;
    
    uses com.javaoktato.multiclock.service.spi.DateTimeService;
}