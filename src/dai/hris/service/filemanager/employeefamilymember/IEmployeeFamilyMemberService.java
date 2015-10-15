package dai.hris.service.filemanager.employeefamilymember;

import java.sql.SQLException;
import java.util.ArrayList;

import dai.hris.model.EmployeeFamilyMember;

/**
 * 
 * @author danielpadilla
 *
 */
public interface IEmployeeFamilyMemberService {
	public ArrayList<EmployeeFamilyMember> getEmployeeFamilyMemberListByEmpId(int empId) throws SQLException, Exception;
	public EmployeeFamilyMember getEmployeeFamilyMemberByEmpFamilyMemberId(int empFamilyMemberId) throws SQLException, Exception;
	public int add(EmployeeFamilyMember empFamilyMember) throws SQLException, Exception;
	public int update(EmployeeFamilyMember empFamilyMember) throws SQLException, Exception;	
}
