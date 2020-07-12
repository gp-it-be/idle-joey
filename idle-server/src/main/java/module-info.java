module idle.server {
    exports server.tempexported;
    requires idle.server.user;
    requires idle.server.requirement;
    requires idle.server.shared;
    requires spring.boot;
    requires spring.webmvc;
    requires spring.web;
    requires spring.boot.autoconfigure;
    requires spring.context;
}