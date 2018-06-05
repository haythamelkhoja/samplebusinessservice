package com.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.sample.dao.customer.Customer;
import com.sample.dao.customer.CustomerDao;
import com.sample.dao.feature.Feature;
import com.sample.dao.feature.FeatureDAO;
import com.sample.service.contants.BoNames;
import com.sample.shared.Constants;

@Component
public class DataLoaderBean {

	@Autowired
	private CustomerDao mOBCustomerRepository;

	@Autowired
	private FeatureDAO mOBFeatureDAO;

	public DataLoaderBean() {
		super();
	}

	
	@EventListener
	public void onApplicationEvent(ContextRefreshedEvent event) {		
		loadFeatureData();
		loadCustomerData();
	}

	private void loadCustomerData() {
		mOBCustomerRepository.save(new Customer("Gökçe", "Tuna"));
		mOBCustomerRepository.save(new Customer("Fatih", "Çelikel"));
		mOBCustomerRepository.save(new Customer("Muharrem", "Gün"));
		mOBCustomerRepository.save(new Customer("Serhat", "Dirik"));
	}

	private void loadFeatureData() {
		Feature lOBFeature = new Feature(Constants.Services.WelcomeService.SERVICE, BoNames.BO_WELCOME_DEFAULT,
				Boolean.TRUE);

		mOBFeatureDAO.save(lOBFeature);
	}

}
