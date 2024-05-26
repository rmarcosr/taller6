/**
 * 
 */
/**
 * 
 */
module T5MarcosRodriguez {
    requires java.xml.bind;
    requires java.xml;
    requires jdk.xml.dom;
    requires java.sql;

    opens dominio to java.xml.bind;
    exports dominio;
}
