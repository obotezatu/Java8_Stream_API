package com.foxminded.obotezatu;

import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		
		try {
			QualificationRace.readTime();
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}

}
