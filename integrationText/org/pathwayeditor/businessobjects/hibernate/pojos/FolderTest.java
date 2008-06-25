package org.pathwayeditor.businessobjects.hibernate.pojos;

import java.sql.Connection;
import java.sql.DriverManager;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DefaultDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.ext.hsqldb.HsqldbDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FolderTest extends DatabaseTestCase {
	private SessionFactory hibFactory; 
	
	 protected IDatabaseConnection getConnection() throws Exception {
			Class.forName("org.hsqldb.jdbcDriver");
			String url = "jdbc:hsqldb:mem:testDb";
			Connection conn = DriverManager.getConnection(url, "sa", "");
		    conn.setAutoCommit(true);
		    DatabaseConnection connection = new DatabaseConnection(conn);
			DatabaseConfig config = connection.getConfig();
		    config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY,
		            new HsqldbDataTypeFactory());
		    return connection;
		  }

		  protected DatabaseOperation getTearDownOperation() {
		    //return DatabaseOperation.DELETE_ALL;
		    return DatabaseOperation.NONE;
		  }
		  
		  public DatabaseOperation getSetUpOperation() throws Exception {
			IDatabaseConnection connection = getConnection();
			Connection conn = connection.getConnection();
			conn.setAutoCommit(false);
			conn.commit();
			conn.close();
		    return DatabaseOperation.CLEAN_INSERT;
		}


		public void setUp() throws Exception {
			Configuration hibConf = new Configuration();
			this.hibFactory = hibConf.configure("test_hibernate.cfg.xml").buildSessionFactory();
		}

		public void tearDown() throws Exception {
			if(this.hibFactory != null){
				this.hibFactory.close();
			}
			this.hibFactory = null;
		}

		@Override
		protected IDataSet getDataSet() throws Exception {
			return new DefaultDataSet();
		}

		public void testFolderCreation(){
			this.hibFactory.getCurrentSession().beginTransaction();
			HibRepository hibRepository = new HibRepository();
			hibRepository.setName("Local");
			hibRepository.setDescription("Local data store");
			HibRootFolder hibRootFolder = new HibRootFolder();
			hibRootFolder.changeDataStore(hibRepository);
			HibNonRootFolder subFolder = new HibNonRootFolder();
			subFolder.setName("subFolder");
			hibRootFolder.addSubFolder(subFolder);
			HibMapDiagram map1 = new HibMapDiagram();
			map1.setName("Test1 Map");
			map1.setDescription("Test1 Map description");
			map1.changeFolder(subFolder);
			Context context = new Context();
			context.setName("TestContext");
			context.setDescription("Test Context Description");
			context.setGlobalId("hdghafafg");
			context.setMajorVersion(1);
			context.setMinorVersion(0);
			context.setPatchVersion(0);
			ObjectType testOT = new ObjectType();
			testOT.setContext(context);
			testOT.setName("testOT");
			testOT.setDescription("testOT description");
			testOT.changeContext(context);
			Canvas canvas = new Canvas();
			canvas.setBackgroundBlue(0);
			canvas.setBackgroundRed(0);
			canvas.setBackgroundGreen(0);
			canvas.setGridX(10);
			canvas.setGridY(10);
			canvas.changeMapDiagram(map1);
			canvas.setContext(context);
			RootShape shape = new RootShape();
			shape.setName("test");
			shape.setDescription("test descn");
			shape.setDetailedDescription("test detailed descn");
			shape.setUrl("url");
			shape.setObjectType(testOT);
			shape.changeCanvas(canvas);
//			this.hibFactory.getCurrentSession().save(context);
			this.hibFactory.getCurrentSession().save(hibRepository);
			this.hibFactory.getCurrentSession().save(canvas);
			this.hibFactory.getCurrentSession().getTransaction().commit();
		}
}
