package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;

public class Program {

	public static void main(String[] args) {
		DepartmentDao depDao = DaoFactory.createDepartmentDao();
		System.out.println(depDao.findAll());
	}

}
