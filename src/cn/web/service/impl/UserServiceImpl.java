package cn.web.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import com.web.util.ExcelUtil;
import cn.web.dao.UserDao;
import cn.web.entity.Role;
import cn.web.entity.User;
import cn.web.entity.UserRole;
import cn.web.entity.UserRoleId;
import cn.web.exception.ActionException;
import cn.web.exception.ServiceException;
import cn.web.service.UserService;

/**
 * 
 * @author 杨建
 * 2016年12月14日下午2:19:11
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService{

	private UserDao userDao;
	
	@Resource
	public void setUserDao(UserDao userDao) {
		super.setBaseDao(userDao);
		this.userDao = userDao;
	}

	@Override
	public void delete(Serializable id) {
		userDao.delete(id);
		//删除用户对应的权限
		userDao.deleteUserRoleByUserId(id);
	}

	@Override
	public void exportExcel(List<User> userList, ServletOutputStream outPutStream) {
		ExcelUtil.exportUserExcel(userList, outPutStream);
	}

	@Override
	public void importExcel(File userExcel, String userExcelFileName) throws ServiceException {
		    
		try {
			FileInputStream fileinputStream = new FileInputStream(userExcel);
			//判断Excel版本
			boolean is03Excel = userExcelFileName.matches("^.+\\.(?i)(xls)$");
			//创建工资簿
			Workbook workbook = is03Excel ? new HSSFWorkbook(fileinputStream) : new XSSFWorkbook(fileinputStream);
			//读取工作表
			Sheet sheet = workbook.getSheetAt(0);
			if(sheet.getPhysicalNumberOfRows() > 2){
				User user = null;
				for(int k=2;k < sheet.getPhysicalNumberOfRows();k++){
					//读取单元格
					Row row = sheet.getRow(k);
					user = new User();
					//用户名
					Cell cell0 = row.getCell(0);
					user.setName(cell0.getStringCellValue());
					//帐号
					Cell cell1 = row.getCell(1);
					user.setAccount(cell1.getStringCellValue());
					//所属部门
					Cell cell2 = row.getCell(2);
					user.setDept(cell2.getStringCellValue());
					//性别
					Cell cell3 = row.getCell(3);
					user.setGender(cell3.getStringCellValue().equals("男"));
					//手机号
					String mobile = "";
					Cell cell4 = row.getCell(4);
					try {
						mobile = cell4.getStringCellValue();
					} catch (Exception e) {
						double dMobile = cell4.getNumericCellValue();
						mobile = BigDecimal.valueOf(dMobile).toString();
					}
					user.setMobile(mobile);
					
					//电子邮箱
					Cell cell5 = row.getCell(5);
					user.setEmail(cell5.getStringCellValue());
					//生日
					Cell cell6 = row.getCell(6);
					if(cell6.getDateCellValue() != null){
						user.setBirthday(cell6.getDateCellValue());
					}
					//默认用户密码为 123456
					user.setPassword("123456");
					//默认用户状态为 有效
					user.setState(User.USER_STATE_VALID);
					//5、保存用户
					save(user);
				}
			}
			workbook.close();
			//关闭流
			fileinputStream.close();
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		
	}
	/**
	 * 根据ID验证账号
	 */
	@Override
	public List<User> findUserByAccountId(String id, String account) {
		
		return userDao.findUserByAccountId(id,account);
	}

	@Override
	public void saveUserAndRole(User user, String... roleIds) {
		//保存用户
		save(user);
		//保存用户的角色
		if(roleIds != null){
			for(String roleId:roleIds){
				userDao.saveUserRole(new UserRole(new UserRoleId(new Role(roleId),user.getId())));
			}
		}
	}

	@Override
	public void updateUserAndRole(User user, String... roleIds) {
		//根据用户删除改用的所有角色
		userDao.deleteUserRoleByUserId(user.getId());
		//更新用户
		update(user);
		//保存用户对应的角色
		if(roleIds != null){
			for(String roleId:roleIds){
				userDao.saveUserRole(new UserRole(new UserRoleId(new Role(roleId),user.getId())));
			}
		}
	}
	
	@Override
	public List<UserRole> getUserRolesByUserId(String id) {
		return userDao.getUserRolesByUserId(id);
	}

	@Override
	public List<User> findUserByAccountAndPass(String account, String password) {
		return userDao.findUserByAccountAndPass(account,password);
	}
	
	
	
}
