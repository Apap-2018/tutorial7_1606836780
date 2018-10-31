package com.apap.tutorial7.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.apap.tutorial7.model.*;
import com.apap.tutorial7.service.*;
import com.apap.tutorial7.model.CarModel;

@RestController
@RequestMapping("/car")
public class CarController {
	@Autowired
	private CarService carService;
	
	@Autowired
	private DealerService dealerService;
	
	@PutMapping(value="/{carId}")
	private String updateCarSubmit(
			@PathVariable(value = "carId") long carId,
			@RequestParam("brand") String brand,
			@RequestParam("type") String type,
			@RequestParam("price") String price, 
			@RequestParam("amount") String amount, 
			@RequestParam("dealerId") String dealerId) {
		
		CarModel car = (CarModel) carService.getCar(carId);
		DealerModel dealer = (DealerModel) dealerService.getDealerDetailById(Long.parseLong(dealerId)).get();
		
		if (car.equals(null)) {
			return "Can't find your car";
		}
		
		car.setAmount(Integer.parseInt(amount));
		car.setId(carId);
		car.setBrand(brand);
		car.setDealer(dealer);
		car.setPrice(Long.parseLong(price));
		car.setType(type);
		carService.updateCar(carId, car);
		return "car update success";
		
	}
	
	@GetMapping(value="/{carId}")
	private CarModel viewCar(@PathVariable("carId") long carId) {
		CarModel car = carService.getCar(carId);
		car.setDealer(null);
		return carService.getCar(carId);
	}
	
	@GetMapping()
	private List<CarModel> viewAllCar(){
		List<CarModel> listCar = carService.getAllCar();
		for (CarModel car : listCar) {
			car.setDealer(null);
			
		}
		return listCar;
		
	}
	
	@DeleteMapping(value="/{carId}")
	private String deleteCar(@PathVariable("carId") long carId) {
		CarModel car = carService.getCar(carId);
		carService.deleteCar(car);
		return "car has been deleted";
	}
	
	@PostMapping(value="/add")
	private CarModel addCarSubmit(@RequestBody CarModel car) {
		return carService.addCar(car);
	}
	
/*	@RequestMapping(value = "/car/add/{dealerId}", method = RequestMethod.GET)
	private String add(@PathVariable(value = "dealerId") Long dealerId, Model model) {
		//CarModel car = new CarModel();
		DealerModel dealer  = dealerService.getDealerDetailById(dealerId).get();
		ArrayList<CarModel> listCar = new ArrayList<CarModel>();
		listCar.add(new CarModel());
		dealer.setListCar(listCar);
		
		//car.setDealer(dealer);
		
		model.addAttribute("dealer", dealer);
		return "addCar";
	}
	
	@RequestMapping(value = "/car/add/{dealerId}", method = RequestMethod.POST, params={"save"})
	private String addCarSubmit(@ModelAttribute DealerModel	dealer) {
		//carService.addCar(car);
		DealerModel dealerNow = dealerService.getDealerDetailById(dealer.getId()).get();
		for(CarModel car: dealer.getListCar()) {
			car.setDealer(dealerNow);
			carService.addCar(car);
				
		}
		return "add";
	}
	
	
	
	@RequestMapping(value = "/car/delete", method = RequestMethod.POST)
	private String delete(@ModelAttribute DealerModel dealer,Model model) {
		for(CarModel car : dealer.getListCar()) {
			carService.deleteCar(car);
		}
		return "delete";
	}
	
	@RequestMapping(value = "/car/delete/{id}", method = RequestMethod.GET)
	private String deleteCar(@PathVariable long id) {
		CarModel car = carService.getCar(id);
		carService.deleteCar(car);
		return "delete";
	}
	
	@RequestMapping(value = "/car/update/{id}", method = RequestMethod.GET)
	private String updateCar(@PathVariable(value = "id") long id, Model model) {
		CarModel car = carService.getCar(id);
		model.addAttribute("car",car);
		return "changeCar";
	}
	
	@RequestMapping(value = "/car/update/{id}", method = RequestMethod.POST)
	private String updateCarSubmit(@PathVariable(value = "id") long id, @ModelAttribute CarModel car) {
		carService.updateCar(id, car);
		return "update";
	}
	
	@RequestMapping(value = "/car/add/{dealerId}", method = RequestMethod.POST, params={"addRow"})
	public String addRow(@ModelAttribute DealerModel dealer, BindingResult bindingResult, Model model) {
		if(dealer.getListCar() == null) {
			dealer.setListCar(new ArrayList<CarModel>());
		}
		dealer.getListCar().add(new CarModel());
		model.addAttribute("dealer", dealer);
		return "addCar";
	}
	
	@RequestMapping(value="/car/add/{dealerId}", method = RequestMethod.POST, params={"removeRow"})
	public String removeRow(@ModelAttribute DealerModel dealer, final BindingResult bindingResult, final HttpServletRequest req, Model model) {
	    final Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
	    dealer.getListCar().remove(rowId.intValue());
	    
	    model.addAttribute("dealer", dealer);
	    return "addCar";
	}*/
	
	
}