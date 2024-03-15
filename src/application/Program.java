package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		SellerDao sellerDao = DaoFactory.createSellerDao();

		System.out.println("*** Test 1 - findById: ***");
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);

		System.out.println("*** Test 2 - findByDepartment: ***");
		Department dep = new Department(1, null);
		List<Seller> sellerList = sellerDao.findByDepartment(dep);
		System.out.println(sellerList);
		
		System.out.println("*** Test 3 - findAll: ***");
		List<Seller> allSellers = sellerDao.findAll();
		allSellers.forEach(sel -> {
			System.out.println(sel);
		});

	}

}
