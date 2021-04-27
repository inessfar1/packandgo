/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Attraction.Entity;

import java.util.regex.*;
import javafx.scene.control.Alert;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class Test
{
  
    private static final Pattern NUMBERS = Pattern.compile("\\d+");
    private static final Pattern LETTERS = Pattern.compile("\\p{Alpha}+");

    public static final boolean isNumeric(String text)
    {
        return NUMBERS.matcher(text).matches();
    }

    public static final boolean isAlpha(String text)
    {
        return LETTERS.matcher(text).matches();
    }
    public static final void msgAlpha(String champ){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Le champ "+champ+" doit être alphabetique");
        alert.showAndWait();   
    }
    public static final void msgNumeric(String champ){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Le champ "+champ+" doit être numerique");
        alert.showAndWait();   
    }
    public static final void msgLength(String champ,String min, String max){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Le champ "+champ+" doit contenir au minimum "+min+" caracteres et au maximum "+max+" caracteres");
        alert.showAndWait();   
    }
    public static boolean isValidEmailAddress(String email) {
   boolean result = true;
   try {
      InternetAddress emailAddr = new InternetAddress(email);
      emailAddr.validate();
   } catch (AddressException ex) {
      result = false;
   }
   return result;
}
     public static final void msgEmail(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Le champ email n'est pas valide");
        alert.showAndWait();   
    }
}
