package dai.hris.service.payroll;

import java.sql.SQLException;
import java.util.List;

import dai.hris.model.LongevityPay;

public interface ILongevityPayService {
	public void add(LongevityPay lp) throws SQLException, Exception;
    public void update(LongevityPay lp) throws SQLException, Exception;
    public void delete(LongevityPay lp) throws SQLException, Exception;
    public int[] batchUpdate(List<LongevityPay> lpList) throws SQLException, Exception;
    public int[] batchInsert(List<LongevityPay> lpList) throws SQLException, Exception;
    public List<LongevityPay> getAllByEmployeeId(int empId) throws SQLException, Exception;
    public List<LongevityPay> getAllByJobTitleId(int jtId) throws SQLException, Exception;
}
