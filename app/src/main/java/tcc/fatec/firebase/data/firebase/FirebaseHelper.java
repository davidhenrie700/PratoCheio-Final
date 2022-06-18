package tcc.fatec.firebase.data.firebase;

import tcc.fatec.firebase.util.Messages;

public class FirebaseHelper {

    private FirebaseHelper() {}

    public static String translateError(String error) {
        if (error.contains("There is no user record")) return Messages.ACCOUNT_NOT_REGISTERED;

        if (error.contains("The email address is badly")) return Messages.INVALID_EMAIL;

        if (error.contains("The password is invalid")) return Messages.INVALID_PASSWORD;

        if (error.contains("The email address is already")) return Messages.EMAIL_IN_USE;

        if (error.contains("Password should be at")) return Messages.STRONG_PASSWORD;

        return Messages.GENERIC;
    }
}
