package com.asiainfo.integration.o2p.basedao.dao.impl;

import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;
import com.ailk.eaap.op2.bo.NodeValReq;
import com.ailk.eaap.op2.bo.ParamVarMap;
import com.ailk.eaap.op2.bo.TransformerRule;

/**
 * The class <code>TransformerRuleDaoImplTest</code> contains tests for the class <code>{@link TransformerRuleDaoImpl}</code>.
 *
 * @generatedBy CodePro at 15-7-10 下午4:32
 * @author thinpad
 * @version $Revision: 1.0 $
 */
public class TransformerRuleDaoImplTest {
	/**
	 * Run the TransformerRuleDaoImpl() constructor test.
	 *
	 * @generatedBy CodePro at 15-7-10 下午4:32
	 */
	@Test
	public void testTransformerRuleDaoImpl_1()
		throws Exception {
		TransformerRuleDaoImpl result = new TransformerRuleDaoImpl();
		assertNotNull(result);
		// add additional test code here
	}

	/**
	 * Run the List<TransformerRule> getAllUsable() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 15-7-10 下午4:32
	 */
	@Test
	public void testGetAllUsable_1()
		throws Exception {
		TransformerRuleDaoImpl fixture = new TransformerRuleDaoImpl();

		List<TransformerRule> result = fixture.getAllUsable();

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.IllegalArgumentException: No SqlMapClient specified
		//       at org.springframework.util.Assert.notNull(Assert.java:112)
		//       at org.springframework.orm.ibatis.SqlMapClientTemplate.execute(SqlMapClientTemplate.java:166)
		//       at org.springframework.orm.ibatis.SqlMapClientTemplate.executeWithListResult(SqlMapClientTemplate.java:249)
		//       at org.springframework.orm.ibatis.SqlMapClientTemplate.queryForList(SqlMapClientTemplate.java:296)
		//       at org.springframework.orm.ibatis.SqlMapClientTemplate.queryForList(SqlMapClientTemplate.java:290)
		//       at com.asiainfo.integration.o2p.basedao.dao.impl.TransformerRuleDaoImpl.getAllUsable(TransformerRuleDaoImpl.java:29)
		assertNotNull(result);
	}

	/**
	 * Run the List<TransformerRule> getAllUsable() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 15-7-10 下午4:32
	 */
	@Test
	public void testGetAllUsable_2()
		throws Exception {
		TransformerRuleDaoImpl fixture = new TransformerRuleDaoImpl();

		List<TransformerRule> result = fixture.getAllUsable();

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.IllegalArgumentException: No SqlMapClient specified
		//       at org.springframework.util.Assert.notNull(Assert.java:112)
		//       at org.springframework.orm.ibatis.SqlMapClientTemplate.execute(SqlMapClientTemplate.java:166)
		//       at org.springframework.orm.ibatis.SqlMapClientTemplate.executeWithListResult(SqlMapClientTemplate.java:249)
		//       at org.springframework.orm.ibatis.SqlMapClientTemplate.queryForList(SqlMapClientTemplate.java:296)
		//       at org.springframework.orm.ibatis.SqlMapClientTemplate.queryForList(SqlMapClientTemplate.java:290)
		//       at com.asiainfo.integration.o2p.basedao.dao.impl.TransformerRuleDaoImpl.getAllUsable(TransformerRuleDaoImpl.java:29)
		assertNotNull(result);
	}

	/**
	 * Run the List<ParamVarMap> getParamVarMapByType() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 15-7-10 下午4:32
	 */
	@Test
	public void testGetParamVarMapByType_1()
		throws Exception {
		TransformerRuleDaoImpl fixture = new TransformerRuleDaoImpl();

		List<ParamVarMap> result = fixture.getParamVarMapByType();

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.IllegalArgumentException: No SqlMapClient specified
		//       at org.springframework.util.Assert.notNull(Assert.java:112)
		//       at org.springframework.orm.ibatis.SqlMapClientTemplate.execute(SqlMapClientTemplate.java:166)
		//       at org.springframework.orm.ibatis.SqlMapClientTemplate.executeWithListResult(SqlMapClientTemplate.java:249)
		//       at org.springframework.orm.ibatis.SqlMapClientTemplate.queryForList(SqlMapClientTemplate.java:296)
		//       at org.springframework.orm.ibatis.SqlMapClientTemplate.queryForList(SqlMapClientTemplate.java:290)
		//       at com.asiainfo.integration.o2p.basedao.dao.impl.TransformerRuleDaoImpl.getParamVarMapByType(TransformerRuleDaoImpl.java:35)
		assertNotNull(result);
	}

	/**
	 * Run the List<ParamVarMap> getParamVarMapByType() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 15-7-10 下午4:32
	 */
	@Test
	public void testGetParamVarMapByType_2()
		throws Exception {
		TransformerRuleDaoImpl fixture = new TransformerRuleDaoImpl();

		List<ParamVarMap> result = fixture.getParamVarMapByType();

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.IllegalArgumentException: No SqlMapClient specified
		//       at org.springframework.util.Assert.notNull(Assert.java:112)
		//       at org.springframework.orm.ibatis.SqlMapClientTemplate.execute(SqlMapClientTemplate.java:166)
		//       at org.springframework.orm.ibatis.SqlMapClientTemplate.executeWithListResult(SqlMapClientTemplate.java:249)
		//       at org.springframework.orm.ibatis.SqlMapClientTemplate.queryForList(SqlMapClientTemplate.java:296)
		//       at org.springframework.orm.ibatis.SqlMapClientTemplate.queryForList(SqlMapClientTemplate.java:290)
		//       at com.asiainfo.integration.o2p.basedao.dao.impl.TransformerRuleDaoImpl.getParamVarMapByType(TransformerRuleDaoImpl.java:35)
		assertNotNull(result);
	}

