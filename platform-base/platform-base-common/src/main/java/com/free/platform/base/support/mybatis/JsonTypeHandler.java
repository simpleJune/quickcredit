package com.free.platform.base.support.mybatis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * mybatis 类型映射转换器
 *
 */
public class JsonTypeHandler extends BaseTypeHandler<Object> {

	@Override
	public void setParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
		if (parameter == null) {
			ps.setString(i, null);
			return;
		}
		String json = JSON.toJSONString(parameter);
		ps.setString(i, json);
	}

	@Override
	public Object getResult(ResultSet rs, String columnName) throws SQLException {
		String json = rs.getString(columnName);
		return JSONObject.parseObject(json);
	}

	@Override
	public Object getResult(ResultSet rs, int columnIndex) throws SQLException {
		String json = rs.getString(columnIndex);
		return JSONObject.parseObject(json);
	}

	@Override
	public Object getResult(CallableStatement cs, int columnIndex) throws SQLException {
		String json = cs.getString(columnIndex);
		return JSONObject.parseObject(json);
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType)
			throws SQLException {
		this.setParameter(ps, i, parameter, jdbcType);
	}

	@Override
	public Object getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return this.getResult(rs, columnName);
	}

	@Override
	public Object getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return this.getResult(rs, columnIndex);
	}

	@Override
	public Object getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return this.getNullableResult(cs, columnIndex);
	}

}
