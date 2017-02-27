package com.ailk.eaap.op2.dao;


import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.List;

import org.apache.log4j.Logger;

import com.ailk.eaap.op2.bo.FileShare; 
import com.linkage.rainbow.dao.SqlMapDAO;
 
public class FileShareDaoImpl   implements FileShareDao {
 	 
	private static final Logger log = Logger.getLogger(FileShareDaoImpl.class);
	private SqlMapDAO sqlMapDao;
	public Integer addFileShare(FileShare fileShareBean){
		 File fileContent = fileShareBean.getSFileContent() ;
		 fileShareBean.setSFileContent(null) ;
		 Integer fileShareId = (Integer)sqlMapDao.insert("fileShare.insertFileShare", fileShareBean) ;
		 return  inImageContent(fileShareId,fileContent) ;
	}
	
	public List<Map>  selectFileShare(FileShare fileShareBean){
		return (List<Map>)sqlMapDao.queryForList("fileShare.selectFileShare", fileShareBean) ;
	}
	
	
	public Integer inImageContent(Integer fileShareId,File fileContent){
		PreparedStatement pstmt = null;
		Connection con = null;
        try {
        	con = sqlMapDao.getSmcTemplate().getDataSource().getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement("update file_share set s_file_content = ? where s_file_id=?");
			FileInputStream stream = new FileInputStream(fileContent);
			pstmt.setInt(2, fileShareId);
			pstmt.setBinaryStream(1, stream, stream.available());
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			log.error(e.getStackTrace());
		}finally{
			try {
				if(pstmt!=null){
					pstmt.close();
				}
				if(con!=null){
						con.close();
				}
			} catch (SQLException e) {
				log.error("SQLException!", e);
			}
		}
	
		 return fileShareId ;
	}
	 
	
    public SqlMapDAO getSqlMapDao() {
		return sqlMapDao;
	}

	public void setSqlMapDao(SqlMapDAO sqlMapDao) {
		this.sqlMapDao = sqlMapDao;
	}	 

}
