package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {
	private Connection conn;

	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Department obj) {
		PreparedStatement st = null;

		try {
			st = this.conn.prepareStatement("INSERT INTO department (Name) VALUES (?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getName());

			int rowsAffected = st.executeUpdate();
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public void update(Department obj) {
		PreparedStatement st = null;

		try {
			st = this.conn.prepareStatement("UPDATE department SET Name = ?, Id = ? WHERE Id = ?",
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getName());
			st.setInt(2, obj.getId());
			st.setInt(3, obj.getId());

			st.executeUpdate();

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;

		try {
			st = this.conn.prepareStatement("DELETE FROM department WHERE department.Id = ?");
			st.setInt(1, id);
			st.executeUpdate();

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = this.conn.prepareStatement("SELECT department.* FROM department WHERE department.Id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();

			Department dep = new Department();
			if (rs.next()) {
				dep.setId(rs.getInt("Id"));
				dep.setName(rs.getString("Name"));
				return dep;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public List<Department> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Department> deps = new ArrayList<Department>();
		try {
			st = this.conn.prepareStatement("SELECT department.Id, department.Name AS DepName FROM department");
			rs = st.executeQuery();

			while (rs.next()) {
				Department dep = new Department(rs.getInt("Id"), rs.getString("DepName"));
				deps.add(dep);
			}
			return deps;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

}
