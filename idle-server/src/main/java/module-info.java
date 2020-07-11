module idle.server {
    exports server.tempexported;
    requires idle.server.user;
    requires idle.server.activity;
    requires idle.server.shared;
}