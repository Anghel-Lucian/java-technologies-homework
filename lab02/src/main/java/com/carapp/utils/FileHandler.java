package com.carapp.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

import com.carapp.model.Car;

public class FileHandler {
	private final String FILE_DIR = "lab02/resources/";
	private final String FILE_NAME = "lab02/resources/cars.txt";
	
	public void writeToFile(Car car) throws IOException {
		File dir = new File(FILE_DIR);
		File file = new File(FILE_NAME);
		String category = car.getCategory();
		String make = car.getMake();
		int year = car.getYear();
		String content = category + "," + make + "," + year + "\n";
		
		if(dir.mkdirs()) {
			file.createNewFile();
		}
		
		Files.write(Paths.get(FILE_NAME), content.getBytes(), StandardOpenOption.APPEND);
	}
	
	public ArrayList<Car> readFile() throws FileNotFoundException {
		ArrayList<Car> cars = new ArrayList<Car>();
		File file = new File(FILE_NAME);
		Scanner reader = new Scanner(file);
		
		while(reader.hasNextLine()) {
			cars.add(this.parseStringToCar(reader.nextLine()));
		}
		reader.close();
		
		return cars;
	}
	
	private Car parseStringToCar(String s) {
		String[] arrOfS = s.split(",");
		String category = arrOfS[0];
		String make = arrOfS[1];
		int year = Integer.parseInt(arrOfS[2]);
		
		Car car = new Car();
		
		car.setCategory(category);
		car.setMake(make);
		car.setYear(year);
		
		return car;
	}
}

