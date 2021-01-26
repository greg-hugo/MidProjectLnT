import java.util.Comparator;

public class Employee{
	private String code;
	private String name;
	private String sex;
	private String job;
	private int salary;
	public Employee() {
		// TODO Auto-generated constructor stub
		
	}
	public Employee(String code, String name, String sex, String job, int salary) {
		super();
		this.code = code;
		this.name = name;
		this.sex = sex;
		this.job = job;
		this.salary = salary;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public int getSalary() {
		return salary;	
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	public static Comparator<Employee> EmployeeNameComparator 
    					= new Comparator<Employee>() {

		public int compare(Employee employee1, Employee employee2) {

			String employeeName1 = employee1.getName().toUpperCase();
			String employeeName2 = employee2.getName().toUpperCase();


			return employeeName1.compareTo(employeeName2);
		}

	};
	
}
