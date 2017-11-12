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
import model.PlaneMeta;
import model.Ticket;
import ui.Repository;;

public class SqliteDataSeed {
	public static void main(String[] args) throws Exception {

		 SqliteDataSeed seed = new  SqliteDataSeed();
		 seed.createTable();
		 seed.insertPlane();
		 seed.insertFlight();
		 seed.insertTicket();
		 seed.showFlight();
		 seed.getSession().close();
		 System.out.println("close");
		
	
	}
	
	
	
	
	private  Configuration config;
	private SessionFactory sessionFactory;
	private Session session;
	
	
	
	
	

	public SqliteDataSeed() {
		this.config = new Configuration();
		this.config.addAnnotatedClass(model.PlaneMeta.class);
		this.config.addAnnotatedClass(model.Flight.class);
		this.config.addAnnotatedClass(model.Ticket.class);
		this.config = config.configure();
		this.sessionFactory = config.buildSessionFactory();
		this.session =  sessionFactory.openSession();
		
	}
	
	
	public void showFlight() {
		//check date
		Repository<Flight> repo = new  Repository<>(Flight.class,this.session);
		List<Flight> list = repo.selectall();
		for(Flight f : list) {
			System.out.println(f.getId());
			System.out.println(f.getDeparture());
			System.out.println(f.getDate());
			System.out.println(f.getTime());
		}
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
		session.save(new  PlaneMeta("B",3));
		session.save(new  PlaneMeta("C",5));
		transaction.commit();

		//嚙踝蕭���蕭蹌莎蕭蹓蕭嚙踐□嚙踝蕭(Hibernate)
		List rows = session.createCriteria(PlaneMeta.class).list();
		for (int i = 0; i < rows.size(); i++) {
			PlaneMeta one = (PlaneMeta) rows.get(i);
			System.out.println("id = " + one.getId());
			System.out.println("typename = " + one.getTypename());
			System.out.println("max_num_people = " + one.getMax_num_people());
		}
	}
	
	public void insertTicket() {


		Transaction transaction = session.beginTransaction();
		Flight flight1 = session.get(Flight.class,1);
		Flight flight3 = session.get(Flight.class,2);
		session.save(new  Ticket(flight1,"user1"));
		session.save(new  Ticket(flight3,"user2"));
		//session.save(new  PlaneMeta("B",5));
		transaction.commit();
		
	}
	
	
	
	
	
	public void insertFlight() {
		Criteria criteria = session.createCriteria(PlaneMeta.class);
		criteria.add(Restrictions.eq("id", new Integer(1)));
		
		//lock https://codereview.stackexchange.com/questions/86872/hibernate-insert-with-foreign-key
		
		PlaneMeta plane = (PlaneMeta) session.createCriteria(PlaneMeta.class).add(Restrictions.eq("id", 1)).uniqueResult();
		System.out.println("id = " + plane.getId());
		
		Transaction transaction = session.beginTransaction();
		session.save(new  Flight(plane, 1, "D1","D2" , "2017-9-9", new Time(23,0,0)));
		Flight f2 = new  Flight(session.load(PlaneMeta.class,2), 1, "D1","D2" ,"2017-8-17", new Time(23,0,0));
		session.save(f2);
				
		
		//session.save(new  Flight(session.load(PlaneMeta.class,3), 1, "D1","D2" , new Date(2017,8,7), new Time(23,0,0)));
		//session.save(session.save(new  Flight(plane, 1, "D1","D2" , new Date(2017,8,7), new Time(23,0,0))));
		//session.save(new  Flight(3 , 3, "D3","D4", new Date(2017,11,11), new Time(15,0,0)));
		//session.save(new  PlaneMeta("B",5));
		transaction.commit();
	}
	
	
	public Connection getConnection() throws Exception {
		Class.forName("org.sqlite.JDBC");
		//for enable foreighkey
		SQLiteConfig sqlconfig = new SQLiteConfig();  
		sqlconfig.enforceForeignKeys(true); 
		Connection conn =
				DriverManager.getConnection("jdbc:sqlite:data.db",sqlconfig.toProperties());
		return conn;
	}
	
	
	public void createTable() throws Exception {
	
		Connection conn = getConnection();
				
		Statement stat = conn.createStatement();
		// planemeta
		stat.executeUpdate("drop table if exists ticket;");
		stat.executeUpdate("drop table if exists flight;");
		stat.executeUpdate("drop table if exists planemeta;");
		
		stat.executeUpdate("create table planemeta(id integer primary key AUTOINCREMENT,"
				+ "max_num_people int not null,typename text not null);");
		
		
		
		//  flight 嚙踐��蕭
		
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