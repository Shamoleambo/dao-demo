package application;

import java.util.Date;
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

		System.out.println();
		System.out.println("*** Test 4 - insert: ***");
		Seller newSeller = new Seller(1, "Euzébio", "euzebio.matozo@bol.com", new Date(), 4250.0,
				new Department(1, null));
		sellerDao.insert(newSeller);
		List<Seller> allSellers2 = sellerDao.findAll();
		allSellers.forEach(sel -> {
			System.out.println(sel);
		});

		System.out.println();
		System.out.println("*** Test 5 - update: ***");
		seller = sellerDao.findById(1);
		seller.setName("Manolo Testudo");
		sellerDao.update(seller);

		System.out.println();
		System.out.println("*** Test 6 - delete: ***");
		sellerDao.delete(1);
	}

}
