package com.apap.tutorial7.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tutorial7.model.CarModel;
import com.apap.tutorial7.model.DealerModel;
import com.apap.tutorial7.repository.DealerDb;

@Service
@Transactional
public class DealerServiceImpl implements DealerService{
	@Autowired
	private DealerDb dealerDb;
	
	@Override
	public Optional<DealerModel> getDealerDetailById(Long id){
		return dealerDb.findById(id);
	}

	@Override
	public DealerModel addDealer(DealerModel dealer) {
		return dealerDb.save(dealer);
	}
	
	@Override
	public void deleteDealer(DealerModel dealer) {
		dealerDb.delete(dealer);
	}


	@Override
	public List<DealerModel> viewAllDealer() {
		return dealerDb.findAll();
	}

	@Override
	public void updateDealer(long id, DealerModel dealer) {
		DealerModel dealerUpdated = dealerDb.getOne(id);
		dealerUpdated.setAlamat(dealer.getAlamat());
		dealerUpdated.setNoTelp(dealer.getNoTelp());
		dealerDb.save(dealerUpdated);
	}

}