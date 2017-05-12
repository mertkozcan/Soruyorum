package com.bmyazilim.soruyorum.models;

/**
 * Created by mertk on 2.05.2017.
 */

public class Questions {

    public int QuestionID;
    public int QuestionPictureID;
    public int CategoryID;
    public String QuestionText;
    public String OptionA;
    public String OptionB;
    public String OptionC;
    public String OptionD;
    public String OptionE;

    public Questions( int QuestionID,int QuestionPictureID,int CategoryID,String QuestionText,String OptionA,String OptionB,String OptionC,String OptionD,String OptionE){

        this.QuestionID=QuestionID;
        this.QuestionPictureID=QuestionPictureID;
        this.CategoryID=CategoryID;
        this.QuestionText=QuestionText;
        this.OptionA=OptionA;
        this.OptionB=OptionB;
        this.OptionC=OptionC;
        this.OptionD=OptionD;
        this.OptionE=OptionE;


    }

}
