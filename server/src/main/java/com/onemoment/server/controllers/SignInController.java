package com.onemoment.server.controllers;
import java.util.Scanner;
import com.onemoment.server.models.SignIn;

public class SignInController {
    public static void main() {
    /*
    Scanner kboard = new Scanner(System.in);
    String uName = kboard.nextLine();
    String pWord = kboard.nextLine();
    private SignIn sIn = new SignIn(uName, pWord);
    */

    //fill with info from frontend?
    String uName = null;
    int uSize = uName.length();
    String pWord = null;
    int pSize = pWord.length();
    SignIn sIn;
    boolean successfulSignIn;

    if(uSize >= 6 && uSize <= 14 && pSize >= 8) {
        sIn = new SignIn(uName, pWord);
        successfulSignIn = sIn.isSuccessful();
    }
    else {
        successfulSignIn = false;
    }
    

}

}