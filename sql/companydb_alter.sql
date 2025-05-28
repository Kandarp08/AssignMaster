alter table employee
add constraint fk_mgr_ssn foreign key (mgr_ssn) references employee (ssn);

alter table works_on
add constraint fk_emp_ssn foreign key (emp_ssn) references employee (ssn)
on delete cascade;

alter table works_on
add constraint fk_proj_id foreign key (project_id) references project (project_id)
on delete cascade;

alter table dependent
add constraint fk_dep_emp_ssn foreign key (emp_ssn) references employee (ssn)
on delete cascade;