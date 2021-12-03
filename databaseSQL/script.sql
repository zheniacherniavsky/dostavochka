create table Account
(
    id integer identity(0,1) primary key,
    login nvarchar(40) not null,
    password nvarchar(MAX) not null
)