module idle.server {
    exports server.tempexported;
    requires idle.server.user;
    requires idle.server.requirement;
    requires idle.server.shared;
}