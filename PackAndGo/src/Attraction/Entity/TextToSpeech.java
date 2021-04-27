/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Attraction.Entity;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
/**
 *
 * @author 21628
 */
public class TextToSpeech {
//String voiceName = "kevin16";
VoiceManager freeVM;
Voice voice;
public TextToSpeech(String words) {
    System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
    freeVM = VoiceManager.getInstance();
    voice = VoiceManager.getInstance().getVoice("kevin16");
    if (voice != null) {
        voice.allocate();//Allocating Voice
    }

    try {
        voice.setRate(190);//Setting the rate of the voice
        voice.setPitch(150);//Setting the Pitch of the voice
        voice.setVolume(3);//Setting the volume of the voice
        SpeakText(words);// Calling speak() method


    } catch (Exception e1) {
        e1.printStackTrace();
    }



}

public void SpeakText(String words) {
    voice.speak(words);
}}
