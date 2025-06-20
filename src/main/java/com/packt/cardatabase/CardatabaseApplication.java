package com.packt.cardatabase;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.packt.cardatabase.domain.AppUser;
import com.packt.cardatabase.domain.AppUserRepository;
import com.packt.cardatabase.domain.Car;
import com.packt.cardatabase.domain.CarRepository;
import com.packt.cardatabase.domain.Owner;
import com.packt.cardatabase.domain.OwnerRepository;

@SpringBootApplication
public class CardatabaseApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(CardatabaseApplication.class);
	
	private final CarRepository repository;
	private final OwnerRepository orepository;
	private final AppUserRepository urepository;
	
	public CardatabaseApplication(CarRepository repository, OwnerRepository orepository, AppUserRepository urepository) {
		this.repository = repository;
		this.orepository = orepository;
		this.urepository = urepository;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(CardatabaseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		// 소유자 객체를 추가하고 데이터베이스에 저장
		Owner owner1 = new Owner("John", "Johnson");
		Owner owner2 = new Owner("Mary", "Robinson");
		orepository.saveAll(Arrays.asList(owner1, owner2));
		
		repository.save(new Car("Ford" , "Mustang" , "Red" , "ADF-1121", 2023, 59000, owner1));
		repository.save(new Car("Nissan" , "Leaf" , "White" , "SSJ-3002", 2020, 29000, owner1));
		repository.save(new Car("Toyota" , "Prius" , "Silver" , "KKO-0212", 2022, 39000, owner2));
		
		// 모든 자동차를 가져와 Console에 로그를 출력
		for (Car car : repository.findAll()) {
			logger.info("brand: {}, model: {}", car.getBrand(), car.getModel());
		}
		
		// 사용자명 : user , 비밀번호 : user
		urepository.save(new AppUser("user", "$2y$04$p3GNosSbCumbb7zP8pW6e.ONo1Qfs8FFyD9FxSKvTEXGPv8Ys/OGm", "USER"));
		
		// 사용자명 : admin, 비밀번호 : admin
		urepository.save(new AppUser("admin", "$2y$04$Oruy.kFZiotg3LRQ.wvvLelhWbk9casEys0bNzJewSJXnQAdu9M9y", "ADMIN"));
	}

}
