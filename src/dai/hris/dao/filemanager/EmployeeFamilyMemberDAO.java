package dai.hris.dao.filemanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dai.hris.dao.DBConstants;
import dai.hris.model.EmployeeFamilyMember;

/**
 * 
 * @author danielpadilla
 *
 */
public class EmployeeFamilyMemberDAO {
	Connection conn = null;
	public EmployeeFamilyMemberDAO() {
		  
    	try {

    		/* MS SQL CODE */    		
    		Class.forName(DBConstants.DB_DRIVER_CLASS_NAME);
        	conn = DriverManager.getConnection(DBConstants.MS_SQL_DB_URL);    		
    		conn.setAutoCommit(false);

		} catch (SQLException sqle) {
			System.out.println("EmployeeFamilyMemberDAO :" + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("EmployeeFamilyMemberDAO :" + e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param empId
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	  public ArrayList<EmployeeFamilyMember> getEmployeeFamilyMemberListByEmpId(int empId) throws SQLException, Exception {

			String sql = "select empFamilyMemberId, empId, name, gender, relation, birthDate, age, remarks, contactNum from empFamilyMember where empId = ?";		
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setInt(1,empId);

		    ResultSet rs = ps.executeQuery();
		    ArrayList<EmployeeFamilyMember> list = new ArrayList<EmployeeFamilyMember>();
		    
		    while (rs.next()) {
		    	EmployeeFamilyMember empFamilyMember = new EmployeeFamilyMember();
		    	empFamilyMember.setAge(rs.getInt("age"));
		    	empFamilyMember.setBirthdate(rs.getString("birthDate"));
		    	empFamilyMember.setContactNum(rs.getString("contactNum"));
		    	empFamilyMember.setEmpFamilyMemberId(rs.getInt("empFamilyMemberId"));
		    	empFamilyMember.setEmpId(rs.getInt("empId"));
		    	empFamilyMember.setGender(rs.getString("gender"));
		    	empFamilyMember.setName(rs.getString("name"));
		    	empFamilyMember.setRelation(rs.getString("relation"));
		    	empFamilyMember.setRemarks(rs.getString("remarks"));
		    	list.add(empFamilyMember);

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
	  public EmployeeFamilyMember getEmployeeFamilyMemberByEmpFamilyMemberId(int empFamilyMemberId) throws SQLException, Exception {
			String sql = "select empFamilyMemberId, empId, name, gender, relation, birthDate, age, remarks, contactNum from empFamilyMember where empFamilyMemberId = ?";
			EmployeeFamilyMember empFamilyMember = null;
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setInt(1,empFamilyMemberId);

		    ResultSet rs = ps.executeQuery();
		    
		    while (rs.next()) {
		    	empFamilyMember = new EmployeeFamilyMember();
		    	empFamilyMember.setAge(rs.getInt("age"));
		    	empFamilyMember.setBirthdate(rs.getString("birthDate"));
		    	empFamilyMember.setContactNum(rs.getString("contactNum"));
		    	empFamilyMember.setEmpFamilyMemberId(rs.getInt("empFamilyMemberId"));
		    	empFamilyMember.setEmpId(rs.getInt("empId"));
		    	empFamilyMember.setGender(rs.getString("gender"));
		    	empFamilyMember.setName(rs.getString("name"));
		    	empFamilyMember.setRelation(rs.getString("relation"));
		    	empFamilyMember.setRemarks(rs.getString("remarks"));
		    }
		    
		    ps.close();
		    rs.close();      
		    return empFamilyMember;     

		}	  
	  
	  /**
	   * 
	   * @param city
	   * @throws SQLException
	   * @throws Exception
	   */
	    public int add(EmployeeFamilyMember empFamilyMember) throws SQLException, Exception {
	  		String sql = "INSERT INTO empFamilyMember (empId, name, gender, relation, birthDate, age, remarks, contactNum) VALUES (?,?,?,?,?,?,?,?)";
	  		PreparedStatement ps  = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
	        ps.setInt(1, empFamilyMember.getEmpId());
	        ps.setString(2, empFamilyMember.getName());
	        ps.setString(3, empFamilyMember.getGender());
	        ps.setString(4, empFamilyMember.getRelation());
	        ps.setString(5, empFamilyMember.getBirthdate());
	        ps.setInt(6, empFamilyMember.getAge());
	        ps.setString(7, empFamilyMember.getRemarks());
	        ps.setString(8, empFamilyMember.getContactNum());
	        
	        int count = ps.executeUpdate();
	        int status = 0;
	        ResultSet keyResultSet = ps.getGeneratedKeys();
	         int generatedAutoIncrementId = 0;
	         if (keyResultSet.next()) {
	          	generatedAutoIncrementId = (int) keyResultSet.getInt(1);
	          	empFamilyMember.setEmpFamilyMemberId(generatedAutoIncrementId);
	          	conn.commit();
	          }

	         ps.close();
	         keyResultSet.close();
	 		 if (count == 1) {
				 status = generatedAutoIncrementId;
			 }	
	          return status;

	  	}
	    
	    /**
	     * 
	     * @param empFamilyMember
	     * @return
	     * @throws SQLException
	     * @throws Exception
	     */
	    public int update(EmployeeFamilyMember empFamilyMember) throws SQLException, Exception {			
	 		String sql = "UPDATE empFamilyMember set empId=?, name=?, gender=?, relation=?, birthDate=?, age=?, remarks=?, contactNum=? where empFamilyMemberId = ?";
	 		PreparedStatement ps  = conn.prepareStatement(sql);
	        ps.setInt(1, empFamilyMember.getEmpId());
	        ps.setString(2, empFamilyMember.getName());
	        ps.setString(3, empFamilyMember.getGender());
	        ps.setString(4, empFamilyMember.getRelation());
	        ps.setString(5, empFamilyMember.getBirthdate());
	        ps.setInt(6, empFamilyMember.getAge());
	        ps.setString(7, empFamilyMember.getRemarks());
	        ps.setString(8, empFamilyMember.getContactNum());
	        ps.setInt(9, empFamilyMember.getEmpFamilyMemberId());
	         
	        int count = ps.executeUpdate();
	        conn.commit();
	        ps.close();
	        return count;

	 	}
	    
	    public void closeConnection() throws SQLException, Exception {
			conn.close();
		}

}
