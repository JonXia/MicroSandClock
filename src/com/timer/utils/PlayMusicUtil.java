package com.timer.utils;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JApplet;

public class PlayMusicUtil {
    public static AudioClip loadSound(String filename) {  
        URL url = null;  
        try {  
            url = new URL("file:" + filename);  
        }   
        catch (MalformedURLException e) {;}  
        return Applet.newAudioClip(url);  
    }  
    public void play() {  
        AudioClip christmas = loadSound("C:\\Users\\X1219\\Desktop\ringtone.wav");
        christmas.loop();
    }  
}
