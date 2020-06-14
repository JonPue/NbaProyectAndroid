package com.devIvan.nba.NBA;

public class user {
    private String uid;
    private String _nombre;
    private String _email;

    public user(){

    }

    public user(String uid, String _nombre, String _email) {
        this.uid = uid;
        this._nombre = _nombre;
        this._email = _email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String get_nombre() {
        return _nombre;
    }

    public void set_nombre(String _nombre) {
        this._nombre = _nombre;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }
}
