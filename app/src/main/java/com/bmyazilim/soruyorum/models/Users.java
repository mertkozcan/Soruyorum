package com.bmyazilim.soruyorum.models;

/**
 * Created by mertk on 14.12.2016.
 */
public class Users {

    public int userID;
    public String userName;
    public String userReelName;
    public String userSurname;
    public String userPassword;
    public String userMail;
    public int profilePictureID;
    public byte faceLogin;
    public byte twitLogin;
    public byte googleLogin;
    public byte deleted;

    public Users(int _userID,String _userName,String _userReelName, String _userSurname, String _userPassword, String _userMail,
                 int _profilePictureID, byte _faceLogin, byte _twitLogin, byte _googleLogin, byte _deleted){


        this.userID=_userID;
        this.userName=_userName;
        this.userReelName=_userReelName;
        this.userSurname=_userSurname;
        this.userPassword=_userPassword;
        this.userMail=_userMail;
        this.profilePictureID=_profilePictureID;
        this.faceLogin=_faceLogin;
        this.twitLogin=_twitLogin;
        this.googleLogin=_googleLogin;
        this.deleted=_deleted;

    }


}
