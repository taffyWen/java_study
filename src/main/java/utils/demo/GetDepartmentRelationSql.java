package utils.demo;

import java.util.List;
import java.util.Map;

import utils.ExcelUtil;
import utils.StringUtil;

public class GetDepartmentRelationSql {

	public static void main(String[] args) {
		
		String excelPath = "D:\\document\\ioFile\\department.xlsx";
		String sql = new GetDepartmentRelationSql().getDepartmentCode(excelPath);
		System.out.println(sql);
	}
	
	
	public String getDepartmentCode(String excelPath) {
		List<Map<String,Object>> list = ExcelUtil.getExcelFile(excelPath);
		StringBuilder sql = new StringBuilder();
		for (Map<String, Object> map : list) {
			int level =  Integer.parseInt((String) map.get("level")) ;
			String departmentCode = (String) map.get("departmentCode");
			String parentDepartmentCode = (String) map.get("parentDepartmentCode");
			
			if(1 != level) {
				sql.append("insert into table values (" + departmentCode +"," 
						+ departmentCode +"," + level + ");\n");
				sql.append("insert into table values (" + departmentCode +"," 
						+ parentDepartmentCode +"," + (level - 1));
				sql.append(");\n");
				sql.append(getParentCode(departmentCode, parentDepartmentCode, list));
			}
		}
		return sql.toString();
	}
	
	public String getParentCode (String departmentCode, String parentDepartmentCode, List<Map<String,Object>> list) {
		StringBuilder stringBuilder = new StringBuilder();
		for (Map<String, Object> map : list) {
			int level =  Integer.parseInt((String) map.get("level")) ;
			if(parentDepartmentCode.equals(map.get("departmentCode")) && StringUtil.isNotEmpty((String) map.get("parentDepartmentCode"))) {
				stringBuilder.append("insert into table values (" + departmentCode +"," 
						+ map.get("parentDepartmentCode") +"," + (level - 1) + ");\n");
				
				stringBuilder.append(getParentCode(departmentCode, (String) map.get("parentDepartmentCode"), list));
			}
		}
		return stringBuilder.toString();
	}
	
}
