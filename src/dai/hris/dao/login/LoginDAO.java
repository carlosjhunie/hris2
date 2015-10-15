package dai.hris.dao.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dai.hris.action.filemanager.util.EmployeeUtil;
import dai.hris.dao.DBConstants;
import dai.hris.model.Employee;

public class LoginDAO {
	
	Connection conn = null;
    
    public LoginDAO() {    	
    	
    	try {
    		
    		/* MYSQL CODE */
//			MysqlDataSource ds = null;
//			ds = new MysqlDataSource();
//			ds.setUrl(DBConstants.DB_URL);
//			ds.setUser(DBConstants.DB_USERNAME);
//			ds.setPassword(DBConstants.DB_PASSWORD);
//			conn = ds.getConnection();
    		
    		/* MS SQL CODE */    		
    		Class.forName(DBConstants.DB_DRIVER_CLASS_NAME);
        	conn = DriverManager.getConnection(DBConstants.MS_SQL_DB_URL);    		
    		conn.setAutoCommit(false);

		} catch (SQLException sqle) {
			System.out.println("LoginDAO :" + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("LoginDAO :" + e.getMessage());
		}
    	
    }
	
    public Employee getEmployee(String username) throws SQLException, Exception {
		Employee emp = null;
		String sql = "SELECT * FROM employee where username = ?";
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setString(1, username);

	    ResultSet rs = ps.executeQuery();
	    
	    if (rs.next()) {
	    	emp = new Employee();
			emp.setEmpId(rs.getInt("empId"));
			emp.setEmpNo(rs.getString("empNo")); // required field
			emp.setLastname(rs.getString("lastname"));
			emp.setFirstname(rs.getString("firstname"));
			emp.setMiddlename(rs.getString("middlename"));
			emp.setDateOfBirth(rs.getString("dateOfBirth"));
			emp.setGender(rs.getString("gender"));
			emp.setCivilStatus(rs.getString("civilStatus"));
			emp.setNationality(rs.getString("nationality"));
			emp.setStreet(rs.getString("street"));
			emp.setCityId(rs.getInt("cityId"));
			emp.setEmail(rs.getString("email"));
			emp.setMobileNo(rs.getString("mobileNo"));
			emp.setTelNo(rs.getString("telNo"));
			emp.setBirthPlace(rs.getString("birthPlace"));
			emp.setProvinceId(rs.getInt("provinceId"));
			emp.setZipCode(rs.getString("zipCode"));
			emp.setJobTitleId(rs.getInt("jobTitleId"));
			emp.setDepartmentId(rs.getInt("departmentId"));
			emp.setDivisionId(rs.getInt("divisionId"));
			emp.setEmployeeTypeId(rs.getInt("employeeTypeId"));
			emp.setEmpStatus(rs.getString("empStatus"));
			emp.setEmploymentDate(rs.getString("employmentDate"));
			emp.setEndOfContract(rs.getString("endOfContract"));
			emp.setSss(rs.getString("sss"));
			emp.setGsis(rs.getString("gsis"));
			emp.setHdmf(rs.getString("hdmf"));
			emp.setTin(rs.getString("tin"));
			emp.setPhic(rs.getString("phic"));
			emp.setTaxstatus(rs.getString("taxstatus"));
			emp.setPicLoc(rs.getString("picLoc"));
			emp.setSuperVisor1Id(rs.getInt("superVisor1Id"));
			emp.setSuperVisor2Id(rs.getInt("superVisor2Id"));
			emp.setSuperVisor3Id(rs.getInt("superVisor3Id"));
			emp.setUsername(rs.getString("username"));			
			emp.setPassword(EmployeeUtil.decodePassword(rs.getString("password")));
		}
	    sql = null;
	    ps.close();
	    rs.close();	 
	    return emp;

	}
    
    public boolean checkLoginCredentials(String username, String password) throws SQLException, Exception {
    	
    	String sql = "SELECT * FROM employee where username = '" + username + "' and password = '" + password + "'";    	
    	
    	PreparedStatement ps = conn.prepareStatement(sql);            

    	ResultSet rs = ps.executeQuery();            

            
    	if (rs.next()) {
    		sql = null;
	        ps.close();
	        rs.close();
    		return true;
    	}
    	sql = null;
        ps.close();
        rs.close();
    	return false;    	
    }
    
    public void updateLoginActivity(int userId, int departmentId, String transType)  throws SQLException, Exception {
    	
    	if("LOGOUT".equals(transType)){
    		String sql = "UPDATE loginactivity SET logout = GETDATE() WHERE userId = " + userId + " AND logout IS NULL AND login = (SELECT MAX(a.login) FROM loginactivity a WHERE loginactivity.userId = a.userid)";        	
    		
    		PreparedStatement ps = conn.prepareStatement(sql);
    		    		    		
        	ps.executeUpdate();
        	conn.commit();  	    	
            ps.close();
            
            
    	} else {
    		String sql = "INSERT INTO loginactivity(userId,login,departmentId) VALUES(?,GETDATE(),?)";
    		PreparedStatement ps = conn.prepareStatement(sql);

    		ps.setInt(1, userId);    		
    		ps.setInt(2, departmentId);    		
        	ps.executeUpdate();
        	conn.commit();  	    	
            ps.close();     
    	}
    	
		
		
    }
    
    public void closeConnection() throws SQLException, Exception {
		conn.close();
	}
	
	
	

}
