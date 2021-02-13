import java.util.Scanner;
import java.util.Collections;
import java.util.Vector;
import java.util.Random;
import java.util.InputMismatchException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Main {
	
	//print table karywan
	static void printEmployee(Vector<Employee> employees) {
		System.out.println("|----|-----------------|------------------------------|---------------|--------------|-------------|");
		System.out.println("|No  |Kode Karyawan    |Nama Karyawan                 |Jenis Kelamin  |Jabatan       |Gaji Karyawan|"); 
		System.out.println("|----|-----------------|------------------------------|---------------|--------------|-------------|");
		int i = 1;
		for(Employee employee : employees) {
			System.out.printf("|%4d|%17s|%30s|%15s|%14s|%13s|\n",i,employee.getCode(),employee.getName(), employee.getSex(), employee.getJob(),employee.getSalary());
			++i;
		}
		System.out.println("|----|-----------------|------------------------------|---------------|--------------|-------------|");
	}
	
	//print space
	static void space() {
		for(int i=0; i<10; i++) {
			System.out.println();
		}
	}
	
	public Main() {
		Scanner scan = new Scanner(System.in);
		Vector<Employee> employees = new Vector<Employee>();
		System.out.println("PT Mentol");
		int menu=0;
		int manager_count=0;
		int super_count=0;
		int admin_count=0;
		do {
			do {
				System.out.println("Pilih Nomor Menu:");
				System.out.println("1. Insert Data Karyawan");
				System.out.println("2. View Data Karyawan");
				System.out.println("3. Update Data Karyawan");
				System.out.println("4. Delete Data Karyawan");
				try {
					System.out.print("Input nomor menu: ");
					menu = scan.nextInt();
					scan.nextLine();				
				} catch (InputMismatchException e) {
					scan.nextLine();
					System.out.println("Mohon input nomor menu menggunakan angka");
					System.out.println("ENTER to return");
					scan.nextLine();
				}
				
			}while(menu<1||menu>4);
			space();
			
			//Insert karyawan baru
			if(menu==1) {
				String name, sex, job, code="";
				int salary=0;
				boolean flag;
				do {
					System.out.print("Input nama karyawan [>=3]: ");
					name = scan.nextLine();
				}while(name.length()<3);
				do {
					System.out.print("Input jenis kelamin [Laki-laki | Perempuan] (Case Sensitive): ");
					sex = scan.nextLine();
					flag = true;
					if(sex.equals("Laki-laki")||sex.equals("Perempuan")) {
						flag = false;
					}
						
				}while(flag);
				do {
					System.out.print("Input jabatan [Manager | Supervisor | Admin] (Case Sensitive): ");
					job = scan.nextLine();
					flag = true;
					if(job.equals("Manager")||job.equals("Supervisor")||job.equals("Admin")) {
						flag = false;
					}
				}while(flag);
				
				//randomize kode karyawan
				Random r = new Random();
				for(int i=0; i<2; i++) {				
					char c = (char)(r.nextInt(26) + 'A');
					code += c;
				}
				code += '-';
				for(int i=0; i<4; i++) {
					char num = (char)(r.nextInt(9) + '0');
					code += num;
				}
				//
				System.out.println("Berhasil menambahkan karyawan dengan id "+ code);
				
				// Menentukan gaji,jumlah jabatan, dan print kode yang gajinya naik
				if(job.equals("Manager")){
					salary = 8000000;
					++manager_count;
					if((manager_count-1)%3==0 && manager_count!=1) {
						System.out.print("Bonus sebesar 10% telah diberikan kepada karyawan dengan id ");
						int comma = manager_count - 1;
						for(Employee employee : employees) {
							if(employee.getJob().equals("Manager")) {
								int raise = employee.getSalary();
								raise =  (int)(0.1 * raise) + raise;
								employee.setSalary(raise);
								System.out.print(employee.getCode());
								if(comma != 1)
									System.out.print(", ");
									--comma;
							}
						}
						
					}
				}
				else if(job.equals("Supervisor")) {
					salary = 6000000;
					++super_count;
					if((super_count-1)%3==0 && super_count!=1) {
						System.out.print("Bonus sebesar 7.5% telah diberikan kepada karyawan dengan id ");
						int comma = super_count - 1;
						for(Employee employee : employees) {
							if(employee.getJob().equals("Supervisor")) {
								int raise = employee.getSalary();
								raise = (int) (0.075 * raise) + raise;
								employee.setSalary(raise);
								System.out.print(employee.getCode());
								if(comma != 1)
									System.out.print(", ");
									--comma;
							}
						}
						
					}
				}				
				else if(job.equals("Admin")) {
					salary = 4000000;
					++admin_count;
					if((admin_count-1)%3==0 && admin_count!=1) {
						System.out.print("Bonus sebesar 5% telah diberikan kepada karyawan dengan id ");
						int comma = admin_count-1;
						for(Employee employee : employees) {
							if(employee.getJob().equals("Admin")) {
								int raise = employee.getSalary();
								raise = (int) (0.05 * raise) + raise;
								employee.setSalary(raise);
								System.out.print(employee.getCode());
								if(comma != 1)
									System.out.print(", ");
									--comma;
							}
						}
						
					}
				}
				//				
				
				employees.add(new Employee(code,name,sex,job,salary));	
				//sort karyawan
				Collections.sort(employees, Employee.EmployeeNameComparator);
				//
			}
			
			//View karyawan
			else if(menu==2) {
				printEmployee(employees);
			}
			
			//Update karyawan
			else if(menu==3) {
				printEmployee(employees);
				System.out.print("Input nomor karywan: ");
				int emp_num = scan.nextInt();
				--emp_num;
				
				String temp = employees.get(emp_num).getJob();
				
				scan.nextLine();
				String name, sex, job, code="";
				int salary=0;
				boolean flag;
				
				//pattern regex untuk format kode karyawan MM-XXXX
				String pattern = "^[A-Z]{2}-\\d{4}$";
				Pattern regex = Pattern.compile(pattern);
				
				do {
					flag=true;
					System.out.print("Input kode karyawan [cth: MI-1923]: ");
					code = scan.nextLine();
					Matcher match = regex.matcher(code);
					if(match.find()) {
						flag = false;
					}
				} while (flag);
				
				do {
					System.out.print("Input nama karyawan [>=3]: ");
					name = scan.nextLine();
				}while(name.length()<3);
				
				do {
					System.out.print("Input jenis kelamin [Laki-laki | Perempuan] (Case Sensitive): ");
					sex = scan.nextLine();
					flag = true;
					if(sex.equals("Laki-laki")||sex.equals("Perempuan")) {
						flag = false;
					}					
				}while(flag);
				
				do {
					System.out.print("Input jabatan [Manager | Supervisor | Admin] (Case Sensitive): ");
					job = scan.nextLine();
					flag = true;
					if(job.equals("Manager")||job.equals("Supervisor")||job.equals("Admin")) {
						flag = false;
					}
				}while(flag);
				
				do {
					try {
						System.out.print("Input gaji karyawan (Angka): ");
						salary = scan.nextInt();
						scan.nextLine();
						
					} catch (InputMismatchException e) {
						scan.nextLine();
						System.out.println("Mohon input gaji dengan angka");
					}
				} while (salary<1||salary>100000000);	
				
				System.out.println("Berhasil mengubah data karyawan dengan id "+code);
				
				if(!temp.equals(job)) {
					//Jika input jabatan berbeda dengan yang sebelumnya maka banyak karyawan di jabatan sebelumnya
					//akan dikurangi
					if(temp.equals("Manager"))
						--manager_count;
					else if(temp.equals("Supervisor"))
						--super_count;
					else if(temp.equals("Admin"))
						--admin_count;
					
					//Sebaliknya jumlah karyawan pada jabatan barunya akan ditambah
					//dan sistem penambahan gaji tetap berjalan apabila terdapat penambahan jabtan dengan kelipatan tiga
					if(job.equals("Manager")) {
						++manager_count;
						if((manager_count-1)%3==0 && manager_count!=1) {
							System.out.print("Bonus sebesar 10% telah diberikan kepada karyawan dengan id ");
							int comma = manager_count - 1;
							for(Employee employee : employees) {
								if(employee.getJob().equals("Manager")) {
									int raise = employee.getSalary();
									raise =  (int)(0.1 * raise) + raise;
									employee.setSalary(raise);
									System.out.print(employee.getCode());
									if(comma != 1)
										System.out.print(", ");
										--comma;
								}
							}
						}
					}
					
					else if(job.equals("Supervisor")) {
						++super_count;
						if((super_count-1)%3==0 && super_count!=1) {
							System.out.print("Bonus sebesar 7.5% telah diberikan kepada karyawan dengan id ");
							int comma = super_count - 1;
							for(Employee employee : employees) {
								if(employee.getJob().equals("Supervisor")) {
									int raise = employee.getSalary();
									raise = (int) (0.075 * raise) + raise;
									employee.setSalary(raise);
									System.out.print(employee.getCode());
									if(comma != 1)
										System.out.print(", ");
										--comma;
								}
							}
						}
					}
					
					else if(job.equals("Admin")) {
						++admin_count;
						if((admin_count-1)%3==0 && admin_count!=1) {
							System.out.print("Bonus sebesar 5% telah diberikan kepada karyawan dengan id ");
							int comma = admin_count-1;
							for(Employee employee : employees) {
								if(employee.getJob().equals("Admin")) {
									int raise = employee.getSalary();
									raise = (int) (0.05 * raise) + raise;
									employee.setSalary(raise);
									System.out.print(employee.getCode());
									if(comma != 1)
										System.out.print(", ");
										--comma;
								}
							}
						}
					}
				}
				
				//set data karyawan baru
				employees.get(emp_num).setCode(code);
				employees.get(emp_num).setName(name);
				employees.get(emp_num).setSex(sex);
				employees.get(emp_num).setJob(job);
				employees.get(emp_num).setSalary(salary);
				//
			}
			
			// Delete karyawan
			else if(menu==4) {
				printEmployee(employees);
				System.out.print("Input nomor karyawan: ");
				int emp_num = scan.nextInt();
				scan.nextLine();
				employees.remove(--emp_num);
			}

			System.out.println("ENTER to return");
			scan.nextLine();
			
			space();	
		}while(true);	
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main(); 
	}

}
