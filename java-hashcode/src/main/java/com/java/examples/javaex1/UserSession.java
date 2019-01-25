package com.java.examples.javaex1;

public class UserSession {
    private String uid;
    private String vkey;

    public UserSession(String uid, String vkey) {
        this.uid = uid;
        this.vkey = vkey;
    }

    public String getUid() {
        return uid;
    }

    public String getVkey() {
        return vkey;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((uid == null) ? 0 : uid.hashCode());
        result = prime * result + ((vkey == null) ? 0 : vkey.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserSession other = (UserSession) obj;
        if (uid == null) {
            if (other.uid != null)
                return false;
        } else if (!uid.equals(other.uid))
            return false;
        if (vkey == null) {
            if (other.vkey != null)
                return false;
        } else if (!vkey.equals(other.vkey))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "uid: " + uid + " vkey: " + vkey;
    }

}

    
