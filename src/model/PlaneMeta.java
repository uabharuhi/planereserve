package model;
import javax.persistence.*;
@Entity
@Table(name = "planemeta")
public class  PlaneMeta {
   @Id 
   @GeneratedValue(generator="sqlite")
   @TableGenerator(name="sqlite", table="sqlite_sequence",  //sqlite��sqlite_sequence�o�i���ӹ�{auto increment..²��ʼ�
   pkColumnName="name", valueColumnName="seq",
   pkColumnValue="planemeta",initialValue=0, allocationSize=1)//���[initialValue=1, allocationSize=1�|�ø�...
   @Column(name = "id",nullable=false)
   private int id;
   
   @Column(name = "typename",nullable=false)
   private String typename;

   @Column(name = "max_num_people",nullable=false)
   private int max_num_people;  
   
   public PlaneMeta() {
	  
   } 
   
   public PlaneMeta(int id,String typename,int max_num_people) {
	   this.id = id;
	   this.typename = typename;
	   this.max_num_people = max_num_people;
   }
   
   
   public PlaneMeta(String typename,int max_num_people) {
	   this.typename = typename;
	   this.max_num_people = max_num_people;
   }
   
   
   public int getId() {
		return id;
   }
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTypename() {
		return typename;
	}
	
	public void setTypename(String typename) {
		this.typename = typename;
	}
	
	public int getMax_num_people() {
		return max_num_people;
	}
	
	public void setMax_num_peopley(int max_num_people) {
		this.max_num_people = max_num_people;
	}

}