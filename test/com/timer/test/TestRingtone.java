package com.timer.test;

import java.io.InputStream;

import org.junit.Test;

import music.Music;

public class TestRingtone {
	@Test
	public void testMethod() {
		InputStream input = getClass().getResourceAsStream("C:\\Users\\X1219\\Desktop\\ringtone.mp3"); //音频存放在src下
        Music music = new Music(input);
        music.start();
	}
}
