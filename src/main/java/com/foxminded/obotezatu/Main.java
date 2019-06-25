package com.foxminded.obotezatu;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;

public class Main {

	public static void main(String[] args){
		List<Racer> racers = new RacerRepository().readRacers();
		toJson(racers);
		int limit = 15;
		RacerFormatter racerFormatter = new RacerFormatter();
		System.out.println(racerFormatter.format(racers, limit));
	}
	
	public static void toJson(List<Racer> racers) {
		Gson gson = new Gson();
		Racer racer1 = racers.get(0);
		try {
			gson.toJson(racer1, new FileWriter("D:\\Books\\java\\Java 2018 USM\\Tasks\\Java8_Stream_API\\src\\test\\resources\\racer.json"));
		} catch (JsonIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
