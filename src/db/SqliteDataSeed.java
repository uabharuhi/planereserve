package db;
import org.hibernate.dialect.SQLiteDialect;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.Time;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.sqlite.SQLiteConfig;

import model.Flight;
import model.PlaneMeta;;

public class SqliteDataSeed {
	public static void main(String[] args) throws Exception {
		//テーブルの削除と作成(SQL)
		
		 SqliteDataSeed seed = new  SqliteDataSeed();
		 seed.createTable();
		 seed.insertPlane();
		 seed.insertFlight();
		 seed.getSession().close();
		 System.out.println("close");

		/*
		Configuration config = new Configuration();
		config = config.configure();
		
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();

		Transaction transaction = session.beginTransaction();
		session.save(new Hello(Integer.valueOf("1"), "test1"));
		session.save(new Hello(Integer.valueOf("2"), "test2"));
		session.save(new Hello(Integer.valueOf("3"), "test3"));
		transaction.commit();

		//データの取得(Hibernate)
		List rows = session.createCriteria(Hello.class).list();
		for (int i = 0; i < rows.size(); i++) {
			Hello one = (Hello) rows.get(i);
			System.out.println("id = " + one.getId());
			System.out.println("name = " + one.getName());
		}
		session.close();
		*/
	}
	
	private  Configuration config;
	private SessionFactory sessionFactory;
	private Session session;
	
	

	public SqliteDataSeed() {
		this.config = new Configuration();
		this.config.addAnnotatedClass(model.PlaneMeta.class);
		this.config.addAnnotatedClass(model.Flight.class);
		this.config = config.configure();
		this.sessionFactory = config.buildSessionFactory();
		this.session =  sessionFactory.openSession();
		
	}
	
	public Session getSession() {
		return session;
	}
	

	public void insertDatas() {
		
		// insert flight
		// insert  
	}
	
	public void insertPlane() {


		Transaction transaction = session.beginTransaction();
		session.save(new  PlaneMeta("A",100));
		session.save(new  PlaneMeta("A",10));
		//session.save(new  PlaneMeta("B",5));
		transaction.commit();

		//データの取得(Hibernate)
		List rows = session.createCriteria(PlaneMeta.class).list();
		for (int i = 0; i < rows.size(); i++) {
			PlaneMeta one = (PlaneMeta) rows.get(i);
			System.out.println("id = " + one.getId());
			System.out.println("typename = " + one.getTypename());
			System.out.println("max_num_people = " + one.getMax_num_people());
		}
	}
	
	public void insertFlight() {
		Transaction transaction = session.beginTransaction();
		Criteria criteria = session.createCriteria(PlaneMeta.class);
		criteria.add(Restrictions.eq("id", new Integer(1)));
		
		//lock https://codereview.stackexchange.com/questions/86872/hibernate-insert-with-foreign-key
		
		//List planes = criteria.list();
		//PlaneMeta plane = (PlaneMeta)planes.get(0);
		//Flight flight = new  Flight(1, "D1","D2" , new Date(2017,8,7), new Time(23,0,0)
		//System.out.println("id = " + plane.getId());
		//System.out.println("typename = " + plane.getTypename());
		PlaneMeta plane = session.load(PlaneMeta.class,1);
		System.out.println("id = " + plane.getId());
		//System.out.println("typename = " + plane.getTypename());
		session.save(new  Flight(plane, 1, "D1","D2" , new Date(2017,8,7), new Time(23,0,0)));
		session.save(new  Flight(session.load(PlaneMeta.class,2), 1, "D1","D2" , new Date(2017,8,7), new Time(23,0,0)));
		//session.save(new  Flight(session.load(PlaneMeta.class,3), 1, "D1","D2" , new Date(2017,8,7), new Time(23,0,0)));
		//session.save(session.save(new  Flight(plane, 1, "D1","D2" , new Date(2017,8,7), new Time(23,0,0))));
		//session.save(new  Flight(3 , 3, "D3","D4", new Date(2017,11,11), new Time(15,0,0)));
		//session.save(new  PlaneMeta("B",5));
		transaction.commit();
	}
	
	public void createTable() throws Exception {
		Class.forName("org.sqlite.JDBC");
		//for enable foreighkey
		SQLiteConfig sqlconfig = new SQLiteConfig();  
		sqlconfig.enforceForeignKeys(true); 
		Connection conn =
				DriverManager.getConnection("jdbc:sqlite:data.db",sqlconfig.toProperties());
		
		Statement stat = conn.createStatement();
		// planemeta
		stat.executeUpdate("drop table if exists ticket;");
		stat.executeUpdate("drop table if exists flight;");
		stat.executeUpdate("drop table if exists planemeta;");
		
		stat.executeUpdate("create table planemeta(id integer primary key AUTOINCREMENT,"
				+ "max_num_people int not null,typename text not null);");
		
		
		
		//  flight 班次
		
		stat.executeUpdate("create table flight(id integer primary key AUTOINCREMENT,"
				+ "plane_id integer not null,"
				+ "price int not null,"
				+ "departure text not null,"
				+ "destination text not null,"
				+ "date date not null,"
				+ "time time not null,"
				+ "FOREIGN KEY(plane_id) REFERENCES planemeta(id));");
		
		
		stat.executeUpdate("create table ticket(id integer primary key AUTOINCREMENT,"
				+ "username text not null,"
				+ "flight_id integer not null,"
				+ "FOREIGN KEY(flight_id) REFERENCES flight(id));");
		
		
		/*
		stat.executeUpdate("insert into planemeta(max_num_people,typename)"
				+ "values (100,\"A\");");
		stat.executeUpdate("insert into flight(plane_id,price,departure,destination,date,time) "
				+ "values(1,100,\"AA\",\"BB\",\"2016-5-5\",\"16:00:00\");");
		//this will raise error
		//stat.executeUpdate("insert into flight(plane_id,price,departure,destination,date,time) "
				//+ "values(2,100,\"AA\",\"BB\",\"2016-5-5\",\"16:00:00\");");
		stat.executeUpdate("insert into ticket(username,flight_id) values(\"faker\",1);"
				);
		//this will raise error
		//stat.executeUpdate("insert into ticket(username,flight_id) values(\"faker\",2);"
				//);
		*/
				 
		System.out.println("create table done");
		conn.close();

	}
}
