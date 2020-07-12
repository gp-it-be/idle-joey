module idle.textclient {
    requires idle.server.shared;
    requires feign.core;
    requires feign.gson;
    requires spring.webflux;
    requires reactor.core;
    requires spring.web;
    requires spring.core;
    requires okhttp.eventsource;
}