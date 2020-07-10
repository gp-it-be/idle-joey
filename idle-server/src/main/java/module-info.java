module idle.server {
    exports server.user.tempexported;//Dit wil ik tijdelijk exporten naar de friendly textclient, zodat die niet via REST rond moet
    exports server.user.exported;
}