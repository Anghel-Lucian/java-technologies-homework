package com.carapp.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.carapp.utils.FileHandler;

// persists added cars and returns them when "result.jsp" is accessed
public class CarBean {
    private ArrayList<String> categories = new ArrayList<String>(Arrays.asList("Coupe", "Sedan", "Break", "Hatchback", "Cabrio", "Roadster", "Truck"));
    private FileHandler fh = new FileHandler();

    public void addCar(Car car) throws IOException {
        this.fh.writeToFile(car);
    }

    public ArrayList<Car> getCars() {
        try {
        	// returning all of the Car entities stored inside the "lab02/resources/cars.txt" file
            return this.fh.readFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<String> getCategories() {
        return this.categories;
    }
}

