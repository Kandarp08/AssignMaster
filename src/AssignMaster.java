// Import required packages

import java.sql.*;
import java.util.Scanner;

public class AssignMaster
{
    // Set JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
   
    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/companydb?useSSL=false";
   
    // Database credentials
    static final String USER = "username";// add your user
    static final String PASSWORD = "password";// add password

    public static void main(String args[])
    {
        Scanner scan = new Scanner(System.in);

        Connection conn = null;
        Statement stmt = null;

        // Connnecting to the database
        try
        {
            // Register JDBC Driver
            Class.forName(JDBC_DRIVER);

            // Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            conn.setAutoCommit(false);

            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            System.out.println("Welcome to the AssignMaster Portal!\n");

            while (true)
            {
                System.out.println("\n0. Exit portal");
                System.out.println("1. Add new employee");
                System.out.println("2. Update employee details");
                System.out.println("3. Add new project");
                System.out.println("4. Update project details");
                System.out.println("5. Show all employees working on a project");
                System.out.println("6. Show employee with highest hours spent for each project");
                System.out.println("7. Show employees working under a given manager");
                System.out.println("8. Delete a project");
                System.out.println("9. Assign a project to an employee");
                System.out.println("10. Add or update dependent of an employee");
                System.out.println("11. Show dependent of an employee who has a phone number");
                System.out.println("12. Assign manager for a project");
                System.out.println("13. Remove an employee from a project");
                System.out.println("14. Remove employee from company");

                System.out.print("\nPlease enter a valid option: ");
                int query_num = scan.nextInt();

                try
                {
                    if (query_num == 0)
                    {
                        System.out.println("\nThank you for visiting the portal!");
                        break;
                    }       

                    // Add new employee
                    else if (query_num == 1)
                    {
                        // Take input for all the attributes

                        String ssn, name, phone_number, date_of_birth, address, sex, mgr_ssn;
                        long salary;
                        scan.nextLine();

                        System.out.print("Enter 9-digit ssn number: ");
                        ssn = scan.nextLine();
                        System.out.print("Enter employee name: ");
                        name = scan.nextLine();

                        while (true)
                        {
                            System.out.print("Enter phone number: ");
                            phone_number = scan.nextLine();

                            if (isValidPhone(phone_number))
                                break;

                            else
                                System.out.println("Invalid phone number. Please try again.");
                        }

                        System.out.print("Enter date of birth (YYYY:MM:DD): ");
                        date_of_birth = scan.nextLine();
                        System.out.print("Enter address: ");
                        address = scan.nextLine();
                        System.out.print("Enter sex (M / F): ");
                        sex = scan.nextLine();
                        System.out.print("Enter salary: ");
                        salary = scan.nextLong();
                        scan.nextLine();
                        System.out.print("Enter manager's ssn number ('-' if NA): ");
                        mgr_ssn = scan.nextLine();

                        if (mgr_ssn.equals("-"))
                            mgr_ssn = null;

                        // Execute the insert statement

                        String addEmployee = "insert into employee values ('" +
                                             ssn + "', '" + name + "', '" + phone_number + "', '" +
                                             date_of_birth + "', '" + address + "', '" + sex + "', " +
                                             salary + ", ";
                                             
                        if (mgr_ssn == null)
                            addEmployee += "null);";

                        else
                            addEmployee += "' " + mgr_ssn + "');";

                        stmt.executeUpdate(addEmployee);

                        System.out.println("\nEmployee added successfully.");

                        conn.commit(); // Commit the changes
                    }

                    // Update employee details
                    else if (query_num == 2)
                    {
                        // Take input of all attribute values

                        String ssn, name, phone_number, date_of_birth, address, sex, mgr_ssn;
                        long salary;
                        scan.nextLine();

                        System.out.print("Enter 9-digit ssn number: ");
                        ssn = scan.nextLine();
                        System.out.print("Enter employee name: ");
                        name = scan.nextLine();

                        while (true)
                        {
                            System.out.print("Enter phone number: ");
                            phone_number = scan.nextLine();

                            if (isValidPhone(phone_number))
                                break;

                            else
                                System.out.println("Invalid phone number. Please try again.");
                        }

                        System.out.print("Enter date of birth (YYYY:MM:DD): ");
                        date_of_birth = scan.nextLine();
                        System.out.print("Enter address: ");
                        address = scan.nextLine();
                        System.out.print("Enter sex (M / F): ");
                        sex = scan.nextLine();
                        System.out.print("Enter salary: ");
                        salary = scan.nextLong();
                        scan.nextLine();
                        System.out.print("Enter manager's ssn number ('-' if NA): ");
                        mgr_ssn = scan.nextLine();

                        if (mgr_ssn.equals("-"))
                            mgr_ssn = null;

                        // Execute update statement

                        String updateEmployee = "update employee set name = '" + 
                                                name + "', phone_number = '" + phone_number + 
                                                "', date_of_birth = '" + date_of_birth + "', address = '" + 
                                                address + "', sex = '" + sex + "', salary = " + salary + ", ";
                                             
                        if (mgr_ssn == null)
                            updateEmployee += "mgr_ssn = null ";

                        else
                            updateEmployee += "mgr_ssn = " + mgr_ssn + "' ";

                        updateEmployee += "where ssn = '" + ssn + "';"; 

                        int rows_affected = stmt.executeUpdate(updateEmployee);

                        if (rows_affected == 0)
                            System.out.println("\nEmployee details could not be updated.");

                        else
                            System.out.println("\nEmployee details updated successfully.");
                            
                        conn.commit(); // Commit the changes
                    }

                    // Add new project
                    else if (query_num == 3)
                    {
                        // Take input of project details

                        int project_id;
                        String project_name;
                        
                        System.out.print("Enter project ID: ");
                        project_id = scan.nextInt();
                        scan.nextLine();
                        System.out.print("Enter project name: ");
                        project_name = scan.nextLine();

                        // Execute insert statement

                        String addProject = "insert into project values (" +
                                            project_id + ", '" + project_name + "');";
                        stmt.executeUpdate(addProject);

                        System.out.println("\nProject added successfully.");

                        conn.commit(); // Commit the changes
                    }

                    // Update project details
                    else if (query_num == 4)
                    {
                        // Take input of project details
                         
                        int project_id;
                        String project_name;

                        System.out.print("Enter project ID: ");
                        project_id = scan.nextInt();
                        scan.nextLine();
                        System.out.print("Enter project name: ");
                        project_name = scan.nextLine();

                        // Execute update statement

                        String updateProject = "update project set project_name = '" +
                                               project_name + "' where project_id = " +
                                               project_id + ";";
                        int rows_affected = stmt.executeUpdate(updateProject);

                        if (rows_affected == 0)
                            System.out.println("\nProject not found.");

                        else
                            System.out.println("\nProject details updated successfully.");
                            
                        conn.commit(); // Commit the changes
                    }

                    // Show all employees working on a project
                    else if (query_num == 5)
                    {
                        int project_id; // Required project ID

                        System.out.print("Enter project ID: ");
                        project_id = scan.nextInt();

                        // Perform an inner join on the relations employee and works_on 

                        String showEmployeesForProject = "select ssn, name, phone_number, mgr_ssn, hours_spent from " +
                                                         "(employee inner join works_on on employee.ssn = works_on.emp_ssn) " +
                                                         "where project_id = " + project_id + ";";

                        ResultSet rs = stmt.executeQuery(showEmployeesForProject);
                        int sr_no = 1;

                        // Display the employee details
                        while (rs.next())
                        {
                            String ssn = rs.getString("ssn");
                            String name = rs.getString("name");
                            String phone_number = rs.getString("phone_number");
                            String mgr_ssn = rs.getString("mgr_ssn");
                            int hours_spent = rs.getInt("hours_spent");

                            System.out.println("\nSr. No.: " + sr_no++);

                            if (ssn.equals(mgr_ssn))
                                System.out.println("MANAGER");

                            System.out.println("Employee SSN: " + ssn);
                            System.out.println("Employee Name: " + name);
                            System.out.println("Phone number: " + phone_number);
                            System.out.println("Hours spent: " + hours_spent);
                        }

                        if (sr_no == 1)
                            System.out.println("\nNo employees are currently working on given project.");
                    }   

                    // Show employee with highest hours spent for each each project
                    else if (query_num == 6)
                    {
                        String getProjectID = "select * from project;";
                        ResultSet project_list = stmt.executeQuery(getProjectID);

                        // Iterate through each project
                        while (project_list.next())
                        {
                            int project_id = project_list.getInt("project_id");
                            String project_name = project_list.getString("project_name");
                            System.out.println("\n*********************************");
                            System.out.println("Project ID: " + project_id);
                            System.out.println("Project Name: " + project_name);

                            // Perform inner join on employee and works_on

                            String getEmployees = "select ssn, name, phone_number, hours_spent from" + 
                                                  "(employee inner join works_on on employee.ssn = works_on.emp_ssn) " + 
                                                  "where project_id = " + project_id + " and " +
                                                  "hours_spent = (select max(hours_spent) from works_on where " +
                                                  "project_id = " + project_id + ");";

                            Statement emp_stmt = conn.createStatement();
                            ResultSet rs = emp_stmt.executeQuery(getEmployees);
                            int sr_no = 1;

                            // Display employee details
                            while (rs.next())
                            {
                                System.out.println("\nSr. No.: " + sr_no++);

                                String ssn = rs.getString("ssn");
                                String name = rs.getString("name");
                                String phone_number = rs.getString("phone_number");
                                int hours_spent = rs.getInt("hours_spent");
                            
                                System.out.println("\nEmployee SSN: " + ssn);
                                System.out.println("Employee Name: " + name);
                                System.out.println("Phone number: " + phone_number);
                                System.out.println("Hours spent: " + hours_spent);
                            }

                            if (sr_no == 1)
                                System.out.println("\nNo employee is working on project.");
                        }
                    }

                    // Show employees working under a given manager
                    else if (query_num == 7)
                    {
                        // Take input of manager ssn

                        String mgr_ssn;
                        scan.nextLine();

                        System.out.print("Enter manager ssn: ");
                        mgr_ssn = scan.nextLine();

                        // Search for the requisite employees

                        String getEmployees = "select ssn, name, phone_number from employee " +
                                              "where mgr_ssn = '" + mgr_ssn + "';";
                        ResultSet rs = stmt.executeQuery(getEmployees);
                        int sr_no = 1;

                        // Display employee details
                        while (rs.next())
                        {
                            String ssn = rs.getString("ssn");
                            String name = rs.getString("name");
                            String phone_number = rs.getString("phone_number");
                    
                            if (ssn.equals(mgr_ssn))
                                continue;

                            System.out.println("\nSr. No.: " + sr_no++);
                            System.out.println("Employee SSN: " + ssn);
                            System.out.println("Employee Name: " + name);
                            System.out.println("Phone number: " + phone_number);
                        }

                        if (sr_no == 1)
                            System.out.println("\nNo employee works under this manager.");
                    }

                    // Delete a project
                    else if (query_num == 8)
                    {
                        // Project ID of project to be deleted
                        int project_id;

                        System.out.print("Enter project ID: ");
                        project_id = scan.nextInt();

                        // Set mgr_ssn for all employees working under this project to be NULL

                        String updateEmployees = "update employee set mgr_ssn = null where ssn in " + 
                                                 "(select emp_ssn from works_on where project_id = " +
                                                 project_id + ");";
                        stmt.executeUpdate(updateEmployees);

                        // Delete project from project relation

                        String deleteProject = "delete from project where project_id = " + project_id + ";";
                        int rows_affected = stmt.executeUpdate(deleteProject);

                        if (rows_affected == 0)
                            System.out.println("\nProject not found.");

                        else
                            System.out.println("\nProject successfully removed.");

                        conn.commit(); // Commit the changes
                    }

                    // Assign a project to an employee
                    else if (query_num == 9)
                    {
                        // Take input of employee ssn and project ID

                        String ssn;
                        int project_id;
                        scan.nextLine();

                        System.out.print("Enter employee ssn: ");
                        ssn = scan.nextLine();
                        System.out.print("Enter new project ID: ");
                        project_id = scan.nextInt();

                        // Find project from project relation
                        String getProject = "select project_id from project where project_id = " + project_id + ";";
                        ResultSet rs = stmt.executeQuery(getProject);

                        // If project does not exist
                        if (!rs.isBeforeFirst())
                        {
                            System.out.println("\nProject does not exist.");
                            continue;
                        }

                        // Find employee from employee relation
                        String getEmployee = "select ssn from employee where ssn = '" + ssn + "';";
                        rs = stmt.executeQuery(getEmployee);

                        // If employee does not exist
                        if (!rs.isBeforeFirst())
                        {
                            System.out.println("\nEmployee does not exist.");
                            continue;
                        }
                        
                        // Update the works_on relation
                        String updateProject = "insert into works_on values ('" +
                                               ssn + "', " + project_id + ", 0) " +
                                               "on duplicate key update " +
                                               "project_id = " + project_id + ", hours_spent = 0;";
                        stmt.executeUpdate(updateProject);

                        // Find the manager for new project
                        String findMgrSsn = "select ssn from employee inner join works_on on " +
                                            "employee.ssn = works_on.emp_ssn where project_id = " +
                                            project_id + " and mgr_ssn = ssn;";
                        rs = stmt.executeQuery(findMgrSsn);

                        String mgr_ssn = null;

                        while (rs.next())
                        {
                            mgr_ssn = rs.getString("ssn");
                        }

                        if (mgr_ssn == null)
                            mgr_ssn = ssn;

                        else
                            mgr_ssn = "'" + mgr_ssn + "'";

                        // Assign mgr_ssn of the employee
                        String updateEmployeeManager = "update employee set mgr_ssn = " +
                                                       mgr_ssn + " where ssn = '" + ssn + "' and ssn <> " +
                                                       mgr_ssn + ";";
                        stmt.executeUpdate(updateEmployeeManager);

                        // Find whether any employee is working under this employee
                        String findEmployees = "select ssn from employee where mgr_ssn = '" + ssn + "' and " +
                                               "ssn <> '" + ssn + "';";
                        rs = stmt.executeQuery(findEmployees);

                        // If employee is manager of others, rollback the changes
                        if (rs.isBeforeFirst())
                        {
                            System.out.println("\nEmployee is manager of other employees.");
                            System.out.println("Please assign some other manager for his / her current project.");

                            conn.rollback();
                            continue;
                        }

                        System.out.println("\nProject assigned successfully.");
                        
                        conn.commit(); // Commit the changes
                    }

                    // Add or update dependent of an employee
                    else if (query_num == 10)
                    {
                        // Take input of dependent details

                        String emp_ssn, name, phone_number, sex, relationship;
                        scan.nextLine();

                        System.out.print("Enter employee ssn: ");
                        emp_ssn = scan.nextLine();

                        String getEmployee = "select ssn from employee where ssn = '" + emp_ssn + "';";
                        ResultSet rs = stmt.executeQuery(getEmployee);

                        // If employee is not found
                        if (!rs.isBeforeFirst())
                        {
                            System.out.println("\nEmployee not found.");
                            continue;
                        }

                        System.out.print("Enter dependent name: ");
                        name = scan.nextLine();
                        
                        while (true)
                        {
                            System.out.print("Enter dependent phone number ('-' if NA): ");
                            phone_number = scan.nextLine();

                            if (phone_number.equals("-"))
                            {
                                phone_number = null;
                                break;
                            }

                            else if (!isValidPhone(phone_number))
                                System.out.println("\nInvalid phone number. Please try again.");

                            else
                                break;
                        }

                        System.out.print("Enter dependent sex (M / F): ");
                        sex = scan.nextLine();
                        System.out.print("Enter dependent relationship with employee: ");
                        relationship = scan.nextLine();

                        // Add dependent of employee
                        String addDependent = "insert into dependent values ('" +
                                              emp_ssn + "', '" + name + "', '" + phone_number + "', '" +
                                              sex + "', '" + relationship + "') on duplicate key update " +
                                              "phone_number = '" + phone_number + "', sex = '" + sex + "', " +
                                              "relationship = '" + relationship + "';";
                        int rows_affected = stmt.executeUpdate(addDependent);

                        if (rows_affected == 1)
                            System.out.println("\nDependent added successfully.");

                        else
                            System.out.println("\nDependent details updated successfully.");
                            
                        conn.commit(); // Commit the changes
                    }

                    // Show dependent of an employee who has a phone number
                    else if (query_num == 11)
                    {
                        // Take input of employee ssn

                        String ssn;
                        scan.nextLine();

                        System.out.print("Enter employee ssn: ");
                        ssn = scan.nextLine();

                        // Find employee
                        String findEmployee = "select ssn from employee where ssn = '" + ssn + "';";
                        ResultSet rs = stmt.executeQuery(findEmployee);

                        // If employee is not found
                        if (!rs.isBeforeFirst())
                        {
                            System.out.println("\nEmployee not found.");
                            continue;
                        }

                        // Find the required dependents
                        String findDependent = "select name, phone_number, sex, relationship from dependent where " +
                                               "emp_ssn = '" + ssn + "' and phone_number is not null;";
                        rs = stmt.executeQuery(findDependent);
                        int sr_no = 1;

                        // Display dependent details
                        while (rs.next())
                        {
                            System.out.println("\nSr. No.: " + sr_no++);

                            String name = rs.getString("name");
                            String phone_number = rs.getString("phone_number");
                            String sex = rs.getString("sex");
                            String relationship = rs.getString("relationship");

                            System.out.println("Name: " + name);
                            System.out.println("Phone number: " + phone_number);
                            System.out.println("Sex: " + sex);
                            System.out.println("Relationship: " + relationship);
                        }

                        if (sr_no == 1)
                            System.out.println("\nNo dependent found having a phone number.");
                    }

                    // Asssign manager for a project
                    else if (query_num == 12)
                    {
                        // Take input of ssn and project ID
                        String ssn;
                        int project_id;
                        scan.nextLine();

                        System.out.print("Enter ssn: ");
                        ssn = scan.nextLine();
                        System.out.print("Enter project ID: ");
                        project_id = scan.nextInt();
                        
                        // Find employee
                        String findEmployee = "select ssn from employee where ssn = '" + ssn + "';";
                        ResultSet rs = stmt.executeQuery(findEmployee);

                        // Employee not found
                        if (!rs.isBeforeFirst())
                        {
                            System.out.println("\nEmployee not found.");
                            continue;
                        }

                        // Find project
                        String findProject = "select project_id from project where project_id = " + project_id + ";";
                        rs = stmt.executeQuery(findProject);

                        // Project not found
                        if (!rs.isBeforeFirst())
                        {
                            System.out.println("\nProject not found.");
                            continue;
                        }

                        // Find employees working on this project
                        String findProjectEmployees = "select emp_ssn from works_on where " +
                                                      "project_id = " + project_id + ";";
                        rs = stmt.executeQuery(findProjectEmployees);

                        Statement update_stmt = conn.createStatement();

                        // Update the mgr_ssn for all the employees of this project
                        while (rs.next())
                        {
                            String emp_ssn = rs.getString("emp_ssn");
                            String updateManager;

                            updateManager = "update employee set mgr_ssn = '" + ssn + "' where ssn = '" + emp_ssn + "';";
                            update_stmt.executeUpdate(updateManager);
                        }
                        
                        // Find current project of the employee (who is made the manager)
                        String findCurrentProject = "select * from works_on where emp_ssn = '" +
                                                    ssn + "' and project_id = " + project_id + ";";
                        rs = stmt.executeQuery(findCurrentProject);

                        // If employee is not working on the given project
                        if (!rs.isBeforeFirst())
                        {
                            System.out.println("\nEmployee is not working on this project.");
                            System.out.println("Please first add him/her to this project.");

                            conn.rollback(); // Rollback the changes
                            continue;
                        }

                        System.out.println("\nManager assigned successfully.");

                        conn.commit(); // Commit the changes
                    }

                    // Remove an employee from a project
                    else if (query_num == 13)
                    {
                        // Take input of ssn

                        String ssn;
                        scan.nextLine();

                        System.out.print("Enter ssn: ");
                        ssn = scan.nextLine();

                        // Remove employee from project
                        String removeFromProject = "delete from works_on where emp_ssn = '" + ssn + "';";
                        int rows_affected = stmt.executeUpdate(removeFromProject);

                        if (rows_affected == 0)
                        {
                            System.out.println("\nEmployee is currently working on no project.");
                            continue;
                        }

                        // Update manager of employees who work under this employee
                        String updateManagerOfOtherEmployees = "update employee set mgr_ssn = null where mgr_ssn = '" + ssn + "';";
                        rows_affected = stmt.executeUpdate(updateManagerOfOtherEmployees);

                        // Removed employee was a manager
                        if (rows_affected > 0)
                        {
                            System.out.println("\nEmployee is manager of the project.");
                            System.out.println("Please assign some other manager for the project.");

                            conn.rollback(); // Rollback the changes
                            continue;
                        }

                        // Update manager to null
                        String updateManager = "update employee set mgr_ssn = null where ssn = '" + ssn + "';";
                        stmt.executeUpdate(updateManager);

                        System.out.println("\nEmployee successfully removed from project.");

                        conn.commit(); // Commit the changes
                    }

                    // Remove employee from company
                    else if (query_num == 14)
                    {
                        // Take input of ssn

                        String ssn;
                        scan.nextLine();

                        System.out.print("Enter employee ssn: ");
                        ssn = scan.nextLine();

                        // Delete the employee
                        String removeEmployee = "delete from employee where ssn = '" + ssn + "'";
                        int rows_affected = stmt.executeUpdate(removeEmployee);

                        if (rows_affected == 0)
                            System.out.println("\nEmployee does not exist.");

                        else
                            System.out.println("\nEmployee removed successfully.");

                        conn.commit(); // Commit the changes
                    }
                }

                catch (SQLException se)
                {
                    se.printStackTrace();
                    
                    System.out.println("Rolling back the previous operations.");
                    
                    try
                    {
                        if (conn != null)
                            conn.rollback();
                    }
        
                    catch (SQLException se1)
                    {
                        System.out.println("Rollback failed.");
                        se1.printStackTrace();
                    }
                }
            }
        }

        // Handle errors for JDBC
        catch (SQLException se)
        {
            se.printStackTrace();
        }

        // Handle errors for Class.forName
        catch (Exception e)
        {
            e.printStackTrace();
        }

        /* finally block used to close resources regardless of whether an exception
           occurred or not */
        finally
        {
            scan.close();

            try
            {
                if (stmt != null)
                    stmt.close();
            }

            catch (SQLException se)
            {
                se.printStackTrace();
            }

            try
            {
                if (conn != null)
                    conn.close();
            }

            catch (SQLException se)
            {
                se.printStackTrace();
            }
        }
    }

    public static boolean isValidPhone(String phone)
    {
        if (phone.length() != 10)
            return false;

        for (int i = 0; i < 10; ++i)
        {
            if (!Character.isDigit(phone.charAt(i)))
                return false;
        }

        return true;
    }
}