package com.apap.tutorial7.service;

import java.util.List;

import com.apap.tutorial7.model.CarModel;
import com.apap.tutorial7.repository.CarDb;

public interface CarService {
	CarModel addCar(CarModel car);
	
	List<CarModel> sortByPriceDesc(Long dealer_id);
	
	void deleteCar(CarModel car);
	
	CarModel getCar(Long id);

	void updateCar(long id,CarModel car);
	
	List<CarModel> getAllCar();
	
}