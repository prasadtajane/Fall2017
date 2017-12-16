
CREATE TABLE Trainly.Member (

    firstName VARCHAR(20),
    lastName VARCHAR(50),
    marital_status  VARCHAR(50),
    whether_dependent  VARCHAR(50),
    home_phone_number  VARCHAR(50),
    
    group_id  int,
    plan_id  int,
    contact_id  int,
    member_id int,
    
    PRIMARY KEY (member_id)
);


select * from Trainly.Member;

INSERT INTO `Trainly`.`Member` (`firstName`, `lastName`, `marital_status`, `whether_dependent`, `home_phone_number`, `group_id`, `plan_id`, `contact_id`) 
VALUES ('pq', 'pq', 'pq', 'pq', 'pq', '12', '12', '12');

INSERT INTO `Trainly`.`Member` (`firstName`, `lastName`, `marital_status`, `whether_dependent`, `home_phone_number`, `group_id`, `plan_id`, `contact_id`) 
VALUES ('p', 'p', 'p', 'p', 'p', '1', '1', '1');

delete from Member where member_id = 12