	/**
	 * Run the TransformerRule getTransformerRuleById(Integer) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 15-7-10 下午4:32
	 */
	@Test
	public void testGetTransformerRuleById_1()
		throws Exception {
		TransformerRuleDaoImpl fixture = new TransformerRuleDaoImpl();
		Integer id = new Integer(1);

		TransformerRule result = fixture.getTransformerRuleById(id);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.IllegalArgumentException: No SqlMapClient specified
		//       at org.springframework.util.Assert.notNull(Assert.java:112)
		//       at org.springframework.orm.ibatis.SqlMapClientTemplate.execute(SqlMapClientTemplate.java:166)
		//       at org.springframework.orm.ibatis.SqlMapClientTemplate.queryForObject(SqlMapClientTemplate.java:271)
		//       at com.asiainfo.integration.o2p.basedao.dao.impl.TransformerRuleDaoImpl.getTransformerRuleById(TransformerRuleDaoImpl.java:23)
		assertNotNull(result);
	}

	/**
	 * Run the TransformerRule getTransformerRuleById(Integer) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 15-7-10 下午4:32
	 */
	@Test
	public void testGetTransformerRuleById_2()
		throws Exception {
		TransformerRuleDaoImpl fixture = new TransformerRuleDaoImpl();
		Integer id = new Integer(1);

		TransformerRule result = fixture.getTransformerRuleById(id);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.IllegalArgumentException: No SqlMapClient specified
		//       at org.springframework.util.Assert.notNull(Assert.java:112)
		//       at org.springframework.orm.ibatis.SqlMapClientTemplate.execute(SqlMapClientTemplate.java:166)
		//       at org.springframework.orm.ibatis.SqlMapClientTemplate.queryForObject(SqlMapClientTemplate.java:271)
		//       at com.asiainfo.integration.o2p.basedao.dao.impl.TransformerRuleDaoImpl.getTransformerRuleById(TransformerRuleDaoImpl.java:23)
		assertNotNull(result);
	}

	/**
	 * Run the NodeValReq queryNodeValReqByNodeId(Integer,Integer) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 15-7-10 下午4:32
	 */
	@Test
	public void testQueryNodeValReqByNodeId_1()
		throws Exception {
		TransformerRuleDaoImpl fixture = new TransformerRuleDaoImpl();
		Integer nodeDescId = new Integer(1);
		Integer transformerRuleId = new Integer(1);

		NodeValReq result = fixture.queryNodeValReqByNodeId(nodeDescId, transformerRuleId);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.IllegalArgumentException: No SqlMapClient specified
		//       at org.springframework.util.Assert.notNull(Assert.java:112)
		//       at org.springframework.orm.ibatis.SqlMapClientTemplate.execute(SqlMapClientTemplate.java:166)
		//       at org.springframework.orm.ibatis.SqlMapClientTemplate.queryForObject(SqlMapClientTemplate.java:271)
		//       at com.asiainfo.integration.o2p.basedao.dao.impl.TransformerRuleDaoImpl.queryNodeValReqByNodeId(TransformerRuleDaoImpl.java:43)
		assertNotNull(result);
	}

	/**
	 * Run the NodeValReq queryNodeValReqByNodeId(Integer,Integer) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 15-7-10 下午4:32
	 */
	@Test
	public void testQueryNodeValReqByNodeId_2()
		throws Exception {
		TransformerRuleDaoImpl fixture = new TransformerRuleDaoImpl();
		Integer nodeDescId = new Integer(1);
		Integer transformerRuleId = new Integer(1);

		NodeValReq result = fixture.queryNodeValReqByNodeId(nodeDescId, transformerRuleId);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.IllegalArgumentException: No SqlMapClient specified
		//       at org.springframework.util.Assert.notNull(Assert.java:112)
		//       at org.springframework.orm.ibatis.SqlMapClientTemplate.execute(SqlMapClientTemplate.java:166)
		//       at org.springframework.orm.ibatis.SqlMapClientTemplate.queryForObject(SqlMapClientTemplate.java:271)
		//       at com.asiainfo.integration.o2p.basedao.dao.impl.TransformerRuleDaoImpl.queryNodeValReqByNodeId(TransformerRuleDaoImpl.java:43)
		assertNotNull(result);
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 15-7-10 下午4:32
	 */
	@Before
	public void setUp()
		throws Exception {
		// add additional set up code here
	}

	/**
	 * Perform post-test clean-up.
	 *
	 * @throws Exception
	 *         if the clean-up fails for some reason
	 *
	 * @generatedBy CodePro at 15-7-10 下午4:32
	 */
	@After
	public void tearDown()
		throws Exception {
		// Add additional tear down code here
	}

	/**
	 * Launch the test.
	 *
	 * @param args the command line arguments
	 *
	 * @generatedBy CodePro at 15-7-10 下午4:32
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(TransformerRuleDaoImplTest.class);
	}
}