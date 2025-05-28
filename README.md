# Introduction

AssignMaster is a terminal application that can be used to manage various projects and assign employees to these projects. It is a Java-based application that uses JDBC and MySQL to perform CRUD operations on the `companydb` database.

# Project Structure

- `lib`: jar file which serves as a dependency.
- `sql`: SQL scripts that initialize the database and provide dummy data.
- `src`: Java source code for the application.

# Setup Instructions

1. Clone the repository to your desired directory.

    `gh repo clone Kandarp08/AssignMaster`
2. Ensure that you have **Java** and **MySQL** installed.
3. Setup user authentication using password in **MySQL**.

# Instructions to Run

1. Open **MySQL** and run the scripts `companydb_create.sql` and `companydb_alter.sql`.

    `source companydb_create.sql` <br>
    `source companydb_alter.sql`

    This will create the necessary relations in the database.

2. If you want to add dummy data for initialization, run the `companydb_insert.sql` and `companydb_update.sql` scripts.

    `source companydb_insert.sql` <br>
    `source companydb_update.sql`

3. In `src/AssignMaster.java`, update the username and password variables as appropriate.

4. Compile the code in the root directory.

    `javac -cp lib/mysql-connector-j-8.3.0.jar:. -d path/to/output/directory/ src/AssignMaster.java` 

5. Make sure that the **MySQL** server is running.

6. Go to the output directory and run the application.

    `cd path/to/output/directory` <br>
    `java -cp ../lib/mysql-connector-j-8.3.0.jar:. AssignMaster` 