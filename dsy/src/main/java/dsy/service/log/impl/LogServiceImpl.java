package dsy.service.log.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import common.DataTablesResult;
import common.SimpleDataTables;
import dsy.dao.BaseJdbcDao;
import dsy.module.SecLog;
import dsy.service.log.LogService;
import net.sf.json.JSONObject;
import util.DateUtil;
import util.PagesBean;
import util.StringUtil;

@Service
public class LogServiceImpl implements LogService {

	@Autowired
	private BaseJdbcDao baseJdbcDao;
	
	@Override
	public boolean saveToDB(SecLog log) {
		String sql = "insert into sec_log (descripe,creater,create_time) "
				+ "values ('"+log.getDescripe()+"','"+log.getCreater()+"','"+DateUtil.Now()+"')";
		return this.baseJdbcDao.executesql(sql);
	}

	@Override
	public JSONObject getLogList(HttpServletRequest request) throws Exception {

		String countSql = "select count(*) from sec_log where 1=1";
		
		String fullSql = "select id,descripe,creater,"
				+ " create_time as createTime "
				+ " from sec_log where 1=1";
		

		StringBuffer appSql = new StringBuffer();
		StringBuffer addSql = new StringBuffer();
		SimpleDataTables dataTables = SimpleDataTables.getFromRequest(request); // 从前端获取的数据
		
		String startTime = request.getParameter("extra_search[logmin]");
		String expireTime = request.getParameter("extra_search[logmax]");
		// for search
		if(StringUtil.isNotBlank(startTime) && StringUtil.isNotBlank(startTime)){
			appSql.append(" and create_time >=")
			.append(startTime)
			.append(" and create_time <=")
			.append(expireTime);
		}
		
		// for sort and order
		if (StringUtil.isNotBlank(dataTables.getSort())) {
			addSql.append(" order by ").append(dataTables.getSort())
							.append(" ").append(dataTables.getOrder());
				}
		fullSql += appSql.toString() + addSql.toString();
		countSql += appSql.toString();
		
		PagesBean pageBean = this.baseJdbcDao.JdbcSimplePage(countSql,
					fullSql, dataTables.getStart(), dataTables.getLength());
		ResultSet rs = pageBean.getResultSet();
				
		List<SecLog> list = new ArrayList<SecLog>();
		while(rs.next()){
			SecLog log = new SecLog();
			
			log.setId(rs.getInt("id"));
			log.setDescripe(rs.getString("descripe"));
			log.setCreater(rs.getString("creater"));
			log.setCreateTime(rs.getString("createTime"));
			
			list.add(log);
		}
		
		int draw = dataTables.getDraw();
		//结果数
		String recordsTotal = String.valueOf(pageBean.getItemCount());
		//过滤后数目，暂时和结果数保持一致
		String recordsFiltered = recordsTotal;
		return DataTablesResult.DataResult(draw, recordsTotal, recordsFiltered,
				list);
	}

	@Override
	public Boolean deleteLog(HttpServletRequest request) {
		String logidArray=request.getParameter("idArray");
		String sql="delete from sec_log where id in ("+logidArray+")";
		return this.baseJdbcDao.executesql(sql);
	}

}
