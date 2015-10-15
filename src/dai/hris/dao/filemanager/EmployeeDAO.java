package dai.hris.dao.filemanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import dai.hris.action.filemanager.util.EmployeeUtil;
import dai.hris.dao.DBConstants;
import dai.hris.model.Department;
import dai.hris.model.Employee;
import dai.hris.model.SearchEmployee;

public class EmployeeDAO {
	Connection conn = null;

	public EmployeeDAO() {

		try {

			/* MS SQL CODE */
			Class.forName(DBConstants.DB_DRIVER_CLASS_NAME);
			conn = DriverManager.getConnection(DBConstants.MS_SQL_DB_URL);
			conn.setAutoCommit(false);

		} catch (SQLException sqle) {
			System.out.println("EmployeeDAO :" + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("EmployeeDAO :" + e.getMessage());
		}
	}

	
	/**
	 * get all employees from employee table
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public ArrayList<Employee> getAll() throws SQLException, Exception {

		String sql = "SELECT * FROM employee";
		PreparedStatement ps = conn.prepareStatement(sql.toString());

		ResultSet rs = ps.executeQuery();
		ArrayList<Employee> list = new ArrayList<Employee>();

		while (rs.next()) {
			Employee emp = new Employee();
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
			emp.setCountryId(rs.getInt("countryId"));
			list.add(emp);

		}

		ps.close();
		rs.close();
		return list;

	}
	
	public ArrayList<Employee> getEmployeeListBySupervisorId(int supervisorId) throws SQLException, Exception {

		String sql = "SELECT * FROM employee WHERE superVisor1Id = ?";
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		
		ps.setInt(1, supervisorId);

		ResultSet rs = ps.executeQuery();
		ArrayList<Employee> list = new ArrayList<Employee>();

		while (rs.next()) {
			Employee emp = new Employee();
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
			emp.setCountryId(rs.getInt("countryId"));
			list.add(emp);

		}

		ps.close();
		rs.close();
		return list;

	}
	
	/**
	 * 
	 * @param empId
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public Employee getEmployee(int empId) throws SQLException, Exception {
		Employee emp = null;
		String sql = "SELECT * FROM employee where empId = ?";
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setInt(1, empId);

	    ResultSet rs = ps.executeQuery();
	    
	    if (rs.next()) {
			emp = new Employee();
			emp.setEmpId(rs.getInt("empId"));
			emp.setEmpNo(rs.getString("empNo")); // required field
			emp.setLastname(rs.getString("lastname"));
			emp.setFirstname(rs.getString("firstname"));
			emp.setMiddlename(rs.getString("middlename"));
			emp.setDateOfBirth(StringUtils.isEmpty(rs.getString("dateOfBirth"))? "" : rs.getString("dateOfBirth").substring(0, 10));
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
			emp.setEmploymentDate(StringUtils.isEmpty(rs.getString("employmentDate")) ? "" : rs.getString("employmentDate").substring(0, 10));
			emp.setEndOfContract(StringUtils.isEmpty(rs.getString("endOfContract")) ? "" : rs.getString("endOfContract").substring(0, 10));
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
			emp.setCountryId(rs.getInt("countryId"));
			
			emp.setJobTitleName(getJobtitleName(rs.getInt("jobTitleId")));
			emp.setDepartmentName(getDepartmentName(rs.getInt("departmentId")));
			emp.setDivisionName(getDivisionName(rs.getInt("divisionId")));

		}
	    sql = null;
	    ps.close();
	    rs.close();	 
	    return emp;

	}
	
	
	public List<SearchEmployee> selectEmployee(String oSearchText) throws SQLException, Exception {
		SearchEmployee semp = null;
		List<SearchEmployee> sempList = new ArrayList<>();
		StringBuffer dynSql = new StringBuffer("");
		PreparedStatement ps = null;
		if (StringUtils.isEmpty(oSearchText)) {
			System.out.println("here at if");
			dynSql.append("select e.empid, e.empNo, e.firstname, e.lastname, ");
			dynSql.append("d.departmentName, divisionName from employee e, department d, division dv ");
			dynSql.append("where e.departmentId=d.departmentId ");
			dynSql.append(" and e.divisionId = dv.divisionId ");
			ps = conn.prepareStatement(dynSql.toString());
		} else{
			System.out.println("here at else");
			dynSql.append("select e.empid, e.empNo, e.firstname, e.lastname, ");
			dynSql.append("d.departmentName, divisionName from employee e, department d, division dv ");
			dynSql.append("where e.departmentId=d.departmentId ");
			dynSql.append(" and e.divisionId = dv.divisionId ");
			dynSql.append(" and (lower(e.firstname) like (?) or lower(e.lastname) like (?) or ");
			dynSql.append("      lower(d.departmentName) like (?) or lower(dv.divisionName) like (?) or ");
			dynSql.append("      lower(e.empNo) like (?) )");
			ps = conn.prepareStatement(dynSql.toString());
			String tmpSearchTxt = "%" + oSearchText.toLowerCase() + "%";
			ps.setString(1, tmpSearchTxt);
			ps.setString(2, tmpSearchTxt);
			ps.setString(3, tmpSearchTxt);
			ps.setString(4, tmpSearchTxt);
			ps.setString(5, tmpSearchTxt);
		}

	    ResultSet rs = ps.executeQuery();
	    
	    while (rs.next()) {
			semp = new SearchEmployee();
			semp.setEmpId(rs.getInt("empId"));
			semp.setEmpNo(rs.getString("empNo")); // required field
			semp.setLastname(rs.getString("lastname"));
			semp.setFirstname(rs.getString("firstname"));

			semp.setDepartmentName(rs.getString("departmentName"));
			semp.setDivisionName(rs.getString("divisionName"));
			sempList.add(semp);
		}
	    dynSql = null;
	    ps.close();
	    rs.close();	 
	    return sempList;
	}
	
	
	public int checkExistingUserName(String username) throws SQLException {		
		String sql = "select empId from employee where lower(username) = ?";
		int empIdExisting = 0;
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setString(1, username);
		
	    ResultSet rs = ps.executeQuery();
	    if (rs.next()) {	    	
	    	empIdExisting = rs.getInt("empId");	    	 
	    }

	    
	    ps.close();
	    rs.close();      
	    return empIdExisting;     
	}
	
	
	public List<Integer> getAllEmpIdsWithEmployeeModuleAccess() throws SQLException {		
		String sql = "select empid from moduleaccess where employee like '%em_employee%'";
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		List<Integer> empListWithEmployeeModuleAccess = new ArrayList<Integer>();
		
	    ResultSet rs = ps.executeQuery();
	    if (rs.next()) {	    	
	    	empListWithEmployeeModuleAccess.add(rs.getInt("empId"));	    	 
	    }

	    
	    ps.close();
	    rs.close();      
	    return empListWithEmployeeModuleAccess;     
	}
	
	
	private String getDepartmentName (int departmentId)  throws SQLException, Exception {
		String sql = "SELECT   departmentId,  departmentName FROM department where departmentId = ?";		
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setInt(1, departmentId);
		
	    ResultSet rs = ps.executeQuery();
	   
	    
	    if (rs.next()) {	    	
	    	return rs.getString("departmentName");	    	 
	    }
	    
	    ps.close();
	    rs.close();      
	    return "";     
	}
	
	private String getDivisionName (int divisionId)  throws SQLException, Exception {
		String sql = "SELECT   divisionId,  divisionName FROM division where divisionId = ?";		
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setInt(1, divisionId);
		
	    ResultSet rs = ps.executeQuery();
	   
	    
	    if (rs.next()) {	    	
	    	return rs.getString("divisionName");	    	 
	    }
	    
	    ps.close();
	    rs.close();      
	    return "";     
	}
	
	private String getJobtitleName (int jobTitleId)  throws SQLException, Exception {
		String sql = "SELECT   jobTitleId,  jobTitle FROM jobTitle where jobTitleId = ?";		
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setInt(1, jobTitleId);
		
	    ResultSet rs = ps.executeQuery();
	   
	    
	    if (rs.next()) {	    	
	    	return rs.getString("jobTitle");	    	 
	    }
	    
	    ps.close();
	    rs.close();      
	    return "";     
	}
	/**
	 * 
	 * @param empId
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public Employee getEmployee(String empNo) throws SQLException, Exception {
		Employee emp = null;
		String sql = "SELECT * FROM employee where empNo = ?";
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setString(1, empNo);

	    ResultSet rs = ps.executeQuery();
	    
	    if (rs.next()) {
			emp = new Employee();
			emp.setEmpId(rs.getInt("empId"));
			emp.setEmpNo(rs.getString("empNo")); // required field
			emp.setLastname(rs.getString("lastname"));
			emp.setFirstname(rs.getString("firstname"));
			emp.setMiddlename(rs.getString("middlename"));
			emp.setDateOfBirth(StringUtils.isEmpty(rs.getString("dateOfBirth"))? "" : rs.getString("dateOfBirth").substring(0, 10));
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
			emp.setEmploymentDate(StringUtils.isEmpty(rs.getString("employmentDate")) ? "" : rs.getString("employmentDate").substring(0, 10));
			emp.setEndOfContract(StringUtils.isEmpty(rs.getString("endOfContract")) ? "" : rs.getString("endOfContract").substring(0, 10));
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
			emp.setCountryId(rs.getInt("countryId"));

		}
	    sql = null;
	    ps.close();
	    rs.close();	 
	    return emp;

	}
	
	public HashMap<String, Object> getEmployeeSupervisors(int empId) throws SQLException, Exception {
	    
	    StringBuffer dynSql = new StringBuffer("");
	    dynSql.append("SELECT e1.empId AS employeeID, e1.superVisor1Id AS employeeSV1, e1.superVisor2Id AS employeeSV2, e1.superVisor3Id AS employeeSV3, ");
	    dynSql.append(" e2.empId as employeeSVID, e2.firstname AS SVFName, e2.lastname SVLName ");
	    dynSql.append(" FROM employee e1, employee e2 ");
	    dynSql.append(" WHERE "); 
	    dynSql.append("  ((e1.superVisor1Id = e2.empId) OR (e1.superVisor2Id = e2.empId) OR (e1.superVisor3Id = e2.empId)) ");
	    //dynSql.append("  AND ((e1.superVisor1Id = ?) OR (e1.superVisor2Id = ?) OR (e1.superVisor3Id = ?)) ");
	    dynSql.append("  AND  e1.empId = ?");
	    
	    PreparedStatement ps2 = conn.prepareStatement(dynSql.toString());
	    ps2.setInt(1, empId);
	    ResultSet rs2 = ps2.executeQuery();
	    HashMap<String, Object> map = new HashMap<String, Object>();
	    int tmpSuperVisor1Id;
	    int tmpSuperVisor2Id;
	    int tmpSuperVisor3Id;
	    int tmpEmployeeSVId;
		while (rs2.next()) {
			tmpSuperVisor1Id = rs2.getInt("employeeSV1");
			tmpSuperVisor2Id = rs2.getInt("employeeSV2");
			tmpSuperVisor3Id = rs2.getInt("employeeSV3");
			tmpEmployeeSVId = rs2.getInt("employeeSVID");
			if (tmpEmployeeSVId == tmpSuperVisor1Id) {
				map.put("supervisor1Id", tmpSuperVisor1Id);
				map.put("supervisor1FN", rs2.getString("SVFName") + " " + rs2.getString("SVLName"));
			}
			if (tmpEmployeeSVId == tmpSuperVisor2Id) {
				map.put("supervisor2Id", tmpSuperVisor2Id);
				map.put("supervisor2FN", rs2.getString("SVFName") + " " + rs2.getString("SVLName"));
			}
			if (tmpEmployeeSVId == tmpSuperVisor3Id) {
				map.put("supervisor3Id", tmpSuperVisor3Id);
				map.put("supervisor3FN", rs2.getString("SVFName") + " " + rs2.getString("SVLName"));
			}

		}

	    dynSql = null;
	    ps2.close();
	    rs2.close();	
	    return map;
	}
	


	/**
	 * 
	 * @param emp
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public int add(Employee emp) throws SQLException, Exception {
		
		String sql = "INSERT INTO employee (empNo, lastname, firstname, middlename, dateOfBirth, gender, civilStatus, nationality, street, cityId, " +
						"email, mobileNo, telNo, birthPlace, provinceId, zipCode, jobTitleId, departmentId, divisionId, employeeTypeId, empStatus, employmentDate, endOfContract, " +
						"sss, gsis, hdmf, tin, phic, taxstatus, picLoc, superVisor1Id, superVisor2Id, superVisor3Id, username, password, createdBy, creationDate) "+
						"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		 
		PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS); 
		
		ps.setString(1, emp.getEmpNo());
		ps.setString(2, emp.getLastname());
		ps.setString(3, emp.getFirstname());
		ps.setString(4, emp.getMiddlename());
		ps.setString(5, emp.getDateOfBirth());
		ps.setString(6, emp.getGender());
		ps.setString(7, emp.getCivilStatus());
		ps.setString(8, emp.getNationality());
		ps.setString(9, emp.getStreet());
		ps.setInt(10,emp.getCityId());
		ps.setString(11, emp.getEmail());
		ps.setString(12, emp.getMobileNo());
		ps.setString(13, emp.getTelNo());
		ps.setString(14, emp.getBirthPlace());
		ps.setInt(15, emp.getProvinceId());
		ps.setString(16, emp.getZipCode());
		ps.setInt(17, emp.getJobTitleId());
		ps.setInt(18, emp.getDepartmentId());
		ps.setInt(19, emp.getDivisionId());
		ps.setInt(20, emp.getEmployeeTypeId());
		ps.setString(21, emp.getEmpStatus());
		ps.setString(22,emp.getEmploymentDate());
		ps.setString(23, emp.getEndOfContract());
		ps.setString(24, emp.getSss());
		ps.setString(25, emp.getGsis());
		ps.setString(26, emp.getHdmf());
		ps.setString(27, emp.getTin());
		ps.setString(28, emp.getPhic());
		ps.setString(29, emp.getTaxstatus());
		ps.setString(30, emp.getPicLoc());
		ps.setInt(31, emp.getSuperVisor1Id());
		ps.setInt(32, emp.getSuperVisor2Id());
		ps.setInt(33, emp.getSuperVisor3Id());
		ps.setString(34, emp.getUsername());		
		ps.setString(35, EmployeeUtil.encodePassword(emp.getPassword()));
		ps.setString(36, emp.getCreatedBy());
		ps.setDate(37, emp.getCreationDate());
	 
		int count = ps.executeUpdate();

		 ResultSet keyResultSet = ps.getGeneratedKeys(); 
		 int generatedAutoIncrementId = 0; 
		 	if (keyResultSet.next()) {
		 		generatedAutoIncrementId = (int) keyResultSet.getInt(1);
		 		emp.setEmpId(generatedAutoIncrementId);
		 		conn.commit(); 
		 	}
		 
		 ps.close();
		 keyResultSet.close();
	 
		 
		 return generatedAutoIncrementId;

	}
	
	public boolean updatePassword(String empId, String password) throws SQLException, Exception {		
		StringBuffer sql = new StringBuffer();
		
		password = EmployeeUtil.encodePassword(password);
		
		sql.append("UPDATE employee set password = '");
		sql.append(password);
		sql.append("' where empId = '");
		sql.append(empId);
		sql.append("'");		
		
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		
		int count = ps.executeUpdate();
		boolean status = false;
		conn.commit();
		ps.close();
		 if (count == 1) {
			 status = true;
		 }		 
		 
		 return status;
	}
	
	public boolean verifyOldPassword(String empId, String password) throws SQLException, Exception {
		StringBuffer sql = new StringBuffer();
		
		password = EmployeeUtil.encodePassword(password);
		
		
		sql.append("SELECT * FROM employee where empId = ");
		sql.append(empId);
		sql.append(" and password = '");
		sql.append(password);
		sql.append("'");
		
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		

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
	


	/**
	 * 
	 * @param emp
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public int update(Employee emp) throws SQLException, Exception {		
		String sql = "UPDATE employee set empNo=?, lastname=?, firstname=?, middlename=?, dateOfBirth=?, gender=?, civilStatus=?, nationality=?, street=?, cityId=?, " +
						"email=?, mobileNo=?, telNo=?, birthPlace=?, provinceId=?, zipCode=?, jobTitleId=?, departmentId=?, divisionId=?, employeeTypeId=?, empStatus=?, employmentDate=?, endOfContract=?, " +
						"sss=?, gsis=?, hdmf=?, tin=?, phic=?, taxstatus=?, picLoc=?, superVisor1Id=?, superVisor2Id=?, superVisor3Id=?, username=?, password=?, createdBy=?, creationDate=? where empId=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		 
		ps.setString(1, emp.getEmpNo());
		ps.setString(2, emp.getLastname());
		ps.setString(3, emp.getFirstname());
		ps.setString(4, emp.getMiddlename());
		ps.setString(5, emp.getDateOfBirth());
		ps.setString(6, emp.getGender());
		ps.setString(7, emp.getCivilStatus());
		ps.setString(8, emp.getNationality());
		ps.setString(9, emp.getStreet());
		ps.setInt(10,emp.getCityId());
		ps.setString(11, emp.getEmail());
		ps.setString(12, emp.getMobileNo());
		ps.setString(13, emp.getTelNo());
		ps.setString(14, emp.getBirthPlace());
		ps.setInt(15, emp.getProvinceId());
		ps.setString(16, emp.getZipCode());
		ps.setInt(17, emp.getJobTitleId());
		ps.setInt(18, emp.getDepartmentId());
		ps.setInt(19, emp.getDivisionId());
		ps.setInt(20, emp.getEmployeeTypeId());
		ps.setString(21, emp.getEmpStatus());
		ps.setString(22,emp.getEmploymentDate());
		ps.setString(23, emp.getEndOfContract());
		ps.setString(24, emp.getSss());
		ps.setString(25, emp.getGsis());
		ps.setString(26, emp.getHdmf());
		ps.setString(27, emp.getTin());
		ps.setString(28, emp.getPhic());
		ps.setString(29, emp.getTaxstatus());
		ps.setString(30, emp.getPicLoc());
		ps.setInt(31, emp.getSuperVisor1Id());
		ps.setInt(32, emp.getSuperVisor2Id());
		ps.setInt(33, emp.getSuperVisor3Id());
		ps.setString(34, emp.getUsername());
		ps.setString(35, EmployeeUtil.encodePassword(emp.getPassword()));
		ps.setString(36, emp.getCreatedBy());
		ps.setDate(37, emp.getCreationDate());
		ps.setInt(38, emp.getEmpId());
		
		int count = ps.executeUpdate();

		conn.commit();
		ps.close();
		
		return count;

	}
	
	
	/**
	 * 
	 * @param emp
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public int updateExcludingPassword(Employee emp) throws SQLException, Exception {		
		String sql = "UPDATE employee set empNo=?, lastname=?, firstname=?, middlename=?, dateOfBirth=?, gender=?, civilStatus=?, nationality=?, street=?, cityId=?, " +
						"email=?, mobileNo=?, telNo=?, birthPlace=?, provinceId=?, zipCode=?, jobTitleId=?, departmentId=?, divisionId=?, employeeTypeId=?, empStatus=?, employmentDate=?, endOfContract=?, " +
						"sss=?, gsis=?, hdmf=?, tin=?, phic=?, taxstatus=?, picLoc=?, superVisor1Id=?, superVisor2Id=?, superVisor3Id=?, username=?, createdBy=?, creationDate=? where empId=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		 
		ps.setString(1, emp.getEmpNo());
		ps.setString(2, emp.getLastname());
		ps.setString(3, emp.getFirstname());
		ps.setString(4, emp.getMiddlename());
		ps.setString(5, emp.getDateOfBirth());
		ps.setString(6, emp.getGender());
		ps.setString(7, emp.getCivilStatus());
		ps.setString(8, emp.getNationality());
		ps.setString(9, emp.getStreet());
		ps.setInt(10,emp.getCityId());
		ps.setString(11, emp.getEmail());
		ps.setString(12, emp.getMobileNo());
		ps.setString(13, emp.getTelNo());
		ps.setString(14, emp.getBirthPlace());
		ps.setInt(15, emp.getProvinceId());
		ps.setString(16, emp.getZipCode());
		ps.setInt(17, emp.getJobTitleId());
		ps.setInt(18, emp.getDepartmentId());
		ps.setInt(19, emp.getDivisionId());
		ps.setInt(20, emp.getEmployeeTypeId());
		ps.setString(21, emp.getEmpStatus());
		ps.setString(22,emp.getEmploymentDate());
		ps.setString(23, emp.getEndOfContract());
		ps.setString(24, emp.getSss());
		ps.setString(25, emp.getGsis());
		ps.setString(26, emp.getHdmf());
		ps.setString(27, emp.getTin());
		ps.setString(28, emp.getPhic());
		ps.setString(29, emp.getTaxstatus());
		ps.setString(30, emp.getPicLoc());
		ps.setInt(31, emp.getSuperVisor1Id());
		ps.setInt(32, emp.getSuperVisor2Id());
		ps.setInt(33, emp.getSuperVisor3Id());
		ps.setString(34, emp.getUsername());
		ps.setString(35, emp.getCreatedBy());
		ps.setDate(36, emp.getCreationDate());
		ps.setInt(37, emp.getEmpId());
		
		int count = ps.executeUpdate();

		conn.commit();
		ps.close();
		
		return count;

	}
	

	public void closeConnection() throws SQLException, Exception {
		conn.close();
	}

}
