package dai.hris.service.filemanager.employeefamilymember;

import java.sql.SQLException;
import java.util.ArrayList;

import dai.hris.dao.filemanager.EmployeeFamilyMemberDAO;
import dai.hris.model.EmployeeFamilyMember;

/**
 * 
 * @author danielpadilla
 *
 */
public class EmployeeFamilyMemberService implements IEmployeeFamilyMemberService {

	public ArrayList<EmployeeFamilyMember> getEmployeeFamilyMemberListByEmpId(
			int empId) throws SQLException, Exception {
		EmployeeFamilyMemberDAO eFMDAO = new EmployeeFamilyMemberDAO();
		ArrayList<EmployeeFamilyMember> eFMList = eFMDAO.getEmployeeFamilyMemberListByEmpId(empId);
		eFMDAO.closeConnection();
		return eFMList;
	}

	public EmployeeFamilyMember getEmployeeFamilyMemberByEmpFamilyMemberId(
			int empFamilyMemberId) throws SQLException, Exception {
		EmployeeFamilyMemberDAO eFMDAO = new EmployeeFamilyMemberDAO();
		EmployeeFamilyMember eFM = eFMDAO.getEmployeeFamilyMemberByEmpFamilyMemberId(empFamilyMemberId);
		eFMDAO.closeConnection();
		return eFM;
	}

	public int add(EmployeeFamilyMember empFamilyMember) throws SQLException,
			Exception {
		int status;
		EmployeeFamilyMemberDAO eFMDAO = new EmployeeFamilyMemberDAO();
		status = eFMDAO.add(empFamilyMember);
		eFMDAO.closeConnection();
		return status;
	}

	public int update(EmployeeFamilyMember empFamilyMember)
			throws SQLException, Exception {
		int status;
		EmployeeFamilyMemberDAO eFMDAO = new EmployeeFamilyMemberDAO();
		status = eFMDAO.update(empFamilyMember);
		eFMDAO.closeConnection();
		return status;
	}

}
