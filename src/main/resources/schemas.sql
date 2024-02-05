CREATE TABLE member (
    INDEX Long AUTO_INCREMENT,
    REG_NO VARCHAR(255) PRIMARY KEY NOT NULL,
    USER_ID VARCHAR(255) UNIQUE NOT NULL,
    PASSWORD VARCHAR(255) NOT NULL,
    NAME VARCHAR(255) NOT NULL
);

CREATE TABLE income_tax (
    income_tax_id Long AUTO_INCREMENT,
    reg_no varchar(255) UNIQUE not null,
    earned_income decimal(10, 0) not null,
    calculated_tax decimal(10, 0) not null,
    expense_id Long,
    FOREIGN KEY (expense_id) REFERENCES expense(expense_id)
);

CREATE TABLE expense (
    expense_id Long AUTO_INCREMENT PRIMARY KEY,
    insurance_premium decimal(10, 0) not null,
    medical_expenses decimal(10, 0) not null,
    education_expenses decimal(10, 0) not null,
    donation_amount decimal(10, 0) not null,
    retirement_fund decimal(10, 0) not null
);

